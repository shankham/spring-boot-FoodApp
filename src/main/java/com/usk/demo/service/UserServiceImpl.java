package com.usk.demo.service;

import com.usk.demo.dto.*;
import com.usk.demo.entity.*;
import com.usk.demo.exception.BusinessLogicException;
import com.usk.demo.exception.ResourceNotFoundException;
import com.usk.demo.feignClient.FundClient;
import com.usk.demo.repository.*;
import com.usk.demo.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl<CardDto> implements UserService {


private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    //private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	AccountNoRepository accountNoRepository;
	

	@Autowired
	private MongoTemplate mongoTemplate;


	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	ProductRepository productRepository;

	@Autowired
	CartRepository cartRepository;

	@Autowired
	PurchasedHistoryRepository purchasedHistoryRepository;
	
	@Autowired
	FundClient fundClient;
	
    @Override
    public String addRegistration(UserRegistrationDTO user) {
        if (user == null || user.getEmailId() == null || user.getPassword() == null) {
            throw new BusinessLogicException("Invalid registration details.");
        }

        UserInfo userinfo = new UserInfo();
        userinfo.setEmailId(user.getEmailId());
        userinfo.setMobileNo(user.getMobileNo());
        userinfo.setUserName(user.getName());
        userinfo.setPassword(user.getPassword());
        userinfo.setRole(user.getRole());

        userrepository.insert(userinfo);
        logger.info("User saved");

        return "User Registration completed!!!";
    }

    private long generateRandomNumber() {
        long leftLimit = 1L, rightLimit = 10L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit) * 8999999999L);
    }


    @Override
    public String loginUser(String email, String password) {
        UserInfo user = userrepository.findByEmailId(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        if (password.equals(user.getPassword()) && "A".equals(user.getRole())) {
            return "Login Successful";
        } else {
            throw new BusinessLogicException("Username or password is incorrect.");
        }
    }

    @Override
    public List<ProductList> searchProduct() {
        return productRepository.findAll();
    }

    @Override
    public String addProductInProductList(AddItemDTO productList) {
        UserInfo user = userrepository.findByEmailId(productList.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        if (productList.getPassword().equals(user.getPassword()) && "A".equals(user.getRole())) {
            productRepository.insert(productList.getItem());
            return "Added successfully";
        } else {
            throw new BusinessLogicException("Not authorized to add the item.");
        }
    }

    @Override
    public String createCart(CartItemDTO addToCartDto) {
        ProductList item = productRepository.findByProductId(addToCartDto.getItemId())
                .orElseThrow(() -> new ResourceNotFoundException("Item not found."));

        int quantity = addToCartDto.getQuantity();
        double itemPrice = item.getPrice();

        Optional<Cart> cartOpt = cartRepository.findByUserId(addToCartDto.getUserId());

        
            if (cartOpt.isEmpty()) {
	            // Create new cart
	            Cart newCart = new Cart();
	            newCart.setUserId(addToCartDto.getUserId());
	            CartItem cartItem = new CartItem();
	            cartItem.setItemId(item.getProductId());
	            cartItem.setQuantity(quantity);
	            cartItem.setTotalPrice(quantity * itemPrice);
	            List<CartItem> items = new ArrayList<>();
	            items.add(cartItem);
	            newCart.setItems(items);
	            newCart.setCartTotal(cartItem.getTotalPrice());
	           
	            cartRepository.insert(newCart);
	            return "Item added to new cart";
        } else {
            Cart cart = cartOpt.get();
            List<CartItem> items = cart.getItems();
            Optional<CartItem> existingItemOpt = items.stream()
                    .filter(i -> i.getItemId().equals(item.getProductId()))
                    .findFirst();

            if (existingItemOpt.isPresent()) {
                CartItem existingItem = existingItemOpt.get();
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
                existingItem.setTotalPrice(existingItem.getQuantity() * itemPrice);
            } else {
                CartItem newItem = new CartItem();
                newItem.setItemId(item.getProductId());
                newItem.setQuantity(quantity);
                newItem.setTotalPrice(quantity * itemPrice);

                items.add(newItem);
            }

            double newTotal = items.stream().mapToDouble(CartItem::getTotalPrice).sum();
            cart.setCartTotal(newTotal);
            cartRepository.save(cart);
            return "Item updated in existing cart";
        }
    
    }
    @Override
    @org.springframework.transaction.annotation.Transactional
    public String purchaseProduct(PurchaseProductRequest purchaseRequest) {
        Cart cart = cartRepository.findByUserId(purchaseRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart is empty."));

        double amount = cart.getCartTotal();
        String status = fundClient.bankFundTransfer(amount, purchaseRequest.getFromAcc());

        if (!"Transaction Successful".equals(status)) {
            throw new BusinessLogicException(status);
        }

        for (CartItem item : cart.getItems()) {
            ProductList product = productRepository.findByProductId(item.getItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found."));

            PurchaseHistory request = new PurchaseHistory();
            request.setPurchaseDate(LocalDateTime.now());
            request.setUserId(purchaseRequest.getUserId());
            request.setFromAccount(purchaseRequest.getFromAcc());
            request.setProductId(item.getItemId());
            request.setProductPrice(item.getTotalPrice());
            request.setQuantity(item.getQuantity());
            purchasedHistoryRepository.insert(request);

            int updatedQty = Integer.parseInt(product.getQty()) - item.getQuantity();
            Query query = new Query(Criteria.where("productId").is(item.getItemId()));
            Update update = new Update().set("qty", updatedQty);
            mongoTemplate.updateFirst(query, update, ProductList.class);
        }

        return "Product purchased";
    }

@Override
public List<PurchaseHistory> getTransactionHistory(Long userId, String year) {
    try {
        return purchasedHistoryRepository.getTransactionHistory(userId, year);
    } catch (Exception e) {
        throw new BusinessLogicException("Failed to retrieve transaction history: " + e.getMessage());
    }
}

@Override
public String addProductInItemList(ProductList productList) {
    try {
        productRepository.insert(productList);

        DeliveryDto deliverydto = new DeliveryDto();
        deliverydto.setModifyDate(LocalDateTime.now());
        deliverydto.setItemId(productList.getProductId());
        deliverydto.setItemDesc(productList.getProductDesc());
        deliverydto.setItemQuantity(productList.getQty());
        deliverydto.setItemPrice(productList.getPrice());

        kafkaTemplate.send("delivery", deliverydto.toString());
        System.out.println("Message sent to the Kafka Topic sa_test Successfully" + deliverydto.toString());

        return "Added successfully";
    } catch (Exception e) {
        throw new BusinessLogicException("Failed to add product to item list: " + e.getMessage());
    }
}

@Override
public String updateItem(Long itemPrice, String itemQty, String id) {
    try {
        Query query = new Query(Criteria.where("productId").is(id));
        Update update = new Update()
            .set("qty", String.valueOf(itemQty))
            .set("price", itemPrice);

        mongoTemplate.updateFirst(query, update, ProductList.class);
        return "Update successfully";
    } catch (Exception e) {
        throw new BusinessLogicException("Failed to update item: " + e.getMessage());
    }
}

@Override
public List<PurchaseResponse> orderByUserId(String userId) {
    try {
        List<PurchaseHistory> historyList = purchasedHistoryRepository.findAllByUserId(userId);
        if (historyList.isEmpty()) {
            throw new ResourceNotFoundException("No purchase history found for user ID: " + userId);
        }

        return historyList.stream()
            .map(history -> new PurchaseResponse(
                history.getProductId(),
                history.getProductPrice(),
                history.getPurchaseDate(),
                history.getQuantity()
            ))
            .collect(Collectors.toList());
    } catch (ResourceNotFoundException e) {
        throw e;
    } catch (Exception e) {
        throw new BusinessLogicException("Failed to retrieve purchase history: " + e.getMessage());
    }
}







}
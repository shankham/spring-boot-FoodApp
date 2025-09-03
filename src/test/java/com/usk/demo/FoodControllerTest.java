package com.usk.demo;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usk.demo.controller.FoodController;
import com.usk.demo.dto.CartDto;
import com.usk.demo.dto.OrderDto;
import com.usk.demo.dto.PurchaseProductRequest;
import com.usk.demo.entity.ProductList;
import com.usk.demo.entity.PurchaseHistory;
import com.usk.demo.entity.UserAccountNumber;
import com.usk.demo.entity.Cart;
import com.usk.demo.entity.UserInfo;
import com.usk.demo.feignClient.FundClient;
import com.usk.demo.repository.AccountNoRepository;
import com.usk.demo.repository.CartRepository;
import com.usk.demo.repository.PurchasedHistoryRepository;
import com.usk.demo.service.UserService;

@WebMvcTest(FoodController.class)
public class FoodControllerTest {
	/*
	 * @Autowired private MockMvc mockMvc;
	 * 
	 * @Mock private FundClient fundClient;
	 * 
	 * @MockBean private AccountNoRepository accountNoRepository;
	 * 
	 * @MockBean CartRepository cartRepository;
	 * 
	 * @MockBean private PurchasedHistoryRepository purchasedHistoryRepository;
	 * 
	 * @MockBean private UserService userservice;
	 * 
	 * @Autowired private ObjectMapper objectMapper;
	 * 
	 * @MockBean OrderDto orderDto;
	 * 
	 * @Test void testAddRegistration() throws Exception { UserInfo user = new
	 * UserInfo(); user.setUserName("Sparsh8397"); user.setPassword("Admin@2025");
	 * 
	 * UserAccountNumber account = new UserAccountNumber();
	 * account.setAccountNumber(123456789L);
	 * 
	 * Mockito.when(userservice.addRegistration(any(UserInfo.class))).thenReturn(
	 * account);
	 * 
	 * mockMvc.perform(post("/registration").contentType(MediaType.APPLICATION_JSON)
	 * .content(objectMapper.writeValueAsString(user))).andExpect(status().isOk())
	 * .andExpect(jsonPath("$.accountNumber").value(123456789));
	 * 
	 * }
	 * 
	 * @Test void testLoginUser() throws Exception {
	 * Mockito.when(userservice.loginUser("Sparsh8397",
	 * "Admin@2025")).thenReturn("Login Successful");
	 * 
	 * mockMvc.perform(get("/loginUser").param("username",
	 * "Sparsh8397").param("password", "Admin@2025"))
	 * .andExpect(status().isOk()).andExpect(content().string("Login Successful"));
	 * 
	 * }
	 * 
	 * @Test public void testLoginUser_Failure() throws Exception {
	 * Mockito.when(userservice.loginUser("Sparsh8397",
	 * "Admin@2026")).thenReturn("Username or password is incorrect");
	 * 
	 * mockMvc.perform(get("/loginUser").param("username",
	 * "Sparsh8397").param("password", "Admin@2026"))
	 * .andExpect(status().isOk()).andExpect(content().
	 * string("Username or password is incorrect")); }
	 * 
	 * @Test void testAddProductInProductList() throws Exception { ProductList
	 * product = new ProductList(); product.setProductDesc("Phone");
	 * 
	 * ProductList product1 = new ProductList(); product1.setProductDesc("Phone");
	 * 
	 * Mockito.when(userservice.addProductInProductList(any())).
	 * thenReturn("Added successfully");
	 * 
	 * mockMvc.perform(post("/addProductInProductList").contentType(MediaType.
	 * APPLICATION_JSON)
	 * .content(objectMapper.writeValueAsString(List.of(product)))).andExpect(status
	 * ().isOk()) .andExpect(content().string("Added successfully")); }
	 * 
	 * @Test void testSearchProduct() throws Exception { ProductList product = new
	 * ProductList(); product.setProductDesc("Laptop");
	 * 
	 * Mockito.when(userservice.searchProduct()).thenReturn(List.of(product));
	 * 
	 * mockMvc.perform(get("/searchProduct")).andExpect(status().isOk())
	 * .andExpect(jsonPath("$[0].productDesc").value("Laptop"));
	 * 
	 * }
	 * 
	 * @Test void testCreateCart() throws Exception { UserCart cart = new
	 * UserCart(); cart.setUserId(1L); cart.setProductDesc("Laptop");
	 * cart.setProductId(1L); cart.setPrice(10L); cart.setQuanity(1);
	 * 
	 * Mockito.when(userservice.createCart(any())).thenReturn("Successfully Added");
	 * 
	 * mockMvc.perform(post("/createCart").contentType(MediaType.APPLICATION_JSON)
	 * .content(objectMapper.writeValueAsString(List.of(cart)))).andExpect(status().
	 * isOk()) .andExpect(jsonPath("$[0].userId").value(1L)); }
	 * 
	 * @Test void testGetUserCart() throws Exception {
	 * 
	 * CartDto cart = new CartDto(); // cart.setUserId(1L);
	 * cart.setProductDesc("Laptop"); cart.setProductId(1L); cart.setPrice(10L);
	 * cart.setQuanity(1);
	 * 
	 * CartDto cart2 = new CartDto(); // cart2.setUserId(1L);
	 * cart2.setProductDesc("SmartPhones"); cart2.setProductId(2L);
	 * cart2.setPrice(100L); cart2.setQuanity(1);
	 * 
	 * List<CartDto> UserCartList = Arrays.asList(cart, cart2);
	 * 
	 * CartDto cartDto = new CartDto();
	 * 
	 * Long userId = 1L;
	 * 
	 * Mockito.when(userservice.getUserCart(userId)).thenReturn(UserCartList);
	 * 
	 * mockMvc.perform(get("/getUserCart/1")).andExpect(status().isOk()).andExpect(
	 * jsonPath("$[0].productId").value(1))
	 * .andExpect(jsonPath("$[0].productDesc").value("Laptop"))
	 * .andExpect(jsonPath("$[1].productDesc").value("SmartPhones"));
	 * 
	 * }
	 * 
	 * 
	 * @Test void testPurchaseProduct() throws Exception { PurchaseProductRequest
	 * request = new PurchaseProductRequest(); request.setUserId(1L);
	 * 
	 * UserAccountNumber mockAccount = new UserAccountNumber();
	 * mockAccount.setAccountNumber(987654321L); mockAccount.setBalance(10000f);
	 * 
	 * OrderDto order1 = new OrderDto(101L, "Phone", 3000L,1L); OrderDto order2 =
	 * new OrderDto(102L, "Tablet", 2000L,1L);
	 * 
	 * request.setOrder(Arrays.asList(order1, order2));
	 * 
	 * Long fromAcc = 1001L; Long toAcc = 2002L; float amount = 5000f; float
	 * bankAmount = 10000f; Long userId = 1L;
	 * 
	 * String expectedResponse = "Transfer Successful";
	 * 
	 * Mockito.when(fundClient.bankFundTransfer( amount, userId))
	 * .thenReturn(expectedResponse);
	 * Mockito.when(accountNoRepository.findById(userId)).thenReturn(Optional.of(
	 * mockAccount));
	 * 
	 * Mockito.when(userservice.purchaseProduct(any())).
	 * thenReturn("Purchase Successful");
	 * 
	 * 
	 * 
	 * mockMvc.perform(post("/purchaseProduct")
	 * .contentType(MediaType.APPLICATION_JSON)
	 * .content(objectMapper.writeValueAsString(request))) .andDo(print())
	 * .andExpect(status().isOk())
	 * .andExpect(content().string("Purchase Successful")); }
	 * 
	 * @Test void testGetTransactionHistory() throws Exception {
	 * 
	 * Long userId = 1L; String year = "2025";
	 * 
	 * PurchaseHistory history1 = new PurchaseHistory(); history1.setUserId(userId);
	 * history1.setProductId(101L); history1.setProductDesc("Smartphone");
	 * history1.setProductPrice(15000f);
	 * history1.setPurchaseDate(LocalDateTime.now());
	 * history1.setFromAccount(1234567890L); history1.setTxnId(1L);
	 * 
	 * PurchaseHistory history2 = new PurchaseHistory(); history2.setUserId(userId);
	 * history2.setProductId(102L); history2.setProductDesc("Headphones");
	 * history2.setProductPrice(3000f);
	 * history2.setPurchaseDate(LocalDateTime.of(2025, 6, 20, 14, 30));
	 * history2.setFromAccount(1234567890L); history2.setTxnId(1L);
	 * 
	 * List<PurchaseHistory> mockHistoryList = Arrays.asList(history1, history2);
	 * 
	 * Mockito.when(userservice.getTransactionHistory(userId,
	 * year)).thenReturn(mockHistoryList);
	 * 
	 * // List<PurchaseHistory> result = userservice.getTransactionHistory(userId,
	 * // year);
	 * 
	 * mockMvc.perform(get("/getTransactionHistory").param("userId",
	 * String.valueOf(userId)).param("year",
	 * String.valueOf(year))).andDo(print()).andExpect(status().isOk())
	 * .andExpect(jsonPath("$[1].productPrice").value(3000f))
	 * .andExpect(jsonPath("$[0].productDesc").value("Smartphone")); }
	 * 
	 */

}

package com.usk.demo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "transactionHistory")
public class TransactionHistory {


	private Long TxnId;
	
	private Long fromAccount;
	
	private Long toAccount;
	
	private float MoneyTranfer;
	
	private String Txntype;
	
	private Long MainAccount;
	
	private LocalDateTime TransactionDate;

	public Long getTxnId() {
		return TxnId;
	}

	public void setTxnId(Long txnId) {
		TxnId = txnId;
	}

	public Long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(Long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public Long getToAccount() {
		return toAccount;
	}

	public void setToAccount(Long toAccount) {
		this.toAccount = toAccount;
	}

	public float getMoneyTranfer() {
		return MoneyTranfer;
	}

	public void setMoneyTranfer(float moneyTranfer) {
		MoneyTranfer = moneyTranfer;
	}

	public LocalDateTime getTransactionDate() {
		return TransactionDate;
	}

	public void setTransactionDate(LocalDateTime localDateTime) {
		TransactionDate = localDateTime;
	}

	public String getTxntype() {
		return Txntype;
	}

	public void setTxntype(String txntype) {
		Txntype = txntype;
	}

	public Long getMainAccount() {
		return MainAccount;
	}

	public void setMainAccount(Long mainAccount) {
		MainAccount = mainAccount;
	}
	
	
	
}

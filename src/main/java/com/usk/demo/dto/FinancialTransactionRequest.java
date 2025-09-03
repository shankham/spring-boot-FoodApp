package com.usk.demo.dto;

import java.sql.Timestamp;

public class FinancialTransactionRequest {

    private long transactionId;

    private String sourceAccountNo;

    private String targetAccountNo;

    private String sourceAccountHolderName;

    private String targetAccountHolderName;

    private String transactionType;

    private double transactionAmount;

    private Timestamp initiationDate;

    private Timestamp completionDate;

    private String reference;

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public String getSourceAccountNo() {
        return sourceAccountNo;
    }

    public void setSourceAccountNo(String sourceAccountNo) {
        this.sourceAccountNo = sourceAccountNo;
    }

    public String getTargetAccountNo() {
        return targetAccountNo;
    }

    public void setTargetAccountNo(String targetAccountNo) {
        this.targetAccountNo = targetAccountNo;
    }

    public String getSourceAccountHolderName() {
        return sourceAccountHolderName;
    }

    public void setSourceAccountHolderName(String sourceAccountHolderName) {
        this.sourceAccountHolderName = sourceAccountHolderName;
    }

    public String getTargetAccountHolderName() {
        return targetAccountHolderName;
    }

    public void setTargetAccountHolderName(String targetAccountHolderName) {
        this.targetAccountHolderName = targetAccountHolderName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Timestamp getInitiationDate() {
        return initiationDate;
    }

    public void setInitiationDate(Timestamp timestamp) {
        this.initiationDate = timestamp;
    }

    public Timestamp getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Timestamp completionDate) {
        this.completionDate = completionDate;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}

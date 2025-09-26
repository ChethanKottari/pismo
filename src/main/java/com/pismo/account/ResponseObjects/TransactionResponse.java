package com.pismo.account.ResponseObjects;

import com.pismo.account.RequestObjects.CreateTransaction;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.TreeMap;

public class TransactionResponse {


    private Long txId;
    private Long accountId;

    private String message;

    private String status;

    private String referenceId;

    public TransactionResponse(Long txId, Long accountId,BigDecimal amount,String currency,Integer operationType,
                               String referenceId, OffsetDateTime createdAt) {

        this.txId = txId;
        this.accountId = accountId;
        this.referenceId = referenceId;
        this.amount = amount;
        this.currency = currency;
        this.operationType = operationType;
        this.createdAt = createdAt;
    }

    private BigDecimal amount;

    private String currency;

    private Integer operationType;

    private OffsetDateTime createdAt;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Long getTxId() {
        return txId;
    }

    public void setTxId(Long txId) {
        this.txId = txId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }



    public TransactionResponse(Long txId, Long accountId, String referenceId,String status,String message) {
        this.txId = txId;
        this.accountId = accountId;
        this.referenceId = referenceId;
        this.status = status;
        this.message = message;
    }

    public TransactionResponse(Long txId, Long accountId, String referenceId) {
        this.txId = txId;
        this.accountId = accountId;
        this.referenceId = referenceId;
    }

    public TransactionResponse(Long accountId, String status,String message) {
        this.accountId = accountId;
        this.status = status;
        this.message = message;
    }



    public static TransactionResponse failure(String status,Long accountId,String message){
        return new TransactionResponse(accountId,status,message);
    }


    public static TransactionResponse success(Long txId, Long accountId, String referenceId,String status,String message){
        return new TransactionResponse(txId,accountId,referenceId,status,message);
    }
}

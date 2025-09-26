package com.pismo.account.ResponseObjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountInsertResponse {
    private String status;
    private String message;

    @JsonProperty("account_id")
    private Long accountId;

    @JsonProperty("document_number")
    private String documentNumber;

    private String error;

    // Success response constructor
    public AccountInsertResponse(String status, String message, Long accountId, String documentNumber) {
        this.status = status;
        this.message = message;
        this.accountId = accountId;
        this.documentNumber = documentNumber;
    }

    // Error response constructor
    public AccountInsertResponse(String status, String message, String error) {
        this.status = status;
        this.message = message;
        this.error = error;
    }

    // Static factory methods for cleaner code
    public static AccountInsertResponse success(Long accountId, String documentNumber) {
        return new AccountInsertResponse("SUCCESS", "Account created successfully", accountId, documentNumber);
    }

    public static AccountInsertResponse alreadyExists(String documentNumber) {
        return new AccountInsertResponse("SUCCESS", "Account already exists", null, documentNumber);
    }

    public static AccountInsertResponse failure(String message, String error) {
        return new AccountInsertResponse("FAILURE", message, error);
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}

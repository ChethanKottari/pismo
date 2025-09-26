package com.pismo.account.ResponseObjects;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;

public class AccountResponse {
    public AccountResponse(Long accountId, String documentNumber, OffsetDateTime createdAt) {
        this.accountId = accountId;
        this.documentNumber = documentNumber;
        this.createdAt = createdAt;
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

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    Long accountId;
    String documentNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    OffsetDateTime createdAt;


}

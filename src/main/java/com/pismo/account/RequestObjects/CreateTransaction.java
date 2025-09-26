package com.pismo.account.RequestObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateTransaction {

    @JsonProperty("acount_id")
    @NotNull(message = "accountId is required")
    @Positive(message = "accountId must be positive")
    private Long accountId;

    @JsonProperty("operation_type")
    @NotNull(message = "operationType is required")
    @Min(value = 0, message = "operationType must be >= 0")
    private Integer operationType;

    @JsonProperty("amount")
    @NotNull(message = "amount is required")
    @DecimalMin(value = "0.01", inclusive = true, message = "amount must be > 0")
    @Digits(integer = 16, fraction = 2, message = "amount must have up to 2 decimal places")
    private BigDecimal amount;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    private String referenceId;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @JsonProperty("code")
    @NotBlank(message = "currencyCode is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "currencyCode must be 3 uppercase letters")
    private String currencyCode;


}

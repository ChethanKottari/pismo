package com.pismo.account.RequestObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateAccountRequest {

    @JsonProperty("document_number")
    @NotBlank(message = "Document number is required")
    @Size(min = 11, max = 50, message = "Document number must be between 11 and 50 characters")
    @Pattern(regexp = "^[0-9]+$", message = "Document number must contain only digits")
    private String documentNumber;

    public CreateAccountRequest() {}

    public CreateAccountRequest(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
}

package com.pismo.account.Service;

import com.pismo.account.DTO.Account;
import com.pismo.account.RequestObjects.CreateAccountRequest;
import com.pismo.account.RequestObjects.CreateTransaction;
import com.pismo.account.ResponseObjects.AccountInsertResponse;
import com.pismo.account.ResponseObjects.TransactionResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface AccountsService {

    Optional<Account> getAccountById(Long id);
    AccountInsertResponse createAccount(CreateAccountRequest request);

    TransactionResponse createTransaction(CreateTransaction request);
}

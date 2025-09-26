package com.pismo.account.ServiceImpl;

import com.pismo.account.DTO.Account;

import com.pismo.account.DTO.OperationType;
import com.pismo.account.Repository.OperationTypeRepository;
import com.pismo.account.RequestObjects.CreateTransaction;
import com.pismo.account.ResponseObjects.AccountResponse;
import com.pismo.account.ResponseObjects.TransactionResponse;
import com.pismo.account.mapper.AccountMapper;
import com.pismo.account.Repository.AccountRepository;
import com.pismo.account.RequestObjects.CreateAccountRequest;
import com.pismo.account.ResponseObjects.AccountInsertResponse;
import com.pismo.account.Service.AccountsService;
import com.pismo.account.mapper.TransactionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.DocFlavor;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationTypeRepository operationTypeRepository;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private TransactionMapper transactionMapper;


    @Override
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    @Transactional
    @Override
    public AccountInsertResponse createAccount(CreateAccountRequest request){

        try{
            String documentNumber = request.getDocumentNumber();
            AccountResponse account = accountMapper.insertAccountIfNotExists(documentNumber);

            if (account != null) {

                return new AccountInsertResponse(
                        "SUCCESS",
                        "Account processed successfully",
                        account.getAccountId(),
                        account.getDocumentNumber()
                );
            } else {
                return AccountInsertResponse.failure(
                        "Failed to process account",
                        "DATABASE_ERROR"
                );
            }

        }catch (Exception w){
            return  AccountInsertResponse.failure(
                    "Internal server error",
                    "INTERNAL_ERROR"
            );

        }

    }

    @Transactional
    @Override
    public TransactionResponse createTransaction(CreateTransaction request){

        Optional<Account> account = accountRepository.findById(request.getAccountId());
        if(account.isEmpty()){
            return TransactionResponse.failure("FAILURE",request.getAccountId(),"404");
        }

        Optional<OperationType> operationType = operationTypeRepository.findById(Long.valueOf(request.getOperationType()));
        if(operationType.isEmpty()){
            return TransactionResponse.failure("FAILURE",request.getAccountId(),"403");
        }

        request.setReferenceId(UUID.randomUUID().toString());
        TransactionResponse response = transactionMapper.insertTransaction(request);
        if(response == null){
            return TransactionResponse.failure("FAILURE",request.getAccountId(),"500");
        }

        return response;
    }
}

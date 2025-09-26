package com.pismo.account.mapper;


import com.pismo.account.DTO.Account;
import com.pismo.account.RequestObjects.CreateAccountRequest;
import com.pismo.account.RequestObjects.CreateTransaction;
import com.pismo.account.ResponseObjects.AccountInsertResponse;
import com.pismo.account.ResponseObjects.AccountResponse;
import com.pismo.account.ResponseObjects.TransactionResponse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


public interface AccountMapper {

    // here we ill get account by doc num
    AccountResponse findByDocumentNumber(@Param("documentNumber") String documentNumber);
    //here we will insert if not exsist with safety or idempotency
    AccountResponse insertAccountIfNotExists(@Param("documentNumber") String documentNumber);

}

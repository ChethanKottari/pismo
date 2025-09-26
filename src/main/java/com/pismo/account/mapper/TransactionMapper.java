package com.pismo.account.mapper;


import com.pismo.account.RequestObjects.CreateTransaction;
import com.pismo.account.ResponseObjects.TransactionResponse;
import org.apache.ibatis.annotations.Mapper;


public interface TransactionMapper {

    TransactionResponse insertTransaction(CreateTransaction req);
}

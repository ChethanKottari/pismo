package com.pismo.account.Controller;


import com.pismo.account.RequestObjects.CreateAccountRequest;
import com.pismo.account.RequestObjects.CreateTransaction;
import com.pismo.account.ResponseObjects.AccountInsertResponse;
import com.pismo.account.ResponseObjects.AccountResponse;
import com.pismo.account.ResponseObjects.TransactionResponse;
import com.pismo.account.Service.AccountsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.pismo.account.Constants.AccountConstants.API_VERSION;

@RestController
@RequestMapping("api/"+API_VERSION)
public class AccountController {

    @Autowired
    private AccountsService accountsService;

    /*
    GET THE ACCCOUNT DETAILS WITH ACCOUNT ID
    WILL RETURN 404 IF NOT FOUND
    WILL RETURN 200 IF FOUND
    HOST:/API/V1/1234567
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long accountId){

        //simpple get call so im calling service directly
        return accountsService.getAccountById(accountId).
                map(a->new AccountResponse(
                        a.getAccountId(),
                        a.getDocumentNumber(),
                        a.getCreatedAt()
                )).
                map(a-> ResponseEntity.ok(a)).
                orElse(ResponseEntity.notFound().build());
    }


    //build a post api to
    @PostMapping("/accounts")
    public ResponseEntity<AccountInsertResponse> createAccountWithAccountId(@Valid @RequestBody CreateAccountRequest request){
        AccountInsertResponse response = accountsService.createAccount(request);
        HttpStatus status = "SUCCESS".equals(response.getStatus()) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
        return ResponseEntity.status(status).body(response);
    }


    @PostMapping("/transactions")
    public ResponseEntity<TransactionResponse> createTransactions(@Valid @RequestBody CreateTransaction request){
        TransactionResponse response = accountsService.createTransaction(request);
        HttpStatus status = HttpStatus.OK;
        if("SUCCESS".equals(response.getStatus())){
             status = HttpStatus.OK;
        }else if("404".equals(response.getMessage())){
            status = HttpStatus.NOT_FOUND;
        } else if ("403".equals(response.getMessage())) {
            status = HttpStatus.FORBIDDEN;
        }else if("500".equals(response.getMessage())){
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(status).body(response);
    }





}

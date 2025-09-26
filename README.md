# Account and Transaction management API service design - PISMO - assignment 

## 1. high level flow

![Pasted Graphic.png](src%2Fmain%2Fresources%2Fstatic%2FPasted%20Graphic.png)

## 2. To start application in the root directory use the docker comppose file

#### The app is in the docker-hub as a image chethan777/account-service:latest
#### docker compose up -d

1. the api will be hosted in the post 8085 (https://locahost:8085/api/V1)
2. pg admin will be available at (https://localhost:8080/) and use the email as admin@mail.com and password admin
3. db is available at the 5432 - app is connecting to this database 
4. in the root **_initdb/schema.sql_** contains the schema definition for all the tables and constraints in the DB

## 3. request and response structures 

#### 3.1 get: http://localhost:8085/api/V1/<<accountId>>

#### request : account id in path
#### response : 200

    {
    "accountId": 1,
    "documentNumber": "12345678900",
    "createdAt": "2025-09-23 05:51"
    }

#### 3.2 post:  http://localhost:8085/api/V1/accounts/

#### request : 

    {
    "document_number":"45876923323"
    }
#### response : 200

    {
    "status": "SUCCESS",
    "message": "Account processed successfully",
    "error": null,
    "account_id": 6,
    "document_number": "45876923323"
}


#### 3.3 post: http://localhost:8085/api/V1/transactions/

#### request : 
    {
    "acount_id":1,
    "operation_type":4,
    "code":"IND",
    "amount":130.34
    }
#### response : 200
    {
    "txId": 18,
    "accountId": 1,
    "message": null,
    "status": null,
    "referenceId": "6b65de88-861e-4c4b-8319-0aced7c35f50",
    "amount": 130,
    "currency": "IND",
    "operationType": 4,
    "createdAt": "2025-09-26T09:46:30.353514Z"
}




## 4. ENDPOINT DETAILS

#### 4.1 get the account with account id

#### get: http://localhost:8085/api/V1/<<accountId>>

#### 4.2 create account
#### post : http://localhost:8085/api/V1/accounts/
req body: 

    {
    "document_number":"45876923323"
    }

#### 4.3 create transaction
#### post: http://localhost:8085/api/V1/transactions/
req body:


    {
    "acount_id":1,
    "operation_type":4,
    "code":"IND",
    "amount":130.34
    }

## 5. DB tables

#### [this is also provided and the init db script] - no need to run manually
### TABLE : ACCOUNTS - CREATED

CREATE TABLE accounts (
account_id SERIAL PRIMARY KEY,        
document_number VARCHAR(50) NOT NULL UNIQUE, 
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
);

### TABLE : OPERATION_TYPE - CREATED

CREATE TABLE operation_type (
operation_type_id serial PRIMARY KEY,
operation_type VARCHAR(50) NOT NULL
);

### INSERT THE DEAULT VALUES OPERATION TPYE 
INSERT INTO operation_type (operation_type) VALUES ('CASH_PURCHASE');
INSERT INTO operation_type (operation_type) VALUES ('INSTALLMENT_PURCHASE');
INSERT INTO operation_type (operation_type) VALUES ('WITHDRAWAL');
INSERT INTO operation_type (operation_type) VALUES ('PAYMENT');

### TABLE : TRANSACTIONS - CREATED

CREATE TABLE transactions (
id BIGSERIAL PRIMARY KEY,
account_id BIGINT NOT NULL,      
amount BIGINT NOT NULL,     
currency CHAR(3) NOT NULL,         
operation_type VARCHAR(20) NOT NULL,        
reference_id VARCHAR(100),                          -- optional free-form details
created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
processed_at TIMESTAMPTZ,
CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);
### Indexes for lookups FROM TRANSACTIONS:

CREATE INDEX idx_tx_account_created_at ON transactions (account_id, created_at DESC);
CREATE INDEX idx_tx_reference_id ON transactions (reference_id);





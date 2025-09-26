# Account and Transaction management API service design - PISMO - assignment 

![Pasted Graphic.png](src%2Fmain%2Fresources%2Fstatic%2FPasted%20Graphic.png)

# USE THIS TO CREATE THE TABLES IN POSTGRES COTAINER 

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


# ENDPOINT DETAILS 
/ACCOUNTS
{
"document_number":"45876923323"
}


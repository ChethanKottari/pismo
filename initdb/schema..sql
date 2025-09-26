-- Enable pgcrypto extension for UUID support
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- ACCOUNTS
CREATE TABLE IF NOT EXISTS accounts (
    account_id SERIAL PRIMARY KEY,
    document_number VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMPTZ DEFAULT now()
);

-- OPERATION TYPES
CREATE TABLE IF NOT EXISTS operation_type (
    operation_type_id SERIAL PRIMARY KEY,
    operation_type VARCHAR(50) NOT NULL UNIQUE
);

-- Seed operation types
INSERT INTO operation_type (operation_type) VALUES
  ('CASH_PURCHASE'),
  ('INSTALLMENT_PURCHASE'),
  ('WITHDRAWAL'),
  ('PAYMENT')
ON CONFLICT (operation_type) DO NOTHING;

-- TRANSACTIONS
CREATE TABLE IF NOT EXISTS transactions (
    id BIGSERIAL PRIMARY KEY,
    account_id BIGINT NOT NULL,
    amount BIGINT NOT NULL CHECK (amount > 0),
    currency CHAR(3) NOT NULL CHECK (currency ~ '^[A-Z]{3}$'),
    operation_type_id INTEGER NOT NULL,
    reference_id UUID NOT NULL DEFAULT gen_random_uuid(),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_transactions_account
      FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE RESTRICT,
    CONSTRAINT fk_transactions_operation_type
      FOREIGN KEY (operation_type_id) REFERENCES operation_type(operation_type_id) ON DELETE RESTRICT
);

-- Indexes
CREATE UNIQUE INDEX IF NOT EXISTS idx_transactions_reference_id ON transactions(reference_id);
CREATE INDEX IF NOT EXISTS idx_transactions_account_id ON transactions(account_id);
CREATE INDEX IF NOT EXISTS idx_transactions_created_at ON transactions(created_at);

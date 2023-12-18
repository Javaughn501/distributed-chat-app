CREATE TABLE member (
    username VARCHAR(255) PRIMARY KEY,
    role VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP(6)
);
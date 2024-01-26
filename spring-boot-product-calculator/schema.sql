CREATE TABLE IF NOT EXISTS product_discounts (
     discount_id INT AUTO_INCREMENT PRIMARY KEY ,
     discount DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    cart_size INTEGER NOT NULL,
    cart_price DECIMAL(10, 2) NOT NULL,
    discount_id INT,
    FOREIGN KEY (discount_id) REFERENCES product_discounts (discount_id)
    );

INSERT INTO product_discounts (discount) VALUES (0.2);
INSERT INTO product_discounts (discount) VALUES (0.3);

INSERT INTO products (product_name, cart_size, cart_price, discount_id) VALUES ('King coconut', 5, 800, 1);
INSERT INTO products (product_name, cart_size, cart_price, discount_id) VALUES ('Apple', 20, 400, 2);
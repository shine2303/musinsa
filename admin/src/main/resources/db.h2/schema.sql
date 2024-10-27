DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS brand;
DROP TABLE IF EXISTS category;

CREATE TABLE IF NOT EXISTS category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    is_active BOOLEAN DEFAULT true,
    create_ymdt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_ymdt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS brand (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    is_active BOOLEAN DEFAULT true,
    create_ymdt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_ymdt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

CREATE TABLE product (
     id BIGINT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(100) NOT NULL,
     price BIGINT NOT NULL,
     category_id BIGINT NOT NULL,
     brand_id BIGINT NOT NULL,
     is_active BOOLEAN DEFAULT true,
     create_ymdt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
     updated_ymdt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

     CONSTRAINT fk_product_category FOREIGN KEY (category_id) REFERENCES category (id),
     CONSTRAINT fk_product_brand FOREIGN KEY (brand_id) REFERENCES brand (id),
     CONSTRAINT uk_product_name_price_category_brand UNIQUE (name, price, category_id, brand_id)
);
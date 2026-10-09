-- liquibase formatted sql

-- changeset bootcamp:1
CREATE TABLE customers (
    id VARCHAR(64) PRIMARY KEY COMMENT '客户唯一标识符',
    secured_id VARCHAR(64) NOT NULL COMMENT '客户安全唯一标识符',
    name VARCHAR(64) NOT NULL COMMENT '客户名称',
    registered_at DATETIME NOT NULL COMMENT '客户创建时间'
) COMMENT '客户信息表';

ALTER TABLE customers
    ADD UNIQUE KEY uk_customers_secured_id (secured_id) COMMENT '客户安全唯一标识符唯一索引';

CREATE TABLE products (
    id VARCHAR(64) PRIMARY KEY COMMENT '客户唯一标识符',
    name VARCHAR(128) NOT NULL COMMENT '产品名称',
    status VARCHAR(16) NOT NULL COMMENT '产品状态',
    price DECIMAL(10, 2) NOT NULL COMMENT '产品价格',
    published_at DATETIME NOT NULL COMMENT '产品创建时间'
) COMMENT '产品信息表';

CREATE TABLE orders (
    id VARCHAR(64) PRIMARY KEY COMMENT '订单唯一标识符',
    customer_id VARCHAR(64) NOT NULL COMMENT '客户唯一标识符',
    status VARCHAR(16) NOT NULL COMMENT '订单状态',
    placed_at DATETIME NOT NULL COMMENT '订单创建时间',
    paid_at DATETIME NULL COMMENT '订单支付时间',
    remark VARCHAR(128) NOT NULL COMMENT '订单备注',
    total_amount DECIMAL(10, 2) NOT NULL COMMENT '订单总金额'
) COMMENT '订单信息表';

CREATE TABLE order_lines (
    order_id VARCHAR(64) PRIMARY KEY COMMENT '订单唯一标识符',
    product_id VARCHAR(64) NOT NULL COMMENT '产品唯一标识符',
    product_name VARCHAR(128) NOT NULL COMMENT '产品名称',
    product_price DECIMAL(10, 2) NOT NULL COMMENT '产品价格',
    quantity INT NOT NULL COMMENT '数量',
    total_amount DECIMAL(10, 2) NOT NULL COMMENT '订单行总金额'
) COMMENT '订单明细表';

-- changeset bootcamp:2
CREATE TABLE inventories (
    id VARCHAR(64) PRIMARY KEY COMMENT '库存唯一标识符',
    product_id VARCHAR(64) NOT NULL COMMENT '产品唯一标识符',
    stock INT NOT NULL COMMENT '库存数量'
) COMMENT '库存信息表';

ALTER TABLE inventories
    ADD UNIQUE KEY uk_inventories_product_id (product_id) COMMENT '产品唯一标识符唯一索引';

-- changeset bootcamp:3
CREATE TABLE payments (
    id VARCHAR(64) PRIMARY KEY COMMENT '支付唯一标识符',
    order_id VARCHAR(64) NOT NULL COMMENT '订单唯一标识符',
    status VARCHAR(16) NOT NULL COMMENT '支付状态',
    amount DECIMAL(10, 2) NOT NULL COMMENT '支付金额',
    paid_at DATETIME NOT NULL COMMENT '支付时间'
) COMMENT '支付信息表';

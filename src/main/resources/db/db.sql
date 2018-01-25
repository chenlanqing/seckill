-- 秒杀用户表
DROP TABLE IF EXISTS `miaosha_user`;
create table `miaosha_user`(
  `id` BIGINT(20) NOT NULL COMMENT '',
  `nickname` VARCHAR(255) NOT NULL,
  `password` VARCHAR(32) DEFAULT NULL COMMENT '',
  `salt` VARCHAR(10) DEFAULT NULL,
  `head` VARCHAR(128) DEFAULT NULL COMMENT '',
  `register_date` DATETIME DEFAULT NULL COMMENT '',
  `last_login_date` DATETIME DEFAULT NULL COMMENT '',
  `login_count` INT(11) DEFAULT '0' COMMENT '',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户表';

-- 商品表
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT  '商品ID',
  `goods_name` VARCHAR(16) DEFAULT NULL COMMENT  '商品名称',
  `goods_title` VARCHAR(64) DEFAULT NULL COMMENT '商品标题',
  `goods_img` VARCHAR(64) DEFAULT NULL COMMENT '商品图片',
  `goods_detail` LONGTEXT COMMENT '商品详情介绍',
  `goods_price` DECIMAL(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `goods_stock` INT(11) DEFAULT '0' COMMENT '商品库存，-1表示没有限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '商品表';

-- 订单表
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info`(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) DEFAULT NULL COMMENT '用户ID',
  `goods_id` BIGINT(2) DEFAULT NULL COMMENT '商品ID',
  `delivery_addr_id` BIGINT(20) DEFAULT NULL COMMENT '收货地址ID',
  `goods_name` VARCHAR(16) DEFAULT NULL COMMENT '冗余过来的商品名称',
  `goods_count` INT(11) DEFAULT '0' COMMENT '商品数量',
  `goods_price` DECIMAL(10,2) DEFAULT '0.00' COMMENT '商品单价',
  `order_channel` TINYINT(4) DEFAULT '0' COMMENT '订单来源：1-PC端；2-Android；3-IOS',
  `status` TINYINT(4) DEFAULT '0' COMMENT '订单状态：0-新建未支付；1-已支付；2-已发货；3-已收货；4-已退款；5-已完成',
  `create_date` DATETIME DEFAULT NULL COMMENT '订单创建时间',
  `pay_date` DATETIME DEFAULT NULL COMMENT '订单支付时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '订单表';

-- 秒杀订单表
DROP TABLE IF EXISTS `miaosha_order`;
CREATE TABLE `miaosha_order`(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20) DEFAULT NULL COMMENT '用户ID',
  `order_id` BIGINT(20) DEFAULT NULL COMMENT '订单ID',
  `goods_id` BIGINT(20) DEFAULT NULL COMMENT '商品ID',
  PRIMARY KEY(`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '秒杀订单表';

-- 秒杀商品表
DROP TABLE IF EXISTS `miaosha_goods`;
CREATE TABLE `miaosha_goods`(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '秒杀商品表ID',
  `goods_id` BIGINT(20) DEFAULT NULL COMMENT '商品ID',
  `miaosha_price` DECIMAL(10,2) DEFAULT '0.00' COMMENT '秒杀价',
  `stock_count` INT(11) DEFAULT NULL COMMENT '库存数量',
  `start_date` DATETIME DEFAULT NULL COMMENT '秒杀开始时间',
  `end_date` DATETIME DEFAULT NULL COMMENT '秒杀结束时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '秒杀商品表';
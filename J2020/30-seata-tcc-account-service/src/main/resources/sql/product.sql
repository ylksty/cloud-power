/*
 Navicat Premium Data Transfer

 Source Server         : 39.99.163.122-root
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 39.99.163.122:3306
 Source Schema         : productdb

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 24/08/2020 13:12:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(10, 2) NOT NULL,
  `stock` int(0) DEFAULT NULL,
  `add_time` datetime(0) DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '电池', 10.00, 99, '2020-08-23 16:27:23', '2020-08-23 22:15:09');

SET FOREIGN_KEY_CHECKS = 1;

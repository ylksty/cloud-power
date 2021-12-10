/*
 Navicat Premium Data Transfer

 Source Server         : 39.99.163.122-root
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 39.99.163.122:3306
 Source Schema         : accountdb

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 24/08/2020 13:12:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) DEFAULT NULL,
  `balance` decimal(10, 2) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, 1, 80.00, '2020-08-23 22:15:25');

SET FOREIGN_KEY_CHECKS = 1;

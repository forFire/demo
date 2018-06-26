/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50627
Source Host           : localhost:3306
Source Database       : db_demo

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2018-06-26 11:12:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `id_card_img` varchar(255) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo
-- ----------------------------

-- ----------------------------
-- Table structure for f_customer
-- ----------------------------
DROP TABLE IF EXISTS `f_customer`;
CREATE TABLE `f_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(12) DEFAULT NULL COMMENT '手机号',
  `sex` varchar(2) DEFAULT NULL,
  `id_card` varchar(64) CHARACTER SET cp1250 DEFAULT '' COMMENT '身份证',
  `address` varchar(128) DEFAULT NULL,
  `id_card_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of f_customer
-- ----------------------------
INSERT INTO `f_customer` VALUES ('13', '张三', '15001291865', null, null, null, 'http://localhost:8080/upload/20180625/1529911595429_596.png');
INSERT INTO `f_customer` VALUES ('23', '李四', '15001291865', null, null, null, 'http://localhost:8080/upload/20180625/1529911590453_23.png');
INSERT INTO `f_customer` VALUES ('24', '123', '123123', null, null, null, 'http://localhost:8080/upload/20180625/1529911232740_10.png');

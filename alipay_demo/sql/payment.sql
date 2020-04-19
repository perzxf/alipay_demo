/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : payment

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2020-04-19 11:05:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `order_id` varchar(32) NOT NULL COMMENT '订单号',
  `subject` varchar(32) DEFAULT NULL COMMENT '订单名称',
  `body` varchar(64) DEFAULT NULL COMMENT '订单描述',
  `money` float DEFAULT NULL COMMENT '付款金额',
  `seller_id` varchar(32) DEFAULT NULL COMMENT '商户UID',
  `alipay_no` varchar(64) DEFAULT NULL COMMENT '支付宝订单号',
  `status` varchar(32) DEFAULT NULL COMMENT '订单状态（与官方统一）【WAIT_BUYER_PAY：交易创建，等待买家付款；TRADE_CLOSED：未付款交易超时关闭，或支付完成后全额退款；TRADE_SUCCESS：交易支付成功；TRADE_FINISHED：交易结束，不可退款】',
  `refund_money` float DEFAULT '0' COMMENT '总计退款金额',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='支付订单信息';

-- ----------------------------
-- Records of order_info
-- ----------------------------
INSERT INTO `order_info` VALUES ('158695459524496', '1234567', '测试', '100', '2088102177218331', '2020041522001449550500647445', 'TRADE_SUCCESS', '0', '2020-04-15 20:43:15', '2020-04-15 20:55:14');
INSERT INTO `order_info` VALUES ('158695589076034', '31232131231', '测试', '1000', '2088102177218331', '2020041522001449550500647634', 'TRADE_SUCCESS', '0', '2020-04-15 21:04:50', '2020-04-15 21:05:17');
INSERT INTO `order_info` VALUES ('158726102929016', '买手机', '购买华为P40', '4899', '2088102177218331', '2020041922001449550500649532', 'TRADE_SUCCESS', '100', '2020-04-19 09:50:29', '2020-04-19 09:53:49');
INSERT INTO `order_info` VALUES ('158726533250350', '买飞机模型', '波音747一架', '1000', '2088102177218331', '2020041922001449550500649667', 'TRADE_SUCCESS', '10', '2020-04-19 11:02:12', '2020-04-19 11:04:13');

-- ----------------------------
-- Table structure for order_refund
-- ----------------------------
DROP TABLE IF EXISTS `order_refund`;
CREATE TABLE `order_refund` (
  `refund_id` varchar(32) NOT NULL COMMENT '退款号',
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单号',
  `money` float DEFAULT NULL COMMENT '退款金额',
  `account` varchar(64) DEFAULT NULL COMMENT '退款账户',
  `reason` varchar(255) DEFAULT NULL COMMENT '退款原因',
  `refund_date` varchar(32) DEFAULT NULL COMMENT '退款时间',
  PRIMARY KEY (`refund_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单退款';

-- ----------------------------
-- Records of order_refund
-- ----------------------------
INSERT INTO `order_refund` VALUES ('158726122695315', '158726102929016', '100', 'yit***@sandbox.com', '差价', '2020-04-19 09:53:48');
INSERT INTO `order_refund` VALUES ('158726544964928', '158726533250350', '10', 'yit***@sandbox.com', '折旧费', '2020-04-19 11:04:11');

-- ----------------------------
-- Table structure for order_transfer
-- ----------------------------
DROP TABLE IF EXISTS `order_transfer`;
CREATE TABLE `order_transfer` (
  `transfer_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT '转账单号',
  `money` float DEFAULT NULL COMMENT '转账金额',
  `account` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '转账支付宝账号',
  `account_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '转账支付宝账号名称',
  `transfer_date` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '转账时间',
  `reason` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '转账原因',
  `alipay_no` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '支付宝订单号',
  PRIMARY KEY (`transfer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='转账信息';

-- ----------------------------
-- Records of order_transfer
-- ----------------------------

/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : dataway

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-05-03 17:01:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for interface_info
-- ----------------------------
DROP TABLE IF EXISTS `interface_info`;
CREATE TABLE `interface_info` (
  `api_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `api_method` varchar(12) NOT NULL COMMENT 'HttpMethod：GET、PUT、POST',
  `api_path` varchar(512) NOT NULL COMMENT '拦截路径',
  `api_status` int(2) NOT NULL COMMENT '状态：0草稿，1发布，2有变更，3禁用',
  `api_comment` varchar(255) DEFAULT NULL COMMENT '注释',
  `api_type` varchar(24) NOT NULL COMMENT '脚本类型：SQL、DataQL',
  `api_script` mediumtext NOT NULL COMMENT '查询脚本：xxxxxxx',
  `api_schema` mediumtext COMMENT '接口的请求/响应数据结构',
  `api_sample` mediumtext COMMENT '请求/响应/请求头样本数据',
  `api_create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `api_gmt_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`api_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='Dataway 中的API';

-- ----------------------------
-- Records of interface_info
-- ----------------------------
INSERT INTO `interface_info` VALUES ('1', 'GET', '/api/interface_info', '1', '', 'SQL', 'select * from interface_info\n', '{}', '{\"requestBody\":\"{\\\"message\\\":\\\"Hello DataQL.\\\"}\",\"headerData\":[]}', '2020-05-03 16:38:49', '2020-05-03 16:41:44');
INSERT INTO `interface_info` VALUES ('2', 'GET', '/api/getUsers/', '1', '', 'SQL', 'select * from t_user', '{}', '{\"requestBody\":\"{\\\"message\\\":\\\"Hello DataQL.\\\"}\",\"headerData\":[]}', '2020-05-03 16:45:08', '2020-05-03 16:46:29');

-- ----------------------------
-- Table structure for interface_release
-- ----------------------------
DROP TABLE IF EXISTS `interface_release`;
CREATE TABLE `interface_release` (
  `pub_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Publish ID',
  `pub_api_id` int(11) NOT NULL COMMENT '所属API ID',
  `pub_method` varchar(12) NOT NULL COMMENT 'HttpMethod：GET、PUT、POST',
  `pub_path` varchar(512) NOT NULL COMMENT '拦截路径',
  `pub_status` int(2) NOT NULL COMMENT '状态：0有效，1无效（可能被下线）',
  `pub_type` varchar(24) NOT NULL COMMENT '脚本类型：SQL、DataQL',
  `pub_script` mediumtext NOT NULL COMMENT '查询脚本：xxxxxxx',
  `pub_script_ori` mediumtext NOT NULL COMMENT '原始查询脚本，仅当类型为SQL时不同',
  `pub_schema` mediumtext COMMENT '接口的请求/响应数据结构',
  `pub_sample` mediumtext COMMENT '请求/响应/请求头样本数据',
  `pub_release_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间（下线不更新）',
  PRIMARY KEY (`pub_id`),
  KEY `idx_interface_release` (`pub_api_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='Dataway API 发布历史。';

-- ----------------------------
-- Records of interface_release
-- ----------------------------
INSERT INTO `interface_release` VALUES ('1', '1', 'GET', '/api/interface_info', '0', 'SQL', 'var tempCall = @@sql(`message`)<%select * from interface_info\n%>;\nreturn tempCall(${message});', 'select * from interface_info\n', '{}', '{\"requestBody\":\"{\\\"message\\\":\\\"Hello DataQL.\\\"}\",\"headerData\":[]}', '2020-05-03 16:41:44');
INSERT INTO `interface_release` VALUES ('2', '2', 'GET', '/api/getUsers/', '0', 'SQL', 'var tempCall = @@sql(`message`)<%select * from t_user%>;\nreturn tempCall(${message});', 'select * from t_user', '{}', '{\"requestBody\":\"{\\\"message\\\":\\\"Hello DataQL.\\\"}\",\"headerData\":[]}', '2020-05-03 16:46:29');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `telephone` varchar(16) DEFAULT NULL COMMENT '住宅电话',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `userface` varchar(255) DEFAULT NULL COMMENT '头像',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '账号是否可用  1：账号可用  0 ：账号不可用',
  `locked` tinyint(1) DEFAULT NULL COMMENT '账号是否被锁  1：账号锁定  0 ：账户未锁定',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('9', '言少钱', '子婴', 'jeams', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', '13812341234', '083-5440781', '四川省成都市', 'https://n.sinaimg.cn/sinacn10112/566/w1018h1148/20191111/fd6e-iieqapt6530904.jpg', '1', '0', null);
INSERT INTO `t_user` VALUES ('10', '恣欢谑', '子豫', 'yiwannuofu', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', '13812341234', '083-5440781', '四川省成都市', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRYXMyc1K89VQRVbxJR_XMv0EAVDghfqh3msebG-pYHEGcICsIaCA&s', '1', '0', null);
INSERT INTO `t_user` VALUES ('12', '黄鹤', '黄鹤一去不复返', 'huanghe', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', '13812341236', '083-5440783', '四川省成都市', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQVXnk8V2X5VNAKsyOWv_TQbb-hks-Pr1LVWSO5iouEVR3GqoOR&s', '1', '0', '');
INSERT INTO `t_user` VALUES ('13', '涧边', '林边相涧小溪沟', 'jianbian', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', '13812341237', '083-5440784', '四川省成都市', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQYSh9xT5-9PeU0POInyHsorHSWSrc8zN4k935SNhcRFChD8aIXKA&s', '1', '0', '');
INSERT INTO `t_user` VALUES ('14', '程曦', '程曦恰比薄雾色', 'chengxi', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', '13812341238', '083-5440785', '四川省成都市', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRuPQgnd8cOW7wiVXEZjFBIIMSyXBxyIbm-8Hd28x0bEl_pw-9h&s', '1', '0', '');
INSERT INTO `t_user` VALUES ('15', '明月', '强弱人心在高楼', 'mingyue', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', '13812341239', '083-5440786', '四川省成都市', 'https://lh3.googleusercontent.com/proxy/T_ELVO3MeV0ok5DSE722Cdj6wIcL1_GpSOUcWonDwGQw4P0A2bPSBVIcud1E3qNIjMVcNriKVXRgA8uDDIvHV1Ee_DZVyBkao9TvAI0jqPRf', '1', '0', '');

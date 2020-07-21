/*
Navicat MySQL Data Transfer

Source Server         : xiaoming
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : videoweb

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2020-07-21 15:39:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_barrage
-- ----------------------------
DROP TABLE IF EXISTS `tb_barrage`;
CREATE TABLE `tb_barrage` (
  `barrage_id` int(11) NOT NULL AUTO_INCREMENT,
  `context` varchar(255) DEFAULT NULL,
  `release_date` datetime DEFAULT NULL,
  `pp_num` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `video_id` int(11) NOT NULL,
  PRIMARY KEY (`barrage_id`),
  KEY `user_id` (`user_id`),
  KEY `video_id` (`video_id`),
  CONSTRAINT `tb_barrage_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_barrage_ibfk_2` FOREIGN KEY (`video_id`) REFERENCES `tb_video` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_collection
-- ----------------------------
DROP TABLE IF EXISTS `tb_collection`;
CREATE TABLE `tb_collection` (
  `collection_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `video_id` int(11) NOT NULL,
  PRIMARY KEY (`collection_id`),
  KEY `user_id` (`user_id`),
  KEY `video_id` (`video_id`),
  CONSTRAINT `tb_collection_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_collection_ibfk_2` FOREIGN KEY (`video_id`) REFERENCES `tb_video` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_commentedstar
-- ----------------------------
DROP TABLE IF EXISTS `tb_commentedstar`;
CREATE TABLE `tb_commentedstar` (
  `commentedstar_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `video_id` int(11) NOT NULL,
  `star_num` int(11) NOT NULL,
  `comment_date` datetime DEFAULT NULL,
  PRIMARY KEY (`commentedstar_id`),
  KEY `tb_commentedstar_ibfk_1` (`user_id`),
  KEY `tb_commentedstar_ibfk_2` (`video_id`),
  CONSTRAINT `tb_commentedstar_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_commentedstar_ibfk_2` FOREIGN KEY (`video_id`) REFERENCES `tb_video` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_focus
-- ----------------------------
DROP TABLE IF EXISTS `tb_focus`;
CREATE TABLE `tb_focus` (
  `focus_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `focused_id` int(11) NOT NULL,
  PRIMARY KEY (`focus_id`),
  KEY `user_id` (`user_id`),
  KEY `focused_id` (`focused_id`),
  CONSTRAINT `tb_focus_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_focus_ibfk_2` FOREIGN KEY (`focused_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_message
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `msg_id` int(11) NOT NULL AUTO_INCREMENT,
  `msg_title` varchar(255) DEFAULT NULL,
  `msg_context` varchar(255) DEFAULT NULL,
  `msg_send_date` datetime DEFAULT NULL,
  `msg_send_user_id` int(11) DEFAULT NULL,
  `msg_receive_user_id` int(11) NOT NULL,
  `msg_state_id` int(11) NOT NULL,
  `msgtype_id` int(11) NOT NULL,
  PRIMARY KEY (`msg_id`),
  KEY `msg_send_user_id` (`msg_send_user_id`),
  KEY `msg_receive_user_id` (`msg_receive_user_id`),
  KEY `msg_state_id` (`msg_state_id`),
  KEY `msgtype_id` (`msgtype_id`),
  CONSTRAINT `tb_message_ibfk_1` FOREIGN KEY (`msg_send_user_id`) REFERENCES `tb_user` (`user_id`),
  CONSTRAINT `tb_message_ibfk_2` FOREIGN KEY (`msg_receive_user_id`) REFERENCES `tb_user` (`user_id`),
  CONSTRAINT `tb_message_ibfk_3` FOREIGN KEY (`msg_state_id`) REFERENCES `tb_state` (`state_id`),
  CONSTRAINT `tb_message_ibfk_4` FOREIGN KEY (`msgtype_id`) REFERENCES `tb_msgtype` (`msgtype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_msgtype
-- ----------------------------
DROP TABLE IF EXISTS `tb_msgtype`;
CREATE TABLE `tb_msgtype` (
  `msgtype_id` int(11) NOT NULL AUTO_INCREMENT,
  `msgtype_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`msgtype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_predict
-- ----------------------------
DROP TABLE IF EXISTS `tb_predict`;
CREATE TABLE `tb_predict` (
  `pre_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `video_id` int(11) DEFAULT NULL,
  `pre_star` float(255,2) DEFAULT NULL,
  PRIMARY KEY (`pre_id`),
  KEY `user_id` (`user_id`),
  KEY `video_id` (`video_id`),
  CONSTRAINT `tb_predict_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_predict_ibfk_2` FOREIGN KEY (`video_id`) REFERENCES `tb_video` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_record`;
CREATE TABLE `tb_record` (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `video_id` int(11) NOT NULL,
  PRIMARY KEY (`record_id`),
  KEY `user_id` (`user_id`),
  KEY `video_id` (`video_id`),
  CONSTRAINT `tb_record_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_record_ibfk_2` FOREIGN KEY (`video_id`) REFERENCES `tb_video` (`video_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_role` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_state
-- ----------------------------
DROP TABLE IF EXISTS `tb_state`;
CREATE TABLE `tb_state` (
  `state_id` int(11) NOT NULL AUTO_INCREMENT,
  `state_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_age` int(11) DEFAULT NULL,
  `user_sex` varchar(255) DEFAULT NULL,
  `user_mail` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `user_address` varchar(255) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `register_date` datetime DEFAULT NULL,
  `fan_num` int(11) DEFAULT NULL,
  `icon_url` varchar(255) DEFAULT NULL,
  `state_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`user_name`),
  KEY `role_id` (`role_id`),
  KEY `state_id` (`state_id`),
  CONSTRAINT `tb_user_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`) ON UPDATE CASCADE,
  CONSTRAINT `tb_user_ibfk_2` FOREIGN KEY (`state_id`) REFERENCES `tb_state` (`state_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_video
-- ----------------------------
DROP TABLE IF EXISTS `tb_video`;
CREATE TABLE `tb_video` (
  `video_id` int(11) NOT NULL AUTO_INCREMENT,
  `video_title` varchar(255) DEFAULT NULL,
  `video_info` varchar(255) DEFAULT NULL,
  `edit_date` datetime DEFAULT NULL,
  `video_url` varchar(255) DEFAULT NULL,
  `thumbnail_url` varchar(255) DEFAULT NULL,
  `video_state_id` int(11) DEFAULT NULL,
  `view_num` int(11) DEFAULT NULL,
  `pp_num` int(11) DEFAULT NULL,
  `videotype_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`video_id`),
  KEY `video_state_id` (`video_state_id`),
  KEY `videotype_id` (`videotype_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_video_ibfk_1` FOREIGN KEY (`video_state_id`) REFERENCES `tb_state` (`state_id`) ON UPDATE CASCADE,
  CONSTRAINT `tb_video_ibfk_2` FOREIGN KEY (`videotype_id`) REFERENCES `tb_videotype` (`videotype_id`) ON UPDATE CASCADE,
  CONSTRAINT `tb_video_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11111168 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_videotype
-- ----------------------------
DROP TABLE IF EXISTS `tb_videotype`;
CREATE TABLE `tb_videotype` (
  `videotype_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`videotype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

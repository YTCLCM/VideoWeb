/*
Navicat MySQL Data Transfer

Source Server         : xiaoming
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : videoweb

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2021-03-19 19:09:06
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
-- Records of tb_barrage
-- ----------------------------

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
-- Records of tb_collection
-- ----------------------------
INSERT INTO `tb_collection` VALUES ('8', '1', '11111126');
INSERT INTO `tb_collection` VALUES ('11', '15', '11111113');
INSERT INTO `tb_collection` VALUES ('12', '15', '11111115');
INSERT INTO `tb_collection` VALUES ('13', '20', '11111124');
INSERT INTO `tb_collection` VALUES ('15', '15', '11111114');
INSERT INTO `tb_collection` VALUES ('16', '15', '11111145');
INSERT INTO `tb_collection` VALUES ('17', '15', '11111117');

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
-- Records of tb_commentedstar
-- ----------------------------
INSERT INTO `tb_commentedstar` VALUES ('12', '15', '11111113', '3', '2020-03-12 21:55:16');
INSERT INTO `tb_commentedstar` VALUES ('13', '1', '11111126', '3', '2020-03-21 16:08:43');
INSERT INTO `tb_commentedstar` VALUES ('14', '15', '11111126', '5', '2020-05-06 15:39:06');
INSERT INTO `tb_commentedstar` VALUES ('15', '1', '11111113', '4', '2020-05-07 15:45:11');
INSERT INTO `tb_commentedstar` VALUES ('16', '15', '11111145', '3', '2020-05-27 22:20:32');
INSERT INTO `tb_commentedstar` VALUES ('17', '15', '11111116', '5', '2020-05-27 22:20:55');
INSERT INTO `tb_commentedstar` VALUES ('18', '15', '11111117', '4', '2020-05-28 09:05:24');
INSERT INTO `tb_commentedstar` VALUES ('19', '15', '11111167', '4', '2020-05-28 09:43:35');
INSERT INTO `tb_commentedstar` VALUES ('22', '34', '11111130', '3', '2020-05-29 20:05:41');

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
-- Records of tb_focus
-- ----------------------------
INSERT INTO `tb_focus` VALUES ('12', '15', '1');
INSERT INTO `tb_focus` VALUES ('13', '1', '15');
INSERT INTO `tb_focus` VALUES ('14', '20', '15');
INSERT INTO `tb_focus` VALUES ('15', '15', '20');
INSERT INTO `tb_focus` VALUES ('16', '34', '20');

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
-- Records of tb_message
-- ----------------------------
INSERT INTO `tb_message` VALUES ('17', '收藏提醒', '你好！你的主题为《Lcm1997》的视频,被用户【LCM】的收藏', '2020-03-10 12:34:00', null, '1', '7', '5');
INSERT INTO `tb_message` VALUES ('19', '点赞提醒', '你好！你的主题为《一个好心情12》的视频,获得用户【lcm1997】的一枚点赞', '2020-03-12 21:55:11', null, '1', '7', '3');
INSERT INTO `tb_message` VALUES ('26', '收藏提醒', '你好！你的主题为《Lcm1997》的视频,被用户【System】的收藏', '2020-03-21 16:45:14', null, '15', '7', '5');
INSERT INTO `tb_message` VALUES ('27', '点赞提醒', '你好！你的主题为《一个好心情12》的视频,获得用户【lcm】的一枚点赞', '2020-03-30 17:18:33', null, '1', '6', '3');
INSERT INTO `tb_message` VALUES ('30', '你好', '123', '2020-04-06 11:00:35', '15', '1', '6', '1');
INSERT INTO `tb_message` VALUES ('31', '收藏提醒', '你好！你的主题为《一个好心情12》的视频,被用户【lcm】的收藏', '2020-04-14 15:30:27', null, '1', '6', '5');
INSERT INTO `tb_message` VALUES ('32', '点赞提醒', '你好！你的主题为《一个好心情12》的视频,获得用户【lcm】的一枚点赞', '2020-05-14 11:11:59', null, '1', '6', '3');
INSERT INTO `tb_message` VALUES ('33', '点赞提醒', '你好！你的主题为《一个好心情》的视频,获得用户【lcm】的一枚点赞', '2020-05-22 19:09:43', null, '1', '6', '3');
INSERT INTO `tb_message` VALUES ('34', '收藏提醒', '你好！你的主题为《一个好心情》的视频,被用户【lcm】的收藏', '2020-05-22 19:09:44', null, '1', '6', '5');
INSERT INTO `tb_message` VALUES ('35', '点赞提醒', '你好！你的主题为《一个好心情》的视频,获得用户【lcm】的一枚点赞', '2020-05-22 19:20:45', null, '1', '6', '3');
INSERT INTO `tb_message` VALUES ('41', 'yy', '我围观了你的视频，为你点赞', '2020-05-22 19:36:33', '20', '15', '7', '1');
INSERT INTO `tb_message` VALUES ('42', 'yy', '我围观了你的视频，为你点赞', '2020-05-22 19:37:00', '20', '15', '6', '1');
INSERT INTO `tb_message` VALUES ('43', '点赞提醒', '你好！你的主题为《Lcm1997》的视频,获得用户【123456】的一枚点赞', '2020-05-22 19:37:13', '20', '15', '6', '3');
INSERT INTO `tb_message` VALUES ('44', '收藏提醒', '你好！你的主题为《Lcm1997》的视频,被用户【123456】的收藏', '2020-05-22 19:37:20', '20', '15', '6', '5');
INSERT INTO `tb_message` VALUES ('54', '系统通知', '你的视频《一周观天下：科学家被困北极与熊为伴》已经通过审核', '2020-05-22 22:29:04', null, '20', '6', '2');
INSERT INTO `tb_message` VALUES ('55', '系统通知', '你的视频《天津大爷: 钱算嘛? 有钱就得吃腰子》已经通过审核', '2020-05-22 22:33:00', null, '20', '6', '2');
INSERT INTO `tb_message` VALUES ('56', '系统通知', '你的视频《属于理工男的浪漫，和你一起鉴赏一次火箭发射》已经通过审核', '2020-05-22 22:37:50', null, '20', '6', '2');
INSERT INTO `tb_message` VALUES ('57', '系统通知', '你的视频《成龙历险记》已经通过审核', '2020-05-22 22:47:03', null, '20', '6', '2');
INSERT INTO `tb_message` VALUES ('58', '系统通知', '你的视频《成龙历险记》已经通过审核', '2020-05-22 22:47:40', null, '20', '6', '2');
INSERT INTO `tb_message` VALUES ('59', '系统通知', '你的视频《实时新闻》等待进一步审核，', '2020-05-22 22:48:30', null, '15', '6', '2');
INSERT INTO `tb_message` VALUES ('60', '系统通知', '你的视频《Lcm1997》等待进一步审核，', '2020-05-22 22:48:56', null, '15', '6', '2');
INSERT INTO `tb_message` VALUES ('63', '系统通知', '你的视频《Lcm1997》已经通过审核', '2020-05-22 22:51:00', null, '15', '6', '2');
INSERT INTO `tb_message` VALUES ('65', '系统通知', '你的视频《Lcm1997》已经通过审核', '2020-05-22 22:51:12', null, '15', '6', '2');
INSERT INTO `tb_message` VALUES ('67', '系统通知', '你的视频《30》未通过审核,审核失败。请重新对内容进行调整', '2020-05-24 08:14:53', null, '15', '6', '2');
INSERT INTO `tb_message` VALUES ('72', '系统通知', '你的视频《Lcm1997》已经通过审核', '2020-05-24 08:19:42', null, '15', '7', '2');
INSERT INTO `tb_message` VALUES ('75', '收藏提醒', '你好！你的主题为《一个好心情》的视频,被用户【lcm】的收藏', '2020-05-27 22:14:26', null, '1', '6', '5');
INSERT INTO `tb_message` VALUES ('78', '收藏提醒', '你好！你的主题为《睡前消息》的视频,被用户【lcm】的收藏', '2020-05-27 22:22:21', null, '20', '6', '5');
INSERT INTO `tb_message` VALUES ('79', '点评提醒', '你好！你的主题为《一个好心情》的视频获得用户【lcm】的4星点评', '2020-05-28 09:05:24', null, '1', '6', '4');
INSERT INTO `tb_message` VALUES ('80', '收藏提醒', '你好！你的主题为《一个好心情》的视频,被用户【lcm】的收藏', '2020-05-28 09:05:34', null, '1', '6', '5');
INSERT INTO `tb_message` VALUES ('81', '点评提醒', '你好！你的主题为《短视频网站》的视频获得用户【lcm】的4星点评', '2020-05-28 09:43:35', null, '15', '6', '4');
INSERT INTO `tb_message` VALUES ('84', '点评提醒', '你好！你的主题为《30》的视频获得用户【liucm】的4星点评', '2020-05-28 09:59:23', null, '15', '6', '4');

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
-- Records of tb_msgtype
-- ----------------------------
INSERT INTO `tb_msgtype` VALUES ('1', 'PrivateMsg');
INSERT INTO `tb_msgtype` VALUES ('2', 'SystemMsg');
INSERT INTO `tb_msgtype` VALUES ('3', 'SuportNotice');
INSERT INTO `tb_msgtype` VALUES ('4', 'EvaluateNotice');
INSERT INTO `tb_msgtype` VALUES ('5', 'CollectionNotice');

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
) ENGINE=InnoDB AUTO_INCREMENT=153 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_predict
-- ----------------------------
INSERT INTO `tb_predict` VALUES ('1', '1', '11111112', '2.15');
INSERT INTO `tb_predict` VALUES ('2', '1', '11111113', '4.00');
INSERT INTO `tb_predict` VALUES ('3', '1', '11111114', '3.19');
INSERT INTO `tb_predict` VALUES ('4', '1', '11111115', '2.34');
INSERT INTO `tb_predict` VALUES ('5', '1', '11111116', '3.13');
INSERT INTO `tb_predict` VALUES ('6', '1', '11111117', '3.34');
INSERT INTO `tb_predict` VALUES ('7', '1', '11111118', '2.83');
INSERT INTO `tb_predict` VALUES ('8', '1', '11111119', '3.20');
INSERT INTO `tb_predict` VALUES ('9', '1', '11111120', '2.72');
INSERT INTO `tb_predict` VALUES ('10', '1', '11111121', '1.25');
INSERT INTO `tb_predict` VALUES ('11', '1', '11111122', '2.66');
INSERT INTO `tb_predict` VALUES ('12', '1', '11111123', '1.84');
INSERT INTO `tb_predict` VALUES ('13', '1', '11111124', '2.23');
INSERT INTO `tb_predict` VALUES ('14', '1', '11111125', '2.66');
INSERT INTO `tb_predict` VALUES ('15', '1', '11111126', '3.00');
INSERT INTO `tb_predict` VALUES ('16', '1', '11111127', '2.91');
INSERT INTO `tb_predict` VALUES ('17', '1', '11111128', '2.36');
INSERT INTO `tb_predict` VALUES ('18', '1', '11111129', '2.58');
INSERT INTO `tb_predict` VALUES ('19', '1', '11111130', '3.36');
INSERT INTO `tb_predict` VALUES ('20', '1', '11111132', '3.15');
INSERT INTO `tb_predict` VALUES ('21', '1', '11111133', '2.79');
INSERT INTO `tb_predict` VALUES ('22', '1', '11111135', '3.34');
INSERT INTO `tb_predict` VALUES ('23', '1', '11111145', '1.87');
INSERT INTO `tb_predict` VALUES ('24', '1', '11111146', '2.55');
INSERT INTO `tb_predict` VALUES ('25', '1', '11111147', '2.65');
INSERT INTO `tb_predict` VALUES ('26', '1', '11111148', '3.11');
INSERT INTO `tb_predict` VALUES ('27', '1', '11111149', '2.17');
INSERT INTO `tb_predict` VALUES ('28', '1', '11111158', '2.63');
INSERT INTO `tb_predict` VALUES ('29', '1', '11111159', '2.70');
INSERT INTO `tb_predict` VALUES ('30', '1', '11111160', '2.42');
INSERT INTO `tb_predict` VALUES ('31', '1', '11111161', '2.61');
INSERT INTO `tb_predict` VALUES ('32', '1', '11111162', '1.95');
INSERT INTO `tb_predict` VALUES ('33', '1', '11111163', '1.48');
INSERT INTO `tb_predict` VALUES ('34', '1', '11111164', '2.31');
INSERT INTO `tb_predict` VALUES ('35', '1', '11111165', '2.56');
INSERT INTO `tb_predict` VALUES ('36', '1', '11111166', '2.78');
INSERT INTO `tb_predict` VALUES ('37', '1', '11111167', '3.29');
INSERT INTO `tb_predict` VALUES ('38', '1', '11111168', '3.50');
INSERT INTO `tb_predict` VALUES ('39', '15', '11111112', '2.24');
INSERT INTO `tb_predict` VALUES ('40', '15', '11111113', '3.00');
INSERT INTO `tb_predict` VALUES ('41', '15', '11111114', '3.60');
INSERT INTO `tb_predict` VALUES ('42', '15', '11111115', '2.40');
INSERT INTO `tb_predict` VALUES ('43', '15', '11111116', '5.00');
INSERT INTO `tb_predict` VALUES ('44', '15', '11111117', '4.00');
INSERT INTO `tb_predict` VALUES ('45', '15', '11111118', '3.69');
INSERT INTO `tb_predict` VALUES ('46', '15', '11111119', '4.04');
INSERT INTO `tb_predict` VALUES ('47', '15', '11111120', '2.66');
INSERT INTO `tb_predict` VALUES ('48', '15', '11111121', '2.08');
INSERT INTO `tb_predict` VALUES ('49', '15', '11111122', '2.92');
INSERT INTO `tb_predict` VALUES ('50', '15', '11111123', '2.31');
INSERT INTO `tb_predict` VALUES ('51', '15', '11111124', '2.75');
INSERT INTO `tb_predict` VALUES ('52', '15', '11111125', '3.35');
INSERT INTO `tb_predict` VALUES ('53', '15', '11111126', '5.00');
INSERT INTO `tb_predict` VALUES ('54', '15', '11111127', '2.47');
INSERT INTO `tb_predict` VALUES ('55', '15', '11111128', '2.62');
INSERT INTO `tb_predict` VALUES ('56', '15', '11111129', '2.15');
INSERT INTO `tb_predict` VALUES ('57', '15', '11111130', '3.78');
INSERT INTO `tb_predict` VALUES ('58', '15', '11111132', '4.00');
INSERT INTO `tb_predict` VALUES ('59', '15', '11111133', '2.76');
INSERT INTO `tb_predict` VALUES ('60', '15', '11111135', '3.54');
INSERT INTO `tb_predict` VALUES ('61', '15', '11111145', '3.00');
INSERT INTO `tb_predict` VALUES ('62', '15', '11111146', '3.87');
INSERT INTO `tb_predict` VALUES ('63', '15', '11111147', '3.69');
INSERT INTO `tb_predict` VALUES ('64', '15', '11111148', '4.23');
INSERT INTO `tb_predict` VALUES ('65', '15', '11111149', '2.11');
INSERT INTO `tb_predict` VALUES ('66', '15', '11111158', '3.22');
INSERT INTO `tb_predict` VALUES ('67', '15', '11111159', '3.57');
INSERT INTO `tb_predict` VALUES ('68', '15', '11111160', '3.15');
INSERT INTO `tb_predict` VALUES ('69', '15', '11111161', '3.49');
INSERT INTO `tb_predict` VALUES ('70', '15', '11111162', '3.42');
INSERT INTO `tb_predict` VALUES ('71', '15', '11111163', '1.50');
INSERT INTO `tb_predict` VALUES ('72', '15', '11111164', '3.06');
INSERT INTO `tb_predict` VALUES ('73', '15', '11111165', '3.59');
INSERT INTO `tb_predict` VALUES ('74', '15', '11111166', '2.92');
INSERT INTO `tb_predict` VALUES ('75', '15', '11111167', '4.00');
INSERT INTO `tb_predict` VALUES ('76', '15', '11111168', '3.25');
INSERT INTO `tb_predict` VALUES ('77', '20', '11111112', '1.11');
INSERT INTO `tb_predict` VALUES ('78', '20', '11111113', '1.80');
INSERT INTO `tb_predict` VALUES ('79', '20', '11111114', '1.73');
INSERT INTO `tb_predict` VALUES ('80', '20', '11111115', '1.42');
INSERT INTO `tb_predict` VALUES ('81', '20', '11111116', '2.18');
INSERT INTO `tb_predict` VALUES ('82', '20', '11111117', '2.39');
INSERT INTO `tb_predict` VALUES ('83', '20', '11111118', '2.22');
INSERT INTO `tb_predict` VALUES ('84', '20', '11111119', '2.25');
INSERT INTO `tb_predict` VALUES ('85', '20', '11111120', '1.43');
INSERT INTO `tb_predict` VALUES ('86', '20', '11111121', '0.85');
INSERT INTO `tb_predict` VALUES ('87', '20', '11111122', '1.51');
INSERT INTO `tb_predict` VALUES ('88', '20', '11111123', '1.50');
INSERT INTO `tb_predict` VALUES ('89', '20', '11111124', '1.68');
INSERT INTO `tb_predict` VALUES ('90', '20', '11111125', '2.14');
INSERT INTO `tb_predict` VALUES ('91', '20', '11111126', '2.14');
INSERT INTO `tb_predict` VALUES ('92', '20', '11111127', '1.48');
INSERT INTO `tb_predict` VALUES ('93', '20', '11111128', '1.57');
INSERT INTO `tb_predict` VALUES ('94', '20', '11111129', '1.37');
INSERT INTO `tb_predict` VALUES ('95', '20', '11111130', '2.23');
INSERT INTO `tb_predict` VALUES ('96', '20', '11111132', '2.23');
INSERT INTO `tb_predict` VALUES ('97', '20', '11111133', '1.76');
INSERT INTO `tb_predict` VALUES ('98', '20', '11111135', '2.01');
INSERT INTO `tb_predict` VALUES ('99', '20', '11111145', '1.31');
INSERT INTO `tb_predict` VALUES ('100', '20', '11111146', '1.97');
INSERT INTO `tb_predict` VALUES ('101', '20', '11111147', '1.95');
INSERT INTO `tb_predict` VALUES ('102', '20', '11111148', '2.32');
INSERT INTO `tb_predict` VALUES ('103', '20', '11111149', '1.30');
INSERT INTO `tb_predict` VALUES ('104', '20', '11111158', '1.77');
INSERT INTO `tb_predict` VALUES ('105', '20', '11111159', '1.69');
INSERT INTO `tb_predict` VALUES ('106', '20', '11111160', '1.70');
INSERT INTO `tb_predict` VALUES ('107', '20', '11111161', '1.82');
INSERT INTO `tb_predict` VALUES ('108', '20', '11111162', '1.58');
INSERT INTO `tb_predict` VALUES ('109', '20', '11111163', '0.71');
INSERT INTO `tb_predict` VALUES ('110', '20', '11111164', '1.13');
INSERT INTO `tb_predict` VALUES ('111', '20', '11111165', '1.92');
INSERT INTO `tb_predict` VALUES ('112', '20', '11111166', '1.34');
INSERT INTO `tb_predict` VALUES ('113', '20', '11111167', '1.88');
INSERT INTO `tb_predict` VALUES ('114', '20', '11111168', '1.66');
INSERT INTO `tb_predict` VALUES ('115', '34', '11111112', '1.83');
INSERT INTO `tb_predict` VALUES ('116', '34', '11111113', '2.71');
INSERT INTO `tb_predict` VALUES ('117', '34', '11111114', '2.76');
INSERT INTO `tb_predict` VALUES ('118', '34', '11111115', '1.95');
INSERT INTO `tb_predict` VALUES ('119', '34', '11111116', '3.48');
INSERT INTO `tb_predict` VALUES ('120', '34', '11111117', '3.09');
INSERT INTO `tb_predict` VALUES ('121', '34', '11111118', '2.88');
INSERT INTO `tb_predict` VALUES ('122', '34', '11111119', '3.17');
INSERT INTO `tb_predict` VALUES ('123', '34', '11111120', '2.27');
INSERT INTO `tb_predict` VALUES ('124', '34', '11111121', '1.35');
INSERT INTO `tb_predict` VALUES ('125', '34', '11111122', '2.19');
INSERT INTO `tb_predict` VALUES ('126', '34', '11111123', '1.76');
INSERT INTO `tb_predict` VALUES ('127', '34', '11111124', '2.01');
INSERT INTO `tb_predict` VALUES ('128', '34', '11111125', '2.40');
INSERT INTO `tb_predict` VALUES ('129', '34', '11111126', '3.55');
INSERT INTO `tb_predict` VALUES ('130', '34', '11111127', '2.04');
INSERT INTO `tb_predict` VALUES ('131', '34', '11111128', '2.07');
INSERT INTO `tb_predict` VALUES ('132', '34', '11111129', '1.80');
INSERT INTO `tb_predict` VALUES ('133', '34', '11111130', '3.00');
INSERT INTO `tb_predict` VALUES ('134', '34', '11111132', '3.05');
INSERT INTO `tb_predict` VALUES ('135', '34', '11111133', '2.37');
INSERT INTO `tb_predict` VALUES ('136', '34', '11111135', '2.79');
INSERT INTO `tb_predict` VALUES ('137', '34', '11111145', '2.24');
INSERT INTO `tb_predict` VALUES ('138', '34', '11111146', '2.60');
INSERT INTO `tb_predict` VALUES ('139', '34', '11111147', '2.69');
INSERT INTO `tb_predict` VALUES ('140', '34', '11111148', '3.17');
INSERT INTO `tb_predict` VALUES ('141', '34', '11111149', '1.66');
INSERT INTO `tb_predict` VALUES ('142', '34', '11111158', '2.67');
INSERT INTO `tb_predict` VALUES ('143', '34', '11111159', '2.93');
INSERT INTO `tb_predict` VALUES ('144', '34', '11111160', '2.05');
INSERT INTO `tb_predict` VALUES ('145', '34', '11111161', '2.33');
INSERT INTO `tb_predict` VALUES ('146', '34', '11111162', '2.31');
INSERT INTO `tb_predict` VALUES ('147', '34', '11111163', '1.23');
INSERT INTO `tb_predict` VALUES ('148', '34', '11111164', '2.35');
INSERT INTO `tb_predict` VALUES ('149', '34', '11111165', '2.60');
INSERT INTO `tb_predict` VALUES ('150', '34', '11111166', '2.43');
INSERT INTO `tb_predict` VALUES ('151', '34', '11111167', '3.14');
INSERT INTO `tb_predict` VALUES ('152', '34', '11111168', '2.80');

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
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_record
-- ----------------------------
INSERT INTO `tb_record` VALUES ('12', '15', '11111115');
INSERT INTO `tb_record` VALUES ('16', '15', '11111117');
INSERT INTO `tb_record` VALUES ('18', '1', '11111119');
INSERT INTO `tb_record` VALUES ('19', '1', '11111129');
INSERT INTO `tb_record` VALUES ('21', '1', '11111113');
INSERT INTO `tb_record` VALUES ('24', '15', '11111123');
INSERT INTO `tb_record` VALUES ('25', '1', '11111123');
INSERT INTO `tb_record` VALUES ('26', '1', '11111127');
INSERT INTO `tb_record` VALUES ('27', '1', '11111115');
INSERT INTO `tb_record` VALUES ('28', '1', '11111122');
INSERT INTO `tb_record` VALUES ('29', '1', '11111118');
INSERT INTO `tb_record` VALUES ('30', '1', '11111128');
INSERT INTO `tb_record` VALUES ('31', '1', '11111117');
INSERT INTO `tb_record` VALUES ('32', '1', '11111112');
INSERT INTO `tb_record` VALUES ('33', '15', '11111112');
INSERT INTO `tb_record` VALUES ('34', '15', '11111127');
INSERT INTO `tb_record` VALUES ('35', '15', '11111121');
INSERT INTO `tb_record` VALUES ('36', '15', '11111130');
INSERT INTO `tb_record` VALUES ('37', '15', '11111128');
INSERT INTO `tb_record` VALUES ('38', '15', '11111116');
INSERT INTO `tb_record` VALUES ('39', '15', '11111114');
INSERT INTO `tb_record` VALUES ('41', '15', '11111122');
INSERT INTO `tb_record` VALUES ('43', '15', '11111119');
INSERT INTO `tb_record` VALUES ('44', '15', '11111113');
INSERT INTO `tb_record` VALUES ('45', '15', '11111124');
INSERT INTO `tb_record` VALUES ('46', '15', '11111126');
INSERT INTO `tb_record` VALUES ('47', '15', '11111125');
INSERT INTO `tb_record` VALUES ('49', '20', '11111125');
INSERT INTO `tb_record` VALUES ('50', '20', '11111124');
INSERT INTO `tb_record` VALUES ('51', '20', '11111119');
INSERT INTO `tb_record` VALUES ('53', '20', '11111126');
INSERT INTO `tb_record` VALUES ('54', '20', '11111112');
INSERT INTO `tb_record` VALUES ('55', '20', '11111132');
INSERT INTO `tb_record` VALUES ('56', '20', '11111133');
INSERT INTO `tb_record` VALUES ('57', '20', '11111117');
INSERT INTO `tb_record` VALUES ('58', '15', '11111133');
INSERT INTO `tb_record` VALUES ('60', '20', '11111135');
INSERT INTO `tb_record` VALUES ('66', '15', '11111129');
INSERT INTO `tb_record` VALUES ('67', '15', '11111161');
INSERT INTO `tb_record` VALUES ('68', '15', '11111166');
INSERT INTO `tb_record` VALUES ('69', '15', '11111158');
INSERT INTO `tb_record` VALUES ('70', '15', '11111132');
INSERT INTO `tb_record` VALUES ('71', '15', '11111159');
INSERT INTO `tb_record` VALUES ('72', '15', '11111148');
INSERT INTO `tb_record` VALUES ('73', '15', '11111145');
INSERT INTO `tb_record` VALUES ('74', '15', '11111146');
INSERT INTO `tb_record` VALUES ('75', '15', '11111135');
INSERT INTO `tb_record` VALUES ('76', '15', '11111120');
INSERT INTO `tb_record` VALUES ('77', '15', '11111118');
INSERT INTO `tb_record` VALUES ('78', '15', '11111147');
INSERT INTO `tb_record` VALUES ('79', '15', '11111149');
INSERT INTO `tb_record` VALUES ('80', '15', '11111167');
INSERT INTO `tb_record` VALUES ('84', '20', '11111115');
INSERT INTO `tb_record` VALUES ('85', '20', '11111114');
INSERT INTO `tb_record` VALUES ('86', '34', '11111133');
INSERT INTO `tb_record` VALUES ('87', '34', '11111113');
INSERT INTO `tb_record` VALUES ('88', '34', '11111130');
INSERT INTO `tb_record` VALUES ('89', '15', '11111168');

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
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', 'admin');
INSERT INTO `tb_role` VALUES ('2', 'public');
INSERT INTO `tb_role` VALUES ('3', 'restrict');

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
-- Records of tb_state
-- ----------------------------
INSERT INTO `tb_state` VALUES ('1', '认证中');
INSERT INTO `tb_state` VALUES ('2', '已认证');
INSERT INTO `tb_state` VALUES ('3', '认证失败');
INSERT INTO `tb_state` VALUES ('4', '上架中');
INSERT INTO `tb_state` VALUES ('5', '已下架');
INSERT INTO `tb_state` VALUES ('6', '未读');
INSERT INTO `tb_state` VALUES ('7', '已读');

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
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '15', '男', '1111111111@qq.com', '22222222222', '江西省鹰潭市', 'admin', '1', '2020-01-29 22:01:28', '100', '\\images\\1584794190176.jpg', '2');
INSERT INTO `tb_user` VALUES ('15', 'lcm', '17', '男', '1111114@qq.com', 'xxxxxxxxxx', '江西南昌', '123456', '2', '2020-02-19 21:30:34', '0', '\\images\\1590629924077.jpg', '1');
INSERT INTO `tb_user` VALUES ('20', '123456', '90', '男', '', 'fsdfs', 'cvdsv', '123456', '2', '2020-05-22 19:21:56', '0', '\\images\\1590147083480.png', '2');
INSERT INTO `tb_user` VALUES ('34', 'yb', '10', '女', '', '', '江西省鹰潭市', '123', '2', '2020-05-29 20:02:23', '0', '\\images\\1590753788840.jpg', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=11111169 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_video
-- ----------------------------
INSERT INTO `tb_video` VALUES ('11111112', '一个好心情12', '这里是块级帮助文本的实例这里是块级帮助文本的实例这里是块级帮助文本的实例', '2020-02-25 20:35:29', '\\videos\\1582634129109.mp4', '\\images\\1582634129226.jpg', '5', '61', '1', '3', '1');
INSERT INTO `tb_video` VALUES ('11111113', '一个好心情12', '这里是块级帮助文本的实例', '2020-05-23 20:35:48', '\\videos\\1582634148889.mp4', '\\images\\1582634148893.jpg', '4', '126', '14', '1', '1');
INSERT INTO `tb_video` VALUES ('11111114', '一个好心情', '这里是块级帮助文本的实例', '2020-02-25 20:36:07', '\\videos\\1582634167413.mp4', '\\images\\1582634167513.jpg', '4', '14', '1', '5', '1');
INSERT INTO `tb_video` VALUES ('11111115', '一个好心情', '这里是块级帮助文本的实例', '2020-02-25 20:36:32', '\\videos\\1582634191909.mp4', '\\images\\1582634192030.jpg', '4', '18', '1', '2', '1');
INSERT INTO `tb_video` VALUES ('11111116', '一个好心情', '这里是块级帮助文本的实例', '2020-02-25 20:36:54', '\\videos\\1582634214162.mp4', '\\images\\1582634214258.jpg', '4', '48', '1', '5', '1');
INSERT INTO `tb_video` VALUES ('11111117', '一个好心情', '这里是块级帮助文本的实例', '2020-03-05 22:35:33', '\\videos\\1583418932994.mp4', '\\images\\1583418933127.jpg', '4', '13', '0', '1', '1');
INSERT INTO `tb_video` VALUES ('11111118', 'Lcm1997', '这里是块级帮助文本的实例', '2020-03-05 22:36:00', '\\videos\\1583418960515.mp4', '\\images\\1583418960638.jpg', '4', '31', '0', '3', '1');
INSERT INTO `tb_video` VALUES ('11111119', '30+', '这里是块级帮助文本的实例', '2020-03-08 10:46:54', '\\videos\\1583635614359.mp4', '\\images\\1583635614456.jpg', '4', '21', '0', '1', '15');
INSERT INTO `tb_video` VALUES ('11111120', 'Lcm1997', '这里是块级帮助文本的实例', '2020-03-10 12:07:54', '\\videos\\1583813274452.mp4', '\\images\\1583813274574.jpg', '4', '28', '0', '1', '1');
INSERT INTO `tb_video` VALUES ('11111121', '实时新闻', '这里是块级帮助文本的实例', '2020-03-14 23:04:48', '\\videos\\1584198288652.mp4', '\\images\\1584198288693.jpg', '4', '3', '0', '2', '15');
INSERT INTO `tb_video` VALUES ('11111122', '事实', '这里是块级帮助文本的实例', '2020-03-14 23:07:02', '\\videos\\1584198422922.mp4', '\\images\\1584198422938.jpg', '4', '2', '0', '2', '15');
INSERT INTO `tb_video` VALUES ('11111123', '实时', '这里是块级帮助文本的实例', '2020-03-14 23:07:29', '\\videos\\1584198449570.mp4', '\\images\\1584198449592.jpg', '4', '8', '0', '3', '15');
INSERT INTO `tb_video` VALUES ('11111124', '美食', '这里是块级帮助文本的实例', '2020-05-23 23:08:02', '\\videos\\1584198482457.mp4', '\\images\\1584198482467.jpg', '4', '9', '1', '4', '15');
INSERT INTO `tb_video` VALUES ('11111125', '美食', '这里是块级帮助文本的实例', '2020-03-14 23:08:29', '\\videos\\1584198509214.mp4', '\\images\\1584198509248.jpg', '4', '8', '0', '4', '15');
INSERT INTO `tb_video` VALUES ('11111126', '美食', '这里是块级帮助文本的实例', '2020-03-14 23:08:53', '\\videos\\1584198533641.mp4', '\\images\\1584198533669.jpg', '4', '12', '1', '4', '15');
INSERT INTO `tb_video` VALUES ('11111127', 'Lcm1997', '这里是块级帮助文本的实例', '2020-03-14 23:10:12', '\\videos\\1584198612308.mp4', '\\images\\1584198612316.jpg', '4', '4', '0', '5', '15');
INSERT INTO `tb_video` VALUES ('11111128', 'Lcm1997', '这是一条重要的信息30', '2020-03-14 23:10:49', '\\videos\\1584198649921.mp4', '\\images\\1584198649971.jpg', '4', '64', '0', '1', '15');
INSERT INTO `tb_video` VALUES ('11111129', 'Lcm1997', '这里是块级帮助文本的实例', '2020-03-14 23:11:15', '\\videos\\1584198675695.mp4', '\\images\\1584198675716.jpg', '4', '9', '0', '3', '15');
INSERT INTO `tb_video` VALUES ('11111130', '影视', '这里是块级帮助文本的实例', '2020-03-14 23:08:29', '\\videos\\1584198509214.mp4', '\\images\\1584198509248.jpg', '4', '25', '0', '1', '15');
INSERT INTO `tb_video` VALUES ('11111132', '我是UP猪', '第一次上传视频', '2020-05-22 20:05:45', '\\videos\\1590149145258.mp4', '\\images\\1590149145657.jpg', '4', '4', '0', '4', '20');
INSERT INTO `tb_video` VALUES ('11111133', 'friends', '辛勤更新视频的up猪', '2020-05-22 20:15:21', '\\videos\\1590149720478.mp4', '\\images\\1590149721047.jpg', '4', '11', '0', '1', '20');
INSERT INTO `tb_video` VALUES ('11111135', '医学教育', '外科手术', '2020-05-22 21:10:46', '\\videos\\1590153042414.mp4', '\\images\\1590153046702.jpg', '4', '5', '0', '13', '20');
INSERT INTO `tb_video` VALUES ('11111145', '睡前消息', '搬运视频', '2020-05-22 22:25:21', '\\videos\\1590157521333.mp4', '\\images\\1590157521924.jpg', '2', '11', '0', '10', '20');
INSERT INTO `tb_video` VALUES ('11111146', '一周观天下：科学家被困北极与熊为伴', '', '2020-05-22 22:27:15', '\\videos\\1590157635666.mp4', '\\images\\1590157635811.jpg', '2', '2', '0', '2', '20');
INSERT INTO `tb_video` VALUES ('11111147', '天津大爷: 钱算嘛? 有钱就得吃腰子', '', '2020-05-22 22:31:11', '\\videos\\1590157870736.mp4', '\\images\\1590157871289.jpg', '2', '2', '0', '4', '20');
INSERT INTO `tb_video` VALUES ('11111148', '属于理工男的浪漫，和你一起鉴赏一次火箭发射', '', '2020-05-22 22:37:07', '\\videos\\1590158227463.mp4', '\\images\\1590158227715.jpg', '2', '4', '0', '11', '20');
INSERT INTO `tb_video` VALUES ('11111149', '成龙历险记', '', '2020-05-22 22:43:15', '\\videos\\1590158592764.mp4', '\\images\\1590158595770.jpg', '2', '3', '0', '9', '20');
INSERT INTO `tb_video` VALUES ('11111158', '30', '这是一条重要的信息30', '2020-05-23 09:15:19', '\\videos\\1590196518994.mp4', '\\images\\1590196519022.jpg', '4', '4', '0', '3', '15');
INSERT INTO `tb_video` VALUES ('11111159', '一个好心情12', '这是一条重要的信息30', '2020-05-23 09:25:31', '\\videos\\1590197131611.mp4', '\\images\\1590197131655.jpg', '4', '10', '0', '1', '15');
INSERT INTO `tb_video` VALUES ('11111160', 'Lcm1997', '这里是块级帮助文本的实例', '2020-05-23 09:27:17', '\\videos\\1590197237627.mp4', '\\images\\1590197237667.jpg', '1', '0', '0', '17', '15');
INSERT INTO `tb_video` VALUES ('11111161', 'Lcm1997', '这里是块级帮助文本的实例这里是', '2020-05-23 09:27:53', '\\videos\\1590197273668.mp4', '\\images\\1590197273703.jpg', '4', '2', '0', '2', '15');
INSERT INTO `tb_video` VALUES ('11111162', '30', '这里是块级帮助文本的实例', '2020-05-23 09:29:47', '\\videos\\1590197387018.mp4', '\\images\\1590197387071.jpg', '1', '0', '0', '1', '15');
INSERT INTO `tb_video` VALUES ('11111163', 'Lcm1997', '这里是块级帮助文本的实例', '2020-05-23 09:31:17', '\\videos\\1590197477651.mp4', '\\images\\1590197477735.jpg', '1', '0', '0', '10', '15');
INSERT INTO `tb_video` VALUES ('11111164', '30+', '这里是块级帮助文本的实例这里是块级帮助文本的实例这里是块级帮助文本的实例', '2020-05-23 09:31:38', '\\videos\\1590197498266.mp4', '\\images\\1590197498286.jpg', '1', '0', '0', '11', '15');
INSERT INTO `tb_video` VALUES ('11111165', 'Lcm1997', '这里是块级帮助文本的实例', '2020-05-23 11:23:04', '\\videos\\1590204184252.mp4', '\\images\\1590204184272.jpg', '4', '0', '0', '5', '15');
INSERT INTO `tb_video` VALUES ('11111166', '我的视频', '我的视频', '2020-05-24 08:28:32', '\\videos\\1590280112300.mp4', '\\images\\1590280112357.jpg', '4', '5', '0', '3', '15');
INSERT INTO `tb_video` VALUES ('11111167', '短视频网站', '我的毕业设计', '2020-05-28 09:41:47', '\\videos\\1590630106922.mp4', '\\images\\1590630107317.jpg', '5', '11', '0', '14', '15');
INSERT INTO `tb_video` VALUES ('11111168', '123', '324', '2020-11-27 23:51:47', '\\videos\\1606492306949.mp4', '\\images\\1606492307276.jpg', '1', '1', '0', '1', '15');

-- ----------------------------
-- Table structure for tb_videotype
-- ----------------------------
DROP TABLE IF EXISTS `tb_videotype`;
CREATE TABLE `tb_videotype` (
  `videotype_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`videotype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_videotype
-- ----------------------------
INSERT INTO `tb_videotype` VALUES ('1', '影视');
INSERT INTO `tb_videotype` VALUES ('2', '新闻');
INSERT INTO `tb_videotype` VALUES ('3', '生活');
INSERT INTO `tb_videotype` VALUES ('4', '美食');
INSERT INTO `tb_videotype` VALUES ('5', '音乐');
INSERT INTO `tb_videotype` VALUES ('7', '电视剧');
INSERT INTO `tb_videotype` VALUES ('8', '舞蹈');
INSERT INTO `tb_videotype` VALUES ('9', '动漫');
INSERT INTO `tb_videotype` VALUES ('10', '娱乐');
INSERT INTO `tb_videotype` VALUES ('11', '科技数码');
INSERT INTO `tb_videotype` VALUES ('12', '体育');
INSERT INTO `tb_videotype` VALUES ('13', '记录片');
INSERT INTO `tb_videotype` VALUES ('14', '白嫖党');
INSERT INTO `tb_videotype` VALUES ('17', '美妆');

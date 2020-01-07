/*
Navicat MySQL Data Transfer
Date: 2020-01-07 11:27:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` int(20) NOT NULL,
  `user_name` varchar(50) default NULL,
  `operation` varchar(50) default NULL,
  `time` int(11) default NULL,
  `method` varchar(200) default NULL,
  `params` varchar(500) default NULL,
  `ip` varchar(64) default NULL,
  `create_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

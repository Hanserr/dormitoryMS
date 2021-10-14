/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : dormitoryms

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 14/10/2021 20:43:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `uid` int(0) NOT NULL,
  `username` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `updated` timestamp(0) NOT NULL,
  `created` timestamp(0) NOT NULL,
  `illustrate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  INDEX `fk_usertable`(`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '13228805019', '$2a$10$sQ8H2gAWQqOu4fV1his8F.XJSD15CGj4kddCxJU7Zlzmbni9NktGS', '2021-10-12 19:46:54', '2021-10-12 19:46:57', 'super');
INSERT INTO `admin` VALUES (2, '18994243691', '$2a$10$sQ8H2gAWQqOu4fV1his8F.XJSD15CGj4kddCxJU7Zlzmbni9NktGS', '2021-10-12 19:46:59', '2021-10-12 19:47:01', 'normal');

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `parentId` int(0) NULL DEFAULT NULL COMMENT '父级菜单',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权',
  `type` int(0) NOT NULL COMMENT '类型 0目录 1菜单 2按钮',
  `created` timestamp(0) NOT NULL,
  `updated` timestamp(0) NULL DEFAULT NULL,
  `status` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES (1, 0, '学生管理', '', 'sys:manageStu', 1, '2021-10-12 18:19:25', '2021-10-12 18:19:27', 1);
INSERT INTO `admin_menu` VALUES (2, 1, '学生信息', '/AdminPage/stuInfo', 'sys:manageStu:stuInfo', 1, '2021-10-12 18:23:33', '2021-10-12 18:23:38', 1);
INSERT INTO `admin_menu` VALUES (3, 2, '普通添加学生', '/AdminPage/addStu/addLeft', 'sys:manageStu:commonAdd', 1, '2021-10-12 18:24:47', '2021-10-12 18:24:52', 1);
INSERT INTO `admin_menu` VALUES (4, 2, '通过宿舍号添加学生', '/AdminPage/addStu/addRight', 'sys:manageStu:addByDnum', 1, '2021-10-12 18:25:54', '2021-10-12 18:26:00', 1);
INSERT INTO `admin_menu` VALUES (5, 0, '宿舍管理', NULL, 'sys:manageDormitory', 1, '2021-10-12 18:26:57', '2021-10-12 18:27:01', 1);
INSERT INTO `admin_menu` VALUES (6, 1, '宿舍状态', '/AdminPage/SOTDormitory', 'sys:manageDormitory:status', 1, '2021-10-12 18:33:09', '2021-10-12 18:33:14', 1);
INSERT INTO `admin_menu` VALUES (7, 1, '更改信息', '/AdminPage/updateDor', 'sys:manageDormitory:update', 1, '2021-10-12 18:34:41', '2021-10-12 18:34:46', 1);
INSERT INTO `admin_menu` VALUES (8, 0, '通知管理', '', 'sys:manageNoti', 1, '2021-10-12 18:36:28', '2021-10-12 18:36:32', 1);
INSERT INTO `admin_menu` VALUES (9, 1, '当前通知', '/AdminPage/currentNoti', 'sys:manageNoti:current', 1, '2021-10-12 18:37:47', '2021-10-12 18:37:52', 1);
INSERT INTO `admin_menu` VALUES (10, 1, '更改通知', '/AdminPage', 'sys:manageNoti:update', 1, '2021-10-12 18:38:29', '2021-10-12 18:38:33', 1);
INSERT INTO `admin_menu` VALUES (11, 0, '卫生信息', '', 'sys:sanitationInfo', 1, '2021-10-12 18:39:38', '2021-10-12 18:39:40', 1);
INSERT INTO `admin_menu` VALUES (12, 1, '排名', '/AdminPage/sanitationRank', 'sys:sanitationInfo:Rank', 1, '2021-10-12 18:40:34', '2021-10-12 18:40:38', 1);
INSERT INTO `admin_menu` VALUES (13, 1, '添加', '/AdminPage/addSanitation', 'sys:sanitationInfo:add', 1, '2021-10-12 18:41:18', '2021-10-12 18:41:22', 1);
INSERT INTO `admin_menu` VALUES (14, 0, '物品报修', NULL, 'sys:repair', 1, '2021-10-12 18:41:54', '2021-10-12 18:41:59', 1);
INSERT INTO `admin_menu` VALUES (15, 1, '已处理', '/AdminPage/solved', 'sys:repair:solved', 1, '2021-10-12 18:43:19', '2021-10-12 18:43:22', 1);
INSERT INTO `admin_menu` VALUES (16, 1, '待处理', '/AdminPage/pending', 'sys:repair:pending', 1, '2021-10-12 18:44:13', '2021-10-12 18:44:18', 1);
INSERT INTO `admin_menu` VALUES (17, 0, '管理员', '/AdminPage/adminInfo', 'sys:adminInfo', 1, '2021-10-12 18:44:54', '2021-10-12 18:44:57', 1);
INSERT INTO `admin_menu` VALUES (18, NULL, '模糊查询学生', NULL, 'sys:queryStu', 2, '2021-10-12 20:49:18', '2021-10-12 20:49:20', 1);
INSERT INTO `admin_menu` VALUES (19, NULL, '查询单条学生数据', NULL, 'sys:queryOneStu', 2, '2021-10-12 20:50:29', '2021-10-12 20:50:30', 1);
INSERT INTO `admin_menu` VALUES (20, NULL, '修改学生数据', NULL, 'sys:updateStu', 2, '2021-10-12 20:52:00', '2021-10-12 20:52:03', 1);
INSERT INTO `admin_menu` VALUES (21, NULL, '批量添加学生', NULL, 'sys:addStuList', 2, '2021-10-12 20:54:12', '2021-10-12 20:54:15', 1);
INSERT INTO `admin_menu` VALUES (22, NULL, '删除指定学生', NULL, 'sys:deleteStu', 2, '2021-10-12 20:55:06', '2021-10-12 20:55:09', 1);
INSERT INTO `admin_menu` VALUES (23, NULL, '获取院系列表', NULL, 'sys:getDepList', 2, '2021-10-12 20:56:41', '2021-10-12 20:56:45', 1);
INSERT INTO `admin_menu` VALUES (24, NULL, '根据宿舍号查询所有学生', NULL, 'sys:queryStuByDorNum', 2, '2021-10-12 20:58:05', '2021-10-12 20:58:02', 1);
INSERT INTO `admin_menu` VALUES (25, NULL, '查询宿舍容量', NULL, 'sys:queryCapacity', 2, '2021-10-12 20:58:48', '2021-10-12 20:58:55', 1);
INSERT INTO `admin_menu` VALUES (26, NULL, '获取10条宿舍数据', NULL, 'sys:getDorListByLimit', 2, '2021-10-12 21:00:01', '2021-10-12 21:00:03', 1);
INSERT INTO `admin_menu` VALUES (27, NULL, '获取宿舍内部详细信息', NULL, 'sys:getDorDetail', 2, '2021-10-12 21:00:47', '2021-10-12 21:00:48', 1);

-- ----------------------------
-- Table structure for admin_middle_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_middle_menu`;
CREATE TABLE `admin_middle_menu`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `adminID` int(0) NOT NULL,
  `menuID` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `adminID`(`adminID`) USING BTREE,
  INDEX `menuID`(`menuID`) USING BTREE,
  CONSTRAINT `admin_middle_menu_ibfk_1` FOREIGN KEY (`adminID`) REFERENCES `admin` (`uid`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `admin_middle_menu_ibfk_2` FOREIGN KEY (`menuID`) REFERENCES `admin_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin_middle_menu
-- ----------------------------
INSERT INTO `admin_middle_menu` VALUES (1, 1, 1);
INSERT INTO `admin_middle_menu` VALUES (2, 1, 2);
INSERT INTO `admin_middle_menu` VALUES (3, 1, 3);
INSERT INTO `admin_middle_menu` VALUES (4, 1, 4);
INSERT INTO `admin_middle_menu` VALUES (5, 1, 5);
INSERT INTO `admin_middle_menu` VALUES (6, 1, 6);
INSERT INTO `admin_middle_menu` VALUES (7, 1, 7);
INSERT INTO `admin_middle_menu` VALUES (8, 1, 8);
INSERT INTO `admin_middle_menu` VALUES (9, 1, 9);
INSERT INTO `admin_middle_menu` VALUES (10, 1, 10);
INSERT INTO `admin_middle_menu` VALUES (11, 1, 11);
INSERT INTO `admin_middle_menu` VALUES (12, 1, 12);
INSERT INTO `admin_middle_menu` VALUES (13, 1, 13);
INSERT INTO `admin_middle_menu` VALUES (14, 1, 14);
INSERT INTO `admin_middle_menu` VALUES (15, 1, 15);
INSERT INTO `admin_middle_menu` VALUES (16, 1, 16);
INSERT INTO `admin_middle_menu` VALUES (17, 1, 17);
INSERT INTO `admin_middle_menu` VALUES (18, 1, 18);
INSERT INTO `admin_middle_menu` VALUES (19, 1, 19);
INSERT INTO `admin_middle_menu` VALUES (20, 1, 20);
INSERT INTO `admin_middle_menu` VALUES (21, 1, 21);
INSERT INTO `admin_middle_menu` VALUES (22, 1, 22);
INSERT INTO `admin_middle_menu` VALUES (23, 1, 23);
INSERT INTO `admin_middle_menu` VALUES (24, 1, 24);
INSERT INTO `admin_middle_menu` VALUES (25, 1, 25);
INSERT INTO `admin_middle_menu` VALUES (26, 1, 26);
INSERT INTO `admin_middle_menu` VALUES (27, 1, 27);

-- ----------------------------
-- Table structure for dormitorydetail
-- ----------------------------
DROP TABLE IF EXISTS `dormitorydetail`;
CREATE TABLE `dormitorydetail`  (
  `id` int(0) NOT NULL,
  `waterbalance` decimal(6, 2) NOT NULL,
  `powerbalance` decimal(6, 2) NOT NULL,
  UNIQUE INDEX `id`(`id`) USING BTREE,
  CONSTRAINT `fk_dormitorydetail` FOREIGN KEY (`id`) REFERENCES `dormitorymiddlelist` (`number`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitorydetail
-- ----------------------------
INSERT INTO `dormitorydetail` VALUES (1101, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1102, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1103, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1104, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1105, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1106, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1107, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1108, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1109, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1110, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1201, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1202, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1203, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1204, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1205, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1206, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1207, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1208, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1209, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1210, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1301, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1302, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1303, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1304, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1305, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1306, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1307, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1308, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1309, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (1310, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2101, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2102, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2103, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2104, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2105, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2106, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2107, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2108, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2109, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2110, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2201, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2202, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2203, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2204, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2205, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2206, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2207, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2208, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2209, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2210, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2301, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2302, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2303, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2304, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2305, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2306, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2307, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2308, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2309, 9999.00, 9999.00);
INSERT INTO `dormitorydetail` VALUES (2310, 9999.00, 9999.00);

-- ----------------------------
-- Table structure for dormitorylist
-- ----------------------------
DROP TABLE IF EXISTS `dormitorylist`;
CREATE TABLE `dormitorylist`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `capacity` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type`(`type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitorylist
-- ----------------------------
INSERT INTO `dormitorylist` VALUES (1, 'A', 4);
INSERT INTO `dormitorylist` VALUES (2, 'B', 6);

-- ----------------------------
-- Table structure for dormitorymiddlelist
-- ----------------------------
DROP TABLE IF EXISTS `dormitorymiddlelist`;
CREATE TABLE `dormitorymiddlelist`  (
  `number` int(0) NOT NULL,
  `type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  UNIQUE INDEX `number`(`number`) USING BTREE,
  INDEX `type`(`type`) USING BTREE,
  CONSTRAINT `dormitory_ibfk_1` FOREIGN KEY (`type`) REFERENCES `dormitorylist` (`type`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitorymiddlelist
-- ----------------------------
INSERT INTO `dormitorymiddlelist` VALUES (1101, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1102, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1103, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1104, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1105, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1106, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1107, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1108, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1109, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1110, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1201, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1202, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1203, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1204, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1205, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1206, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1207, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1208, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1209, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1210, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1301, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1302, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1303, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1304, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1305, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1306, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1307, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1308, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1309, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (1310, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2101, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2102, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2103, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2104, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2105, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2106, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2107, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2108, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2109, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2110, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2201, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2202, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2203, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2204, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2205, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2206, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2207, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2208, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2209, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2210, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2301, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2302, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2303, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2304, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2305, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2306, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2307, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2308, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2309, 'A');
INSERT INTO `dormitorymiddlelist` VALUES (2310, 'A');

-- ----------------------------
-- Table structure for faculty
-- ----------------------------
DROP TABLE IF EXISTS `faculty`;
CREATE TABLE `faculty`  (
  `id` int(0) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of faculty
-- ----------------------------
INSERT INTO `faculty` VALUES (1, '计算机科学与技术');
INSERT INTO `faculty` VALUES (2, '哲学');
INSERT INTO `faculty` VALUES (3, '经济学');
INSERT INTO `faculty` VALUES (4, '法学');
INSERT INTO `faculty` VALUES (5, '社会学');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `stuid` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学号',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '姓名',
  `age` int(0) NOT NULL COMMENT '年龄',
  `avatarUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像路径地址',
  `gender` int(0) NOT NULL COMMENT '性别 0:女生 1:男生',
  `grade` int(0) NOT NULL COMMENT '年级',
  `department` int(0) NOT NULL COMMENT '院系',
  `role` int(0) NOT NULL COMMENT '宿舍内担任角色',
  `dormitoryNum` int(0) NOT NULL COMMENT '宿舍号',
  `updateTime` timestamp(0) NOT NULL COMMENT '更新时间',
  `createTime` timestamp(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `stuid`(`stuid`) USING BTREE,
  INDEX `student_ibfk_2`(`dormitoryNum`) USING BTREE,
  INDEX `fk_faculty`(`department`) USING BTREE,
  CONSTRAINT `fk_faculty` FOREIGN KEY (`department`) REFERENCES `faculty` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `student_ibfk_2` FOREIGN KEY (`dormitoryNum`) REFERENCES `dormitorymiddlelist` (`number`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '2021070801010', '甘雨', 18, NULL, 0, 2021, 1, 1, 1101, '2021-10-08 21:45:51', '2021-10-08 21:46:00');
INSERT INTO `student` VALUES (7, '2021071701020', '刻晴', 18, NULL, 0, 2021, 2, 0, 1101, '2021-10-08 21:45:56', '2021-10-08 21:46:02');
INSERT INTO `student` VALUES (12, '2021071701011', '钟离', 18, NULL, 1, 2021, 5, 1, 2101, '2021-10-08 21:45:58', '2021-10-08 21:46:03');

SET FOREIGN_KEY_CHECKS = 1;

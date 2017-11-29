/*
Navicat MySQL Data Transfer

Source Server         : my
Source Server Version : 50619
Source Host           : localhost:3306
Source Database       : tianyita

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2014-09-11 15:32:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `app`
-- ----------------------------
DROP TABLE IF EXISTS `app`;
CREATE TABLE `app` (
  `a_id` int(11) NOT NULL,
  `t_id` int(11) DEFAULT NULL,
  `a_name` varchar(20) DEFAULT NULL,
  `a_picture` varchar(255) DEFAULT NULL,
  `a_address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`a_id`),
  KEY `FK_is` (`t_id`),
  CONSTRAINT `FK_is` FOREIGN KEY (`t_id`) REFERENCES `apptype` (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app
-- ----------------------------
INSERT INTO `app` VALUES ('1', '1', '我叫MT', 'http://192.168.137.1:8080/Doom/picture/wojiaomt.png', 'http://im mt');
INSERT INTO `app` VALUES ('2', '1', '秦时明月', 'http://192.168.137.1:8080/Doom/picture/qinshimingyue.png', 'http://qinshimoon');
INSERT INTO `app` VALUES ('3', '2', '就爱买', 'http://192.168.137.1:8080/Doom/picture/jiuaimai.png', 'http://igouwu');
INSERT INTO `app` VALUES ('4', '3', '360手机助手', 'http://192.168.137.1:8080/Doom/picture/360shoujizhushou.png', 'http://360shoujizhushou');

-- ----------------------------
-- Table structure for `apptype`
-- ----------------------------
DROP TABLE IF EXISTS `apptype`;
CREATE TABLE `apptype` (
  `t_id` int(11) NOT NULL,
  `t_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apptype
-- ----------------------------
INSERT INTO `apptype` VALUES ('1', '游戏');
INSERT INTO `apptype` VALUES ('2', '生活');
INSERT INTO `apptype` VALUES ('3', '办公');

-- ----------------------------
-- Table structure for `busdevelop`
-- ----------------------------
DROP TABLE IF EXISTS `busdevelop`;
CREATE TABLE `busdevelop` (
  `b_id` int(11) NOT NULL,
  `b_name` varchar(20) DEFAULT NULL,
  `b_content` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of busdevelop
-- ----------------------------
INSERT INTO `busdevelop` VALUES ('1', '业务1', '这是业务1的内容');
INSERT INTO `busdevelop` VALUES ('2', '业务2', '业务2的内容');

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `phone_num1` varchar(30) NOT NULL,
  `regtime` date DEFAULT NULL,
  `phone_num2` varchar(11) DEFAULT NULL,
  `phone_model` varchar(20) DEFAULT NULL,
  `phone_num3` varchar(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`phone_num1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('+8613250740436', null, '', 'default', '', '南俊。');
INSERT INTO `customer` VALUES ('+8615696123597', null, '', 'default', '', '栗博');
INSERT INTO `customer` VALUES ('029-873-22616', null, '', 'default', '', '王九洲');
INSERT INTO `customer` VALUES ('029-875-71236', null, '', 'default', '', '王伟老师');
INSERT INTO `customer` VALUES ('02983534515', null, '18291839811', 'default', '', '白金明');
INSERT INTO `customer` VALUES ('02984250941', null, '18817391334', 'default', '', '高绥凯');
INSERT INTO `customer` VALUES ('02984705782', null, '13609256916', 'default', '', '杨旭');
INSERT INTO `customer` VALUES ('02986258152', null, '13892888253', 'default', '', '吴佳丽');
INSERT INTO `customer` VALUES ('02987333448', null, '02987851125', 'default', '13891855476', '李莹');
INSERT INTO `customer` VALUES ('02988151880', null, '18729525171', 'default', '', '景泽昊');
INSERT INTO `customer` VALUES ('09112267400', null, '18609111162', 'default', '', '寇楠');
INSERT INTO `customer` VALUES ('1 232-134-5433', null, '', 'default', '', '3');
INSERT INTO `customer` VALUES ('1 345-678-3222', null, '', 'default', '', '1');
INSERT INTO `customer` VALUES ('1 435-654-6565', null, '', 'default', '', '2');
INSERT INTO `customer` VALUES ('1-315-207-5181', null, '', 'default', '', '赵鹰翔');
INSERT INTO `customer` VALUES ('1-319-330-9448', null, '', 'default', '', '温天英');
INSERT INTO `customer` VALUES ('1-329-917-0372', null, '', 'default', '', '张桂花');
INSERT INTO `customer` VALUES ('1-336-846-7038', null, '', 'default', '', '龚茜（新闻部）');
INSERT INTO `customer` VALUES ('1-338-498-6889', null, '', 'default', '', '小费叔叔');
INSERT INTO `customer` VALUES ('1-345-222-2211', null, '', 'default', '', '郭导');
INSERT INTO `customer` VALUES ('1-351-234-1054', null, '', 'default', '', '李媛');
INSERT INTO `customer` VALUES ('1-357-222-3758', null, '', 'default', '', '高欢老师');
INSERT INTO `customer` VALUES ('1-357-227-0655', null, '', 'default', '', '吕佳辰');
INSERT INTO `customer` VALUES ('1-357-291-1655', null, '', 'default', '', '樊博远');
INSERT INTO `customer` VALUES ('1-360-136-4118', null, '', 'default', '', '申祺');
INSERT INTO `customer` VALUES ('1-364-928-4421', null, '', 'default', '', '张丽');
INSERT INTO `customer` VALUES ('1-366-767-4554', null, '', 'default', '', '王弘厉');
INSERT INTO `customer` VALUES ('1-366-768-9473', null, '', 'default', '', '郑国祺');
INSERT INTO `customer` VALUES ('1-375-996-1771', null, '', 'default', '', '贾晨曦');
INSERT INTO `customer` VALUES ('1-377-262-0897', null, '', 'default', '', '李爷爷');
INSERT INTO `customer` VALUES ('1-389-184-9873', null, '', 'default', '', '蒲强');
INSERT INTO `customer` VALUES ('1-389-188-4097', null, '', 'default', '', '高誉欣');
INSERT INTO `customer` VALUES ('1-399-103-1922', null, '', 'default', '', '王甫社');
INSERT INTO `customer` VALUES ('1-507-102-9636', null, '', 'default', '', '孙懿雯');
INSERT INTO `customer` VALUES ('1-511-186-9889', null, '', 'default', '', '史琦');
INSERT INTO `customer` VALUES ('1-511-194-2275', null, '', 'default', '', '吴丰顺');
INSERT INTO `customer` VALUES ('1-511-195-3973', null, '', 'default', '', '秋水老乡');
INSERT INTO `customer` VALUES ('1-511-195-8173', null, '', 'default', '', '江唯维');
INSERT INTO `customer` VALUES ('1-512-308-3230', null, '', 'default', '', '过橙聪');
INSERT INTO `customer` VALUES ('1-512-308-6377', null, '', 'default', '', '赵伟（重大）');
INSERT INTO `customer` VALUES ('1-521-503-0655', null, '', 'default', '', '张林');
INSERT INTO `customer` VALUES ('1-526-623-5359', null, '', 'default', '', '霍思彤');
INSERT INTO `customer` VALUES ('1-552-922-8086', null, '', 'default', '', '李程');
INSERT INTO `customer` VALUES ('1-569-164-9000', null, '', 'default', '', '杨毅超');
INSERT INTO `customer` VALUES ('1-582-358-6953', null, '', 'default', '', '李万鑫');
INSERT INTO `customer` VALUES ('1-592-287-3159', null, '', 'default', '', '罗卓');
INSERT INTO `customer` VALUES ('1-599-897-6089', null, '626-089', 'default', '', '喻松');
INSERT INTO `customer` VALUES ('1-862-340-1560', null, '', 'default', '', '房朴');
INSERT INTO `customer` VALUES ('123423434433', null, '', 'default', '', '123');
INSERT INTO `customer` VALUES ('13002334569', null, '', 'default', '', '李双庆');
INSERT INTO `customer` VALUES ('13016080103', null, '', 'default', '', '爸爸');
INSERT INTO `customer` VALUES ('13035337186', null, '', 'default', '', '熊硕Warros');
INSERT INTO `customer` VALUES ('13080640131', null, '', 'default', '', '大胖');
INSERT INTO `customer` VALUES ('13100602933', null, '', 'default', '', '李琨');
INSERT INTO `customer` VALUES ('13125119189', null, '', 'default', '', '阮洋');
INSERT INTO `customer` VALUES ('13163286862', null, '', 'default', '', '姜焱熊');
INSERT INTO `customer` VALUES ('13167974360', null, '', 'default', '', '微微');
INSERT INTO `customer` VALUES ('13175110170', null, '', 'default', '', '郭响');
INSERT INTO `customer` VALUES ('132 1725 3530', null, '', 'default', '', '刘梦雪');
INSERT INTO `customer` VALUES ('13259727810', null, '13259727810', 'default', '', '郝姝然');
INSERT INTO `customer` VALUES ('13271923650', null, '', 'default', '', '李小娅');
INSERT INTO `customer` VALUES ('133 8499 3034', null, '', 'default', '', '方方');
INSERT INTO `customer` VALUES ('13368395014', null, '', 'default', '', '秀（重庆号）');
INSERT INTO `customer` VALUES ('13409946771', null, '', 'default', '', '圣人。');
INSERT INTO `customer` VALUES ('13412681095', null, '', 'default', '', '哥。');
INSERT INTO `customer` VALUES ('13437138723', null, '', 'default', '', '方逸豪');
INSERT INTO `customer` VALUES ('13452222211', null, '', 'default', '', '辅导员 郭坤');
INSERT INTO `customer` VALUES ('13469919544', null, '', 'default', '', '外婆');
INSERT INTO `customer` VALUES ('13476070457', null, '', 'default', '', '熊良');
INSERT INTO `customer` VALUES ('13476197195', null, '', 'default', '', '饶国亚。');
INSERT INTO `customer` VALUES ('13476708258', null, '', 'default', '', '书记');
INSERT INTO `customer` VALUES ('13476712666', null, '', 'default', '', '宋老板');
INSERT INTO `customer` VALUES ('13477644322', null, '', 'default', '', '姚红');
INSERT INTO `customer` VALUES ('13500379593', null, '', 'default', '', '陈道县');
INSERT INTO `customer` VALUES ('13508381688', null, '', 'default', '', '周阿姨');
INSERT INTO `customer` VALUES ('13512341054', null, '', 'default', '', '李媛');
INSERT INTO `customer` VALUES ('13512350741', null, '', 'default', '', '张桉');
INSERT INTO `customer` VALUES ('13527354649', null, '', 'default', '', '陈欢');
INSERT INTO `customer` VALUES ('13527398817', null, '', 'default', '', '刘铭lmeiko');
INSERT INTO `customer` VALUES ('13572212909', null, '15691705792', 'default', '', '王建宏');
INSERT INTO `customer` VALUES ('13594270343', null, '', 'default', '', '操作系统助教');
INSERT INTO `customer` VALUES ('13608305419', null, '', 'default', '', '周晰');
INSERT INTO `customer` VALUES ('13608396361', null, '', 'default', '', '暗影');
INSERT INTO `customer` VALUES ('13608398016', null, '', 'default', '', '陈佳涛');
INSERT INTO `customer` VALUES ('13609208825', null, '', 'default', '', '张世研');
INSERT INTO `customer` VALUES ('13618258353', null, '', 'default', '', '李帅');
INSERT INTO `customer` VALUES ('13618291584', null, '', 'default', '', 'KFC');
INSERT INTO `customer` VALUES ('13618395301', null, '', 'default', '', '魔术社');
INSERT INTO `customer` VALUES ('13629265991', null, '', 'default', '', '李哲');
INSERT INTO `customer` VALUES ('13636027880', null, '', 'default', '', '涂老师');
INSERT INTO `customer` VALUES ('13636091917', null, '', 'default', '', '李老师');
INSERT INTO `customer` VALUES ('13637965689', null, '', 'default', '', '陈卓');
INSERT INTO `customer` VALUES ('13637980685', null, '', 'default', '', '肖亚斌');
INSERT INTO `customer` VALUES ('13640578586', null, '', 'default', '', '概率论老师');
INSERT INTO `customer` VALUES ('13647617022', null, '', 'default', '', '班主任王小龙');
INSERT INTO `customer` VALUES ('13657138552', null, '', 'default', '', '大娟姐');
INSERT INTO `customer` VALUES ('13657138741', null, '', 'default', '', '秦洋。');
INSERT INTO `customer` VALUES ('13667190378', null, '', 'default', '', '姚声禹');
INSERT INTO `customer` VALUES ('13667605104', null, '', 'default', '', '李国兴');
INSERT INTO `customer` VALUES ('13667638462', null, '', 'default', '', '辅导员谢碧如');
INSERT INTO `customer` VALUES ('13669261169', null, '', 'default', '', '小白叔叔');
INSERT INTO `customer` VALUES ('13670298591', null, '', 'default', '', '细姑爷');
INSERT INTO `customer` VALUES ('13679168285', null, '', 'default', '', '贺舒亮');
INSERT INTO `customer` VALUES ('13687136504', null, '', 'default', '', '老瞿');
INSERT INTO `customer` VALUES ('13701392446', null, '', 'default', '', '赵嘉伟');
INSERT INTO `customer` VALUES ('13713057043', null, '', 'default', '', '妈妈');
INSERT INTO `customer` VALUES ('13717575582', null, '', 'default', '', '朱洁');
INSERT INTO `customer` VALUES ('13720438005', null, '', 'default', '', '王格');
INSERT INTO `customer` VALUES ('13724507953', null, '', 'default', '', '妈妈。');
INSERT INTO `customer` VALUES ('13752893103', null, '', 'default', '', '影子君');
INSERT INTO `customer` VALUES ('13759835522', null, '', 'default', '', '陈艳娟');
INSERT INTO `customer` VALUES ('13759890366', null, '18792597103', 'default', '', '冯星源');
INSERT INTO `customer` VALUES ('13782812642', null, '', 'default', '', '周庆');
INSERT INTO `customer` VALUES ('13872017658', null, '', 'default', '', '周承毅');
INSERT INTO `customer` VALUES ('13883315369', null, '', 'default', '', '李佳');
INSERT INTO `customer` VALUES ('13891908803', null, '', 'default', '', '赵博超');
INSERT INTO `customer` VALUES ('13891911819', null, '', 'default', '', '逯睿');
INSERT INTO `customer` VALUES ('13972743930', null, '', 'default', '', '江');
INSERT INTO `customer` VALUES ('13991888155', null, '', 'default', '', '胡阿姨');
INSERT INTO `customer` VALUES ('15002379054', null, '', 'default', '', '阮博');
INSERT INTO `customer` VALUES ('15002916003', null, '', 'default', '', '刘靖宜');
INSERT INTO `customer` VALUES ('15010317279', null, '', 'default', '', '陈馨怡');
INSERT INTO `customer` VALUES ('15025438778', null, '', 'default', '', '陈晓波');
INSERT INTO `customer` VALUES ('15025439315', null, '', 'default', '', '徐伟chasel');
INSERT INTO `customer` VALUES ('15025613693', null, '', 'default', '', '程移峰');
INSERT INTO `customer` VALUES ('15027070946', null, '', 'default', '', '夏端');
INSERT INTO `customer` VALUES ('15027081171', null, '', 'default', '', '周彬curly');
INSERT INTO `customer` VALUES ('15029012855', null, '18356039501', 'default', '', '宋忆南');
INSERT INTO `customer` VALUES ('15029016611', null, '18040032053', 'default', '', '刘一鸣');
INSERT INTO `customer` VALUES ('15071678462', null, '', 'default', '', '李茂');
INSERT INTO `customer` VALUES ('15071738308', null, '', 'default', '', '张月季');
INSERT INTO `customer` VALUES ('15071767005', null, '', 'default', '', '龙兄');
INSERT INTO `customer` VALUES ('15072848146', null, '', 'default', '', '长江叔');
INSERT INTO `customer` VALUES ('15072848322', null, '', 'default', '', '杨晗');
INSERT INTO `customer` VALUES ('15072855980', null, '', 'default', '', '兴元叔');
INSERT INTO `customer` VALUES ('15091325369', null, '', 'default', '', '毕雪');
INSERT INTO `customer` VALUES ('15098009164', null, '', 'default', '', '杨杜');
INSERT INTO `customer` VALUES ('151 1196 5355', null, '', 'default', '', '方方1');
INSERT INTO `customer` VALUES ('151 1197 7616', null, '', 'default', '', '吴艺');
INSERT INTO `customer` VALUES ('15105694993', null, '', 'default', '', '刘宇宁');
INSERT INTO `customer` VALUES ('15111942275', null, '', 'default', '', '吴丰顺');
INSERT INTO `customer` VALUES ('15111951665', null, '', 'default', '', '吴珂');
INSERT INTO `customer` VALUES ('15111957933', null, '', 'default', '', '王福临');
INSERT INTO `customer` VALUES ('15111958173', null, '', 'default', '', '江唯伟');
INSERT INTO `customer` VALUES ('15111959316', null, '', 'default', '', '严会');
INSERT INTO `customer` VALUES ('15111960397', null, '', 'default', '', '刘秀梅');
INSERT INTO `customer` VALUES ('15111977616', null, '', 'default', '', '吴艺');
INSERT INTO `customer` VALUES ('1511941404', null, '', 'default', '', '平措');
INSERT INTO `customer` VALUES ('15123066737', null, '', 'default', '', '周章清');
INSERT INTO `customer` VALUES ('15123191369', null, '', 'default', '', '邹秀佳');
INSERT INTO `customer` VALUES ('15123907480', null, '', 'default', '', 'a刘明');
INSERT INTO `customer` VALUES ('15129056960', null, '', 'default', '', '屈岳');
INSERT INTO `customer` VALUES ('15163295392', null, '', 'default', '', '林欢');
INSERT INTO `customer` VALUES ('15171603314', null, '', 'default', '', '秀');
INSERT INTO `customer` VALUES ('15171662841', null, '', 'default', '', '厨师');
INSERT INTO `customer` VALUES ('15171665011', null, '', 'default', '', '爸爸（浠水）');
INSERT INTO `customer` VALUES ('15172437130', null, '', 'default', '', '舒峰Lance');
INSERT INTO `customer` VALUES ('152 1119 2048', null, '', 'default', '', '细娟姐');
INSERT INTO `customer` VALUES ('152 7201 3637', null, '', 'default', '', '可蒙');
INSERT INTO `customer` VALUES ('15201231070', null, '', 'default', '', '徐洁');
INSERT INTO `customer` VALUES ('15201461975', null, '', 'default', '', '易莎');
INSERT INTO `customer` VALUES ('15207106211', null, '', 'default', '', '刘兴晨.ipa');
INSERT INTO `customer` VALUES ('15213219410', null, '', 'default', '', '夏潇');
INSERT INTO `customer` VALUES ('15215030655', null, '', 'default', '', '张林');
INSERT INTO `customer` VALUES ('15260169160', null, '', 'default', '', '吴文燚');
INSERT INTO `customer` VALUES ('15275311073', null, '', 'default', '', '李俊舸');
INSERT INTO `customer` VALUES ('15295528366', null, '', 'default', '', '郑婷');
INSERT INTO `customer` VALUES ('15319436626', null, '', 'default', '', '驾校');
INSERT INTO `customer` VALUES ('15334556990', null, '', 'default', '', '小刀');
INSERT INTO `customer` VALUES ('15337284010', null, '', 'default', '', '陈默');
INSERT INTO `customer` VALUES ('15377134406', null, '', 'default', '', '小娟姐');
INSERT INTO `customer` VALUES ('15377149063', null, '', 'default', '', '郭浪');
INSERT INTO `customer` VALUES ('15377187877', null, '', 'default', '', '熊雅琴');
INSERT INTO `customer` VALUES ('15389277411', null, '', 'default', '', '谭飞');
INSERT INTO `customer` VALUES ('15523792749', null, '', 'default', '', '宋林岭（新闻部）');
INSERT INTO `customer` VALUES ('15527018765', null, '', 'default', '', '舒宏宽');
INSERT INTO `customer` VALUES ('15527058780', null, '', 'default', '', '贺遥');
INSERT INTO `customer` VALUES ('15527329681', null, '', 'default', '', '张震');
INSERT INTO `customer` VALUES ('15527824935', null, '', 'default', '', '小扁。');
INSERT INTO `customer` VALUES ('15527966568', null, '', 'default', '', '杜森曼');
INSERT INTO `customer` VALUES ('15549463360', null, '', 'default', '', '涂珂丹Coco');
INSERT INTO `customer` VALUES ('156 2349 5956', null, '', 'default', '', '刘梦缘');
INSERT INTO `customer` VALUES ('15683016854', null, '15123213207', 'default', '', '秦浩');
INSERT INTO `customer` VALUES ('15683403674', null, '', 'default', '', '杨帆');
INSERT INTO `customer` VALUES ('15683421176', null, '', 'default', '', '王清');
INSERT INTO `customer` VALUES ('15723229585', null, '', 'default', '', '赵九洲');
INSERT INTO `customer` VALUES ('158 2960 9190', null, '', 'default', '', '王翌汀');
INSERT INTO `customer` VALUES ('15826198850', null, '', 'default', '', '42.7');
INSERT INTO `customer` VALUES ('15884265090', null, '', 'default', '', '四个二。');
INSERT INTO `customer` VALUES ('15902375204', null, '', 'default', '', '陈明伟');
INSERT INTO `customer` VALUES ('15922872774', null, '', 'default', '', '林耐奖');
INSERT INTO `customer` VALUES ('15922873159', null, '', 'default', '', '罗卓');
INSERT INTO `customer` VALUES ('15923570740', null, '', 'default', '', '卢婵婵');
INSERT INTO `customer` VALUES ('15926701304', null, '', 'default', '', '远建');
INSERT INTO `customer` VALUES ('15971325655', null, '', 'default', '', '细舅');
INSERT INTO `customer` VALUES ('15971335168', null, '', 'default', '', '涛哥');
INSERT INTO `customer` VALUES ('15971448661', null, '', 'default', '', '徐焕');
INSERT INTO `customer` VALUES ('15972820561', null, '', 'default', '', '王松');
INSERT INTO `customer` VALUES ('15991269303', null, '', 'default', '', '李岳苁');
INSERT INTO `customer` VALUES ('15997403952', null, '', 'default', '', '大姑爷');
INSERT INTO `customer` VALUES ('15998976089', null, '', 'default', '', '喻松');
INSERT INTO `customer` VALUES ('15998999571', null, '', 'default', '', '张浩');
INSERT INTO `customer` VALUES ('18007430257', null, '15923513964', 'default', '', '匡芝兰');
INSERT INTO `customer` VALUES ('18015730381', null, '', 'default', '', '南刚');
INSERT INTO `customer` VALUES ('18091854630', null, '', 'default', '', '高轩宇');
INSERT INTO `customer` VALUES ('18092239034', null, '18580185272', 'default', '', '李禹默');
INSERT INTO `customer` VALUES ('18223149740', null, '', 'default', '', '清');
INSERT INTO `customer` VALUES ('18270668873', null, '', 'default', '', '宋金花');
INSERT INTO `customer` VALUES ('18270918984', null, '', 'default', '', '杨立');
INSERT INTO `customer` VALUES ('18271545253', null, '', 'default', '', '奶奶');
INSERT INTO `customer` VALUES ('18289768350', null, '', 'default', '', '朱南');
INSERT INTO `customer` VALUES ('18298339150', null, '', 'default', '', '朱联。');
INSERT INTO `customer` VALUES ('183 9242 2653', null, '', 'default', '', '王昕钰');
INSERT INTO `customer` VALUES ('18502372957', null, '', 'default', '', '张林。');
INSERT INTO `customer` VALUES ('18509263859', null, '', 'default', '', '陈越之');
INSERT INTO `customer` VALUES ('18511599297', null, '18810543761', 'default', '', '耿屹川');
INSERT INTO `customer` VALUES ('18523540042', null, '', 'default', '', '饶梦云');
INSERT INTO `customer` VALUES ('186 0453 9801', null, '', 'default', '', '铭哥');
INSERT INTO `customer` VALUES ('186 8329 6797', null, '', 'default', '', '张挺');
INSERT INTO `customer` VALUES ('18602355214', null, '', 'default', '', '祝梦朝');
INSERT INTO `customer` VALUES ('18602743393', null, '', 'default', '', '大舅，');
INSERT INTO `customer` VALUES ('18623274109', null, '', 'default', '', '金路凯');
INSERT INTO `customer` VALUES ('18623401560', null, '', 'default', '', '房朴');
INSERT INTO `customer` VALUES ('18624053821', null, '', 'default', '', '占恒');
INSERT INTO `customer` VALUES ('18627034900', null, '', 'default', '', '陈昶');
INSERT INTO `customer` VALUES ('18677512018', null, '', 'default', '', '韩旭');
INSERT INTO `customer` VALUES ('18680812540', null, '', 'default', '', '程铭');
INSERT INTO `customer` VALUES ('18680821637', null, '', 'default', '', '李强强');
INSERT INTO `customer` VALUES ('18680967665', null, '', 'default', '', '睿子');
INSERT INTO `customer` VALUES ('18696569633', null, '', 'default', '', '金姐姐');
INSERT INTO `customer` VALUES ('18696950311', null, '', 'default', '', '王盛永');
INSERT INTO `customer` VALUES ('18710354662', null, '15829477335', 'default', '', '张煜');
INSERT INTO `customer` VALUES ('18710924165', null, '', 'default', '', '李书帆');
INSERT INTO `customer` VALUES ('18716451250', null, '', 'default', '', '柏顺 孔');
INSERT INTO `customer` VALUES ('18740411045', null, '', 'default', '', '张一哲');
INSERT INTO `customer` VALUES ('18761721315', null, '', 'default', '', '裴翠云');
INSERT INTO `customer` VALUES ('18766522340', null, '', 'default', '', '张慧明');
INSERT INTO `customer` VALUES ('18771639787', null, '', 'default', '', '娟姐');
INSERT INTO `customer` VALUES ('18792404514', null, '', 'default', '', '王力成');
INSERT INTO `customer` VALUES ('18801907031', null, '', 'default', '', '田序');
INSERT INTO `customer` VALUES ('18810541612', null, '', 'default', '', '吴江伟');
INSERT INTO `customer` VALUES ('18883367675', null, '', 'default', '', '冯慧茹');
INSERT INTO `customer` VALUES ('18883385482', null, '', 'default', '', '惠泽');
INSERT INTO `customer` VALUES ('18972756117', null, '', 'default', '', '无线网卡');
INSERT INTO `customer` VALUES ('18983424112', null, '', 'default', '', '宋桂宇');
INSERT INTO `customer` VALUES ('18995627130', null, '', 'default', '', '群哥。');
INSERT INTO `customer` VALUES ('4008308300', null, '', 'default', '', '华为客服');
INSERT INTO `customer` VALUES ('51137010', null, '', 'default', '', '汉口火车站服务台');
INSERT INTO `customer` VALUES ('610104199306180012', null, '', 'default', '', '身份证号');
INSERT INTO `customer` VALUES ('61251496', null, '', 'default', '', '重大财务处');
INSERT INTO `customer` VALUES ('616493', null, '13648316493', 'default', '', '陈帅（主席）');
INSERT INTO `customer` VALUES ('65103012', null, '', 'default', '', '学工办');
INSERT INTO `customer` VALUES ('65106523', null, '', 'default', '', '教工办');

-- ----------------------------
-- Table structure for `favorite`
-- ----------------------------
DROP TABLE IF EXISTS `favorite`;
CREATE TABLE `favorite` (
  `o_account` varchar(8) NOT NULL,
  `a_id` int(11) NOT NULL,
  PRIMARY KEY (`o_account`,`a_id`),
  KEY `FK_favorite2` (`a_id`),
  CONSTRAINT `FK_favorite` FOREIGN KEY (`o_account`) REFERENCES `operater` (`o_account`),
  CONSTRAINT `FK_favorite2` FOREIGN KEY (`a_id`) REFERENCES `app` (`a_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of favorite
-- ----------------------------

-- ----------------------------
-- Table structure for `inforcontent`
-- ----------------------------
DROP TABLE IF EXISTS `inforcontent`;
CREATE TABLE `inforcontent` (
  `con_id` int(11) NOT NULL,
  `infor_id` int(11) DEFAULT NULL,
  `con_title` varchar(20) DEFAULT NULL,
  `con_date` date DEFAULT NULL,
  `con_text` text,
  PRIMARY KEY (`con_id`),
  KEY `FK_inlist` (`infor_id`),
  CONSTRAINT `FK_inlist` FOREIGN KEY (`infor_id`) REFERENCES `infortype` (`infor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inforcontent
-- ----------------------------
INSERT INTO `inforcontent` VALUES ('1', '1', '逗你玩', '2014-09-04', '逗你玩那');
INSERT INTO `inforcontent` VALUES ('2', '2', '卖', '2014-09-12', '卖东西');
INSERT INTO `inforcontent` VALUES ('3', '1', '你妹', '2014-09-17', '你妹夫');
INSERT INTO `inforcontent` VALUES ('4', '2', '阿西吧', '2014-09-16', '爱惜把下巴');

-- ----------------------------
-- Table structure for `infortype`
-- ----------------------------
DROP TABLE IF EXISTS `infortype`;
CREATE TABLE `infortype` (
  `infor_id` int(11) NOT NULL,
  `infor_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`infor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of infortype
-- ----------------------------
INSERT INTO `infortype` VALUES ('1', '培训资料');
INSERT INTO `infortype` VALUES ('2', '营销信息');

-- ----------------------------
-- Table structure for `manager`
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `m_account` varchar(10) NOT NULL,
  `m_name` varchar(20) DEFAULT NULL,
  `m_phone` varchar(11) DEFAULT NULL,
  `m_email` varchar(30) DEFAULT NULL,
  `m_pwd` varchar(8) DEFAULT NULL,
  `m_permit` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`m_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `mes_id` int(11) NOT NULL,
  `b_id` int(11) DEFAULT NULL,
  `mes_name` varchar(20) DEFAULT NULL,
  `mes_content` varchar(140) DEFAULT NULL,
  PRIMARY KEY (`mes_id`),
  KEY `FK_push` (`b_id`),
  CONSTRAINT `FK_push` FOREIGN KEY (`b_id`) REFERENCES `busdevelop` (`b_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for `operater`
-- ----------------------------
DROP TABLE IF EXISTS `operater`;
CREATE TABLE `operater` (
  `o_account` varchar(8) NOT NULL,
  `o_name` varchar(20) DEFAULT NULL,
  `o_phone` varchar(11) DEFAULT NULL,
  `o_email` varchar(30) DEFAULT NULL,
  `o_pwd` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`o_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operater
-- ----------------------------
INSERT INTO `operater` VALUES ('chenzhuo', null, null, null, 'zouni');
INSERT INTO `operater` VALUES ('rmy', null, null, null, '1234');

-- ----------------------------
-- Table structure for `personalcus`
-- ----------------------------
DROP TABLE IF EXISTS `personalcus`;
CREATE TABLE `personalcus` (
  `o_account` varchar(8) NOT NULL,
  `phone_num1` varchar(50) NOT NULL,
  PRIMARY KEY (`o_account`,`phone_num1`),
  KEY `FK_personalcus2` (`phone_num1`),
  CONSTRAINT `FK_personalcus` FOREIGN KEY (`o_account`) REFERENCES `operater` (`o_account`),
  CONSTRAINT `FK_personalcus2` FOREIGN KEY (`phone_num1`) REFERENCES `customer` (`phone_num1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of personalcus
-- ----------------------------
INSERT INTO `personalcus` VALUES ('rmy', '+8613250740436');
INSERT INTO `personalcus` VALUES ('rmy', '+8615696123597');
INSERT INTO `personalcus` VALUES ('chenzhuo', '029-873-22616');
INSERT INTO `personalcus` VALUES ('chenzhuo', '029-875-71236');
INSERT INTO `personalcus` VALUES ('chenzhuo', '02983534515');
INSERT INTO `personalcus` VALUES ('chenzhuo', '02984250941');
INSERT INTO `personalcus` VALUES ('chenzhuo', '02984705782');
INSERT INTO `personalcus` VALUES ('chenzhuo', '02986258152');
INSERT INTO `personalcus` VALUES ('chenzhuo', '02987333448');
INSERT INTO `personalcus` VALUES ('chenzhuo', '02988151880');
INSERT INTO `personalcus` VALUES ('chenzhuo', '09112267400');
INSERT INTO `personalcus` VALUES ('rmy', '1 232-134-5433');
INSERT INTO `personalcus` VALUES ('rmy', '1 345-678-3222');
INSERT INTO `personalcus` VALUES ('rmy', '1 435-654-6565');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-315-207-5181');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-319-330-9448');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-329-917-0372');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-336-846-7038');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-338-498-6889');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-345-222-2211');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-351-234-1054');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-357-222-3758');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-357-227-0655');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-357-291-1655');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-360-136-4118');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-364-928-4421');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-366-767-4554');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-366-768-9473');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-375-996-1771');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-377-262-0897');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-389-184-9873');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-389-188-4097');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-399-103-1922');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-507-102-9636');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-511-186-9889');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-511-194-2275');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-511-195-3973');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-511-195-8173');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-512-308-3230');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-512-308-6377');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-521-503-0655');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-526-623-5359');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-552-922-8086');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-569-164-9000');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-582-358-6953');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-592-287-3159');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-599-897-6089');
INSERT INTO `personalcus` VALUES ('chenzhuo', '1-862-340-1560');
INSERT INTO `personalcus` VALUES ('rmy', '123423434433');
INSERT INTO `personalcus` VALUES ('rmy', '13002334569');
INSERT INTO `personalcus` VALUES ('rmy', '13016080103');
INSERT INTO `personalcus` VALUES ('rmy', '13035337186');
INSERT INTO `personalcus` VALUES ('rmy', '13080640131');
INSERT INTO `personalcus` VALUES ('rmy', '13100602933');
INSERT INTO `personalcus` VALUES ('rmy', '13125119189');
INSERT INTO `personalcus` VALUES ('rmy', '13163286862');
INSERT INTO `personalcus` VALUES ('rmy', '13167974360');
INSERT INTO `personalcus` VALUES ('rmy', '13175110170');
INSERT INTO `personalcus` VALUES ('rmy', '132 1725 3530');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13259727810');
INSERT INTO `personalcus` VALUES ('rmy', '13271923650');
INSERT INTO `personalcus` VALUES ('rmy', '133 8499 3034');
INSERT INTO `personalcus` VALUES ('rmy', '13368395014');
INSERT INTO `personalcus` VALUES ('rmy', '13409946771');
INSERT INTO `personalcus` VALUES ('rmy', '13412681095');
INSERT INTO `personalcus` VALUES ('rmy', '13437138723');
INSERT INTO `personalcus` VALUES ('rmy', '13452222211');
INSERT INTO `personalcus` VALUES ('rmy', '13469919544');
INSERT INTO `personalcus` VALUES ('rmy', '13476070457');
INSERT INTO `personalcus` VALUES ('rmy', '13476197195');
INSERT INTO `personalcus` VALUES ('rmy', '13476708258');
INSERT INTO `personalcus` VALUES ('rmy', '13476712666');
INSERT INTO `personalcus` VALUES ('rmy', '13477644322');
INSERT INTO `personalcus` VALUES ('rmy', '13500379593');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13508381688');
INSERT INTO `personalcus` VALUES ('rmy', '13512341054');
INSERT INTO `personalcus` VALUES ('rmy', '13512350741');
INSERT INTO `personalcus` VALUES ('rmy', '13527354649');
INSERT INTO `personalcus` VALUES ('rmy', '13527398817');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13572212909');
INSERT INTO `personalcus` VALUES ('rmy', '13594270343');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13608305419');
INSERT INTO `personalcus` VALUES ('rmy', '13608396361');
INSERT INTO `personalcus` VALUES ('rmy', '13608398016');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13609208825');
INSERT INTO `personalcus` VALUES ('rmy', '13618258353');
INSERT INTO `personalcus` VALUES ('rmy', '13618291584');
INSERT INTO `personalcus` VALUES ('rmy', '13618395301');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13629265991');
INSERT INTO `personalcus` VALUES ('rmy', '13636027880');
INSERT INTO `personalcus` VALUES ('rmy', '13636091917');
INSERT INTO `personalcus` VALUES ('rmy', '13637965689');
INSERT INTO `personalcus` VALUES ('rmy', '13637980685');
INSERT INTO `personalcus` VALUES ('rmy', '13640578586');
INSERT INTO `personalcus` VALUES ('rmy', '13647617022');
INSERT INTO `personalcus` VALUES ('rmy', '13657138552');
INSERT INTO `personalcus` VALUES ('rmy', '13657138741');
INSERT INTO `personalcus` VALUES ('rmy', '13667190378');
INSERT INTO `personalcus` VALUES ('rmy', '13667605104');
INSERT INTO `personalcus` VALUES ('rmy', '13667638462');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13669261169');
INSERT INTO `personalcus` VALUES ('rmy', '13670298591');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13679168285');
INSERT INTO `personalcus` VALUES ('rmy', '13687136504');
INSERT INTO `personalcus` VALUES ('rmy', '13701392446');
INSERT INTO `personalcus` VALUES ('rmy', '13713057043');
INSERT INTO `personalcus` VALUES ('rmy', '13717575582');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13720438005');
INSERT INTO `personalcus` VALUES ('rmy', '13724507953');
INSERT INTO `personalcus` VALUES ('rmy', '13752893103');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13759835522');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13759890366');
INSERT INTO `personalcus` VALUES ('rmy', '13782812642');
INSERT INTO `personalcus` VALUES ('rmy', '13872017658');
INSERT INTO `personalcus` VALUES ('rmy', '13883315369');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13891908803');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13891911819');
INSERT INTO `personalcus` VALUES ('rmy', '13972743930');
INSERT INTO `personalcus` VALUES ('chenzhuo', '13991888155');
INSERT INTO `personalcus` VALUES ('rmy', '15002379054');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15002916003');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15010317279');
INSERT INTO `personalcus` VALUES ('rmy', '15025438778');
INSERT INTO `personalcus` VALUES ('rmy', '15025439315');
INSERT INTO `personalcus` VALUES ('rmy', '15025613693');
INSERT INTO `personalcus` VALUES ('rmy', '15027070946');
INSERT INTO `personalcus` VALUES ('rmy', '15027081171');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15029012855');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15029016611');
INSERT INTO `personalcus` VALUES ('rmy', '15071678462');
INSERT INTO `personalcus` VALUES ('rmy', '15071738308');
INSERT INTO `personalcus` VALUES ('rmy', '15071767005');
INSERT INTO `personalcus` VALUES ('rmy', '15072848146');
INSERT INTO `personalcus` VALUES ('rmy', '15072848322');
INSERT INTO `personalcus` VALUES ('rmy', '15072855980');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15091325369');
INSERT INTO `personalcus` VALUES ('rmy', '15098009164');
INSERT INTO `personalcus` VALUES ('rmy', '151 1196 5355');
INSERT INTO `personalcus` VALUES ('chenzhuo', '151 1197 7616');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15105694993');
INSERT INTO `personalcus` VALUES ('rmy', '15111942275');
INSERT INTO `personalcus` VALUES ('rmy', '15111951665');
INSERT INTO `personalcus` VALUES ('rmy', '15111957933');
INSERT INTO `personalcus` VALUES ('rmy', '15111958173');
INSERT INTO `personalcus` VALUES ('rmy', '15111959316');
INSERT INTO `personalcus` VALUES ('rmy', '15111960397');
INSERT INTO `personalcus` VALUES ('rmy', '15111977616');
INSERT INTO `personalcus` VALUES ('rmy', '1511941404');
INSERT INTO `personalcus` VALUES ('rmy', '15123066737');
INSERT INTO `personalcus` VALUES ('rmy', '15123191369');
INSERT INTO `personalcus` VALUES ('rmy', '15123907480');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15129056960');
INSERT INTO `personalcus` VALUES ('rmy', '15163295392');
INSERT INTO `personalcus` VALUES ('rmy', '15171603314');
INSERT INTO `personalcus` VALUES ('rmy', '15171662841');
INSERT INTO `personalcus` VALUES ('rmy', '15171665011');
INSERT INTO `personalcus` VALUES ('rmy', '15172437130');
INSERT INTO `personalcus` VALUES ('rmy', '152 1119 2048');
INSERT INTO `personalcus` VALUES ('rmy', '152 7201 3637');
INSERT INTO `personalcus` VALUES ('rmy', '15201231070');
INSERT INTO `personalcus` VALUES ('rmy', '15201461975');
INSERT INTO `personalcus` VALUES ('rmy', '15207106211');
INSERT INTO `personalcus` VALUES ('rmy', '15213219410');
INSERT INTO `personalcus` VALUES ('rmy', '15215030655');
INSERT INTO `personalcus` VALUES ('rmy', '15260169160');
INSERT INTO `personalcus` VALUES ('rmy', '15275311073');
INSERT INTO `personalcus` VALUES ('rmy', '15295528366');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15319436626');
INSERT INTO `personalcus` VALUES ('rmy', '15334556990');
INSERT INTO `personalcus` VALUES ('rmy', '15337284010');
INSERT INTO `personalcus` VALUES ('rmy', '15377134406');
INSERT INTO `personalcus` VALUES ('rmy', '15377149063');
INSERT INTO `personalcus` VALUES ('rmy', '15377187877');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15389277411');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15523792749');
INSERT INTO `personalcus` VALUES ('rmy', '15527018765');
INSERT INTO `personalcus` VALUES ('rmy', '15527058780');
INSERT INTO `personalcus` VALUES ('rmy', '15527329681');
INSERT INTO `personalcus` VALUES ('rmy', '15527824935');
INSERT INTO `personalcus` VALUES ('rmy', '15527966568');
INSERT INTO `personalcus` VALUES ('rmy', '15549463360');
INSERT INTO `personalcus` VALUES ('rmy', '156 2349 5956');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15683016854');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15683403674');
INSERT INTO `personalcus` VALUES ('rmy', '15683421176');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15723229585');
INSERT INTO `personalcus` VALUES ('chenzhuo', '158 2960 9190');
INSERT INTO `personalcus` VALUES ('rmy', '15826198850');
INSERT INTO `personalcus` VALUES ('rmy', '15884265090');
INSERT INTO `personalcus` VALUES ('rmy', '15902375204');
INSERT INTO `personalcus` VALUES ('rmy', '15922872774');
INSERT INTO `personalcus` VALUES ('rmy', '15922873159');
INSERT INTO `personalcus` VALUES ('rmy', '15923570740');
INSERT INTO `personalcus` VALUES ('rmy', '15926701304');
INSERT INTO `personalcus` VALUES ('rmy', '15971325655');
INSERT INTO `personalcus` VALUES ('rmy', '15971335168');
INSERT INTO `personalcus` VALUES ('rmy', '15971448661');
INSERT INTO `personalcus` VALUES ('rmy', '15972820561');
INSERT INTO `personalcus` VALUES ('chenzhuo', '15991269303');
INSERT INTO `personalcus` VALUES ('rmy', '15997403952');
INSERT INTO `personalcus` VALUES ('rmy', '15998976089');
INSERT INTO `personalcus` VALUES ('rmy', '15998999571');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18007430257');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18015730381');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18091854630');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18092239034');
INSERT INTO `personalcus` VALUES ('rmy', '18223149740');
INSERT INTO `personalcus` VALUES ('rmy', '18270668873');
INSERT INTO `personalcus` VALUES ('rmy', '18270918984');
INSERT INTO `personalcus` VALUES ('rmy', '18271545253');
INSERT INTO `personalcus` VALUES ('rmy', '18289768350');
INSERT INTO `personalcus` VALUES ('rmy', '18298339150');
INSERT INTO `personalcus` VALUES ('chenzhuo', '183 9242 2653');
INSERT INTO `personalcus` VALUES ('rmy', '18502372957');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18509263859');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18511599297');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18523540042');
INSERT INTO `personalcus` VALUES ('rmy', '186 0453 9801');
INSERT INTO `personalcus` VALUES ('rmy', '186 8329 6797');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18602355214');
INSERT INTO `personalcus` VALUES ('rmy', '18602743393');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18623274109');
INSERT INTO `personalcus` VALUES ('rmy', '18623401560');
INSERT INTO `personalcus` VALUES ('rmy', '18624053821');
INSERT INTO `personalcus` VALUES ('rmy', '18627034900');
INSERT INTO `personalcus` VALUES ('rmy', '18677512018');
INSERT INTO `personalcus` VALUES ('rmy', '18680812540');
INSERT INTO `personalcus` VALUES ('rmy', '18680821637');
INSERT INTO `personalcus` VALUES ('rmy', '18680967665');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18696569633');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18696950311');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18710354662');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18710924165');
INSERT INTO `personalcus` VALUES ('rmy', '18716451250');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18740411045');
INSERT INTO `personalcus` VALUES ('rmy', '18761721315');
INSERT INTO `personalcus` VALUES ('rmy', '18766522340');
INSERT INTO `personalcus` VALUES ('rmy', '18771639787');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18792404514');
INSERT INTO `personalcus` VALUES ('rmy', '18801907031');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18810541612');
INSERT INTO `personalcus` VALUES ('rmy', '18883367675');
INSERT INTO `personalcus` VALUES ('chenzhuo', '18883385482');
INSERT INTO `personalcus` VALUES ('rmy', '18972756117');
INSERT INTO `personalcus` VALUES ('rmy', '18983424112');
INSERT INTO `personalcus` VALUES ('rmy', '18995627130');
INSERT INTO `personalcus` VALUES ('rmy', '4008308300');
INSERT INTO `personalcus` VALUES ('rmy', '51137010');
INSERT INTO `personalcus` VALUES ('chenzhuo', '610104199306180012');
INSERT INTO `personalcus` VALUES ('rmy', '61251496');
INSERT INTO `personalcus` VALUES ('chenzhuo', '616493');
INSERT INTO `personalcus` VALUES ('rmy', '65103012');
INSERT INTO `personalcus` VALUES ('rmy', '65106523');

-- ----------------------------
-- Table structure for `provinces`
-- ----------------------------
DROP TABLE IF EXISTS `provinces`;
CREATE TABLE `provinces` (
  `pro_id` int(11) NOT NULL,
  `pro_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of provinces
-- ----------------------------

-- ----------------------------
-- Table structure for `pusharea`
-- ----------------------------
DROP TABLE IF EXISTS `pusharea`;
CREATE TABLE `pusharea` (
  `b_id` int(11) NOT NULL,
  `pro_id` int(11) NOT NULL,
  PRIMARY KEY (`b_id`,`pro_id`),
  KEY `FK_pushArea2` (`pro_id`),
  CONSTRAINT `FK_pushArea` FOREIGN KEY (`b_id`) REFERENCES `busdevelop` (`b_id`),
  CONSTRAINT `FK_pushArea2` FOREIGN KEY (`pro_id`) REFERENCES `provinces` (`pro_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pusharea
-- ----------------------------

-- ----------------------------
-- Table structure for `readinfor`
-- ----------------------------
DROP TABLE IF EXISTS `readinfor`;
CREATE TABLE `readinfor` (
  `o_account` varchar(8) NOT NULL,
  `con_id` int(11) NOT NULL,
  PRIMARY KEY (`o_account`,`con_id`),
  KEY `FK_readInfor2` (`con_id`),
  CONSTRAINT `FK_readInfor` FOREIGN KEY (`o_account`) REFERENCES `operater` (`o_account`),
  CONSTRAINT `FK_readInfor2` FOREIGN KEY (`con_id`) REFERENCES `inforcontent` (`con_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of readinfor
-- ----------------------------

-- ----------------------------
-- Table structure for `request`
-- ----------------------------
DROP TABLE IF EXISTS `request`;
CREATE TABLE `request` (
  `re_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) DEFAULT NULL,
  `o_account` varchar(8) DEFAULT NULL,
  `m_account` varchar(10) DEFAULT NULL,
  `re_content` text,
  `re_response` text,
  PRIMARY KEY (`re_id`),
  KEY `FK_istype` (`type_id`),
  KEY `FK_reply` (`m_account`),
  KEY `FK_suggest` (`o_account`),
  CONSTRAINT `FK_istype` FOREIGN KEY (`type_id`) REFERENCES `requesttype` (`type_id`),
  CONSTRAINT `FK_reply` FOREIGN KEY (`m_account`) REFERENCES `manager` (`m_account`),
  CONSTRAINT `FK_suggest` FOREIGN KEY (`o_account`) REFERENCES `operater` (`o_account`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of request
-- ----------------------------
INSERT INTO `request` VALUES ('1', '2', 'rmy', null, '你好', null);
INSERT INTO `request` VALUES ('3', '2', 'rmy', null, 'nihao', null);
INSERT INTO `request` VALUES ('4', '3', 'rmy', null, 'nihao', null);
INSERT INTO `request` VALUES ('5', '1', 'rmy', null, '你好', null);

-- ----------------------------
-- Table structure for `requesttype`
-- ----------------------------
DROP TABLE IF EXISTS `requesttype`;
CREATE TABLE `requesttype` (
  `type_id` int(11) NOT NULL,
  `type_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of requesttype
-- ----------------------------
INSERT INTO `requesttype` VALUES ('1', '意见');
INSERT INTO `requesttype` VALUES ('2', '建议');
INSERT INTO `requesttype` VALUES ('3', '其他');

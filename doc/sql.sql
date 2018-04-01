/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.7.17-log : Database - lingshi_papermgr
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lingshi_papermgr` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lingshi_papermgr`;

/*Table structure for table `paper` */

DROP TABLE IF EXISTS `paper`;

CREATE TABLE `paper` (
  `paperId` varchar(20) NOT NULL COMMENT '主键',
  `paperTitle` varchar(500) NOT NULL DEFAULT '' COMMENT '试卷开头',
  `paperGroupId` varchar(20) NOT NULL DEFAULT '' COMMENT '试卷组别',
  `paperNote` varchar(10) NOT NULL DEFAULT '' COMMENT '试卷备注 如A:B',
  `paperTime` datetime NOT NULL COMMENT '创建时间',
  `subjectId` varchar(20) NOT NULL COMMENT '学科编号',
  PRIMARY KEY (`paperId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `paper` */

insert  into `paper`(`paperId`,`paperTitle`,`paperGroupId`,`paperNote`,`paperTime`,`subjectId`) values ('LG21916423760321','化学期中考试','','期中考试','2018-03-15 16:42:38','S60710052055613');

/*Table structure for table `paperquestion` */

DROP TABLE IF EXISTS `paperquestion`;

CREATE TABLE `paperquestion` (
  `paperQuestionId` varchar(20) NOT NULL COMMENT '主键',
  `paperId` varchar(20) NOT NULL COMMENT '试卷编号',
  `questionId` varchar(20) DEFAULT NULL COMMENT '问题编号',
  PRIMARY KEY (`paperQuestionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `paperquestion` */

insert  into `paperquestion`(`paperQuestionId`,`paperId`,`questionId`) values ('LG52616474700429','LG21916423760321','LG46216154208413'),('LG56716474700731','LG21916423760321','LG75017282269313'),('LG60616474700328','LG21916423760321','LG36816204925014'),('LG89116474700630','LG21916423760321','LG52516413697820'),('LG96916474700127','LG21916423760321','LG39015000911012');

/*Table structure for table `question` */

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
  `questionId` varchar(20) NOT NULL COMMENT '主键',
  `questionContent` varchar(500) NOT NULL DEFAULT '' COMMENT '题目',
  `questionSelect` varchar(300) NOT NULL DEFAULT '' COMMENT '选择题选项',
  `questionAnswer` varchar(2000) NOT NULL DEFAULT '' COMMENT '题目答案',
  `questionType` int(11) NOT NULL DEFAULT '0' COMMENT '题目类型 1.选择题(多选题) 2.填空 3解答',
  `subjectId` varchar(20) NOT NULL COMMENT '学科',
  `questionTime` datetime NOT NULL COMMENT '创建时间',
  `questionRatio` int(11) NOT NULL DEFAULT '3' COMMENT '难度系数',
  PRIMARY KEY (`questionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `question` */

insert  into `question`(`questionId`,`questionContent`,`questionSelect`,`questionAnswer`,`questionType`,`subjectId`,`questionTime`,`questionRatio`) values ('LG36816204925014','____杂志的纠错文献对外声明，某团队去年用核磁共振扫描了狗的大脑，发现狗是用左脑对语言进行处理的，这篇研究还得到了发表。然而，今年，团队研究人员意识到，人是躺着进去的，而狗是趴着进去的，所以左右脑搞反了','','《科学》',2,'S60710052055613','2018-03-12 16:20:49',3),('LG39015000911012','设小明有20头羊，小明是个船长，问小明现在几岁？','10,20,30,尼玛，有毒','D（解析：傻逼出的题目）',1,'S60710052055613','2018-03-12 15:00:09',2),('LG41815021328413','闽江学院谁最帅','小虎哥,李明镐,娇妹','A(解析，C是女的，不能用帅，B不是闽江学院的)',1,'S77010004594711','2018-03-12 15:02:13',3),('LG46216154208413','电玩巴士,中国综合__门户站,一直致力于发展中国__产业和____事业,提供游戏__下载,及时的游戏资讯,完整的游戏攻略,及时的游戏视频攻略,以及打造拥有数千万会员','','游戏,电玩,网络游戏,免费',2,'S60710052055613','2018-03-12 16:15:42',3),('LG52516413697820','<p>往①中加入铁屑至浸出液显紫色，此时溶液仍呈强酸性。该过程中有如下反应发生。 2Fe3＋ ＋Fe === 3Fe2＋&nbsp; &nbsp;2TiO2＋ (无色) ＋Fe＋4H＋&nbsp; === 2Ti3＋ (紫色) ＋Fe2＋ ＋2H2O Ti3＋ (紫色) ＋Fe3＋ ＋H2O ===TiO2＋ (无色) ＋Fe2＋ ＋2H＋&nbsp; &nbsp;（2）在②→③工艺中需要控制条件以形成TiO2·n H2O溶胶，该分散质颗粒直径大小在_____________范围。 （3）若把③中制得的固体TiO2·n H2O用酸清洗除去其中的Fe (OH)3杂质，还可制得钛白粉。已知25</p><p>℃时， ,该温度下反应Fe (OH)3＋3H</p><p>＋ Fe3＋&nbsp; ＋H2O的平衡常数K=_____________。&nbsp;</p>','','',3,'S60710052055613','2018-03-15 16:41:37',3),('LG75017282269313','<p><span>题主物理和数学都还不错,</span><span>化学</span><span>基础也比较良好,小题一般都可以全对。但是到</span><span>大题</span><span>里,题目给出一堆未知或者没有学过的物质,让我判断反应物生成物或者写</span><span>化学<img src=\"http://localhost:8091/WebContent/Contents/lib/layui/images/face/0.gif\" alt=\"[微笑]\"></span></p><p><span><img src=\"http://192.168.20.120:8091/WebContent/Contents/lib/layui/images/face/25.gif\" alt=\"[抱抱]\"><br></span></p>','','',3,'S60710052055613','2018-03-12 17:28:23',3),('LG99516215870715','有天他qq收到一个__申请，验证消息上写的是:哥哥加我，我是妹妹，他以为是性骚扰，就没加，直接回了一句:我喜欢少妇过了一会儿，他姑姑就给他打了个电话，说:你妹妹qq上加你，你怎么不同意，她想问你几道数学题，你说你喜欢少妇','','好友',2,'S60710052055613','2018-03-12 16:21:59',3);

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `subjectId` varchar(20) NOT NULL COMMENT '主键',
  `subjectName` varchar(20) NOT NULL DEFAULT '' COMMENT '学科名称',
  `subjectTime` datetime NOT NULL COMMENT '创建时间',
  `userId` varchar(20) NOT NULL COMMENT '用户编号',
  PRIMARY KEY (`subjectId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `subject` */

insert  into `subject`(`subjectId`,`subjectName`,`subjectTime`,`userId`) values ('S60710052055613','化学','2018-03-12 10:05:21','A92916433644115'),('S77010004594711','数学','2018-03-12 10:00:46','A92916433644115'),('S89110051568112','物理','2018-03-12 10:05:16','A92916433644115');

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `userId` varchar(20) NOT NULL COMMENT '主键，用户编号',
  `userName` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(40) NOT NULL DEFAULT '' COMMENT '密码',
  `realname` varchar(10) NOT NULL DEFAULT '' COMMENT '姓名',
  `headImgUrl` varchar(100) NOT NULL DEFAULT '' COMMENT '用户头像',
  `userType` int(11) NOT NULL DEFAULT '1' COMMENT '-1系统默认用户 1教师',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `userinfo` */

insert  into `userinfo`(`userId`,`userName`,`password`,`realname`,`headImgUrl`,`userType`,`createTime`) values ('A18916445513817','mingming','202CB962AC59075B964B07152D234B70','明明','/Uploadfile/userheads/LG13416445377716.png',1,'2018-03-09 16:44:55'),('A92916433644115','wangli','202CB962AC59075B964B07152D234B70','王丽','/Uploadfile/userheads/LG17616431763114.png',1,'2018-03-09 16:43:36'),('UROOT','root','C4CA4238A0B923820DCC509A6F75849B','管理员','/Uploadfile/userheads/LG15016071069311.png',-1,'2018-03-08 14:14:41');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

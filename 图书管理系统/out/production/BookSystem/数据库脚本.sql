﻿-- 删除数据库
DROP DATABASE BookSystem;

-- 创建数据库
CREATE DATABASE BookSystem;

-- 使用数据库
USE BookSystem;

-- 删除数据表

-- 创建数据表
-- 1.创建管理员表
      CREATE TABLE admin (
         aid                  VARCHAR(50) NOT NULL,
         password             VARCHAR(32),
         lastdate             DATETIME,
         flag                 INT,
         status               INT,
         CONSTRAINT pk_aid PRIMARY KEY(aid)
       );

--2.用户表
      CREATE TABLE member (
         mid                  VARCHAR(50) NOT NULL,
         name                 VARCHAR(50),
         age                  INT,
         sex                  INT,
         phoneNumber          VARCHAR(32),
         CONSTRAINT pk_mid PRIMARY KEY (mid)
      );

--3.类别表
      CREATE TABLE item (
         iid                  INT NOT NULL AUTO_INCREMENT,
         name                 VARCHAR(100),
         note                 TEXT,
         CONSTRAINT pk_iid PRIMARY KEY(iid)
      );

--4.图书详情表
      CREATE TABLE books (
        bid                  INT NOT NULL  AUTO_INCREMENT,
        aid                  VARCHAR(50),
        iid                  INT,
        name                 VARCHAR(100),
        credate              DATETIME,
        note                 TEXT,
        status               INT,
        CONSTRAINT pk_bid PRIMARY KEY(bid),
        CONSTRAINT fk_iid FOREIGN KEY (iid) REFERENCES item(iid) ON DELETE CASCADE,
        CONSTRAINT fk_aid FOREIGN KEY (aid) REFERENCES admin(aid) ON DELETE CASCADE
      );
--5.购买详情表
    CREATE TABLE purchase(
        pid                  INT NOT NULL AUTO_INCREMENT,
        bid                  INT,
        aid                  VARCHAR(50),
        credate              DATETIME,
        price			INT,
	status			INT,
	note			TEXT

      );

--6. 增加测试数据
--管理员id:admin
--管理员密码:admin
  INSERT  INTO admin(aid,password,flag,status)VALUES ('admin','F6FDFFE48C908DEB0F4C3BD36C032E72',1,1);

-- 提交
COMMIT;
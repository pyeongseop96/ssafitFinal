-- drop DATABASE ssafit;
create DATABASE ssafit;
use ssafit;

drop table videos;
CREATE TABLE if not exists videos(
  userID varchar(50) NOT NULL key,
  password varchar(50) NOT NULL,
  name varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  age int NOT NULL
);

drop table users;
CREATE TABLE if not exists users (
  videoID int NOT NULL key,
  title varchar(50) NOT NULL,
  channelName varchar(50) NOT NULL,
  viewCnt varchar(50) NOT NULL DEFAULT 0,
  partInfo varchar(50) NOT NULL
);

drop table reviews;
CREATE TABLE if not exists reviews (
  reviewID int AUTO_INCREMENT key NOT NULL,
  videoID int NOT NULL,
  userID varchar(50) NOT NULL,
  title varchar(50) NOT NULL,
  content varchar(50) NOT NULL,
  regDate TIMESTAMP DEFAULT NOW() NOT NULL,
  reviewCnt int DEFAULT 0
);
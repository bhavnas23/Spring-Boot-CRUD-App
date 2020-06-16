 CREATE DATABASE `cardb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
 CREATE TABLE `cardetails` (
    `CarID` varchar(12) NOT NULL,
    `CarName` varchar(50) DEFAULT NULL,
    `ModelName` varchar(50) DEFAULT NULL,
    `ManufactureYear` char(4) DEFAULT NULL,
    `color` char(20) DEFAULT NULL,
    `ManufactureName` varchar(50) DEFAULT NULL,
    PRIMARY KEY (`CarID`)
  ) ;
  
  create table users (
      username varchar(50) not null primary key,
      password varchar(120) not null,
      enabled boolean not null
  );

   create table authorities(
       username varchar(50) not null,
       role varchar(50) not null,
       foreign key (username) references users (username)
   );

   insert into users(username, password, enabled)values('admin1','$2a$10$69UiTvvPC1B7c2uWDLKD3u5AII2UZ8we2LYbMSpmxFdoVUtDUFF.G',true);

  insert into authorities(username,role)values('admin1','ROLE_ADMIN');

  insert into users(username, password, enabled)values('user1','user1',true);

  insert into authorities(username,role)values('user1','ROLE_USER');
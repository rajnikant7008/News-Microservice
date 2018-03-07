CREATE DATABASE IF NOT EXISTS newsdb;

DROP TABLE IF EXISTS news;
CREATE TABLE IF NOT EXISTS news (
     id int NOT NULL AUTO_INCREMENT,
     headline varchar(255) DEFAULT '',
     author varchar(255)  DEFAULT '',
     title varchar(255) NOT NULL DEFAULT '',
     description text NOT NULL DEFAULT '',
     url varchar(255) DEFAULT NULL,
     source_id varchar(255) DEFAULT NULL,
     source_name varchar(255) DEFAULT NULL,
     url_to_image varchar(255) DEFAULT NULL,
     published_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
     updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
    ) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;





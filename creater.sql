CREATE DATABASE PhoneBookDB CHARACTER SET utf8 COLLATE utf8_general_ci;

USE PhoneBookDB;

CREATE TABLE Phone_Book ( id int(10) unsigned NOT NULL AUTO_INCREMENT, FIRSTNAME varchar (50) NOT NULL, LASTNAME varchar (50) NOT NULL, 
EMAIL varchar (50) NOT NULL, PHONE varchar (50) NOT NULL, PRIMARY KEY (ID) );

INSERT INTO Phone_Book(FIRSTNAME, LASTNAME, EMAIL, PHONE) values('firstname1','lastname1', 'email1', 'phone1');
	
  	
  	
  	
	
  	
  	
  	
 

  
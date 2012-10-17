
USE enappwebshop;

-- first delete all data
DELETE FROM purchaseitem WHERE id >=0;
DELETE FROM purchase WHERE id >=0;
DELETE FROM product WHERE id >=0;
DELETE FROM customer WHERE id >=0;

START TRANSACTION;

-- insert some products
INSERT INTO product (id,name,description,mediapath,unitprice)
VALUES (1,'Track 1','some music','...',1);
INSERT INTO product (id,name,description,mediapath,unitprice)
VALUES (2,'Track 2','some music','...',4);
INSERT INTO product (id,name,description,mediapath,unitprice)
VALUES (3,'Track 3','some music','...',6);
INSERT INTO product (id,name,description,mediapath,unitprice)
VALUES (4,'Track 4','some music','...',8);
INSERT INTO product (id,name,description,mediapath,unitprice)
VALUES (5,'Track 5','some music','...',10);

-- insert some users
INSERT INTO customer(id,username, password,name,address,email)
VALUES(1, 'dude','dude','Dude','somewhere','dude@duderino.ch');
INSERT INTO customer(id,username, password,name,address,email)
VALUES(2, 'donny','donny','Donny','somewhere','donny@duderino.ch');

-- insert a purchase with items
INSERT INTO purchase(id,customerid,datetime,status)
VALUES(1,1,now(), 'New');
INSERT INTO purchaseitem(purchaseid,productid,quantity,unitprice,lineamount,description)
VALUES(1,1,3,1,4,'some music...');
INSERT INTO purchaseitem(purchaseid,productid,quantity,unitprice,lineamount,description)
VALUES(1,3,3,1,4,'some music...');

INSERT INTO customergroups (username, groupname)
VALUES ('dude', 'users');
INSERT INTO customergroups (username, groupname)
VALUES ('donny', 'users');

COMMIT
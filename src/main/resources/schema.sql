DROP TABLE IF EXISTS ITEM;
DROP TABLE IF EXISTS AUCTION_CLIENT;
DROP TABLE IF EXISTS LOT;
DROP TABLE IF EXISTS LOT_HISTORY;
DROP TABLE IF EXISTS CATEGORY;

CREATE TABLE ITEM (
       ID INT NOT NULL AUTO_INCREMENT
     , TITLE VARCHAR(40) NOT NULL
     , DESCRIPTION VARCHAR(160) NOT NULL
     , WIDTH DECIMAL(10, 3)
     , HEIGHT DECIMAL(10, 3)
     , WEIGHT DECIMAL(10, 3)
     , VERSION INT NOT NULL DEFAULT 0
     , UNIQUE UQ_ITEM_1 (TITLE)
     , PRIMARY KEY (ID)
);

CREATE TABLE AUCTION_CLIENT (
       ID INT NOT NULL AUTO_INCREMENT
     , LOGIN VARCHAR(16) NOT NULL
     , NAME VARCHAR(16) NOT NULL
     , LAST_NAME VARCHAR(16)
     , DELIVERY_ADDRESS VARCHAR(150) NOT NULL
     , CONTACT_PHONE VARCHAR(14) NOT NULL
     , UNIQUE UQ_USER_1 (LOGIN)
     , UNIQUE UQ_USER_2 (CONTACT_PHONE)
     , PRIMARY KEY (ID)
);

CREATE TABLE LOT (
       ID INT NOT NULL AUTO_INCREMENT
     , ITEM_ID INT NOT NULL
     , OWNER_ID INT NOT NULL
     , LAST_UPDATE DATETIME
     , START_PRICE DECIMAL(10, 2)
     , BUYER_ID INT NOT NULL
     , CURRENT_PRICE DECIMAL(10, 2)
     , DATE_END DATETIME
     , PRIMARY KEY (ID)
     , CONSTRAINT FK_LOT_ITEM FOREIGN KEY (ITEM_ID)
                  REFERENCES ITEM (ID)
     , CONSTRAINT FK_USER_OWNER FOREIGN KEY (OWNER_ID)
                  REFERENCES AUCTION_CLIENT (ID)
     , CONSTRAINT FK_USER_BUYER FOREIGN KEY (BUYER_ID)
                  REFERENCES AUCTION_CLIENT (ID)
);

CREATE TABLE LOT_HISTORY (
       ID INT NOT NULL AUTO_INCREMENT
     , LOT_ID INT NOT NULL
     , BUYER_ID INT NOT NULL
     , PRICE DECIMAL(10, 2)
     , CHANGE_DATE DATETIME
     , PRIMARY KEY (ID)
     , CONSTRAINT FK_HIST_LOT FOREIGN KEY (LOT_ID)
                  REFERENCES LOT (ID)
     , CONSTRAINT FK_HIST_BUYER FOREIGN KEY (BUYER_ID)
                  REFERENCES AUCTION_CLIENT (ID)
);


CREATE TABLE CATEGORY (
       ID INT NOT NULL AUTO_INCREMENT
     , TITLE VARCHAR(20)
     , DESCRIPTION VARCHAR(160)
     , UNIQUE UQ_CATEGORY_1 (TITLE)
     , PRIMARY KEY (ID)
);

CREATE TABLE ITEM_CATEGORY (
       ITEM_ID INT NOT NULL
     , CATEGORY_ID INT NOT NULL
     , PRIMARY KEY (ITEM_ID, CATEGORY_ID)
     , CONSTRAINT FK_ITEM_CATEGORY_1 FOREIGN KEY (ITEM_ID)
                  REFERENCES ITEM (ID) ON DELETE CASCADE
     , CONSTRAINT FK_ITEM_CATEGORY_2 FOREIGN KEY (CATEGORY_ID)
                  REFERENCES CATEGORY (ID) ON DELETE CASCADE
);


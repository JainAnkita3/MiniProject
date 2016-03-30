CREATE TABLE IF NOT EXISTS PRODUCT (
        PRODUCT_ID               INT NOT NULL AUTO_INCREMENT UNIQUE,
        PRODUCT_NAME             VARCHAR(20) NOT NULL,
        MANUFACTURER             VARCHAR(32),
        SELLING_PRICE            NUMERIC(10,2) NOT NULL,
        STOCK                    VARCHAR(20) NOT NULL,
        PRIMARY KEY              (PRODUCT_ID) 
);


CREATE TABLE IF NOT EXISTS CUSTOMER  -- Customer and Address has one to many relationship
  (
     CUSTOMER_ID           INT NOT NULL AUTO_INCREMENT UNIQUE,
     FIRST_NAME            VARCHAR(20) NOT NULL,
     LAST_NAME             VARCHAR(20),
     PASSWORD              CHAR(4) NOT NULL,
     ADDRESS               VARCHAR(20),
     EMAIL                 VARCHAR(55) NOT NULL,  -- Email verification needs to send to Customer for first time login OR Password reset link
     PH_NUMBER             INT,
     PRIMARY KEY           (CUSTOMER_ID)
  );


CREATE TABLE IF NOT EXISTS CART  -- Orders also having same primary key
  (
     ORDER_ID               INT NOT NULL AUTO_INCREMENT UNIQUE,
     CUSTOMER_ID            INT NOT NULL,
     PRODUCT_ID             INT NOT NULL,
     QUANTITY_ORDERED		VARCHAR(20) NOT NULL,   -- Inserted by User
     C_PROD_PRICE           NUMERIC(10,2) NOT NULL, -- DIRECTLY FROM JAVA CODE CONSTRAINT (PRODUCT.SELLING_PRICE * CART.QUANTITY_ORDERED),  2*2.99
     TOTAL_AMOUNT           NUMERIC(10,2) NOT NULL, -- Total payable amount, Calculated from JAVA code.
     PRIMARY KEY            (ORDER_ID),             -- Need to insert SELLING_PRICE data/values in C_PROD_PRICE column too from JAVA --FOREIGN KEY(C_PROD_PRICE) REFERENCES PRODUCT(SELLING_PRICE) ON DELETE CASCADE 
     FOREIGN KEY            (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID) ON DELETE CASCADE,
     FOREIGN KEY            (PRODUCT_ID) REFERENCES PRODUCT(PRODUCT_ID) ON DELETE CASCADE
  );
  
  
CREATE TABLE IF NOT EXISTS ORDERS  -- Just for History mainteance for later use i.e. after placing order, Tracking purpose
  (
     ORDER_ID               INT NOT NULL,
     CUSTOMER_ID            INT NOT NULL,
     ORDER_DATE             TIMESTAMP(6) NOT NULL,
     QUANTITY_ORDERED		VARCHAR(20) NOT NULL,    -- Just for the history maintinance to show to customer , Insert it from Java Code
     C_PROD_PRICE           NUMERIC(10,2) NOT NULL,  -- Just for the history maintinance to show to customer, Price of Particular product  as per quantity
     TOTAL_AMOUNT           NUMERIC(10,2) NOT NULL,  -- Just for the history maintinance to show to customer, Total amount payable
     STATUS                 VARCHAR(20),             -- Need to insert from JAVA code.
     TRACKING_NUM           VARCHAR(32),             -- Need to insert from JAVA code.
     CHECK (STATUS IN ('SHIPPED','PROCESS', 'DELIVERED')),
     PRIMARY KEY            (ORDER_ID),
     FOREIGN KEY            (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID) ON DELETE CASCADE,
     FOREIGN KEY            (ORDER_ID) REFERENCES CART(ORDER_ID) ON DELETE CASCADE
  );



CREATE TABLE IF NOT EXISTS ADDRESSES  -- Customer and Address has one to many relationship
  (
     ADDRESS_ID             INT NOT NULL AUTO_INCREMENT UNIQUE,
     CUSTOMER_ID            INT NOT NULL,
     STREET_NAME            VARCHAR(20),
     CITY                   VARCHAR(20),
     APT                    CHAR(8),
     ADDRESS_TYPE           VARCHAR(20)  DEFAULT 'BILLING',
     CHECK (ADDRESS_TYPE IN ('BUSSINESS','RESI', 'BILLING', 'OTHER')),
     PRIMARY KEY            (ADDRESS_ID),
     FOREIGN KEY            (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID) ON DELETE CASCADE
       );
     

CREATE TABLE IF NOT EXISTS CARDDETAILS   -- Payment Mode --> COD, CreditCard, DebitCard, InternetBanking
  (
     CUSTOMER_ID            INT NOT NULL,
     CARD_NUMBER            VARCHAR(20) NOT NULL,
     PIN                    CHAR(4),
     CARD_EXPIRY_DTM        TIMESTAMP(6) NOT NULL,
     CARD_TYPE              VARCHAR(20) NOT NULL, 
     CHECK (ADDRESS_TYPE IN ('MASTER','VISA', 'DEBIT')),
     BANK_NAME              VARCHAR(20),
     AMOUNT                 NUMERIC(15,2) NOT NULL,   -- TOTAL PAYMENT DUE -->DIRECTLY FROM JAVA CODE -- Card.TOTAL_AMOUNT
     PRIMARY KEY            (CARD_NUMBER),
     FOREIGN KEY            (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID) ON DELETE CASCADE
  );

CREATE TABLE IF NOT EXISTS CUSTOMERADDRESS  -- IF WE REALLY NEED IT, One to Many relationship
  (
     CUSTOMER_ID           INT NOT NULL,
     ADDRESS_ID            INT NOT NULL,
     FOREIGN KEY           (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID) ON DELETE CASCADE
     FOREIGN KEY           (ADDRESS_ID) REFERENCES ADDRESSES(ADDRESS_ID) ON DELETE CASCADE
  );


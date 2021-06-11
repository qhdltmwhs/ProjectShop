DROP TABLE jumun_detail CASCADE CONSTRAINTS;
DROP TABLE jumun CASCADE CONSTRAINTS;
DROP TABLE cart CASCADE CONSTRAINTS;
DROP TABLE product CASCADE CONSTRAINTS;
DROP TABLE userinfo CASCADE CONSTRAINTS;

CREATE TABLE userinfo(
		userId                        		VARCHAR2(100)		 NULL 		 PRIMARY KEY,
		password                      		VARCHAR2(100)		 NULL ,
		name                          		VARCHAR2(100)		 NULL ,
		email                         		VARCHAR2(100)		 NULL 
);


CREATE TABLE product(
		p_no                          		NUMBER(10)		 NULL 		 PRIMARY KEY,
		p_name                        		VARCHAR2(50)		 NOT NULL,
		p_price                       		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		p_image                       		VARCHAR2(100)		 DEFAULT 'images/no_image.jpg'		 NOT NULL,
		p_desc                        		VARCHAR2(200)		 NULL ,
		p_click_count                 		NUMBER(10)		 DEFAULT 0		 NOT NULL
);

DROP SEQUENCE product_p_no_SEQ;

CREATE SEQUENCE product_p_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

/**********************************/
/* Table Name: cart */
/**********************************/
CREATE TABLE cart(
		cart_no                       		NUMBER(10)		 NULL ,
		cart_qty                      		NUMBER(10)		 DEFAULT 1		 NULL ,
		p_no                          		NUMBER(10)		 NULL ,
		userId                        		VARCHAR2(100)		 NULL 
);

DROP SEQUENCE cart_cart_no_SEQ;

CREATE SEQUENCE cart_cart_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

/**********************************/
/* Table Name: jumun */
/**********************************/
CREATE TABLE jumun(
		j_no                          		NUMBER(10)		 NULL ,
		j_desc                        		VARCHAR2(100)		 NULL ,
		j_date                        		DATE		 NULL ,
		j_price                       		NUMBER(10)		 NULL ,
		userId                        		VARCHAR2(100)		 NULL 
);

DROP SEQUENCE jumun_j_no_SEQ;

CREATE SEQUENCE jumun_j_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;

/**********************************/
/* Table Name: jumun_detail */
/**********************************/
CREATE TABLE jumun_detail(
		jd_no                         		NUMBER(10)		 NULL ,
		jd_qty                        		NUMBER(10)		 NULL ,
		j_no                          		NUMBER(10)		 NULL ,
		p_no                          		NUMBER(10)		 NULL 
);

DROP SEQUENCE jumun_detail_jd_no_SEQ;

CREATE SEQUENCE jumun_detail_jd_no_SEQ NOMAXVALUE NOCACHE NOORDER NOCYCLE;
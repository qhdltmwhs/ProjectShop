--userinfo insert 유저3명
insert into userinfo(userid, password, name, email) values('guard1', '1111', '김경호1', 'guard1@korea.com');
insert into userinfo(userid, password, name, email) values('guard2', '2222', '김경호2', 'guard2@korea.com');
insert into userinfo(userid, password, name, email) values('guard3', '3333', '김경호3', 'guard3@korea.com');

--product insert 제품3개
insert into product values(product_p_no_SEQ.nextval, '비글', 550000, 'bigle.gif', '기타 상세 정보 등...', 0);
insert into product values(product_p_no_SEQ.nextval, '달마시안', 500000, 'dalma.gif', '기타 상세 정보 등...', 0);
insert into product values(product_p_no_SEQ.nextval, '퍼그', 400000, 'pug.gif', '기타 상세 정보 등...', 0);
insert into product values(product_p_no_SEQ.nextval, '페키니즈', 450000, 'pekiniz.gif', '기타 상세 정보 등...', 0);

--userinfo select 유저1
--userinfo select 유저3
select userid, password, name, email from userinfo where userid = 'guard1';
select userid, password, name, email from userinfo;

--userinfo update
update  userinfo set password='1318', name='모네', email='mone@mone.com' where userid = 'mone';
--userinfo delete
delete from userinfo where userid = 'mone';

--product select 제품1 
--product select 제품3
select p_no, p_name, p_price, p_image, p_desc, p_click_count from product where p_no = 1;
select p_no, p_name, p_price, p_image, p_desc, p_click_count from product;

--로그인한 유저 cart에 제품3종류 담기(insert)
insert into cart values (cart_cart_no_SEQ.nextval, 2, 1, 'guard1');
insert into cart values (cart_cart_no_SEQ.nextval, 1, 2, 'guard1');
insert into cart values (cart_cart_no_SEQ.nextval, 3, 3, 'guard1');

insert into cart values (cart_cart_no_SEQ.nextval, 1, 1, 'guard2');
insert into cart values (cart_cart_no_SEQ.nextval, 1, 2, 'guard2');

--로그인한 유저 cat에 제품존재여부 (userid, p_no) 
select  count(*) from cart where userid='guard1' and p_no=2;
select  count(*) from cart where userid='guard2' and p_no=4;

--로그인한 유저 cart에 있는 제품 1개 셀렉트 (유저정보, 제품정보)
select * from cart c join product p on c.p_no = p.p_no join userinfo u on c.userid = u.userid  where u.userid = 'guard1';
select * from cart c join product p on c.p_no = p.p_no join userinfo u on c.userid = u.userid  where u.userid = 'guard2';

--로그인한 유저 cart에 있는 제품 전체 셀렉트 (유저정보, 제품정보)
select * from cart where userid = 'guard1' 

--로그이한 유저 cart에 제품 update
update cart set cart_qty = cart_qty + 1 where userid = 'guard1' and p_no = 2;

--로그인한 유저 cart에 있는 제품 전체 delete
--delete from cart where userid = 'guard1';

--로그인한 유저 cart에 있는 제품 품목한개  delete
--delete from cart where cart_no=1 ;

/**********************jumun insert****************************/
--guard1 님이 1번제품 2개와 2번제품3개를 주문
desc jumun
desc jumun_detail
--insert jumun
--insert jumun_detail
--insert jumun_detail
insert into jumun values (jumun_j_no_seq.nextval, '비글외1종', sysdate, 2600000, 'guard1');
insert into jumun_detail values (jumun_detail_jd_no_seq.nextval, 2, jumun_j_no_seq.currval, 1);
insert into jumun_detail values (jumun_detail_jd_no_seq.nextval, 3, jumun_j_no_seq.currval, 2);

--guard2 님이 1번제품 1개 ,2번제품 1개와 4번제품2개를 주문
--insert jumun
--insert jumun_detail
--insert jumun_detail
--insert jumun_detail
insert into jumun(j_no, j_desc, j_date, j_price, userid) values(jumun_j_no_seq.nextval, '비글외2종', sysdate, 99000000, 'guard2');
insert into jumun_detail(jd_no, jd_qty, j_no, p_no) values(jumun_jd_no_seq.nextval, 1, jumun_j_no_seq.currval, 1);  
insert into jumun_detail(jd_no, jd_qty, j_no, p_no) values(jumun_jd_no_seq.nextval, 1, jumun_j_no_seq.currval, 2);  
insert into jumun_detail(jd_no, jd_qty, j_no, p_no) values(jumun_jd_no_seq.nextval, 2, jumun_j_no_seq.currval, 4);


/**********************jumun select****************************/
--guard1님 주문목록
select * from jumun where userid = 'guard1';
--guard1님 주문1개 상세(제품정보,주문자정보)
select * from jumun j
join jumun_detail jd on j.j_no = jd.j_no 
join product p on jd.p_no = p.p_no 
join userinfo u on j.userid = u.userid 
where j.userid = 'guard1' and j.j_no = 1;

commit;
--rollback
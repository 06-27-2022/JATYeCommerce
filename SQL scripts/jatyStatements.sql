--drop all tables (if you want to start over with your tables)
drop table jatyaccount ,jatyproduct ,jatytag ,jatyproducttotag, jatyreview, jatywallet;

--JATYACCOUNT
--get all accounts
select * from jatyaccount;
--add account
insert into jatyaccount values(default,'name2','pass2','test','testcity','teststate');
--delete account
delete from jatyaccount where id=1;

--JATYPRODUCT
--get all products
select * from jatyproduct;
--get all products from account where id=?
select * from jatyproduct where accountid = 1;
--get seller address
select city,state from jatyaccount where id=2;
--get all products + seller address
select * from jatyproduct left join jatyaccount on jatyproduct.accountid =jatyaccount.id;
--get all products + seller address where id=?
select * from jatyproduct left join jatyaccount on jatyproduct.accountid =jatyaccount.id where accountid=2;
--add product to table(don't forget to add jatytags and jatyproducttotag)
insert into jatyproduct values (default,2,null,'test product',10,1234.12);
insert into jatyproduct values (default,2,null,'test product2',1,1.23);
--get ban status of product
select ban from (select productid,tagid,tag,ban from jatytag left join jatyproducttotag on jatytag.id = jatyproducttotag.tagid) as foo where productid=1 order by ban desc limit 1;
--get tags of a product with an id of ?
	--in this case the id=1
select * from jatytag where id in (select tagid from jatyproducttotag where productid=1);


--JATYTAG
--get all tags
select * from jatytag order by id asc;
--get product id of the products with tags (?,?,...)
	--the "count"=? must be equivalent to the size of the array of tags we are searching
	--in this case, the array of tags is ('asdf1','asdf2') and the size of the array is 2
select productid from (select productid, count(*) from (select productid,tagid,tag from jatytag left join jatyproducttotag on jatytag.id = jatyproducttotag.tagid) as foo 
where tag in ('asdf1','asdf3') group by productid) as foo where "count"=2;
--set ban status of a tag by tagname
update jatytag set ban=true where tag='asdf1';
--set ban status of a tag by id
update jatytag set ban=true where id=1;

--add tags to tag table
insert into jatytag values (default,'asdf1',false);
insert into jatytag values (default,'asdf2',false);
insert into jatytag values (default,'asdf3',false);


--JATYPRODUCTTOTAG
--get all productid to tagid relationships table
select * from jatyproducttotag order by productid asc;
--add productid to tagid relationship table
insert into jatyproducttotag values(1,1);
insert into jatyproducttotag values(1,1),(1,2),(1,3),(2,1),(2,3);


--JATYWALLET
--get all wallets
select * from jatywallet;
--get wallet of account wher id=?
select * from jatywallet where accountid=2;
--add wallet to table
insert into jatywallet values(default,2,1234.56);

select * from mk_provider

alter table mk_provider add createDate date default sysdate;
alter table mk_provider add status number default 1;
//供应商分页查询
select * from (select t.*, rownum rn from (select * from mk_provider where 1=1 and name like '%公司%') t)
where rn>3 and rn <=6;

select * from mk_provider;
commit;
rollback;
 select count (1) from mk_provider where 1=1  and name like '%公司%'
 select count (1) from mk_provider where 1=1  and name like '%公司%'
 
select * from (select rownum rn, t.* from (select * from mk_order where 1=1) t)
where rn>1 and rn<=2

select * from mk_product
select id from mk_product where name='哇哈哈'

select * from mk_provider;
select id from mk_provider where name='公司2';


select count(1) c from mk_order where ispay=2 and c_pid in
(select id from c_provide where provider_id=2)

select * from mk_provider

select * from (select t.* , rownum rn from (select * from mk_order where c_pid in (select id from c_provide where 1=1  
)  )t)t1 where rn3 and rn<=6  
begin :3 end:6
  
select * from mk_order where c_pid in (select id from c_provide where 1=1 )

select * from mk_order o, c_provide cp, mk_product pt, mk_provider pv where o.c_pid= p.id and

select distinct pd.* from mk_product pd, c_provide pr where 
pd.id = pr.product_id and pr.id =6;

select distinct pd.* from mk_product pd, c_provide pr , mk_provider pv
where pd.id= pr.product_id and pv.id = pr.provider_id
and pv.id= 2

select * from mk_provider pv, c_provide cp 
where pv.id = cp.provider_id and cp.id =2

 select distinct pd.* from mk_product pd, c_provide pr where pd.id = pr.product_id and pr.id =2 

select * from mk_product
select * from c_provide 
select * from mk_order

select count(1) from mk_order 
where c_pid in 
(select id from c_provide where 1=1 and product_id =1 and provider_Id=32)

select * from mk_product mp, c_provide cp
where mp.id = cp.product_id
select * from c_provide
select * from mk_order

 select provider_id from mk_order mo, c_provide cp where mo.c_pid = cp.id and mo.id= 30
 select distinct pd.* from mk_product pd, c_provide pr where pd.id = pr.product_id and pr.id =1
 
 select * from mk_order
 
 select * from mk_role
 select * from mk_user
 
 alter table mk_provider modify(fax varchar2(20))

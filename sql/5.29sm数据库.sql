create table mk_role (
  roleid number primary key, 
  rolename VARCHAR2(10) 
  );
  comment on table mk_role is '•Œã¸®§¶‚™Ì';
create table mk_user (  
  ID       NUMBER primary key,
  USERNAME VARCHAR2(10) unique,
  PASSWORD VARCHAR2(10),
  SEX      NUMBER,
  AGE      NUMBER,
  MOBILE   NUMBER,
  ADDRESS  VARCHAR2(10),
  roleid   number
);
  comment on table mk_user is '•Œã¸™Ì';
  comment on table mk_user.username is '•Œã¸¶W';
  comment on table mk_user.password is '±K?°A•Œmd5¶s?';
  comment on table mk_user.sex is '© ?';
  comment on table mk_user.age is '¶~?';
  comment on table mk_user.mobile is '§‚…Ûè≠';
  comment on table mk_user.address is '¶Ìß}';
  comment on table mk_user.roleid is '®§¶‚id';
  commit;
  
alter table mk_user add constraint fk_role foreign key (roleid) references mk_role(roleid);
create table mk_product(
  id            number primary key ,
  NAME          VARCHAR2(10) not null,
  UNIT          VARCHAR2(10) not null,
  price         number not null
);
create table mk_provider(
  ID          NUMBER primary key,
  NAME    VARCHAR2(10),
  CONTACT VARCHAR2(10),
  pro_desc    VARCHAR2(10),
  PHONE   VARCHAR2(10),
  ADDRESS VARCHAR2(10),
  FAX     VARCHAR2(10)
);
create table c_provide(
  id number,
  provider_id number ,
  product_id number ,
  primary key  (id),
  foreign key (provider_id) references mk_provider(id),
  foreign key (product_id) references mk_product(id)
);
create table mk_bill(
  ID       NUMBER primary key,
  TIME     DATE,
  ISPAY    NUMBER,
  MONEY    VARCHAR2(10),
  COUNTS   NUMBER,
  bill_DESC     VARCHAR2(100),
  c_pid number ,
  foreign key (c_pid) references c_provide(id)
);

commit;
--each table has its sequence 
create sequence seq_user start with 10
increment by 1
maxvalue 2000 nocycle;
create sequence seq_role start with 10
increment by 1
maxvalue 2000 nocycle;
create sequence seq_product start with 10
increment by 1
maxvalue 2000 nocycle;
create sequence seq_provide start with 10
increment by 1
maxvalue 2000 nocycle;
create sequence seq_provider start with 10
increment by 1
maxvalue 2000 nocycle;
create sequence seq_bill start with 10
increment by 1
maxvalue 2000 nocycle;
select seq_user.nextval from dual;

create or replace procedure addUser ()



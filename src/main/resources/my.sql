insert into user_details (id, birthdate,name)
values (1001,current_date,'Ranga');
insert into user_details (id, birthdate,name)
values (1002,current_date,'Rangan');
insert into user_details (id, birthdate,name)
values (1003,current_date,'Rangoli');

insert into post (id, description,user_id)
values (2001,'I Want to learn java',1001);
insert into post (id, description,user_id)
values (2002,'I Want to learn Docker',1001);
insert into post (id, description,user_id)
values (2003,'I Want to learn C',1002);
insert into post (id, description,user_id)
values (2004,'I Want to learn was',1002);

insert into  TODO (id,username,description,DUE_DATE,completed)
values ( 1,'Jay','Learn Java',CURRENT_DATE,false );
insert into  TODO (id,username,description,DUE_DATE,completed)
values ( 2,'Jay','Learn C++',CURRENT_DATE,false );
insert into  TODO (id,username,description,DUE_DATE,completed)
values ( 3,'Jay','Learn Scala',CURRENT_DATE,false );
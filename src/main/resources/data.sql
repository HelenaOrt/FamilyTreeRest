insert into personModel(name, last_name, age, country) values('Helena', 'Ortiz', 22, 'Spain');
insert into personModel(name, last_name, age, country) values('Juan Antonio', 'Ortiz', 60, 'Spain');
insert into personModel(name, last_name, age, country) values('Nacho', 'Casarrubios', 59, 'Spain');
insert into personModel(name, last_name, age, country, father_id) values('Diana', 'Casarrubios', 22, 'Spain', 3);
insert into personModel(name, last_name, age, country) values('Jose', 'Gonzalez', 60, 'Spain');
insert into personModel(name, last_name, age, country, father_id) values('Rocio', 'Gonzalez', 23, 'Spain', 5);
insert into personModel(name, last_name, age, country) values('Eugenio', 'Díaz', 55, 'Spain');
insert into personModel(name, last_name, age, country, father_id) values('Patricia', 'Díaz', 30, 'Spain',7);
insert into personModel(name, last_name, age, country, father_id) values('Jesús', 'Díaz', 30, 'Spain',7);


update personModel set father_id = 2 where id = 1;

insert into web_user(name, password) values('Cristian', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');
insert into web_user(name, password) values('Alberto', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');
insert into web_user(name, password) values('Elena', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');
insert into web_user(name, password) values('Pilar', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');
insert into web_user(name, password) values('Cuco', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');
insert into web_user(name, password) values('Jorge', '$2a$12$k4Zt1f1CYw34zkrU2P7Iz.IAyklW4yawVZtYVGqNJ2gkGj4lwsjze');

insert into authority(id, name) values(1, 'ROLE_ADMIN');
insert into authority(id, name) values(2, 'ROLE_USER');

insert into web_user_authorities(web_users_id, authorities_id) values(1, 1);
insert into web_user_authorities(web_users_id, authorities_id) values(3, 1);
insert into web_user_authorities(web_users_id, authorities_id) values(2, 2);
insert into web_user_authorities(web_users_id, authorities_id) values(4, 2);
insert into web_user_authorities(web_users_id, authorities_id) values(5, 2);
insert into web_user_authorities(web_users_id, authorities_id) values(6, 2);




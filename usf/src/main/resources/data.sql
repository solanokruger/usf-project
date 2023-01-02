insert into role (`name`) values ('ROLE_ADMIN');
insert into role (`name`) values ('ROLE_USF_OPERATOR');
insert into role (`name`) values ('ROLE_DOCTOR');

insert into user (`login`, `name`, `password`) values ('admin', 'Aline', '$2a$10$vughfMsGcLLI3kyXAeDe8.AoxRZL8RBy04oXv8I9yX6lJZh6Y5kjC');
insert into user (`login`, `name`, `password`) values ('operator', 'Bruno', '$2a$10$vughfMsGcLLI3kyXAeDe8.AoxRZL8RBy04oXv8I9yX6lJZh6Y5kjC');
insert into user(`login`, `name`, `password`) values ('doctor', 'Carlos', '$2a$10$vughfMsGcLLI3kyXAeDe8.AoxRZL8RBy04oXv8I9yX6lJZh6Y5kjC');

insert into user_roles (`user_id`, `roles_id`) values (1, 1);
insert into user_roles (`user_id`, `roles_id`) values (2, 2);
insert into user_roles (`user_id`, `roles_id`) values (3, 3);
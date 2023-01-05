insert into role (`name`) values ('ROLE_ADMIN');
insert into role (`name`) values ('ROLE_USF_OPERATOR');
insert into role (`name`) values ('ROLE_DOCTOR');

insert into user (`login`, `name`, `password`) values ('admin', 'Aline', '$2a$10$vughfMsGcLLI3kyXAeDe8.AoxRZL8RBy04oXv8I9yX6lJZh6Y5kjC');
insert into user (`login`, `name`, `password`) values ('operator', 'Bruno', '$2a$10$vughfMsGcLLI3kyXAeDe8.AoxRZL8RBy04oXv8I9yX6lJZh6Y5kjC');
insert into user(`login`, `name`, `password`) values ('doctor', 'Carlos', '$2a$10$vughfMsGcLLI3kyXAeDe8.AoxRZL8RBy04oXv8I9yX6lJZh6Y5kjC');

insert into user_roles (`user_id`, `roles_id`) values (1, 1);
insert into user_roles (`user_id`, `roles_id`) values (2, 2);
insert into user_roles (`user_id`, `roles_id`) values (3, 3);

insert into doctor (`name`, `specialization`) values ('Joao', 'Oftalmologista');
insert into doctor (`name`, `specialization`) values ('Társila', 'Dermatologista');

insert into resource (`category`, `description`, `name`) values ('MEDICAMENTO', 'Neusaldina', 'Remédio para dor de cabeça');
insert into resource (`category`, `description`, `name`) values ('EQUIPAMENTO', 'Gaze', 'Item de saúde e cuidados pessoais que na maioria das vezes é usada pra fazer curativos em machucados na pele');

insert into team (`color`) values ('Azul');
insert into team (`color`) values ('Vermelho');

insert into usf (`address`, `name`) values ('Avenida 2 de Julho', 'Unidade de Saúde da Família da Avenida 2 de Julho');
insert into usf (`address`, `name`) values ('Rua do Comércio', 'Unidade de Saúde da Família da Rua do Comércio');

insert into inventory (`amount`, `resource_id`, `usf_id`) values (50, 1, 1);
insert into inventory (`amount`, `resource_id`, `usf_id`) values (70, 2, 2);

insert into solicitation (`necessary_amount`, `request_date`, `status_solicitation`, `resource_id`, `usf_id`) values (50, '2023-01-05 18:15:32.117216', 'PENDENTE', 1, 1);
insert into solicitation (`answer_date`, `necessary_amount`, `request_date`, `status_solicitation`, `resource_id`, `usf_id`) values ('2023-01-07 21:15:32', 50, '2023-01-05 18:15:32.117216', 'CONCLUIDO', 2, 2);
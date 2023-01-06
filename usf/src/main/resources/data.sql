insert into role (`name`) values ('ROLE_ADMIN');
insert into role (`name`) values ('ROLE_USF_OPERATOR');
insert into role (`name`) values ('ROLE_DOCTOR');

insert into user (`login`, `name`, `password`) values ('admin', 'Alexandre', '$2a$10$vughfMsGcLLI3kyXAeDe8.AoxRZL8RBy04oXv8I9yX6lJZh6Y5kjC');
insert into user (`login`, `name`, `password`) values ('operator', 'Bruna', '$2a$10$vughfMsGcLLI3kyXAeDe8.AoxRZL8RBy04oXv8I9yX6lJZh6Y5kjC');
insert into user(`login`, `name`, `password`) values ('doctor', 'Carlos', '$2a$10$vughfMsGcLLI3kyXAeDe8.AoxRZL8RBy04oXv8I9yX6lJZh6Y5kjC');

insert into user_roles (`user_id`, `roles_id`) values (1, 1);
insert into user_roles (`user_id`, `roles_id`) values (2, 2);
insert into user_roles (`user_id`, `roles_id`) values (3, 3);

insert into doctor (`name`, `specialization`) values ('Pedro Henrique Castro', 'Oftalmologista');
insert into doctor (`name`, `specialization`) values ('Pedro Argolo de Souza', 'Cardiologista');
insert into doctor (`name`, `specialization`) values ('Tarsila Catarina Vitoriano', 'Dermatologista');

insert into resource (`category`, `description`, `name`) values ('MEDICAMENTO', 'Neusaldina', 'Remedio para dor de cabeça');
insert into resource (`category`, `description`, `name`) values ('EQUIPAMENTO', 'Gaze', 'Item de saude e cuidados pessoais que na maioria das vezes e usada pra fazer curativos em machucados na pele');

insert into team (`color`) values ('Azul');
insert into team (`color`) values ('Vermelho');

insert into usf (`address`, `name`) values ('Avenida 2 de Julho', 'Unidade de Saude da Familia da Avenida 2 de Julho');
insert into usf (`address`, `name`) values ('Rua do Comercio', 'Unidade de Saude da Familia da Rua do Comercio');

insert into inventory (`amount`, `resource_id`, `usf_id`) values (50, 1, 1);
insert into inventory (`amount`, `resource_id`, `usf_id`) values (70, 2, 2);

insert into solicitation (`necessary_amount`, `request_date`, `status_solicitation`, `resource_id`, `usf_id`) values (50, '2023-01-05 18:15:32.117216', 'PENDENTE', 1, 1);
insert into solicitation (`necessary_amount`, `request_date`, `status_solicitation`, `resource_id`, `usf_id`) values (90, '2023-01-05 18:15:32.117216', 'PENDENTE', 1, 2);
insert into solicitation (`answer_date`, `necessary_amount`, `request_date`, `status_solicitation`, `resource_id`, `usf_id`) values ('2023-01-07 21:15:32', 0, '2023-01-05 18:15:32.117216', 'CONCLUIDO', 2, 2);
insert into role values (1, "ROLE_ADMIN");
insert into role values (2, "ROLE_USF_OPERATOR");
insert into role values (3, "ROLE_DOCTOR");

insert into user values (1, "admin", "Aline", "$2a$10$vughfMsGcLLI3kyXAeDe8.AoxRZL8RBy04oXv8I9yX6lJZh6Y5kjC");
insert into user values (2, "operator", "Bruno", "$2a$10$vughfMsGcLLI3kyXAeDe8.AoxRZL8RBy04oXv8I9yX6lJZh6Y5kjC");
insert into user values (3, "doctor", "Carlos", "$2a$10$vughfMsGcLLI3kyXAeDe8.AoxRZL8RBy04oXv8I9yX6lJZh6Y5kjC");

insert into user_roles values (1, 1);
insert into user_roles values (2, 2);
insert into user_roles values (3, 3);
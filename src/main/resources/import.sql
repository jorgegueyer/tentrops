/*Users*/
INSERT INTO users (name, surname, birthday, create_at) VALUES ('Jorge', 'Guerra Yerpes', '1990-08-05', '2018-03-31');

/*Sports*/
INSERT INTO sports (id, name, kcal) VALUES (1,'Run',50);
INSERT INTO sports (id, name, kcal) VALUES (2,'Run Indoor',25);
INSERT INTO sports (id, name, kcal) VALUES (3,'Swim - Sea',60);
INSERT INTO sports (id, name, kcal) VALUES (4,'Swim - River',45);
INSERT INTO sports (id, name, kcal) VALUES (5,'Swim - Reservoir',35);
INSERT INTO sports (id, name, kcal) VALUES (6,'Swim Indoor - 25 Meters',20);
INSERT INTO sports (id, name, kcal) VALUES (7,'Swim Indoor - 50 Meters',2);
INSERT INTO sports (id, name, kcal) VALUES (8,'Cycling Road',70);
INSERT INTO sports (id, name, kcal) VALUES (9,'Cycling Mountain',80);
INSERT INTO sports (id, name, kcal) VALUES (10,'Cycling Indoor',50);

/* Registry with Activities*/
INSERT INTO registries (id, date_at, description, observation, create_at) VALUES (1522447200000, '2018-03-31', 'Run Train', 'Any descriptions', '2018-03-31');
INSERT INTO activities (duration, distance, registry_id, sport_id) VALUES ('00:45:56', 2.50, 1522447200000, 3);
INSERT INTO activities (duration, distance, registry_id, sport_id) VALUES ('01:12:16', 16.00, 1522447200000, 1);
-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');

--insert into Pizza(id, name, beschreibung, preis) values(1, 'Salami', 'Abc', 5.99);
--insert into Pizza(id, name, beschreibung, preis) values(23, 'Margaritha', 'Efg', 4.89);
insert into Skater(id,vorname,nachname,disziplin,alter) values(1,'Max','Meier','Einzel',19);
insert into Skater(id,vorname,nachname,disziplin,alter) values(3,'Hanna','Mueller','Doppel',20);
insert into Turnier(name,date,ort,skater) values(3,'25.3.2024','Hannover','''Hanna'',''Mueller'',''Doppel'',20');
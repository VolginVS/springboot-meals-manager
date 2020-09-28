delete from food;
insert into food(id, name, species) values
(1,'Potato', 'Red'),
(2,'Milk', '2,5%'),
(3,'Chicken meat', 'Breast'),
(4,'Pasta','Spagetti'),
(5,'Tomato paste','Classic'),
(6,'Millet', 'Groats');

alter sequence food_id_seq restart with 10;
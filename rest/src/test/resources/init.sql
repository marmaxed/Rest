INSERT INTO workers(fio, birth_date)
VALUES ('First Test Teacher', '2000-03-03'::date),
       ('Second Test Director', '1990-03-03'::date);


INSERT INTO school_classes(name, count, teacher)
       SELECT 'Test Class', 2, id FROM workers;

INSERT INTO students(fio, birth_date, school_class)
SELECT 'Test Student 1-1', '2019-03-03'::date, id FROM school_classes;

INSERT INTO schools(title, year, director, rating)
SELECT 'Best Test School', 1936, id, 5 FROM workers LIMIT 1;
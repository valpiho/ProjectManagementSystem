INSERT INTO PROJECT (PROJECT_NAME, DESCRIPTION, START_DATE, END_DATE) VALUES ('My First Project', 'This is me testing out the database', '2021-03-06', '2021-08-01');
INSERT INTO PROJECT (PROJECT_NAME, DESCRIPTION, START_DATE, END_DATE) VALUES ('My Second Project', 'This is me testing out the database once again', '2021-06-16', '2021-12-31');


INSERT INTO TASK (project_id, title, task_description, priority, user_id, status) VALUES (1, 'First Task', 'This is my first task', 'HIGH', 1, 'NOT_STARTED');
INSERT INTO TASK (project_id, title, task_description, priority, user_id, status) VALUES (2, 'Second Task', 'This is my second task', 'LOW', 2, 'NOT_STARTED');
INSERT INTO TASK (project_id, title, task_description, priority, user_id, status) VALUES (2, 'Third Task', 'This is my third task', 'LOW', 2, 'IN_PROGRESS');
INSERT INTO TASK (project_id, title, task_description, priority, user_id, status) VALUES (1, 'Fourth Task', 'This is my fourth task', 'MEDIUM', 2, 'COMPLETED');





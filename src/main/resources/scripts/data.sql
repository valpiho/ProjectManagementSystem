INSERT INTO PROJECT (PROJECT_NAME, DESCRIPTION, START_DATE, END_DATE) VALUES ('My First Project', 'This is me testing out the database', '2021-03-06', '2021-08-01');
INSERT INTO PROJECT (PROJECT_NAME, DESCRIPTION, START_DATE, END_DATE) VALUES ('My Second Project', 'This is me testing out the database once again', '2021-06-16', '2021-12-31');


INSERT INTO TASK (project_id, task_description, priority, user_id, status) VALUES (1, 'This is my first task', 'HIGH', 1, 'DONE');
INSERT INTO TASK (project_id, task_description, priority, user_id, status) VALUES (7, 'This is my second task', 'LOW', 1, 'IN_PROGRESS');
INSERT INTO TASK (project_id, task_description, priority, user_id, status) VALUES (4, 'This is my third task', 'LOW', 1, 'IN_PROGRESS');
INSERT INTO TASK (project_id, task_description, priority, user_id, status) VALUES (1, 'This is my fourth task', 'MEDIUM', 1, 'IN_PROGRESS');
create table hibernate_sequence
(
    next_val bigint null
);

create table project
(
    id bigint auto_increment
        primary key,
    description varchar(255) null,
    end_date datetime(6) null,
    project_name varchar(255) null,
    start_date datetime(6) null,
    status varchar(11) null,
    task_id bigint null
);

create table user
(
    id bigint not null
        primary key,
    authorities tinyblob null,
    email varchar(255) null,
    first_name varchar(255) null,
    last_name varchar(255) null,
    password varchar(255) null,
    role varchar(255) null,
    username varchar(255) null
);

create table task
(
    id bigint auto_increment
        primary key,
    priority varchar(6) null,
    status varchar(11) null,
    task_description varchar(255) null,
    project_id bigint null,
    user_id bigint null,
    task_id bigint null,
    constraint FK2hsytmxysatfvt0p1992cw449
        foreign key (user_id) references user (id),
    constraint FKae1mroqa689kinqybv3bcauv
        foreign key (task_id) references user (id),
    constraint FKk8qrwowg31kx7hp93sru1pdqa
        foreign key (project_id) references project (id)
);

alter table project
    add constraint FKfhq6ejqa3bedwlymsw10j1pb2
        foreign key (task_id) references task (id);

create table users_projects
(
    user_id bigint not null,
    project_id bigint not null,
    primary key (user_id, project_id),
    constraint FK5gka63hbj8siyiahiqs0kupe2
        foreign key (project_id) references project (id),
    constraint FK5tvof3bxcwalwr5y11jo3fpn0
        foreign key (user_id) references user (id)
);


-- Drop section
drop schema if exists oracle_partner_network;
drop user if exists 'admin'@'localhost';

-- Create section
create schema oracle_partner_network;
create user 'admin'@'localhost' identified by 'Root12345!';

-- Grant section
grant select, insert, delete, update, create on oracle_partner_network.* to 'admin'@'localhost';

use oracle_partner_network;

-- Create table section
create table company (
     id bigint unsigned not null auto_increment,
     name varchar(50),
     slogan varchar(200),
     credit_hold varchar(40),
     cnpj varchar(150) unique,
     country varchar(20),
     state varchar(50),
     city varchar(100),
     address varchar(200),
     opn_status enum('MEMBER', 'EXPIRED','INACTIVE'),
     ingestion_operation enum('CSV', 'MANUAL'),
     status enum('ACTIVE', 'INACTIVE'),
     create_at timestamp default current_timestamp,
     update_at timestamp default current_timestamp,
     primary key (id)
);

create table partner (
    id bigint unsigned not null auto_increment,
    username varchar(50) unique not null,
    password varchar(100) not null,
    role enum('ADM', 'USER') not null,
    ingestion_operation enum('CSV', 'MANUAL'),
    status enum('ACTIVE', 'INACTIVE'),
    create_at timestamp default current_timestamp,
    update_at timestamp default current_timestamp,
    primary key (id)
);

create table employee (
    id bigint unsigned not null auto_increment,
    company_id bigint unsigned not null,
    email varchar(50) unique,
    password varchar(100) not null,
    name varchar(50) not null,
    role enum('ADM', 'USER') not null,
    membership_type enum('PRINCIPAL', 'WORLDWIDE'),
    ingestion_operation enum('CSV', 'MANUAL'),
    status enum('ACTIVE', 'INACTIVE'),
    create_at timestamp default current_timestamp,
    update_at timestamp default current_timestamp,
    primary key (id),
    foreign key company_fk (company_id) references company (id) on delete restrict on update cascade
);

create table workload (
    id bigint unsigned not null auto_increment,
    name varchar(100) unique,
    description varchar(250),
    ingestion_operation enum('CSV', 'MANUAL'),
    status enum('ACTIVE', 'INACTIVE'),
    create_at timestamp default current_timestamp,
    update_at timestamp default current_timestamp,
    primary key (id)
);

create table service_expertise (
    id bigint unsigned not null auto_increment,
    name varchar(100) unique,
    description varchar(500),
    ingestion_operation enum('CSV', 'MANUAL'),
    status enum('ACTIVE', 'INACTIVE'),
    create_at timestamp default current_timestamp,
    update_at timestamp default current_timestamp,
    primary key (id)
);

create table opn_track (
    id bigint unsigned not null auto_increment,
    name varchar(50),
    ingestion_operation enum('CSV', 'MANUAL'),
    status enum('ACTIVE', 'INACTIVE'),
    create_at timestamp default current_timestamp,
    update_at timestamp default current_timestamp,
    primary key (id)
);

create table certification (
    id bigint unsigned not null auto_increment,
    name varchar(100) unique,
    description varchar(250),
    life_time_month int,
    ingestion_operation enum('CSV', 'MANUAL'),
    create_at timestamp default current_timestamp,
    update_at timestamp default current_timestamp,
    primary key (id)
);

create table company_opn_tracks (
    id bigint unsigned not null auto_increment,
    company_id bigint unsigned not null,
    opn_tracks_id bigint unsigned not null,
    primary key (id),
    foreign key company_fk (company_id) references company (id) on delete restrict on update cascade,
    foreign key opn_track_fk (opn_tracks_id) references opn_track (id) on delete restrict on update cascade
);

create table company_expertise (
   id bigint unsigned not null auto_increment,
   company_id bigint unsigned not null,
   expertise_id bigint unsigned not null,
   primary key (id),
   foreign key company_fk (company_id) references company (id) on delete restrict on update cascade,
   foreign key expertise_fk (expertise_id) references service_expertise (id) on delete restrict on update cascade
);


create table employee_certification (
    id bigint unsigned not null auto_increment,
    certification_id bigint unsigned not null,
    employee_id bigint unsigned not null,
    expires_at timestamp not null,
    status enum('PASSED', 'IN_PROGRESS', 'EXPIRED'),
    create_at timestamp default current_timestamp,
    update_at timestamp default current_timestamp,
    primary key (id),
    foreign key employee_fk (employee_id) references employee (id) on delete restrict on update cascade,
    foreign key certification_fk (certification_id) references certification (id) on delete restrict on update cascade
);

create table opn_track_and_expertise (
    id bigint unsigned not null auto_increment,
    opn_track_id bigint unsigned not null,
    expertise_id bigint unsigned not null,
    primary key (id),
    foreign key opn_track_fk (opn_track_id) references opn_track (id) on delete restrict on update cascade,
    foreign key service_expertise_fk (expertise_id) references service_expertise (id) on delete restrict on update cascade
);

create table workload_and_expertise (
    id bigint unsigned not null auto_increment,
    workload_id bigint unsigned not null,
    expertise_id bigint unsigned not null,
    primary key (id),
    foreign key workload_fk (workload_id) references workload (id) on delete restrict on update cascade,
    foreign key service_expertise_fk (expertise_id) references service_expertise (id) on delete restrict on update cascade
);

create table expertise_certification (
    id bigint unsigned not null auto_increment,
    certification_id bigint unsigned not null,
    expertise_id bigint unsigned not null,
    primary key (id),
    foreign key certification_fk (certification_id) references certification (id) on delete restrict on update cascade,
    foreign key expertise_fk (expertise_id) references service_expertise (id) on delete restrict on update cascade
);

create table change_history (
    id bigint unsigned not null auto_increment,
    changed_by_partner_id bigint unsigned not null,
    table_name varchar(100) not null,
    change_type enum('CREATE', 'INSERT', 'UPDATE', 'DELETE') not null,
    old_value_hexadecimal text not null,
    new_value_hexadecimal text not null,
    changed_at timestamp default current_timestamp,
    primary key (id),
    foreign key partner_fk (changed_by_partner_id) references partner (id)
);

insert into partner (username, password, ingestion_operation, status)
values('admin', 'admin12', 'MANUAL', 'ACTIVE');


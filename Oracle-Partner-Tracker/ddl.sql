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
create table user (
    id bigint unsigned not null auto_increment,
    email varchar(50) unique,
    password varchar(100) not null,
    name varchar(50),
    role enum('ADM', 'USER'),
    membership_type enum('PRINCIPAL', 'WORLDWIDE'),
    status enum('ACTIVE', 'INACTIVE'),
    primary key (id)
);

create table company (
    id bigint unsigned not null auto_increment,
    name varchar(50),
    slogan varchar(200),
    credit_hold boolean,
    cnpj varchar(150),
    country varchar(20),
    state varchar(50),
    city varchar(100),
    address varchar(200),
    zip_code varchar(10),
    opn_status enum('MEMBER'),
    ingestion_operation enum('CSV', 'MANUAL'),
    status enum('ACTIVE', 'INACTIVE'),
    create_on timestamp default current_timestamp,
    primary key (id)
);

create table workload (
    id bigint unsigned not null auto_increment,
    name varchar(100) unique,
    description varchar(250),
    status enum('ACTIVE', 'INACTIVE'),
    primary key (id)
);

create table service_expertise (
    id bigint unsigned not null auto_increment,
    name varchar(100) unique,
    description varchar(250),
    life_time_month int,
    status enum('ACTIVE', 'INACTIVE'),
    primary key (id)
);

create table opn_track (
    id bigint unsigned not null auto_increment,
    name varchar(20),
    status enum('ACTIVE', 'INACTIVE'),
    primary key (id)
);

create table user_and_company (
    id bigint unsigned not null auto_increment,
    user_id bigint unsigned not null unique,
    company_id bigint unsigned not null,
    primary key (id),
    foreign key user_fk (user_id) references user (id) on delete restrict on update cascade,
    foreign key company_fk (company_id) references company (id) on delete restrict on update cascade
);

create table user_and_expertise (
    id bigint unsigned not null auto_increment,
    user_id bigint unsigned not null,
    expertise_id bigint unsigned not null,
    score numeric(2,2),
    status enum('ACTIVE', 'INACTIVE'),
    create_on timestamp default current_timestamp,
    expiration_date timestamp,
    primary key (id),
    foreign key user_fk (user_id) references user (id) on delete restrict on update cascade,
    foreign key service_expertise_fk (expertise_id) references service_expertise (id) on delete restrict on update cascade
);

create table opn_track_and_expertise (
    id bigint unsigned not null auto_increment,
    opn_track_id bigint unsigned not null,
    expertise_id bigint unsigned not null,
    primary key (id),
    foreign key opn_track_fk (opn_track_id) references opn_track (id) on delete restrict on update cascade,
    foreign key service_expertise_fk (expertise_id) references service_expertise (id) on delete restrict on update cascade
);

create table opn_track_and_company (
    id bigint unsigned not null auto_increment,
    opn_track_id bigint unsigned not null,
    company_id bigint unsigned not null,
    primary key (id),
    foreign key opn_track_fk (opn_track_id) references opn_track (id) on delete restrict on update cascade,
    foreign key company_fk (company_id) references company (id) on delete restrict on update cascade
);

create table opn_track_and_workload (
    id bigint unsigned not null auto_increment,
    opn_track_id bigint unsigned not null,
    workload_id bigint unsigned not null,
    primary key (id),
    foreign key opn_track_fk (opn_track_id) references opn_track (id) on delete restrict on update cascade,
    foreign key workload_fk (workload_id) references workload (id) on delete restrict on update cascade
);

create table workload_and_expertise (
    id bigint unsigned not null auto_increment,
    workload_id bigint unsigned not null,
    expertise_id bigint unsigned not null,
    primary key (id),
    foreign key workload_fk (workload_id) references workload (id) on delete restrict on update cascade,
    foreign key service_expertise_fk (expertise_id) references service_expertise (id) on delete restrict on update cascade
);

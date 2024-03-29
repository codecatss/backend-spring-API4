-- Drop section
drop schema if exists oracle_partner_network;
drop user if exists 'admin'@'localhost';

-- Create section
create schema oracle_partner_network;
create user 'admin'@'localhost' identified by 'Root12345!';

-- Grant section
grant select, insert, delete, update on oracle_partner_network.* to 'admin'@'localhost';

use oracle_partner_network;

-- Create table section
create table user (
    id bigint unsigned not null auto_increment,
    email varchar(50) not null unique,
    password varchar(100) not null,
    name varchar(50) not null,
    role varchar(50) not null,
    membership_type varchar(10) not null,
    status boolean,
    create_on timestamp,
    constraint ck_role check (UPPER(role) IN ('ADM', 'USER')),
    constraint ck_membership_type check (UPPER(membership_type) IN ('PRINCIPAL', 'NO PRINCIPAL')),
    primary key (id)
);

create table company (
    id bigint unsigned not null auto_increment,
    name varchar(50) not null,
    company_id varchar(20) not null,
    password varchar(150) not null,
    -- size varchar(50) not null,
    opn_status varchar(15) not null,
    credit_hold varchar(50) not null,
    cnpj varchar(150) not null,
    email varchar(100) not null,
    country varchar(20) not null,
    state varchar(50) not null,
    cep varchar(10) not null,
    city varchar(100) not null,
    address varchar(200) not null,
    status boolean,
    create_on timestamp,
    constraint ck_opn_status check (UPPER(opn_status) IN ('ACTIVE', 'INACTIVE')),
    primary key (id)
);

create table workload (
    id bigint unsigned not null auto_increment,
    name varchar(50) not null,
    description varchar(250),
    primary key (id)
);

create table service_expertise (
    id bigint unsigned not null auto_increment,
    name varchar(50) not null,
    description varchar(250),
    min_score int,
    max_score int,
    validatedMonth int,
    primary key (id)
);

create table track (
    id bigint unsigned not null auto_increment,
    name varchar(20) not null,
    constraint ck_track_name check (UPPER(name) IN ('CLOUD BUILD',  'CLOUD SELL',  'CLOUD SERVICE,' 'INDUSTRY HEALTHCARE',  'LICENSE & HARDWARE')),
    primary key (id)
);

-- Insert section




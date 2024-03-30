-- Drop section
drop schema if exists oracle_partner_network;
drop user if exists 'alec'@'localhost';
drop user if exists 'danko'@'localhost';
drop user if exists 'larissa'@'localhost';
drop user if exists 'laroy'@'localhost';
drop user if exists 'lucas'@'localhost';
drop user if exists 'lukas'@'localhost';
drop user if exists 'pablo'@'localhost';

-- Create section
create schema oracle_partner_network;
create user 'alec'@'localhost' identified by 'Root12345!';
create user 'danko'@'localhost' identified by 'Root12345!';
create user 'larissa'@'localhost' identified by 'Root12345!';
create user 'laroy'@'localhost' identified by 'Root12345!';
create user 'lucas'@'localhost' identified by 'Root12345!';
create user 'lukas'@'localhost' identified by 'Root12345!';
create user 'pablo'@'localhost' identified by 'Root12345!';

-- Grant section
grant select, insert, delete, update on oracle_partner_network.* to 'alec'@'localhost';
grant select, insert, delete, update on oracle_partner_network.* to 'danko'@'localhost';
grant select, insert, delete, update on oracle_partner_network.* to 'larissa'@'localhost';
grant select, insert, delete, update on oracle_partner_network.* to 'laroy'@'localhost';
grant select, insert, delete, update on oracle_partner_network.* to 'lucas'@'localhost';
grant select, insert, delete, update on oracle_partner_network.* to 'lukas'@'localhost';
grant select, insert, delete, update on oracle_partner_network.* to 'pablo'@'localhost';

use oracle_partner_network;

-- Create table section
create table user (
    id bigint unsigned not null auto_increment,
    email varchar(50) not null unique,
    password varchar(100) not null,
    name varchar(50) not null,
    role varchar(50) not null,
    membership_type varchar(10) not null,
    user_status boolean,
    create_on timestamp,
    constraint ck_role check (UPPER(role) IN ('ADM', 'USER')),
    constraint ck_membership_type check (UPPER(membership_type) IN ('PRINCIPAL', 'WORLDWIDE')),
    primary key (id)
);

create table company (
    id varchar(50) not null,
    name varchar(50) not null,
    credit_hold varchar(50) not null,
    cnpj varchar(150) not null,
    country varchar(20) not null,
    state varchar(50) not null,
    city varchar(100) not null,
    address varchar(200) not null,
    cep varchar(10) not null,
    opn_status boolean,
    company_status boolean,
    create_on timestamp,
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

create table opn_track (
    id bigint unsigned not null auto_increment,
    name varchar(20) not null,
    constraint ck_opn_track_name check (UPPER(name) IN ('CLOUD BUILD',  'CLOUD SELL',  'CLOUD SERVICE,' 'INDUSTRY HEALTHCARE',  'LICENSE & HARDWARE')),
    primary key (id)
);

create table user_and_expertise (
    id bigint unsigned not null auto_increment,
    user_id bigint unsigned not null,
    expertise_id bigint unsigned not null,
    score numeric(2,2),
    status boolean,
    create_on timestamp,
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
    company_id varchar(50) not null,
    primary key (id),
    foreign key opn_track_fk (opn_track_id) references opn_track (id) on delete restrict on update cascade,
    foreign key company_fk (company_id) references company (id) on delete restrict on update cascade
);


-- Insert section


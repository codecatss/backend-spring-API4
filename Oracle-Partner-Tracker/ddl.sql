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
    id varchar(50) not null,
    email varchar(50) not null unique,
    password varchar(100) not null,
    name varchar(50) not null,
    role varchar(50) not null,
    membership_type varchar(10) not null,
    user_status boolean,
    create_on timestamp default current_timestamp,
    constraint ck_role check (UPPER(role) IN ('ADM', 'USER')),
    constraint ck_membership_type check (UPPER(membership_type) IN ('PRINCIPAL', 'WORLDWIDE')),
    primary key (id)
);

create table company (
    id varchar(50) not null,
    name varchar(50) not null,
    slogan varchar(200) not null,
    credit_hold boolean,
    cnpj varchar(150) not null,
    country varchar(20) not null,
    state varchar(50) not null,
    city varchar(100) not null,
    address varchar(200) not null,
    cep varchar(10) not null,
    opn_status boolean,
    company_status boolean,
    create_on timestamp default current_timestamp,
    primary key (id)
);

create table workload (
    id varchar(50) not null,
    name varchar(50) not null unique,
    description varchar(250),
    primary key (id)
);

create table service_expertise (
    id varchar(50) not null,
    name varchar(50) not null,
    description varchar(250),
    min_score int,
    max_score int,
    validatedMonth int,
    primary key (id)
);

create table opn_track (
    id varchar(50) not null,
    name varchar(20) not null,
    constraint ck_opn_track_name check (UPPER(name) IN ('CLOUD BUILD','CLOUD BUILD & SERVICE','CLOUD SELL','CLOUD SELL & SERVICE','CLOUD SERVICE','INDUSTRY HEALTHCARE','LICENSE AND HARDWARE','OPN MEMBER')),
    primary key (id)
);

create table user_and_company (
    id varchar(50) not null,
    user_id varchar(50) not null,
    company_id varchar(50) not null,
    primary key (id),
    foreign key user_fk (user_id) references user (id) on delete restrict on update cascade,
    foreign key company_fk (company_id) references company (id) on delete restrict on update cascade
);

create table user_and_expertise (
    id varchar(50) not null,
    user_id varchar(50) not null,
    expertise_id varchar(50) not null,
    score numeric(2,2),
    status boolean,
    create_on timestamp default current_timestamp,
    expiration_date timestamp,
    primary key (id),
    foreign key user_fk (user_id) references user (id) on delete restrict on update cascade,
    foreign key service_expertise_fk (expertise_id) references service_expertise (id) on delete restrict on update cascade
);

create table opn_track_and_expertise (
    id varchar(50) not null,
    opn_track_id varchar(50) not null,
    expertise_id varchar(50) not null,
    primary key (id),
    foreign key opn_track_fk (opn_track_id) references opn_track (id) on delete restrict on update cascade,
    foreign key service_expertise_fk (expertise_id) references service_expertise (id) on delete restrict on update cascade
);

create table opn_track_and_company (
    id varchar(50) not null,
    opn_track_id varchar(50) not null,
    company_id varchar(50) not null,
    primary key (id),
    foreign key opn_track_fk (opn_track_id) references opn_track (id) on delete restrict on update cascade,
    foreign key company_fk (company_id) references company (id) on delete restrict on update cascade
);


-- Insert section it is in file "inserts.sql"



-- Drop section
begin
    execute immediate 'drop user admin cascade';
exception
    when others then
        if sqlcode <> -1918 then
            raise;
        end if;
end;
/

-- Create section
begin
    execute immediate 'create user admin identified by Root12345!';
end;
/

grant select, insert, delete, update on oracle_partner_network.* to admin;

-- Create table section
create table oracle_partner_network.user (
    id number(20) not null,
    email varchar2(50) not null unique,
    password varchar2(100) not null,
    name varchar2(50) not null,
    role varchar2(50) not null,
    membership_type varchar2(10) not null,
    user_status number(1),
    create_on timestamp,
    constraint user_pk primary key (id),
    constraint ck_role check (UPPER(role) IN ('ADM', 'USER')),
    constraint ck_membership_type check (UPPER(membership_type) IN ('PRINCIPAL', 'WORLDWIDE'))
);

create table oracle_partner_network.company (
    id varchar2(50) not null,
    name varchar2(50) not null,
    credit_hold varchar2(50) not null,
    cnpj varchar2(150) not null,
    country varchar2(20) not null,
    state varchar2(50) not null,
    city varchar2(100) not null,
    address varchar2(200) not null,
    cep varchar2(10) not null,
    opn_status number(1),
    company_status number(1),
    create_on timestamp,
    constraint company_pk primary key (id)
);

create table oracle_partner_network.workload (
    id number(20) not null,
    name varchar2(50) not null,
    description varchar2(250),
    constraint workload_pk primary key (id)
);

create table oracle_partner_network.service_expertise (
    id number(20) not null,
    name varchar2(50) not null,
    description varchar2(250),
    min_score number,
    max_score number,
    validatedMonth number,
    constraint service_expertise_pk primary key (id)
);

create table oracle_partner_network.opn_track (
    id number(20) not null,
    name varchar2(20) not null,
    constraint opn_track_pk primary key (id),
    constraint ck_opn_track_name check (UPPER(name) IN ('CLOUD BUILD', 'CLOUD SELL', 'CLOUD SERVICE', 'INDUSTRY HEALTHCARE', 'LICENSE & HARDWARE'))
);

create table oracle_partner_network.user_and_expertise (
    id number(20) not null,
    user_id number(20) not null,
    expertise_id number(20) not null,
    score number(2,2),
    status number(1),
    create_on timestamp,
    expiration_date timestamp,
    constraint user_and_expertise_pk primary key (id),
    constraint user_fk foreign key (user_id) references oracle_partner_network.user (id) on delete restrict,
    constraint service_expertise_fk foreign key (expertise_id) references oracle_partner_network.service_expertise (id) on delete restrict
);

create table oracle_partner_network.opn_track_and_expertise (
    id number(20) not null,
    opn_track_id number(20) not null,
    expertise_id number(20) not null,
    constraint opn_track_and_expertise_pk primary key (id),
    constraint opn_track_fk foreign key (opn_track_id) references oracle_partner_network.opn_track (id) on delete restrict,
    constraint service_expertise_fk foreign key (expertise_id) references oracle_partner_network.service_expertise (id) on delete restrict
);

create table oracle_partner_network.opn_track_and_company (
    id number(20) not null,
    opn_track_id number(20) not null,
    company_id varchar2(50) not null,
    constraint opn_track_and_company_pk primary key (id),
    constraint opn_track_fk foreign key (opn_track_id) references oracle_partner_network.opn_track (id) on delete restrict,
    constraint company_fk foreign key (company_id) references oracle_partner_network.company (id) on delete restrict
);

commit;

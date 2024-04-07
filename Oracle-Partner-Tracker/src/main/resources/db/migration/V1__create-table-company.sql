
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
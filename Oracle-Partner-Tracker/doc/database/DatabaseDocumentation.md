
<div style="font-size: 25pt; font-weight: bold;">
   Database Documentation
</div>

# 1. Visão Geral
teste teste teste


# 2. Modelo de Dados

## 2.1 Tables

### Table user
- **id**
    - Type: bigint unsigned
    - Description: Unique identifier of the user.
- **email**
    - Type: varchar(50)
    - Description: User's email.
- **password**
    - Type: varchar(100)
    - Description: User's password.
- **name**
    - Type: varchar(50)
    - Description: User's name.
- **role**
    - Type: enum('ADM', 'USER')
    - Description: User's role in the system (administrator or user).
- **membership_type**
    - Type: enum('PRINCIPAL', 'WORLDWIDE')
    - Description: User's membership type (principal or worldwide).
- **ingestion_operation**
    - Type: enum('CSV', 'MANUAL')
    - Description: User's preferred data ingestion method.
- **status**
    - Type: enum('ACTIVE', 'INACTIVE')
    - Description: User's account status (active or inactive).
- **created_at**
    - Type: timestamp
    - Description: Date and time when the record was created.
- **updated_at**
    - Type: timestamp
    - Description: Date and time of the last update to the record.
- primary key (id)

### Table company
- **id**
    - Type: bigint unsigned
    - Description: Unique identifier of the company.
- **name**
    - Type: varchar(50)
    - Description: Company's name.
- **slogan**
    - Type: varchar(200)
    - Description: Company's slogan.
- **credit_hold**
    - Type: varchar(40)
    - Description: Credit hold information.
- **cnpj**
    - Type: varchar(150)
    - Description: Company's CNPJ.
- **country**
    - Type: varchar(20)
    - Description: Company's country.
- **state**
    - Type: varchar(50)
    - Description: Company's state.
- **city**
    - Type: varchar(100)
    - Description: Company's city.
- **address**
    - Type: varchar(200)
    - Description: Company's address.
- **opn_status**
    - Type: enum('MEMBER', 'EXPIRED','INACTIVE')
    - Description: Company's status in the system.
- **ingestion_operation**
    - Type: enum('CSV', 'MANUAL')
    - Description: Company's preferred data ingestion method.
- **company_status**
    - Type: enum('ACTIVE', 'INACTIVE')
    - Description: Company's status (active or inactive).
- **created_at**
    - Type: timestamp
    - Description: Date and time when the record was created.
- **updated_at**
    - Type: timestamp
    - Description: Date and time of the last update to the record.
- primary key (id)

### Table workload
- **id**
    - Type: bigint unsigned
    - Description: Unique identifier of the workload.
- **name**
    - Type: varchar(100)
    - Description: Workload's name.
- **description**
    - Type: varchar(250)
    - Description: Workload's description.
- **ingestion_operation**
    - Type: enum('CSV', 'MANUAL')
    - Description: Workload's preferred data ingestion method.
- **status**
    - Type: enum('ACTIVE', 'INACTIVE')
    - Description: Workload's status (active or inactive).
- **created_at**
    - Type: timestamp
    - Description: Date and time when the record was created.
- **updated_at**
    - Type: timestamp
    - Description: Date and time of the last update to the record.
- primary key (id)

### Table service_expertise
- **id**
    - Type: bigint unsigned
    - Description: Unique identifier of the service expertise.
- **name**
    - Type: varchar(100)
    - Description: Service expertise's name.
- **description**
    - Type: varchar(250)
    - Description: Service expertise's description.
- **life_time_month**
    - Type: int
    - Description: Service expertise's lifetime in months.
- **ingestion_operation**
    - Type: enum('CSV', 'MANUAL')
    - Description: Service expertise's preferred data ingestion method.
- **status**
    - Type: enum('ACTIVE', 'INACTIVE')
    - Description: Service expertise's status (active or inactive).
- **created_at**
    - Type: timestamp
    - Description: Date and time when the record was created.
- **updated_at**
    - Type: timestamp
    - Description: Date and time of the last update to the record.
- primary key (id)

### Table opn_track
- **id**
    - Type: bigint unsigned
    - Description: Unique identifier of the operation track.
- **name**
    - Type: varchar(50)
    - Description: Operation track's name.
- **ingestion_operation**
    - Type: enum('CSV', 'MANUAL')
    - Description: Operation track's preferred data ingestion method.
- **status**
    - Type: enum('ACTIVE', 'INACTIVE')
    - Description: Operation track's status (active or inactive).
- **created_at**
    - Type: timestamp
    - Description: Date and time when the record was created.
- **updated_at**
    - Type: timestamp
    - Description: Date and time of the last update to the record.
- primary key (id)


## 2.2 Relationships

### Table company_and_user
- **company_id**
    - Type: bigint unsigned
    - Description: Foreign key referencing the company table by ID.
- **user_id**
    - Type: bigint unsigned
    - Description: Foreign key referencing the user table by ID.

### Table company_and_opn_track
- **company_id**
    - Type: bigint unsigned
    - Description: Foreign key referencing the company table by ID.
- **opn_track_id**
    - Type: bigint unsigned
    - Description: Foreign key referencing the opn_track table by ID.

### Table company_and_workload
- **company_id**
    - Type: bigint unsigned
    - Description: Foreign key referencing the company table by ID.
- **workload_id**
    - Type: bigint unsigned
    - Description: Foreign key referencing the workload table by ID.
- **score**
    - Type: numeric(3,2)
    - Description: Score associated with the relationship between the company and the workload.
- **status**
    - Type: enum('PASSED', 'IN PROGRESS', 'EXPIRED')
    - Description: Status of the relationship between the company and the workload.
- **created_at**
    - Type: timestamp
    - Description: Date and time when the record was created.
- **expiration_date**
    - Type: timestamp
    - Description: Expiration date of the relationship between the company and the workload.

### Table opn_track_and_expertise
- **opn_track_id**
    - Type: bigint unsigned
    - Description: Foreign key referencing the opn_track table by ID.
- **expertise_id**
    - Type: bigint unsigned
    - Description: Foreign key referencing the service_expertise table by ID.

### Table workload_and_expertise
- **workload_id**
    - Type: bigint unsigned
    - Description: Foreign key referencing the workload table by ID.
- **expertise_id**
    - Type: bigint unsigned
    - Description: Foreign key referencing the service_expertise table by ID.

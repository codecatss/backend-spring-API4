# Database Documentation

## 1. Overview
This database is being used in the [4th Semester of FATEC's API](https://github.com/codecatss/API-BD4).
We utilize a DDL script to create the database before executing the Java script, ensuring the highest possible quality of the SQL script.

## 2. Data Model

### 2.1 Tables

#### Table employee:
**Table Description:**
- This table stores employee information, including Oracle users and users responsible for the company.

**Fields:**
- **id**
  - Type: bigint unsigned
  - Description: Unique identifier of the employee.
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
  - Description: User's role in the system (administrator or employee).
- **membership_type**
  - Type: enum('PRINCIPAL', 'WORLDWIDE')
  - Description: User's membership type (principal or worldwide).
- **ingestion_operation**
  - Type: enum('CSV', 'MANUAL')
  - Description: Data ingestion method used to save the employee.
- **status**
  - Type: enum('ACTIVE', 'INACTIVE')
  - Description: User account status (active or inactive).
- **created_at**
  - Type: timestamp
  - Description: Date and time of record creation.
- primary key (id)

#### Table company
**Table Description:**
- This table stores company information, such as CNPJ, city, among others.

**Fields:**
- **id**
  - Type: bigint unsigned
  - Description: Unique identifier of the company.
- **name**
  - Type: varchar(50)
  - Description: Company name.
- **slogan**
  - Type: varchar(200)
  - Description: Company slogan.
- **credit_hold**
  - Type: varchar(40)
  - Description: Credit information.
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
  - Description: Data ingestion method used to save the company.
- **company_status**
  - Type: enum('ACTIVE', 'INACTIVE')
  - Description: Company status (active or inactive).
- **created_at**
  - Type: timestamp
  - Description: Date and time of record creation.
- **updated_at**
  - Type: timestamp
  - Description: Date and time of the last record update.
- primary key (id)

#### Table workload
**Table Description:**
- This table stores workload information.

**Fields:**
- **id**
  - Type: bigint unsigned
  - Description: Unique identifier of the workload.
- **name**
  - Type: varchar(100)
  - Description: Workload name.
- **description**
  - Type: varchar(250)
  - Description: Workload description.
- **ingestion_operation**
  - Type: enum('CSV', 'MANUAL')
  - Description: Data ingestion method used to save the workload.
- **status**
  - Type: enum('ACTIVE', 'INACTIVE')
  - Description: Workload status (active or inactive).
- **created_at**
  - Type: timestamp
  - Description: Date and time of record creation.
- **updated_at**
  - Type: timestamp
  - Description: Date and time of the last record update.
- primary key (id)

#### Table service_expertise
**Table Description:**
- This table stores expertise information.

**Fields:**
- **id**
  - Type: bigint unsigned
  - Description: Unique identifier of the expertise.
- **name**
  - Type: varchar(100)
  - Description: Expertise name.
- **description**
  - Type: varchar(250)
  - Description: Expertise description.
- **ingestion_operation**
  - Type: enum('CSV', 'MANUAL')
  - Description: Data ingestion method used to save the expertise.
- **status**
  - Type: enum('ACTIVE', 'INACTIVE')
  - Description: Expertise status (active or inactive).
- **created_at**
  - Type: timestamp
  - Description: Date and time of record creation.
- **updated_at**
  - Type: timestamp
  - Description: Date and time of the last record update.
- primary key (id)

#### Table opn_track
**Table Description:**
- This table stores opn track information.

**Fields:**
- **id**
  - Type: bigint unsigned
  - Description: Unique identifier of the opn track.
- **name**
  - Type: varchar(50)
  - Description: Opn track name.
- **ingestion_operation**
  - Type: enum('CSV', 'MANUAL')
  - Description: Data ingestion method used to save the opn track.
- **status**
  - Type: enum('ACTIVE', 'INACTIVE')
  - Description: Opn track status (active or inactive).
- **created_at**
  - Type: timestamp
  - Description: Date and time of record creation.
- **updated_at**
  - Type: timestamp
  - Description: Date and time of the last record update.
- primary key (id)

#### Table certification
**Table Description:**
- This table stores certification information.

**Fields:**
- **id**
  - Type: bigint unsigned
  - Description: Unique identifier of the certification.
- **name**
  - Type: varchar(50)
  - Description: Certification name.
- **description**
  - Type: varchar(250)
  - Description: Certification description.
- **life_time_month**
  - Type: int
  - Description: Number of months the certification is valid.
- **ingestion_operation**
  - Type: enum('CSV', 'MANUAL')
  - Description: Data ingestion method used to save the certification.
- **created_at**
  - Type: timestamp
  - Description: Date and time of record creation.
- **updated_at**
  - Type: timestamp
  - Description: Date and time of the last record update.
- primary key (id)

### 2.2 Relationships between Tables

#### Table company_expertise
**Relation Description:**
- This relationship joins the Company and Service_expertise tables, where a company can be related to N expertise and a company can have N certifications.

**Relation:** Company (0-n : 0-n) Service_expertise.

**Fields:**
- **company_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the service_expertise table by ID.
- **service_expertise**
  - Type: bigint unsigned
  - Description: Foreign key referencing the company table by ID.

#### Table expertise_certification
**Relation Description:**
- This relationship joins the Service_expertise and Certification tables, where an expertise can contain N certifications and a certification can belong to N expertise.

**Relation:** Certification (0-n : 0-n) Service_expertise.

**Fields:**
- **service_expertise**
  - Type: bigint unsigned
  - Description: Foreign key referencing the certification table by ID.
- **certification**
  - Type: bigint unsigned
  - Description: Foreign key referencing the service_expertise table by ID.

#### Table company_and_user
**Relation Description:**
- This relationship joins the Company and User tables, where a employee can be related to only one company.

**Relation:** Company (0-1 : 0-n) User.

**Fields:**
- **company_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the company table by ID.
- **user_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the employee table by ID.

#### Table company_opn_track
**Relation Description:**
- This relationship joins the Company and Opn_track tables.

**Relation:** Company (0-n : 0-n) Opn_track.

**Fields:**
- **company_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the company table by ID.
- **opn_track_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the opn_track table by ID.

#### Table company_and_workload
**Relation Description:**
- This relationship joins the Company and Workload tables and saves the workload progress (how much of the workload the company has completed).

**Relation:** Company (0-n : 0-n) Workload.

**Fields:**
- **company_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the company table by ID.
- **workload_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the workload table by ID.
- **score**
  - Type: numeric(3,2)
  - Description: Percentage of the workload the company has completed.
- **status**
  - Type: enum('PASSED', 'IN PROGRESS', 'EXPIRED')
  - Description: Status to track the progress of the workload.
- **created_at**
  - Type: timestamp
  - Description: Date and time of record creation.
- **expiration_date**
  - Type: timestamp
  - Description: Expiration date of the relationship between the company and the workload.

#### Table opn_track_and_expertise
**Relation Description:**
- This relationship joins the Opn_track and Service_expertise tables.

**Relation:** Opn_track (0-n : 0-n) Service_expertise.

**Fields:**
- **opn_track_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the opn_track table by ID.
- **expertise_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the service_expertise table by ID.

#### Table workload_and_expertise
**Relation Description:**
- This relationship joins the Workload and Service_expertise tables.

**Relation:** Workload (0-n : 0-n) Service_expertise.

**Fields:**
- **workload_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the workload table by ID.
- **expertise_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the service_expertise table by ID.



# 3. Diagram

![image](https://github.com/codecatss/backend-spring-API4/assets/94874696/45a459c8-e81f-48a0-b2e0-ebfa603d019d)



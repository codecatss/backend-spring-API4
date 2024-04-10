
# Database Documentation 

# 1. Visão Geral
Este banco de dados esta sendo usado na API da FATEC do 4° Semestre.
Usamos um script DDL para criar o banco antes de rodar o script java,
assim garantimos que a qualidade do script sql seja a melhor possivel.


# 2. Modelo de Dados

## 2.1 Tabelas

### Tabela user
- **id**
  - Tipo: bigint unsigned
  - Descrição: Identificador único do usuário.
- **email**
  - Tipo: varchar(50)
  - Descrição: Email do usuário.
- **password**
  - Tipo: varchar(100)
  - Descrição: Senha do usuário.
- **name**
  - Tipo: varchar(50)
  - Descrição: Nome do usuário.
- **role**
  - Tipo: enum('ADM', 'USER')
  - Descrição: Papel do usuário no sistema (administrador ou usuário).
- **membership_type**
  - Tipo: enum('PRINCIPAL', 'WORLDWIDE')
  - Descrição: Tipo de adesão do usuário (principal ou global).
- **ingestion_operation**
  - Tipo: enum('CSV', 'MANUAL')
  - Descrição: Método de ingestão de dados preferido pelo usuário.
- **status**
  - Tipo: enum('ACTIVE', 'INACTIVE')
  - Descrição: Status da conta do usuário (ativo ou inativo).
- **created_at**
  - Tipo: timestamp
  - Descrição: Data e hora de criação do registro.
- **updated_at**
  - Tipo: timestamp
  - Descrição: Data e hora da última atualização do registro.
- primary key (id)

### Tabela company
- **id**
  - Tipo: bigint unsigned
  - Descrição: Identificador único da empresa.
- **name**
  - Tipo: varchar(50)
  - Descrição: Nome da empresa.
- **slogan**
  - Tipo: varchar(200)
  - Descrição: Slogan da empresa.
- **credit_hold**
  - Tipo: varchar(40)
  - Descrição: Informação sobre crédito.
- **cnpj**
  - Tipo: varchar(150)
  - Descrição: CNPJ da empresa.
- **country**
  - Tipo: varchar(20)
  - Descrição: País da empresa.
- **state**
  - Tipo: varchar(50)
  - Descrição: Estado da empresa.
- **city**
  - Tipo: varchar(100)
  - Descrição: Cidade da empresa.
- **address**
  - Tipo: varchar(200)
  - Descrição: Endereço da empresa.
- **opn_status**
  - Tipo: enum('MEMBER', 'EXPIRED','INACTIVE')
  - Descrição: Status da empresa no sistema.
- **ingestion_operation**
  - Tipo: enum('CSV', 'MANUAL')
  - Descrição: Método de ingestão de dados preferido pela empresa.
- **company_status**
  - Tipo: enum('ACTIVE', 'INACTIVE')
  - Descrição: Status da empresa (ativo ou inativo).
- **created_at**
  - Tipo: timestamp
  - Descrição: Data e hora de criação do registro.
- **updated_at**
  - Tipo: timestamp
  - Descrição: Data e hora da última atualização do registro.
- primary key (id)

### Tabela workload
- **id**
  - Tipo: bigint unsigned
  - Descrição: Identificador único da carga de trabalho.
- **name**
  - Tipo: varchar(100)
  - Descrição: Nome da carga de trabalho.
- **description**
  - Tipo: varchar(250)
  - Descrição: Descrição da carga de trabalho.
- **ingestion_operation**
  - Tipo: enum('CSV', 'MANUAL')
  - Descrição: Método de ingestão de dados preferido para a carga de trabalho.
- **status**
  - Tipo: enum('ACTIVE', 'INACTIVE')
  - Descrição: Status da carga de trabalho (ativo ou inativo).
- **created_at**
  - Tipo: timestamp
  - Descrição: Data e hora de criação do registro.
- **updated_at**
  - Tipo: timestamp
  - Descrição: Data e hora da última atualização do registro.
- primary key (id)

### Tabela service_expertise
- **id**
  - Tipo: bigint unsigned
  - Descrição: Identificador único da expertise de serviço.
- **name**
  - Tipo: varchar(100)
  - Descrição: Nome da expertise de serviço.
- **description**
  - Tipo: varchar(250)
  - Descrição: Descrição da expertise de serviço.
- **life_time_month**
  - Tipo: int
  - Descrição: Tempo de vida da expertise de serviço em meses.
- **ingestion_operation**
  - Tipo: enum('CSV', 'MANUAL')
  - Descrição: Método de ingestão de dados preferido para a expertise de serviço.
- **status**
  - Tipo: enum('ACTIVE', 'INACTIVE')
  - Descrição: Status da expertise de serviço (ativo ou inativo).
- **created_at**
  - Tipo: timestamp
  - Descrição: Data e hora de criação do registro.
- **updated_at**
  - Tipo: timestamp
  - Descrição: Data e hora da última atualização do registro.
- primary key (id)

### Tabela opn_track
- **id**
  - Tipo: bigint unsigned
  - Descrição: Identificador único do rastreamento de operação.
- **name**
  - Tipo: varchar(50)
  - Descrição: Nome do rastreamento de operação.
- **ingestion_operation**
  - Tipo: enum('CSV', 'MANUAL')
  - Descrição: Método de ingestão de dados preferido para o rastreamento de operação.
- **status**
  - Tipo: enum('ACTIVE', 'INACTIVE')
  - Descrição: Status do rastreamento de operação (ativo ou inativo).
- **created_at**
  - Tipo: timestamp
  - Descrição: Data e hora de criação do registro.
- **updated_at**
  - Tipo: timestamp
  - Descrição: Data e hora da última atualização do registro.
- primary key (id)


## 2.2 Relações entre Tabelas

### Tabela company_and_user
- **company_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the company table by ID.
- **user_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the user table by ID.

### Tabela company_and_opn_track
- **company_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the company table by ID.
- **opn_track_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the opn_track table by ID.

### Tabela company_and_workload
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

### Tabela opn_track_and_expertise
- **opn_track_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the opn_track table by ID.
- **expertise_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the service_expertise table by ID.

### Tabela workload_and_expertise
- **workload_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the workload table by ID.
- **expertise_id**
  - Type: bigint unsigned
  - Description: Foreign key referencing the service_expertise table by ID.

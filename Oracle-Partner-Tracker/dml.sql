use oracle_partner_network;

select * from company;
select * from company_and_opn_track;
select * from company_and_user;
select * from company_and_workload;
select * from flyway_schema_history;
select * from opn_track;
select * from opn_track_and_expertise;
select * from service_expertise;
select * from user;
select * from workload;
select * from workload_and_expertise;
select * from get_all_kpis;

create view DashboardDTO as
SELECT 
    (SELECT COUNT(*) FROM company c WHERE c.opn_status = 'MEMBER') AS qtyPartners,
    (SELECT COUNT(*) FROM company c WHERE c.opn_status = 'MEMBER' AND c.company_status = 'ACTIVE') AS qtyPartnersActive,
    (SELECT COUNT(*) FROM company c WHERE c.opn_status = 'MEMBER' AND c.company_status = 'INACTIVE') AS qtyPartnersInactive,
    (SELECT AVG(repeat_id) FROM (SELECT COUNT(*) AS repeat_id FROM company_and_opn_track c GROUP BY company_id) AS TracksPerPartners) AS averageTracksPerPartners,
    (SELECT COUNT(*) FROM user u WHERE u.status = 'ACTIVE') AS qtyUsers,
    (SELECT COUNT(*) FROM opn_track o WHERE o.status = 'ACTIVE') AS qtyTracks,
    (SELECT COUNT(*) FROM service_expertise s WHERE s.status = 'ACTIVE') AS DashboardDTO;
    
select 
c.name,
count(o.name) qtde
from company c 
left join company_and_opn_track co on co.company_id = c.id
left join opn_track o on o.id = co.opn_track_id 
group by c.name;

-- TERMINAR - averageExpertisePerCompany
select count(w.workload_id) as workload, w.expertise_id from workload_and_expertise w group by w.expertise_id;


-- INSERT

-- SERVICE_EXPERTISE
INSERT INTO `service_expertise`(`id`,`name`,`status`, `created_at`,`updated_at`) VALUES (2,"Oracle Cloud Platform Integration","ACTIVE","2024-01-01 00:00:00","2024-01-01 00:00:00");
INSERT INTO `service_expertise`(`id`,`name`,`status`,`created_at`,`updated_at`) VALUES (6,"Dev Ops on Oracle Cloud","ACTIVE","2024-01-01 00:00:00","2024-01-01 00:00:00");
INSERT INTO `service_expertise`(`id`,`name`,`status`,`created_at`,`updated_at`) VALUES (4,"High Performance Computing and Big Data Solutions on OCI","ACTIVE","2024-01-01 00:00:00","2024-01-01 00:00:00");
INSERT INTO `service_expertise`(`id`,`name`,`status`,`created_at`,`updated_at`) VALUES (102,"PeopleSoft Applications to Oracle Cloud","ACTIVE","2024-01-01 00:00:00","2024-01-01 00:00:00");

-- COMPANY
INSERT INTO `company`(`id`,`name`,`slogan`,`cnpj`,`country`,`state`,`city`,`address`,`opn_status`,`ingestion_operation`,`created_at`,`updated_at`) VALUES (1,"company 1","FEITO","111","brasil","sao paulo","sao paulo","rua x","MEMBER","MANUAL","2024-01-01 00:00:00","2024-01-01 00:00:00");
INSERT INTO `company`(`id`,`name`,`slogan`,`cnpj`,`country`,`state`,`city`,`address`,`opn_status`,`ingestion_operation`,`created_at`,`updated_at`) VALUES (2,"company 2","FEITO","112","brasil","sao paulo","sao paulo","rua x","MEMBER","MANUAL","2024-01-01 00:00:00","2024-01-01 00:00:00");
INSERT INTO `company`(`id`,`name`,`slogan`,`cnpj`,`country`,`state`,`city`,`address`,`opn_status`,`ingestion_operation`,`created_at`,`updated_at`) VALUES (3,"company 3","FEITO","113","brasil","sao paulo","sao paulo","rua x","MEMBER","MANUAL","2024-01-01 00:00:00","2024-01-01 00:00:00");

-- OPN_TRACK
INSERT INTO `opn_track`(`id`,`name`,`ingestion_operation`,`status`,`created_at`,`updated_at`) VALUES (1,"cloud","MANUAL","ACTIVE","2024-01-01 00:00:00","2024-01-01 00:00:00");
INSERT INTO `opn_track`(`id`,`name`,`ingestion_operation`,`status`,`created_at`,`updated_at`) VALUES (2,"service","MANUAL","ACTIVE","2024-01-01 00:00:00","2024-01-01 00:00:00");
INSERT INTO `opn_track`(`id`,`name`,`ingestion_operation`,`status`,`created_at`,`updated_at`) VALUES (3,"healfcare","MANUAL","ACTIVE","2024-01-01 00:00:00","2024-01-01 00:00:00");

-- USER
INSERT INTO `user`(`id`,`email`,`password`,`name`,`role`,`membership_type`,`ingestion_operation`,`status`,`created_at`,`updated_at`) VALUES (1,"lucas@gmail.com","123","Lucas","USER","PRINCIPAL","MANUAL","ACTIVE","2024-01-01 00:00:00","2024-01-01 00:00:00");

-- WORKLOAD
INSERT INTO `workload`(`id`,`name`,`description`,`ingestion_operation`,`status`,`created_at`,`updated_at`) VALUES (1,"AI infrastructure","AI infrastructure","MANUAL","ACTIVE","2024-01-01 00:00:00","2024-01-01 00:00:00");
INSERT INTO `workload`(`id`,`name`,`description`,`ingestion_operation`,`status`,`created_at`,`updated_at`) VALUES (2,"Application Integration","Application Integration","MANUAL","ACTIVE","2024-01-01 00:00:00","2024-01-01 00:00:00");

-- WORKLOAD AND EXPERTISE
INSERT INTO `workload_and_expertise`(`workload_id`,`expertise_id`) VALUES (1,2);
INSERT INTO `workload_and_expertise`(`workload_id`,`expertise_id`) VALUES (2,4);
INSERT INTO `workload_and_expertise`(`workload_id`,`expertise_id`) VALUES (1,4);

-- OPN_TRACK_AND_EXPERTISE
INSERT INTO `opn_track_and_expertise`(`id`,`opn_track_id`,`expertise_id`) VALUES (1,1,2);
INSERT INTO `opn_track_and_expertise`(`id`,`opn_track_id`,`expertise_id`) VALUES (2,2,6);
INSERT INTO `opn_track_and_expertise`(`id`,`opn_track_id`,`expertise_id`) VALUES (3,2,4);
INSERT INTO `opn_track_and_expertise`(`id`,`opn_track_id`,`expertise_id`) VALUES (4,3,102);

-- COMPANY_AND_OPN_TRACK
INSERT INTO `company_and_opn_track`(`id`,`company_id`,`opn_track_id`)VALUES(1,1,1);
INSERT INTO `company_and_opn_track`(`id`,`company_id`,`opn_track_id`)VALUES(2,2,2);
INSERT INTO `company_and_opn_track`(`id`,`company_id`,`opn_track_id`)VALUES(3,3,3);
INSERT INTO `company_and_opn_track`(`id`,`company_id`,`opn_track_id`)VALUES(4,2,3);
INSERT INTO `company_and_opn_track`(`id`,`company_id`,`opn_track_id`)VALUES(5,2,1);

-- COMPANY_AND_USER
INSERT INTO `company_and_user`(`id`,`company_id`,`user_id`) VALUES (1,1,1);

-- COMPANY_AND_WORKLOAD
INSERT INTO `company_and_workload`(`id`,`company_id`,`workload_id`,`score`,`status`,`created_at`,`expiration_date`) VALUES (1,1,1,0.80,"PASSED","2024-04-09 00:00:00","2026-04-09 00:00:00");
INSERT INTO `company_and_workload`(`id`,`company_id`,`workload_id`,`score`,`status`,`created_at`,`expiration_date`) VALUES (2,2,2,0.70,"PASSED","2024-04-09 00:00:00","2026-04-09 00:00:00");
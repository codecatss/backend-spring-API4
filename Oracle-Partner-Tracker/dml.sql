use oracle_partner_network;

--drop view if exists DashboardDTO;
-- VIEW's
-- create view DashboardDTO as
-- SELECT
--     (SELECT COUNT(*) FROM company c WHERE c.opn_status = 'MEMBER') AS qtyPartners,
--     (SELECT COUNT(*) FROM company c WHERE c.opn_status = 'MEMBER' AND c.company_status = 'ACTIVE') AS qtyPartnersActive,
--     (SELECT COUNT(*) FROM company c WHERE c.opn_status = 'MEMBER' AND c.company_status = 'INACTIVE') AS qtyPartnersInactive,
--     (SELECT AVG(repeat_id) FROM (SELECT COUNT(*) AS repeat_id FROM company_and_opn_track c GROUP BY company_id) AS TracksPerPartners) AS averageTracksPerPartners,
--     (SELECT COUNT(*) FROM user u WHERE u.status = 'ACTIVE') AS qtyUsers,
--     (SELECT COUNT(*) FROM opn_track o WHERE o.status = 'ACTIVE') AS qtyTracks,
--     (SELECT COUNT(*) FROM service_expertise s WHERE s.status = 'ACTIVE') AS DashboardDTO;

-- create view TrackPerCompany as
-- select
-- o.name,
-- count(c.name) qtde
-- from company c
-- left join company_and_opn_track co on co.company_id = c.id
-- left join opn_track o on o.id = co.opn_track_id
-- group by o.name;


drop view if exists StatePerCompany;
create view StatePerCompany as
select
c.state,
count(c.name) qtde
from company c
group by c.state;

-- TERMINAR - averageExpertisePerCompany
select count(w.workload_id) as workload, w.expertise_id from workload_and_expertise w group by w.expertise_id
;


drop view if exists company_expertise_user_count;
-- Cria a view que trará a quantidade de usuários por empresa com certificação e o progresso de cada um
create view company_expertise_user_count AS
SELECT 
    c.name AS company_name,
    c.state AS company_state,
    e.name AS expertise_name,
    t.name AS track_name,
    COUNT(DISTINCT cert.id) AS total_certifications,
    COUNT(DISTINCT CASE WHEN uc.status = 'PASSED' THEN cert.id END) AS passed_certifications,
    (COUNT(DISTINCT CASE WHEN uc.status = 'PASSED' THEN cert.id END) / COUNT(DISTINCT cert.id)) * 100 AS completion_percentage
FROM 
    company c
JOIN 
    company_expertise ce ON c.id = ce.company_id
JOIN 
    service_expertise e ON ce.expertise_id = e.id
JOIN 
    expertise_certification ec ON e.id = ec.expertise_id
JOIN 
    certification cert ON ec.certification_id = cert.id
LEFT JOIN 
    employee_certification uc ON uc.certification_id = cert.id
JOIN 
    opn_track_and_expertise otae ON e.id = otae.expertise_id
JOIN 
    opn_track t ON otae.opn_track_id = t.id
GROUP BY 
    c.name, c.state, e.name, t.name;
SELECT * FROM company_expertise_user_count
;


drop view if exists view_change_history;
create view view_change_history as
select
    ch.id, ch.changed_by_partner_id, ch.table_name, ch.record_id, coalesce(c.name, se.name) as name
from
    change_history ch
left join company c
    on c.id = case when ch.table_name = 'COMPANY' then ch.record_id else 0 end
left join service_expertise se
    on se.id = case when ch.table_name = 'EXPERTISE' then ch.record_id else 0 end
;



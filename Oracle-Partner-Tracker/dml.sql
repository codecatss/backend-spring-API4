use oracle_partner_network;

-- drop view if exists DashboardDTO;
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

--- Drop the existing view if it exists
DROP VIEW IF EXISTS company_expertise_user_count;



-- Drop the existing view if it exists
DROP VIEW IF EXISTS company_expertise_user_count;

-- Cria a view que trará a quantidade de usuários por empresa com certificação, progresso de cada um,
-- o CNPJ da empresa e o workload relacionado à expertise da empresa
CREATE VIEW company_expertise_user_count AS
SELECT
    c.name AS company_name,
    c.cnpj AS company_cnpj,  -- Adiciona o CNPJ da empresa
    c.state AS company_state,
    e.name AS expertise_name,
    t.name AS track_name,
    w.name AS workload_name,
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
        JOIN
    workload_and_expertise wae ON e.id = wae.expertise_id
        JOIN
    workload w ON wae.workload_id = w.id
GROUP BY
    c.name, c.cnpj, c.state, e.name, t.name, w.name;

-- Seleciona tudo da view criada para visualização
SELECT * FROM company_expertise_user_count;


-- Seleciona tudo da view criada para visualização
SELECT * FROM company_expertise_user_count;

drop view if exists view_change_history;
create view view_change_history as
select
    cp.username,
    ch.change_type,
    ch.table_name,
    ch.record_id,
    coalesce(c.name,e.name,se.name,ot.name,w.name,ce.name) as name,
    ch.old_value,
    ch.new_value,
    ch.changed_at
from
    change_history ch
left join partner cp
    on cp.id = ch.changed_by_partner_id
left join company c
    on c.id = case when upper(ch.table_name) = 'COMPANY' then ch.record_id else 0 end
left join employee e
    on e.id = case when upper(ch.table_name) = 'EMPLOYEE' then ch.record_id else 0 end
left join service_expertise se
    on se.id = case when upper(ch.table_name) = 'EXPERTISE' then ch.record_id else 0 end
left join opn_track ot
    on ot.id = case when upper(ch.table_name) = 'TRACK' then ch.record_id else 0 end
left join workload w
    on w.id = case when upper(ch.table_name) = 'WORKLOAD' then ch.record_id else 0 end
left join certification ce
    on ce.id = case when upper(ch.table_name) = 'CERTIFICATION' then ch.record_id else 0 end
order by
    ch.changed_at asc
;


use oracle_partner_network;

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

create view StatePerCompany as
select
c.state,
count(c.name) qtde
from company c
group by c.state;

-- TERMINAR - averageExpertisePerCompany
select count(w.workload_id) as workload, w.expertise_id from workload_and_expertise w group by w.expertise_id;




-- Cria a view que trará a quantidade de usuários por empresa com certificação e o progresso de cada um
CREATE VIEW company_expertise_user_count AS
SELECT c.name AS company_name,
       c.state AS company_state,
       se.name AS expertise_name,
       ot.name AS track_name,
       ROUND((COUNT(uc.user_id) / cer.certification_count) * 100, 2) AS progress_percentage,
       DATEDIFF(uc.expires_at, CURRENT_DATE()) AS deadline
FROM company c
JOIN company_expertise ce ON c.id = ce.company_id
JOIN service_expertise se ON ce.expertise_id = se.id
JOIN opn_track_and_expertise otae ON se.id = otae.expertise_id
JOIN opn_track ot ON otae.opn_track_id = ot.id
LEFT JOIN (SELECT expertise_id,
                  COUNT(certification_id) AS certification_count
           FROM expertise_certification
           GROUP BY expertise_id) AS cer ON ce.expertise_id = cer.expertise_id
LEFT JOIN user_certification uc ON ce.expertise_id = uc.certification_id
JOIN certification ctf ON uc.certification_id = ctf.id
WHERE uc.status = 'PASSED'
GROUP BY c.id, c.state, se.id, ot.id, cer.certification_count, uc.expires_at;

SELECT * FROM company_expertise_user_count;

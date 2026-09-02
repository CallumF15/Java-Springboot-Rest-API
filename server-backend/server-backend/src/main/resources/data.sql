-- Sectors
INSERT INTO sector (id, name)
VALUES
  (1, 'Finance'),
  (2, 'Healthcare'),
  (3, 'Technology');

-- Finance industries
INSERT INTO industry (name, sector_id)
VALUES
  ('Banking', 1),
  ('Insurance', 1),
  ('Investment Management', 1),
  ('Accounting', 1),
  ('Financial Technology', 1);

-- Healthcare industries
INSERT INTO industry (name, sector_id)
VALUES
  ('Biotechnology', 2),
  ('Pharmaceuticals', 2),
  ('Medical Devices', 2),
  ('Healthcare Services', 2),
  ('Health Technology', 2);

-- Technology industries
INSERT INTO industry (name, sector_id)
VALUES
  ('Software Development', 3),
  ('Cybersecurity', 3),
  ('Cloud Computing', 3),
  ('Artificial Intelligence', 3),
  ('Information Technology', 3);


-- Show industries with their associated sector
--
-- SELECT
--   industry.id AS industry_id,
--   industry.name AS industry_name,
--   sector.id AS sector_id,
--   sector.name AS sector_name
-- FROM industry
--        JOIN sector
--             ON industry.sector_id = sector.id
-- ORDER BY sector.name, industry.name;
--
-- -- Count industries in each sector
-- SELECT
--   sector.name AS sector_name,
--   COUNT(industry.id) AS industry_count
-- FROM sector
--        LEFT JOIN industry
--                  ON industry.sector_id = sector.id
-- GROUP BY sector.id, sector.name
-- ORDER BY sector.name;

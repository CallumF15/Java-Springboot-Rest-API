-- Sectors
INSERT INTO sector (id, name) VALUES (1, 'Technology');
INSERT INTO sector (id, name) VALUES (2, 'Finance');
INSERT INTO sector (id, name) VALUES (3, 'Healthcare');

INSERT INTO sector (name) VALUES ('Finance'), ('Healthcare'), ('Tech');
INSERT INTO industry (name, sector_id) VALUES ('Banking', 1), ('Insurance', 1), ('Biotech', 2);

-- Industries
INSERT INTO industry (id, name, sector_id) VALUES (1, 'Software Development', 1);
INSERT INTO industry (id, name, sector_id) VALUES (2, 'Cybersecurity', 1);
INSERT INTO industry (id, name, sector_id) VALUES (3, 'Banking', 2);
INSERT INTO industry (id, name, sector_id) VALUES (4, 'Insurance', 2);
INSERT INTO industry (id, name, sector_id) VALUES (5, 'Hospitals', 3);
INSERT INTO industry (id, name, sector_id) VALUES (6, 'Pharmaceuticals', 3);

-- Optional example business
INSERT INTO business (id, name, industry_id) VALUES (1, 'TechCorp', 1);
INSERT INTO business (id, name, industry_id) VALUES (2, 'BankOfWorld', 3);

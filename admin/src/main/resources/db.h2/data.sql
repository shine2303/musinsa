INSERT INTO category (name) VALUES
    ('상의'),
    ('아우터'),
    ('바지'),
    ('스니커즈'),
    ('가방'),
    ('모자'),
    ('양말'),
    ('액세서리');

INSERT INTO brand (name) VALUES
    ('A'),
    ('B'),
    ('C'),
    ('D'),
    ('E'),
    ('F'),
    ('G'),
    ('H'),
    ('I');

INSERT INTO product (name, price, category_id, brand_id, is_active) VALUES
('A 브랜드 상의', 11200, 1, 1, true),
('A 브랜드 아우터', 5500, 2, 1, true),
('A 브랜드 바지', 4200, 3, 1, true),
('A 브랜드 스니커즈', 9000, 4, 1, true),
('A 브랜드 가방', 2000, 5, 1, true),
('A 브랜드 모자', 1700, 6, 1, true),
('A 브랜드 양말', 1800, 7, 1, true),
('A 브랜드 액세서리', 2300, 8, 1, true),

('B 브랜드 상의', 10500, 1, 2, true),
('B 브랜드 아우터', 5900, 2, 2, true),
('B 브랜드 바지', 3800, 3, 2, true),
('B 브랜드 스니커즈', 9100, 4, 2, true),
('B 브랜드 가방', 2100, 5, 2, true),
('B 브랜드 모자', 2000, 6, 2, true),
('B 브랜드 양말', 2000, 7, 2, true),
('B 브랜드 액세서리', 2200, 8, 2, true),

('C 브랜드 상의', 10000, 1, 3, true),
('C 브랜드 아우터', 6200, 2,3, true),
('C 브랜드 바지', 3300, 3, 3, true),
('C 브랜드 스니커즈', 9200, 4, 3, true),
('C 브랜드 가방', 2200, 5, 3, true),
('C 브랜드 모자', 1900, 6, 3, true),
('C 브랜드 양말', 2200, 7, 3, true),
('C 브랜드 액세서리', 2100, 8, 3, true),

('D 브랜드 상의', 10100, 1, 4, true),
('D 브랜드 아우터', 5100, 2, 4, true),
('D 브랜드 바지', 3000, 3, 4, true),
('D 브랜드 스니커즈', 9500, 4, 4, true),
('D 브랜드 가방', 2500, 5, 4, true),
('D 브랜드 모자', 1500, 6, 4, true),
('D 브랜드 양말', 2400, 7, 4, true),
('D 브랜드 액세서리', 2000, 8, 4, true),

('E 브랜드 상의', 10700, 1, 5, true),
('E 브랜드 아우터', 5000, 2, 5, true),
('E 브랜드 바지', 3800, 3, 5, true),
('E 브랜드 스니커즈', 9900, 4, 5, true),
('E 브랜드 가방', 2300, 5, 5, true),
('E 브랜드 모자', 1800, 6, 5, true),
('E 브랜드 양말', 2100, 7, 5, true),
('E 브랜드 액세서리', 2100, 8, 5, true),

('F 브랜드 상의', 11200, 1, 6, true),
('F 브랜드 아우터', 7200, 2, 6, true),
('F 브랜드 바지', 4000, 3, 6, true),
('F 브랜드 스니커즈', 9300, 4, 6, true),
('F 브랜드 가방', 2100, 5, 6, true),
('F 브랜드 모자', 1600, 6, 6, true),
('F 브랜드 양말', 2300, 7, 6, true),
('F 브랜드 액세서리', 1900, 8, 6, true),

('G 브랜드 상의', 10500, 1, 7, true),
('G 브랜드 아우터', 5800, 2, 7, true),
('G 브랜드 바지', 3900, 3, 7, true),
('G 브랜드 스니커즈', 9000, 4, 7, true),
('G 브랜드 가방', 2200, 5, 7, true),
('G 브랜드 모자', 1700, 6, 7, true),
('G 브랜드 양말', 2100, 7, 7, true),
('G 브랜드 액세서리', 2000, 8, 7, true),

('H 브랜드 상의', 10800, 1, 8, true),
('H 브랜드 아우터', 6300, 2, 8, true),
('H 브랜드 바지', 3100, 3, 8, true),
('H 브랜드 스니커즈', 9700, 4, 8, true),
('H 브랜드 가방', 2100, 5, 8, true),
('H 브랜드 모자', 1600, 6, 8, true),
('H 브랜드 양말', 2000, 7, 8, true),
('H 브랜드 액세서리', 2000, 8, 8, true),

('I 브랜드 상의', 11400, 1, 9, true),
('I 브랜드 아우터', 6700, 2, 9, true),
('I 브랜드 바지', 3200, 3, 9, true),
('I 브랜드 스니커즈', 9500, 4, 9, true),
('I 브랜드 가방', 2400, 5, 9, true),
('I 브랜드 모자', 1700, 6, 9, true),
('I 브랜드 양말', 1700, 7, 9, true),
('I 브랜드 액세서리', 2400, 8, 9, true)
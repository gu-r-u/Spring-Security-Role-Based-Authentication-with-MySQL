INSERT INTO users (username, password, role, active)
VALUES ('ram', '$2a$10$SnyiVmlTTyt1qnvVA5xFh.Sps8g8YMRwBqKRlBjN23ApezRbwEuZi', 'USER', 1)
ON DUPLICATE KEY UPDATE username=username;

INSERT INTO users (username, password, role, active)
VALUES ('admin', '$2a$10$lTEByaFGBve48qYDfpUy9.15CaLsRHuh3Nm0MYXrAWcgct1Mkqf.K', 'ADMIN', 1)
ON DUPLICATE KEY UPDATE username=username;

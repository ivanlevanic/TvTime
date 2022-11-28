delete from tv_show_data;
delete from authorities;
delete from users;

insert into users (username, password, enabled) values
                                                    ('admin', '$2a$10$F76NApcWn/.cZZLvwpgPdOVgjWd0m17t2PhU2pEnYCf5RYpSO.rem', 1),
                                                    ('watcher', '$2a$10$F76NApcWn/.cZZLvwpgPdOVgjWd0m17t2PhU2pEnYCf5RYpSO.rem', 1);

insert into authorities (username, authority) values
                                                  ('admin', 'ROLE_ADMIN'),
                                                  ('admin', 'ROLE_USER'),
                                                  ('watcher', 'ROLE_USER');

insert into tv_show_data (id, name, price, streamingservice, seasons) values
    (1, 'got', 12.99, 'Netflix', 7),
    (2, 'got', 7.99, 'Disney+', 8),
    (3, 'got', 5.99, 'HboMAX', 8),
    (4, 'fargo', 7.99, 'Disney+', 4),
    (5, 'fargo', 5.99, 'HboMAX', 4),
    (6, 'breakingbad', 12.99, 'Netflix', 5),
    (7, 'hotd', 5.99, 'HboMAX', 1),
    (8, 'hotd', 7.99, 'Disney+', 1),
    (9, 'lost', 7.99, 'Disney+', 4),
    (10, 'blackmirror', 12.99, 'Netflix', 5),
    (11, 'blackmirror', 7.99, 'Disney+', 4),
    (12, 'blackmirror', 5.99, 'HboMAX', 3);

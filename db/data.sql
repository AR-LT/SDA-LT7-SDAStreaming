INSERT INTO genres (id, name) VALUES
(1, "Country"),
(2, "Pop");

INSERT INTO authors (id, name, created) VALUES
(1, "Camila Cabello", NOW()),
(2, "Lil Nas", NOW()),
(3, "George Erza", NOW()),
(4, "Billie Eilish", NOW()),
(5, "Ed Sheeran", NOW()),
(6, "Lady Gaga", NOW());

INSERT INTO songs (id, title, length, author_id, genre_id) VALUES
(1, "Senorita", 191, 1, 2),
(2, "Havana", 217, 1, 2),
(3, "Old Town Road", 157, 2, 2),
(4, "Shotgun", 211, 3, 2),
(5, "bad guy", 194, 4, 2),
(6, "xanny", 243, 4, 2),
(7, "!!!!!!!", 13, 4, 2),
(8, "8", 177, 4, 2),
(9, "I Don't Care", 219, 5, 2),
(10, "Shallow", 215, 6, 1),
(11, "Rain On Me", 193, 6, 2),
(12, "Plastic Doll", 221, 6, 2);

INSERT INTO albums (id, name, author_id, releaseDate) VALUES
(1, "A Star Is Born Soundrack", 6, "2018-11-04"),
(2, "Chromatica", 6, "2020-05-28");

INSERT INTO album_songs (album_id, song_id) VALUES
(1, 10),
(2, 11),
(2, 12);

INSERT INTO users (email, password, username, birthdate) VALUE
("john.smith@email.test", SHA1("test123"), "jsmith", "1983-08-15"),
("vardenis.pavardenis@yahoo.no", SHA1("pwd555"), "vpav", "2011-02-17");

--
-- Table structure for table `genres`
--

DROP TABLE IF EXISTS `genres`;
CREATE TABLE `genres` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `genre_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
CREATE TABLE `authors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `created` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `songs`
--

DROP TABLE IF EXISTS `songs`;
CREATE TABLE `songs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `length` int NOT NULL,
  `lyrics` text,
  `author_id` int NOT NULL,
  `genre_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_song_author_idx` (`author_id`),
  KEY `fk_song_genre_idx` (`genre_id`),
  CONSTRAINT `fk_song_author` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`),
  CONSTRAINT `fk_song_genre` FOREIGN KEY (`genre_id`) REFERENCES `genres` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `albums`
--

DROP TABLE IF EXISTS `albums`;
CREATE TABLE `albums` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `author_id` int NOT NULL,
  `releaseDate` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_album_author` (`author_id`),
  CONSTRAINT `fk_album_author` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `album_songs`
--

DROP TABLE IF EXISTS `album_songs`;
CREATE TABLE `album_songs` (
  `album_id` int NOT NULL,
  `song_id` int NOT NULL,
  PRIMARY KEY (`album_id`,`song_id`),
  KEY `fk_album_songs_songs_idx` (`song_id`),
  CONSTRAINT `fk_album_songs_albums` FOREIGN KEY (`album_id`) REFERENCES `albums` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_album_songs_songs` FOREIGN KEY (`song_id`) REFERENCES `songs` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(40) NOT NULL,
  `username` varchar(100) NOT NULL,
  `birthdate` date DEFAULT NULL,
  `created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `playlists`
--

DROP TABLE IF EXISTS `playlists`;
CREATE TABLE `playlists` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `private` tinyint NOT NULL DEFAULT '1',
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_name` (`name`,`user_id`),
  KEY `fk_playlists_users_idx` (`user_id`),
  CONSTRAINT `fk_playlists_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

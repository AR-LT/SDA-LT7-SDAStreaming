package dev.sdacademy.sdastreaming;

import dev.sdacademy.sdastreaming.entity.Author;
import dev.sdacademy.sdastreaming.entity.Song;
import dev.sdacademy.sdastreaming.repository.AuthorCRUDRepository;
import dev.sdacademy.sdastreaming.repository.CRUDRepository;
import dev.sdacademy.sdastreaming.repository.SongCRUDRepository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main implements AutoCloseable {

    private static final String HOST = "localhost";  // 127.0.0.1 | localhost
    private static final String DB = "sdastreaming";
    private static final String USER = "root";
    private static final String PASSWORD = "gItara0130_";

    public static void main(String[] args) throws Exception {
        try (Main main = new Main()) {
            main.run();
        }
    }

    private final Connection connection;
    private final CRUDRepository<Song> songRepository;
    private final CRUDRepository<Author> authorRepository;

    public Main() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DB, USER, PASSWORD);
            songRepository = new SongCRUDRepository(connection);
            authorRepository = new AuthorCRUDRepository(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public void run() {
        Song newSong = new Song();
        newSong.setId(19);
        newSong.setTitle("some song");
        newSong.setLength(100);
        newSong.setAuthorId(1);
        newSong.setGenreId(2);
//        songRepository.save(newSong);

        songRepository.findAll().forEach(song -> {
            System.out.println(song);
        });

        Author newAuthor = new Author();
        newAuthor.setId(7);
        newAuthor.setName("James Bland");
        newAuthor.setCreated(Date.valueOf(LocalDate.now()));
//        authorRepository.save(newAuthor);
//        authorRepository.delete(7);
        authorRepository.findAll().forEach(author -> {
            System.out.println(author);
        });
    }
}

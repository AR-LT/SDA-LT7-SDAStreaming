package dev.sdacademy.sdastreaming;

import dev.sdacademy.sdastreaming.repository.AuthorCRUDrepository;
import dev.sdacademy.sdastreaming.repository.GenreCRUDrepository;
import dev.sdacademy.sdastreaming.repository.SongCRUDRepository;
import dev.sdacademy.sdastreaming.repository.UserCRUDrepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main implements AutoCloseable {

    private static final String HOST = "localhost";  // 127.0.0.1 | localhost
    private static final String DB = "music";
    private static final String USER = "adi";
    private static final String PASSWORD = "BMWokm011";

    public static void main(String[] args) throws Exception {
        try (Main main = new Main()) {
            main.run();
        }
    }

    private final Connection connection;
    private final SongCRUDRepository songRepository;
    private final AuthorCRUDrepository authorRepository;
    private final UserCRUDrepository userRepository;
    private final GenreCRUDrepository genreRepository;

    public Main() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DB, USER, PASSWORD);
            songRepository = new SongCRUDRepository(connection);
            authorRepository = new AuthorCRUDrepository(connection);
            userRepository = new UserCRUDrepository(connection);
            genreRepository = new GenreCRUDrepository(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public void run() {
        //---------------
        // Read Songs
        //---------------
        songRepository.findAll().forEach(song -> {
            System.out.println(song);
        });
        //---------------
        //Create Song
        //---------------
     /*   Song newSong = new Song();
        newSong.setTitle("The Good Ones");
        newSong.setLength(227);
        newSong.setAuthorId(2);
        newSong.setGenreId(1);
        songRepository.save(newSong);
        songRepository.findAll().forEach(song -> {
            System.out.println(song);
        });*/
        //---------------
        //Delete Song
        //---------------
/*        songRepository.delete(14);
        songRepository.findAll().forEach(song -> {
            System.out.println(song);
        });*/
        //---------------
        //Update Song
        //---------------
/*        Song newSong = new Song();
        newSong.setId(7);
        newSong.setTitle("?!?!?!?!?!?");
        newSong.setLength(200);
        newSong.setAuthorId(2);
        newSong.setGenreId(2);
        songRepository.save(newSong);
        songRepository.findAll().forEach(song -> {
            System.out.println(song);
        });*/

        //---------------
        // Read Authors
        //---------------
        authorRepository.findAll().forEach(author -> {
            System.out.println(author);
        });
        //---------------
        //Create Author
        //---------------
/*        Author newAuthor = new Author();
        newAuthor.setName("Gabby Barrett");
        newAuthor.setCreated(Date.valueOf(LocalDate.now()));
        authorRepository.save(newAuthor);
        authorRepository.findAll().forEach(author -> {
            System.out.println(author);
        });*/
        //---------------
        //Delete Author
        //---------------
/*        authorRepository.delete(7);
        authorRepository.findAll().forEach(author -> {
            System.out.println(author);
        });*/
        //---------------
        //Update Author
        //---------------
/*        Author newAuthor = new Author();
        newAuthor.setId(7);
        newAuthor.setName("Gabby Barrett");
        newAuthor.setCreated(Date.valueOf(LocalDate.now()));
        authorRepository.save(newAuthor);
        authorRepository.findAll().forEach(author -> {
            System.out.println(author);
        });*/

        //---------------
        // Read Users
        //---------------
        userRepository.findAll().forEach(user -> {
            System.out.println(user);
        });
        //---------------
        //Create User
        //---------------
/*        User newUser = new User();
        newUser.setEmail("jonas.jonaitis@gmail.com");
        newUser.setPassword("dangus");
        newUser.setUsername("jonjon");
        newUser.setBirthdate(Date.valueOf(LocalDate.of(2015, 2, 15)));
        userRepository.save(newUser);
        userRepository.findAll().forEach(user -> {
            System.out.println(user);
        });*/
        //---------------
        //Delete User
        //---------------
/*        userRepository.delete(3);
        userRepository.findAll().forEach(user -> {
            System.out.println(user);
        });*/
        //---------------
        //Update User
        //---------------
/*        User newUser = new User();
        newUser.setId(4);
        newUser.setEmail("jonas.jonaitis@gmail.com");
        newUser.setPassword("melynasdangus");
        newUser.setUsername("jonjon");
        newUser.setBirthdate(Date.valueOf(LocalDate.of(2010, 4, 15)));
        userRepository.save(newUser);
        userRepository.findAll().forEach(user -> {
            System.out.println(user);

        });*/

        //---------------
        // Read Genres
        //---------------
        genreRepository.findAll().forEach(genre -> {
            System.out.println(genre);
        });
        //---------------
        //Create Genre
        //---------------
/*        Genre newGenre = new Genre();
        newGenre.setName("Jazzzz");
        genreRepository.save(newGenre);
        genreRepository.findAll().forEach(genre -> {
            System.out.println(genre);
        });*/
        //---------------
        //Delete Genre
        //---------------
/*        genreRepository.delete(3);
        genreRepository.findAll().forEach(genre -> {
            System.out.println(genre);
        });*/
        //---------------
        //Update Genre
        //---------------
/*        Genre newGenre = new Genre();
        newGenre.setId(4);
        newGenre.setName("Jazz");
        genreRepository.save(newGenre);
        genreRepository.findAll().forEach(genre -> {
            System.out.println(genre);

        });*/

    }
}

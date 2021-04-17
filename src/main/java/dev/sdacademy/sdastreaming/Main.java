package dev.sdacademy.sdastreaming;

import dev.sdacademy.sdastreaming.entity.Song;
import dev.sdacademy.sdastreaming.repository.SongCRUDRepository;

import java.sql.*;

public class Main implements AutoCloseable {

    private static final String HOST = "localhost";  // 127.0.0.1 | localhost
    private static final String DB = "sdastreaming";
    private static final String USER = "root";
    private static final String PASSWORD = "Dd%UB#s7x5yxDrhD";

    public static void main(String[] args) throws Exception {
        try (Main main = new Main()) {
            main.run();
        }
    }

    private final Connection connection;
    private final SongCRUDRepository songRepository;

    public Main() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DB, USER, PASSWORD);
            songRepository = new SongCRUDRepository(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public void run() {
//        Song newSong = new Song();
//        newSong.setTitle("test \"new\" song");
//        newSong.setLength(100);
//        newSong.setAuthorId(1);
//        newSong.setGenreId(2);
//        songRepository.create(newSong);

//        songRepository.delete(18);

        songRepository.findAll().forEach(song -> {
            System.out.println(song);
        });
    }
}

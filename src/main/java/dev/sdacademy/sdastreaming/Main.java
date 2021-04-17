package dev.sdacademy.sdastreaming;

import dev.sdacademy.sdastreaming.repository.SongCRUDRepository;

import java.sql.*;

public class Main {

    private static final String HOST = "localhost";  // 127.0.0.1 | localhost
    private static final String DB = "sdastreaming";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DB, USER, PASSWORD)) {
            SongCRUDRepository songRepository = new SongCRUDRepository(conn);
            songRepository.findAll().forEach(song -> {
                System.out.println(song.getTitle());
            });
        }
    }
}

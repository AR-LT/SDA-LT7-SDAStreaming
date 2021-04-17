package dev.sdacademy.sdastreaming.repository;

import dev.sdacademy.sdastreaming.entity.Song;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SongCRUDRepository {

    private final Connection connection;

    public SongCRUDRepository(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    // READ
    public List<Song> findAll() {
        List<Song> songs = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM songs");
            while (resultSet.next()) {
                songs.add(toSong(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return songs;
    }

    // UPDATE
    // DELETE

    private Song toSong(ResultSet rs) throws SQLException {
        Song song = new Song();
        song.setId(rs.getInt("id"));
        song.setTitle(rs.getString("title"));
        song.setLength(rs.getInt("length"));
        song.setLyrics(rs.getString("lyrics"));
        song.setAuthorId(rs.getInt("author_id"));
        song.setGenreId(rs.getInt("genre_id"));
        return song;
    }
}

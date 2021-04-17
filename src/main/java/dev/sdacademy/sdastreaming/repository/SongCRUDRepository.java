package dev.sdacademy.sdastreaming.repository;

import dev.sdacademy.sdastreaming.entity.Song;
import dev.sdacademy.sdastreaming.exception.SDAStreamingException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongCRUDRepository {

    private final Connection connection;

    public SongCRUDRepository(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void create(Song song) {
        String sql = "INSERT INTO songs (title, length, lyrics, author_id, genre_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, song.getTitle());
            stmt.setInt(2, song.getLength());
            stmt.setString(3, song.getLyrics());
            stmt.setInt(4, song.getAuthorId());
            stmt.setInt(5, song.getGenreId());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
    }

    // READ
    public List<Song> findAll() {
        List<Song> songs = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM songs");
            while (resultSet.next()) {
                songs.add(toSong(resultSet));
            }
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
        return songs;
    }

    // UPDATE
    public void update(Song song) {
        String sql = "UPDATE songs SET title=?, length=?, lyrics=?, author_id=?, genre_id=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, song.getTitle());
            stmt.setInt(2, song.getLength());
            stmt.setString(3, song.getLyrics());
            stmt.setInt(4, song.getAuthorId());
            stmt.setInt(5, song.getGenreId());
            stmt.setInt(6, song.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
    }

    // DELETE
    public void delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM songs WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
    }

    public void save(Song song) {
        if (song.getId() != null) {
            update(song);
        } else {
            create(song);
        }
    }

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

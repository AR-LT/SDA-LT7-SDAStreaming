package dev.sdacademy.sdastreaming.repository;


import dev.sdacademy.sdastreaming.entity.Genre;
import dev.sdacademy.sdastreaming.exception.SDAStreamingException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenreCRUDrepository {

    private final Connection connection;

    public GenreCRUDrepository(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void create(Genre genre) {
        String sql = "INSERT INTO genres (name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, genre.getName());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
    }

    // READ
    public List<Genre> findAll() {
        List<Genre> genre = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM genres");
            while (resultSet.next()) {
                genre.add(toGenre(resultSet));
            }
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
        return genre;
    }

    // UPDATE
    public void update(Genre genre) {
        String sql = "UPDATE genres SET name=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, genre.getName());
            stmt.setInt(2, genre.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
    }

    // DELETE
    public void delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM genres WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
    }

    public void save(Genre genre) {
        if (genre.getId() != null) {
            update(genre);
        } else {
            create(genre);
        }
    }

    private Genre toGenre(ResultSet rs) throws SQLException {
        Genre genre = new Genre();
        genre.setId(rs.getInt("id"));
        genre.setName(rs.getString("name"));
        return genre;
    }

}

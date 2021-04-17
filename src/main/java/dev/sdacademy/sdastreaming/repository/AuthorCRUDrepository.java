package dev.sdacademy.sdastreaming.repository;

import dev.sdacademy.sdastreaming.entity.Author;
import dev.sdacademy.sdastreaming.exception.SDAStreamingException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AuthorCRUDrepository {

    private final Connection connection;

    public AuthorCRUDrepository(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void create(Author song) {
        String sql = "INSERT INTO authors (name, created) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, song.getName());
            stmt.setDate(2, song.getCreated());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
    }

    // READ
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM authors");
            while (resultSet.next()) {
                authors.add(toAuthor(resultSet));
            }
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
        return authors;
    }

    // UPDATE
    public void update(Author author) {
        String sql = "UPDATE authors SET name=?, created=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, author.getName());
            stmt.setDate(2, author.getCreated());
            stmt.setInt(3, author.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
    }

    // DELETE
    public void delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM authors WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
    }

    public void save(Author author) {
        if (author.getId() != null) {
            update(author);
        } else {
            create(author);
        }
    }

    private Author toAuthor(ResultSet rs) throws SQLException {
        Author author = new Author();
        author.setId(rs.getInt("id"));
        author.setName(rs.getString("name"));
        author.setCreated(rs.getDate("created"));
        return author;
    }

}

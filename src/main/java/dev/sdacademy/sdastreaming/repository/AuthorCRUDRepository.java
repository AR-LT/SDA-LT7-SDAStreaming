package dev.sdacademy.sdastreaming.repository;

import dev.sdacademy.sdastreaming.entity.Author;
import dev.sdacademy.sdastreaming.exception.SDAStreamingException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorCRUDRepository implements CRUDRepository<Author> {

    private final Connection connection;

    public AuthorCRUDRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Author author) {
        String sql = "INSERT INTO authors (name, created) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, author.getName());
            stmt.setDate(2, author.getCreated());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
    }

    @Override
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

    @Override
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

    @Override
    public void delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM authors WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
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

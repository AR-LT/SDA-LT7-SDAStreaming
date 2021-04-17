package dev.sdacademy.sdastreaming.repository;

import dev.sdacademy.sdastreaming.entity.User;
import dev.sdacademy.sdastreaming.exception.SDAStreamingException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserCRUDrepository {

    private final Connection connection;

    public UserCRUDrepository(Connection connection) {
        this.connection = connection;
    }

    // CREATE
    public void create(User user) {
        String sql = "INSERT INTO users (email, password, username, birthdate) VALUES (?, SHA1(?), ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUsername());
            stmt.setDate(4, user.getBirthdate());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
    }

    // READ
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                users.add(toUser(resultSet));
            }
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
        return users;
    }

    // UPDATE
    public void update(User user) {
        String sql = "UPDATE users SET email=?, password=?, username=?, birthdate=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUsername());
            stmt.setDate(4, user.getBirthdate());
            stmt.setInt(5, user.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
    }

    // DELETE
    public void delete(int id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new SDAStreamingException(e);
        }
    }

    public void save(User user) {
        if (user.getId() != null) {
            update(user);
        } else {
            create(user);
        }
    }

    private User toUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setUsername(rs.getString("username"));
        user.setBirthdate(rs.getDate("birthdate"));
        return user;
    }

}

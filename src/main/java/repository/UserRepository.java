package repository;

import entity.User;
import org.postgresql.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserRepository {

    private static final Logger log = Logger.getLogger(UserRepository.class.getName());

    private static final String jdbcURL = "jdbc:postgresql://localhost:5555/postgres";
    private static final String jdbcUsername = "postgres";
    private static final String jdbcPassword = "postgres";

    public UserRepository() {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        Connection conn = null;

        conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        conn.setSchema("public");
        return conn;
    }

    public void save(User user) throws SQLException {
        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name,email,country) VALUES (?,?,?)");
        ) {
            log.info("user udate : " + user);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getCountry());
            preparedStatement.executeUpdate();
        }
    }

    public User findById(int id) throws SQLException {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT name,email,country FROM users WHERE id=?");) {

            log.info("findById: " + id);

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                user = new User(id, name, email, country);
            }
        }
        return user;
    }

    public List<User> findAll() throws SQLException {

        List<User> users = new ArrayList<>();

        try (
                Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
                ResultSet rs = preparedStatement.executeQuery();
        ) {
            log.info("findAll");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                users.add(new User(id, name, email, country));
            }
        }
        return users;
    }

    public void deleteById(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");) {

            log.info("deleteById :" + id);
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public void update(User user) throws SQLException {
        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement("UPDATE users SET name=?,email=?,country=? WHERE id = ?");
        ) {

            log.info("update: " + user);

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getCountry());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
        }
    }
}
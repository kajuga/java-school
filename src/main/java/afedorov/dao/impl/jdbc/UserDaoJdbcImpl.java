package afedorov.dao.impl.jdbc;

import afedorov.dao.interfaces.UserDao;
import afedorov.entities.User;
import afedorov.exceptions.EntityExistException;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    @Override
    public void add(User user) {
        User findedByMail = findByMail(user.getMail());
        if (findedByMail != null) {
            throw new EntityExistException("Клиент уже существует");
        }
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO users (name, lastname, birthdate, role, mail, password)" +
                " VALUES (?,?,?,?,?,?)")){
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setDate(3, Date.valueOf(user.getBirthDate()));
            preparedStatement.setString(4,user.getRole());
            preparedStatement.setString(5,user.getMail());
            preparedStatement.setString(6,user.getPassword());
           preparedStatement.executeUpdate();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void remove(Long id) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM users WHERE id=(?)")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public User findById(Long id) {
        User user = new User();
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM users WHERE id=(?)")) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setBirthDate(resultSet.getObject(3, LocalDate.class));
                user.setRole(resultSet.getNString(5));
                user.setMail(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByMail(String mail) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM users WHERE mail=(?)")) {
            preparedStatement.setString(1, mail);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setBirthDate(resultSet.getDate(4).toLocalDate());
                user.setRole(resultSet.getString(5));
                user.setMail(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));
                return user;
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Long id, User user) {
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM users WHERE id=(?)")) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.updateString(2, user.getName());
                resultSet.updateString(3, user.getLastName());
                resultSet.updateDate(4, Date.valueOf(user.getBirthDate()));
                resultSet.updateString(5, user.getRole());
                resultSet.updateString(6, user.getMail());
                resultSet.updateString(7, user.getPassword());
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        ResultSet resultSet = null;
        try (Statement statement = getConnection().createStatement()) {
            resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setBirthDate(resultSet.getObject(3, LocalDate.class));
                user.setRole(resultSet.getNString(5));
                user.setMail(resultSet.getString(6));
                user.setPassword(resultSet.getString(7));
                users.add(user);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return users;
    }

    private Connection getConnection(){
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbs:postgresql://localhost:5432/ishop", "kajuga", "sashok");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package afedorov.dao.impl.jdbc;

import afedorov.dao.interfaces.AddressDao;
import afedorov.entities.Address;
import afedorov.entities.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoJdbcImpl implements AddressDao {

    @Override
    public void add(Address address) {
        try (Connection conn = getConnection();
        PreparedStatement repStat = conn.prepareStatement("INSERT INTO address (user_id, country, city, postcode, street, housenumber, room, phone) VALUES (?, ?,?,?,?,?,?,?)")) {
            repStat.setInt(1, Math.toIntExact(address.getUser().getId()));
            repStat.setString(2, address.getCountry());
            repStat.setString(3, address.getCity());
            repStat.setInt(4, address.getPostcode());
            repStat.setString(5, address.getStreet());
            repStat.setString(6, address.getHouseNumber());
            repStat.setString(7, address.getRoom());
            repStat.setString(8, address.getPhone());
            repStat.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Long id) {
        try (PreparedStatement prepStat = getConnection().prepareStatement("DELETE FROM address WHERE id =(?)")) {
            prepStat.setLong(1, id);
            prepStat.executeUpdate();
        } catch (SQLException exc) {
        }
    }

    @Override
    public void update(Long id, Address address) {
        try(PreparedStatement prepStatment = getConnection().prepareStatement("SELECT * FROM address WHERE id = (?)")) {
            prepStatment.setLong(1, id);
            ResultSet resultSet = prepStatment.executeQuery();
            while (resultSet.next()) {
                resultSet.updateString(3, address.getCountry());
                resultSet.updateString(4, address.getCity());
                resultSet.updateInt(5, address.getPostcode());
                resultSet.updateString(6, address.getStreet());
                resultSet.updateString(7, address.getHouseNumber());
                resultSet.updateString(8, address.getRoom());
                resultSet.updateString(9, address.getPhone());
            }
        }catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public Address findByUserID(Long id) {
        Address address = new Address();
        try(PreparedStatement prepStatment = getConnection().prepareStatement("SELECT a.id, a.user_id, u.name AS user_name, u.lastName AS user_lastname, u.birthDate as user_birthdate, u.role AS user_role,\n" +
                "u.mail AS user_email, u.password AS user_password, a.country, a.city, a.postcode, a.street, a.houseNumber, a.room, a.phone FROM address AS a\n" +
                "LEFT JOIN users AS u on a.user_id = u.id WHERE a.user_id = (?)")) {
            prepStatment.setLong(1, id);
            ResultSet resultSet = prepStatment.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                address.setId(resultSet.getLong(1));
                user.setId(resultSet.getLong(2));
                user.setName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setBirthDate(resultSet.getObject(5, LocalDate.class));
                user.setRole(resultSet.getNString(6));
                user.setMail(resultSet.getString(7));
                user.setPassword(resultSet.getString(8));
                address.setUser(user);
                address.setCountry(resultSet.getString(9));
                address.setCity(resultSet.getString(10));
                address.setPostcode(resultSet.getInt(11));
                address.setStreet(resultSet.getString(12));
                address.setHouseNumber(resultSet.getString(13));
                address.setRoom(resultSet.getString(14));
                address.setPhone(resultSet.getString(15));
            }
        }catch (SQLException exc) {
            exc.printStackTrace();
        }
        return address;
    }

    @Override
    public Address findById(Long id) {
        Address address = new Address();
        try(PreparedStatement prepStatment = getConnection().prepareStatement("SELECT a.id, a.user_id, u.name AS user_name, u.lastName AS user_lastname, u.birthDate as user_birthdate, u.role AS user_role,\n" +
                "u.mail AS user_email, u.password AS user_password, a.country, a.city, a.postcode, a.street, a.houseNumber, a.room, a.phone FROM address AS a\n" +
                "LEFT JOIN users AS u on a.user_id = u.id WHERE a.id = (?)")) {
            prepStatment.setLong(1, id);
            ResultSet resultSet = prepStatment.executeQuery();
            User user = new User();
            address.setId(resultSet.getLong(1));
            user.setId(resultSet.getLong(2));
            user.setName(resultSet.getString(3));
            user.setLastName(resultSet.getString(4));
            user.setBirthDate(resultSet.getObject(5, LocalDate.class));
            user.setRole(resultSet.getNString(6));
            user.setMail(resultSet.getString(7));
            user.setPassword(resultSet.getString(8));
            address.setUser(user);
            address.setCountry(resultSet.getString(9));
            address.setCity(resultSet.getString(10));
            address.setPostcode(resultSet.getInt(11));
            address.setStreet(resultSet.getString(12));
            address.setHouseNumber(resultSet.getString(13));
            address.setRoom(resultSet.getString(14));
            address.setPhone(resultSet.getString(15));
        }catch (SQLException exc) {
            exc.printStackTrace();
        }
        return address;
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = new ArrayList<>();
        ResultSet resultSet;

        try(Statement statement = getConnection().createStatement()) {
            resultSet = statement.executeQuery("SELECT a.id, a.user_id, u.name AS user_name, u.lastName AS user_lastname, u.birthDate as user_birthdate, u.role AS user_role,\n" +
                    "u.mail AS user_email, u.password AS user_password, a.country, a.city, a.postcode, a.street, a.houseNumber, a.room, a.phone FROM address AS a\n" +
                    "LEFT JOIN users AS u on a.user_id = u.id ");
            while (resultSet.next()) {
                Address address = new Address();
                User user = new User();
                address.setId(resultSet.getLong(1));
                user.setId(resultSet.getLong(2));
                user.setName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setBirthDate(resultSet.getObject(5, LocalDate.class));
                user.setRole(resultSet.getNString(6));
                user.setMail(resultSet.getString(7));
                user.setPassword(resultSet.getString(8));
                address.setUser(user);
                address.setCountry(resultSet.getString(9));
                address.setCity(resultSet.getString(10));
                address.setPostcode(resultSet.getInt(11));
                address.setStreet(resultSet.getString(12));
                address.setHouseNumber(resultSet.getString(13));
                address.setRoom(resultSet.getString(14));
                address.setPhone(resultSet.getString(15));
                addresses.add(address);
            }
        }catch (SQLException exc) {
            exc.printStackTrace();
        }
        return addresses;
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

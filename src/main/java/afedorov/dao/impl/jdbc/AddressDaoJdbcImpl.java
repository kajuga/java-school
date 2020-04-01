package afedorov.dao.impl.jdbc;

import afedorov.dao.interfaces.AddressDao;
import afedorov.entities.Address;

import java.sql.*;
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
            resultSet.updateString(3, address.getCountry());
            resultSet.updateString(4, address.getCity());
            resultSet.updateInt(5, address.getPostcode());
            resultSet.updateString(6, address.getStreet());
            resultSet.updateString(7, address.getHouseNumber());
            resultSet.updateString(8, address.getRoom());
            resultSet.updateString(9, address.getPhone());
        }catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public Address findByUserID(Long id) {
        try(PreparedStatement prepStatment = getConnection().prepareStatement("SELECT * FROM address WHERE user_id = (?)")) {
            prepStatment.setLong(1, id);
            ResultSet resultSet = prepStatment.executeQuery();
            Address address = new Address();
            address.setId(resultSet.getLong(1));
            address.setId(resultSet.getLong(1));
            address.setId(resultSet.getLong(1));
            address.setId(resultSet.getLong(1));
            address.setId(resultSet.getLong(1));
            address.setId(resultSet.getLong(1));
            address.setId(resultSet.getLong(1));
            address.setId(resultSet.getLong(1));

        }catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public Address findById(Long id) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        return null;
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

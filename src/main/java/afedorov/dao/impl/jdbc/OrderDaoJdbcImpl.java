package afedorov.dao.impl.jdbc;

import afedorov.dao.interfaces.OrderDao;
import afedorov.entities.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public void add(Order order) {
            try (PreparedStatement prepStat = getConnection().prepareStatement("INSERT INTO orders (user_id, address_id, paymentmethod, deliverymethod, paymentstate, orderstatus, ordercost) VALUES (?, ?, ?, ?, ?, ?, ?)")) {

                prepStat.setLong(1, order.getUser().getId());
                prepStat.setLong(2, order.getAddress().getId());
                prepStat.setString(3, order.getPaymentMethod().name());
                prepStat.setString(4, order.getOrderStatus().name());
                prepStat.setBigDecimal(5, order.getOrderCost());
                prepStat.executeUpdate();
            } catch (SQLException exc) {
            }
        }

    @Override
    public void update(Long id, Order newOrder) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM orders WHERE id=(?)")) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.updateLong(2, newOrder.getUser().getId());
            resultSet.updateLong(3, newOrder.getAddress().getId());
            resultSet.updateString(4, newOrder.getPaymentMethod().name());
            resultSet.updateString(5, newOrder.getDeliveryMethod().name());
            resultSet.updateString(6, newOrder.getPaymentState().name());
            resultSet.updateString(7, newOrder.getOrderStatus().name());
            resultSet.updateBigDecimal(8, newOrder.getOrderCost());
        } catch (SQLException exc) {
        }
    }

    @Override
    public void repeatOrder(Long id) {
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM orders LEFT JOIN (SELECT * FROM users) as usr ON orders.user_id = usr.id WHERE orders.id = (?)")) {
//                PreparedStatement prepStat = getConnection().prepareStatement("INSERT INTO orders (user_id, address_id, paymentmethod, deliverymethod, paymentstate, orderstatus, ordercost) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            Order order = new Order();
            User user = new User();
            user.setId(resultSet.getLong(9));
            user.setName(resultSet.getString(10));
            user.setLastName(resultSet.getString(11));
            user.setBirthDate(resultSet.getObject(12, LocalDate.class));
            user.setRole(resultSet.getNString(13));
            user.setMail(resultSet.getString(14));
            user.setPassword(resultSet.getString(15));
            order.setUser(user);




            prepStat.setLong(1, order.getUser().getId());
            prepStat.setLong(2, order.getAddress().getId());
            prepStat.setString(3, order.getPaymentMethod().name());
            prepStat.setString(4, order.getOrderStatus().name());
            prepStat.setBigDecimal(5, order.getOrderCost());
            prepStat.executeUpdate();
        } catch (SQLException exc) {
        }
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAllByUser(Long userId) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void changeOrderStatus(Long id, OrderStatus status) {

    }

    @Override
    public void changePaymentState(Long id, PaymentState state) {

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

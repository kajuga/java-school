package afedorov.dao.impl.jdbc;

import afedorov.dao.interfaces.OrderDao;
import afedorov.entities.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ResultSet resultSetDataToRepeat;
        ResultSet resultSetCountNewSumm;
        ResultSet resultSetOldProductsInCartData;
        ResultSet resultSetFillNewProductInCart;
        try (PreparedStatement prepStatOldProductsInCartData = getConnection().prepareStatement("SELECT p_cart.product_id, p_cart.count, pr.price AS current_price_per_one, (p_cart.count * pr.price) AS current_summar_price_for_position FROM productInCart AS p_cart LEFT JOIN product AS pr ON p_cart.product_id = pr.id WHERE p_cart.order_id = (?)");
             PreparedStatement prepStatNewPrice = getConnection().prepareStatement("SELECT sum(current_summar_price_for_position) FROM (SELECT p_cart.count, (p_cart.count * pr.price) AS current_summar_price_for_position FROM productInCart AS p_cart LEFT JOIN product AS pr ON p_cart.product_id = pr.id WHERE p_cart.order_id = (?)) AS q");
             PreparedStatement prepStatOldOrderData = getConnection().prepareStatement("SELECT o.user_id, o.address_id, o.paymentMethod, o.deliveryMethod, o.orderCost FROM orders AS o WHERE o.id = (?)");
             PreparedStatement prepStatFillNewOrderData = getConnection().prepareStatement("INSERT INTO orders (user_id, address_id, paymentmethod, deliverymethod, paymentstate, orderstatus, ordercost) VALUES (?, ?, ?, ?, ?, ?, ?)");
             PreparedStatement prepStatFillProductInCartTable = getConnection().prepareStatement("INSERT INTO productincart (order_id, product_id, price, count) VALUES (?, ?, ?, ?)")) {

            prepStatOldOrderData.setLong(1, id);
            prepStatNewPrice.setLong(1, id);
            resultSetCountNewSumm = prepStatNewPrice.executeQuery();
            resultSetDataToRepeat = prepStatOldOrderData.executeQuery();
            BigDecimal newPriceForRepeatedOrder = resultSetCountNewSumm.getBigDecimal(1);
//            заполняю повторный заказ + свежая стоимость
            prepStatFillNewOrderData.setLong(1, resultSetDataToRepeat.getLong(1));
            prepStatFillNewOrderData.setLong(2, resultSetDataToRepeat.getLong(2));
            prepStatFillNewOrderData.setString(3, resultSetDataToRepeat.getString(3));
            prepStatFillNewOrderData.setString(4, resultSetDataToRepeat.getString(4));
            prepStatFillNewOrderData.setBigDecimal(5, newPriceForRepeatedOrder);
            prepStatFillNewOrderData.executeQuery(); //новый заказ запихнул в бд
//            тут ссылки на товары в корзине на него
            resultSetOldProductsInCartData = prepStatOldProductsInCartData.executeQuery();
            while (resultSetOldProductsInCartData.next()) {
                prepStatFillProductInCartTable.setLong(1, resultSetDataToRepeat.getLong(1));
                prepStatFillProductInCartTable.setLong(2, resultSetDataToRepeat.getLong(2));
                prepStatFillProductInCartTable.setBigDecimal(3, resultSetOldProductsInCartData.getBigDecimal(3));
                prepStatFillProductInCartTable.setInt(4, resultSetOldProductsInCartData.getInt(2));
                prepStatFillProductInCartTable.executeQuery();
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

//    SELECT p_cart.product_id, p_cart.count, pr.price AS current_price_per_one, (p_cart.count * pr.price) AS current_summar_price_for_position
//    FROM productInCart AS p_cart LEFT JOIN product AS pr ON p_cart.product_id = pr.id WHERE p_cart.order_id = 1;
//
//    SELECT sum(current_summar_price_for_position) FROM
//(SELECT p_cart.count, (p_cart.count * pr.price) AS current_summar_price_for_position
//    FROM productInCart AS p_cart LEFT JOIN product AS pr ON p_cart.product_id = pr.id WHERE p_cart.order_id = 1) AS q;


    @Override
    public Order findById(Long id) {
        ResultSet resultOrderSet;
        ResultSet resultCartSet;
        try (PreparedStatement prepOrderContents = getConnection().prepareStatement("SELECT * FROM orders LEFT JOIN (SELECT * FROM users) as usr ON orders.user_id = usr.id WHERE orders.id = (?)");
             PreparedStatement prepCartContents = getConnection().prepareStatement("SELECT p_cart.order_id, p_cart.product_id, p_cart.count AS count_incart,\n" +
                     "       p.title, p.category_id, c.title, p.brand, p.color, p.weight, p.price AS price_of_this_date, p.description\n" +
                     "       FROM productInCart AS p_cart LEFT JOIN product p ON p_cart.product_id = p.id LEFT JOIN category AS c ON p.category_id = c.id where p_cart.order_id=(?)")) {
            prepOrderContents.setLong(1, id);
            prepCartContents.setLong(1, id);
            resultOrderSet = prepOrderContents.executeQuery();
            resultCartSet = prepCartContents.executeQuery();
            Order order = new Order();
            User user = new User();
            user.setId(resultOrderSet.getLong(9));
            user.setName(resultOrderSet.getString(10));
            user.setLastName(resultOrderSet.getString(11));
            user.setBirthDate(resultOrderSet.getObject(12, LocalDate.class));
            user.setRole(resultOrderSet.getString(13));
            user.setMail(resultOrderSet.getString(14));
            user.setPassword(resultOrderSet.getString(15));
            order.setUser(user);
            Address address = new Address();
            address.setId(resultOrderSet.getLong(3));
            address.setUser(user);
            address.setCountry(resultOrderSet.getString(15));
            address.setCity(resultOrderSet.getString(16));
            address.setPostcode(resultOrderSet.getInt(17));
            address.setStreet(resultOrderSet.getString(18));
            address.setHouseNumber(resultOrderSet.getString(19));
            address.setRoom(resultOrderSet.getString(20));
            address.setPhone(resultOrderSet.getString(21));
            order.setAddress(address);

            Map<ProductInCart, Integer> products = new HashMap<>();

            while (resultCartSet.next()) {
                ProductInCart pcart = new ProductInCart();
                pcart.setId(resultCartSet.getLong(2));
//                pcart.

            }


            order.setProducts(products);

            order.setPaymentMethod(PaymentMethod.fromKey(resultOrderSet.getString(23)));
            order.setDeliveryMethod(DeliveryMethod.fromKey(resultOrderSet.getString(24)));
            order.setPaymentState(PaymentState.fromKey(resultOrderSet.getString(25)));

            //TODO тут посчитать из корзины заново
//            order.setOrderCost(PaymentMethod.fromKey(resultOrderSet.getString(23)));


//address
//            private Long id;
//            private User user;
//            private String country;
//            private String city;
//            private Integer postcode;
//            private String street;
//            private String houseNumber;
//            private String room;
//            private String phone;


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

    private Connection getConnection() {
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

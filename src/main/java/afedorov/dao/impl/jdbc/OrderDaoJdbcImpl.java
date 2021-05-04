package afedorov.dao.impl.jdbc;

import afedorov.dao.interfaces.CategoryDao;
import afedorov.dao.interfaces.OrderDao;
import afedorov.entities.*;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public void add(Order order) {
        try (PreparedStatement prepStat = getConnection()
                .prepareStatement(
                        "INSERT INTO orders (user_id, address_id, paymentmethod, deliverymethod, paymentstate, orderstatus, ordercost) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        PreparedStatement prepProdInCart = getConnection()
                .prepareStatement("INSERT INTO productincart (order_id, product_id, price, count) " +
                "VALUES (?,?,?,?)")) {

            prepStat.setLong(1, order.getUser().getId());
            prepStat.setLong(2, order.getAddress().getId());
            prepStat.setString(3, order.getPaymentMethod().name());
            prepStat.setString(4, order.getDeliveryMethod().name());
            prepStat.setString(5, order.getPaymentState().name());
            prepStat.setString(6, order.getOrderStatus().name());
            prepStat.setBigDecimal(7, order.getOrderCost());
            prepStat.executeUpdate();
            ResultSet resultSet = prepStat.getGeneratedKeys();
            long order_id = 0;
            if (resultSet.next()) {
                order_id = resultSet.getLong(1);
            }
            for (Map.Entry<ProductInCart, Integer> pr: order.getProducts().entrySet()) {
                prepProdInCart.setLong(1, order_id);
                prepProdInCart.setLong(2, pr.getKey().getId());
                prepProdInCart.setBigDecimal(3, pr.getKey().getPrice());
                prepProdInCart.setInt(4, pr.getValue());
                prepProdInCart.executeUpdate();
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Order newOrder) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM orders WHERE id=(?)")) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultSet.updateLong(2, newOrder.getUser().getId());
                resultSet.updateLong(3, newOrder.getAddress().getId());
                resultSet.updateString(4, newOrder.getPaymentMethod().name());
                resultSet.updateString(5, newOrder.getDeliveryMethod().name());
                resultSet.updateString(6, newOrder.getPaymentState().name());
                resultSet.updateString(7, newOrder.getOrderStatus().name());
                resultSet.updateBigDecimal(8, newOrder.getOrderCost());
            }
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


    @Override
    public Order findById(Long id) {
        Order order = new Order();
        ResultSet resOrder;
        ResultSet resCartOrder;
        ResultSet resCartPrice;
        try(PreparedStatement prepOrderFind = getConnection().prepareStatement("SELECT o.id AS order_id, o.paymentMethod, o.deliveryMethod, o.paymentState, o.orderStatus, o.orderCost,\n" +
                "       u.id AS user_id, u.name, u.lastName, u.birthDate, u.role, u.mail, u.password,\n" +
                "       a.id AS address_id, a.country, a.city, a.postcode, a.street, a.houseNumber, a.room, a.phone\n" +
                "           from orders AS o LEFT JOIN users AS u ON o.user_id = u.id LEFT JOIN address AS a on u.id = a.user_id WHERE o.id = (?)");
            PreparedStatement prepCartOrder = getConnection().prepareStatement("SELECT prc.product_id, prc.order_id AS order_id, prc.count AS count_in_cart, prc.price AS prod_in_cart_price, (prc.count * prc.price) AS summar_cost,\n" +
                    "       pr.title AS product_title, cat.id AS cat_id, cat.title AS category_title,\n" +
                    "       pr.brand, pr.color, pr.weight, pr.description from productInCart AS prc\n" +
                    "           LEFT JOIN product AS pr on prc.product_id = pr.id LEFT JOIN category AS cat ON pr.category_id = cat.id WHERE prc.order_id = (?)");
            PreparedStatement prepCartPrice = getConnection().prepareStatement("SELECT sum(summar_cost) FROM(SELECT  (prc.count * prc.price) AS summar_cost\n" +
                    "       from productInCart AS prc LEFT JOIN product AS pr on prc.product_id = pr.id\n" +
                    "LEFT JOIN category AS cat ON pr.category_id = cat.id WHERE prc.order_id = (?)) as q")) {
            prepOrderFind.setLong(1, id);
                    resOrder = prepOrderFind.executeQuery();
                    while (resOrder.next()) {
                        User user = new User();
                        Address address = new Address();
                        order.setId(resOrder.getLong(1));
                        user.setId(resOrder.getLong(7));
                        user.setName(resOrder.getString(8));
                        user.setLastName(resOrder.getString(9));
                        user.setBirthDate(resOrder.getDate(10));
                        user.setRole(resOrder.getString(11));
                        user.setMail(resOrder.getString(12));
                        user.setPassword(resOrder.getString(13));
                        order.setUser(user);
                        address.setId(resOrder.getLong(14));
                        address.setUser(user);
                        address.setCountry(resOrder.getString(15));
                        address.setCity(resOrder.getString(16));
                        address.setPostcode(resOrder.getInt(17));
                        address.setStreet(resOrder.getString(18));
                        address.setHouseNumber(resOrder.getString(19));
                        address.setRoom(resOrder.getString(20));
                        address.setPhone(resOrder.getString(21));
                        order.setAddress(address);
                        order.setPaymentMethod(PaymentMethod.fromKey(resOrder.getString(2)));
                        order.setDeliveryMethod(DeliveryMethod.fromKey(resOrder.getString(3)));
                        order.setPaymentState(PaymentState.fromKey(resOrder.getString(4)));
                        order.setOrderStatus(OrderStatus.fromKey(resOrder.getString(5)));
//                        order.setOrderCost(resOrderWithoutCart.getBigDecimal(6)); //todo возможно косяк со стоимостью корзины, разобраться откуда ее брать для повторения или для инфы
                        //todo осталось заполнить Map<ProductInCart, Integer> products
                    }

                    prepCartOrder.setLong(1, id);
                    resCartOrder = prepCartOrder.executeQuery();
                    Map<ProductInCart, Integer> productsInOrder = new HashMap<>();
                    while (resCartOrder.next()) {
                        ProductInCart productInCart = new ProductInCart();
                        productInCart.setId(resCartOrder.getLong(1));
                        productInCart.setTitle(resCartOrder.getString(6));

                        Category category = new Category();
                        category.setId(resCartOrder.getLong("cat_id"));
                        category.setTitle(resCartOrder.getString("category_title"));

                        productInCart.setCategory(category);
                        productInCart.setBrand(resCartOrder.getString(9));
                        productInCart.setColor(resCartOrder.getString(10));
                        productInCart.setWeight(resCartOrder.getDouble(11));
                        productInCart.setDescription(resCartOrder.getString(12));
                        productInCart.setPrice(resCartOrder.getBigDecimal(4));
                        Integer count = resCartOrder.getInt(3);
                        productsInOrder.put(productInCart, count);
                    }
                    order.setProducts(productsInOrder);

                    prepCartPrice.setLong(1, id);
                    resCartPrice = prepCartPrice.executeQuery();
                    BigDecimal priceOrder = null;
                    while (resCartPrice.next()) {
                        priceOrder = resCartPrice.getBigDecimal(1);
                    }
                    order.setOrderCost(priceOrder);

            } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return order;
    }

    @Override
    public List<Order> findAllByUser(Long userId) {
        List<Order> orders = new ArrayList<>();
        ResultSet resOrders;
        try(PreparedStatement prepOrderFind = getConnection().prepareStatement("SELECT id, user_id FROM orders WHERE user_id = (?)")) {
            prepOrderFind.setLong(1, userId);
            resOrders = prepOrderFind.executeQuery();
            while (resOrders.next()) {
                Long id = resOrders.getLong(1);
                Order order = new Order();
                User user = new User();
                Address address = new Address();



                ResultSet resOrderWithoutCart;
                ResultSet resCartOrder;
                ResultSet resCartPrice;
                try (PreparedStatement prepOrderWithoutCart = getConnection().prepareStatement("SELECT o.id AS order_id, o.paymentMethod, o.deliveryMethod, o.paymentState, o.orderStatus, o.orderCost,\n" +
                        "       u.id AS user_id, u.name, u.lastName, u.birthDate, u.role, u.mail, u.password,\n" +
                        "       a.id AS address_id, a.country, a.city, a.postcode, a.street, a.houseNumber, a.room, a.phone\n" +
                        "           from orders AS o LEFT JOIN users AS u ON o.user_id = u.id LEFT JOIN address AS a on u.id = a.user_id WHERE o.id = (?)");
                     PreparedStatement prepCartOrder = getConnection().prepareStatement("SELECT prc.id, prc.order_id AS order_id, prc.count AS count_in_cart, prc.price AS prod_in_cart_price, (prc.count * prc.price) AS summar_cost, pr.title AS product_title, cat.id AS cat_id, cat.title AS category_title, pr.brand, pr.color, pr.weight, pr.description from productInCart AS prc LEFT JOIN product AS pr on prc.product_id = pr.id LEFT JOIN category AS cat ON pr.category_id = cat.id WHERE prc.order_id = (?)");
                     PreparedStatement prepCartPrice = getConnection().prepareStatement("SELECT sum(summar_cost) FROM(SELECT  (prc.count * prc.price) AS summar_cost\n" +
                             "       from productInCart AS prc LEFT JOIN product AS pr on prc.product_id = pr.id\n" +
                             "LEFT JOIN category AS cat ON pr.category_id = cat.id WHERE prc.order_id = (?)) as q")) {
                    prepOrderWithoutCart.setLong(1, id);
                    resOrderWithoutCart = prepOrderWithoutCart.executeQuery();

                    while (resOrderWithoutCart.next()) {
                        order.setId(resOrderWithoutCart.getLong(1));
                        user.setId(resOrderWithoutCart.getLong(7));
                        user.setName(resOrderWithoutCart.getString(8));
                        user.setLastName(resOrderWithoutCart.getString(9));
                        user.setBirthDate(resOrderWithoutCart.getDate(10));
                        user.setRole(resOrderWithoutCart.getString(11));
                        user.setMail(resOrderWithoutCart.getString(12));
                        user.setPassword(resOrderWithoutCart.getString(13));
                        order.setUser(user);
                        address.setId(resOrderWithoutCart.getLong(14));
                        address.setUser(user);
                        address.setCountry(resOrderWithoutCart.getString(15));
                        address.setCity(resOrderWithoutCart.getString(16));
                        address.setPostcode(resOrderWithoutCart.getInt(17));
                        address.setStreet(resOrderWithoutCart.getString(18));
                        address.setHouseNumber(resOrderWithoutCart.getString(19));
                        address.setRoom(resOrderWithoutCart.getString(20));
                        address.setPhone(resOrderWithoutCart.getString(21));
                        order.setAddress(address);
                        order.setPaymentMethod(PaymentMethod.fromKey(resOrderWithoutCart.getString(2)));
                        order.setDeliveryMethod(DeliveryMethod.fromKey(resOrderWithoutCart.getString(3)));
                        order.setPaymentState(PaymentState.fromKey(resOrderWithoutCart.getString(4)));
                        order.setOrderStatus(OrderStatus.fromKey(resOrderWithoutCart.getString(5)));
//                        order.setOrderCost(resOrderWithoutCart.getBigDecimal(6)); //todo возможно косяк со стоимостью корзины, разобраться откуда ее брать для повторения или для инфы
                        //todo осталось заполнить Map<ProductInCart, Integer> products
                    }
                    prepCartOrder.setLong(1, id);
                    resCartOrder = prepCartOrder.executeQuery();
                    Map<ProductInCart, Integer> productsInOrder = new HashMap<>();
                    while (resCartOrder.next()) {
                        ProductInCart productInCart = new ProductInCart();
                        productInCart.setId(resCartOrder.getLong(1));
                        productInCart.setTitle(resCartOrder.getString(6));

                        Category category = new Category();
                        category.setId(resCartOrder.getLong("cat_id"));
                        category.setTitle(resCartOrder.getString("category_title"));

                        productInCart.setCategory(category);
                        productInCart.setBrand(resCartOrder.getString(9));
                        productInCart.setColor(resCartOrder.getString(10));
                        productInCart.setWeight(resCartOrder.getDouble(11));
                        productInCart.setDescription(resCartOrder.getString(12));
                        productInCart.setPrice(resCartOrder.getBigDecimal(4));
                        Integer count = resCartOrder.getInt(3);
                        productsInOrder.put(productInCart, count);
                    }
                    order.setProducts(productsInOrder);

                    prepCartPrice.setLong(1, id);
                    resCartPrice = prepCartPrice.executeQuery();
                    BigDecimal priceOrder = null;
                    while (resCartPrice.next()) {
                        priceOrder = resCartPrice.getBigDecimal(1);
                    }
                    order.setOrderCost(priceOrder);
            }
                orders.add(order);

            }
        }catch (SQLException exc) {
            exc.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        ResultSet resOrders;
        try(PreparedStatement prepOrderFind = getConnection().prepareStatement("SELECT id, user_id FROM orders")) {
            resOrders = prepOrderFind.executeQuery();
            while (resOrders.next()) {
                Long id = resOrders.getLong(2);
                Order order = new Order();
                ResultSet resOrderWithoutCart;
                ResultSet resCartOrder;
                ResultSet resCartPrice;
                try (PreparedStatement prepOrderWithoutCart = getConnection().prepareStatement("SELECT o.id AS order_id, o.paymentMethod, o.deliveryMethod, o.paymentState, o.orderStatus, o.orderCost,\n" +
                        "       u.id AS user_id, u.name, u.lastName, u.birthDate, u.role, u.mail, u.password,\n" +
                        "       a.id AS address_id, a.country, a.city, a.postcode, a.street, a.houseNumber, a.room, a.phone\n" +
                        "           from orders AS o LEFT JOIN users AS u ON o.user_id = u.id LEFT JOIN address AS a on u.id = a.user_id WHERE o.id = (?)");
                     PreparedStatement prepCartOrder = getConnection().prepareStatement("SELECT prc.id, prc.order_id AS order_id, prc.count AS count_in_cart, prc.price AS prod_in_cart_price, (prc.count * prc.price) AS summar_cost,\n" +
                             "       pr.title, cat.title, pr.brand, pr.color, pr.weight, pr.description from productInCart AS prc LEFT JOIN product AS pr on prc.product_id = pr.id\n" +
                             "LEFT JOIN category AS cat ON pr.category_id = cat.id WHERE prc.order_id = (?);");
                     PreparedStatement prepCartPrice = getConnection().prepareStatement("SELECT sum(summar_cost) FROM(SELECT  (prc.count * prc.price) AS summar_cost\n" +
                             "       from productInCart AS prc LEFT JOIN product AS pr on prc.product_id = pr.id\n" +
                             "LEFT JOIN category AS cat ON pr.category_id = cat.id WHERE prc.order_id = (?)) as q")) {
                    prepOrderWithoutCart.setLong(1, id);
                    prepCartOrder.setLong(1, id);
                    prepCartPrice.setLong(1, id);
                    resOrderWithoutCart = prepOrderWithoutCart.executeQuery();
                    resCartOrder = prepCartOrder.executeQuery();
                    resCartPrice = prepCartPrice.executeQuery();
                    order.setId(resOrderWithoutCart.getLong(1));
                    User user = new User();
                    user.setId(resOrderWithoutCart.getLong(7));
                    user.setName(resOrderWithoutCart.getString(8));
                    user.setLastName(resOrderWithoutCart.getString(9));
                    user.setBirthDate(resOrderWithoutCart.getDate(10));
                    user.setRole(resOrderWithoutCart.getString(11));
                    user.setMail(resOrderWithoutCart.getString(12));
                    user.setPassword(resOrderWithoutCart.getString(13));
                    order.setUser(user);
                    Address address = new Address();
                    address.setId(resOrderWithoutCart.getLong(14));
                    address.setUser(user);
                    address.setCountry(resOrderWithoutCart.getString(15));
                    address.setCity(resOrderWithoutCart.getString(16));
                    address.setPostcode(resOrderWithoutCart.getInt(17));
                    address.setStreet(resOrderWithoutCart.getString(18));
                    address.setHouseNumber(resOrderWithoutCart.getString(19));
                    address.setRoom(resOrderWithoutCart.getString(20));
                    address.setPhone(resOrderWithoutCart.getString(21));
                    order.setAddress(address);
                    order.setOrderCost(resCartPrice.getBigDecimal(1));
                    Map<ProductInCart, Integer> products = new HashMap<>();
                    while (resCartOrder.next()) {
                        ProductInCart pcart = new ProductInCart();
                        pcart.setId(resCartOrder.getLong(1));
                        pcart.setTitle(resCartOrder.getString(6));
                        Category category = new Category();
                        category.setId(resCartOrder.getLong(7));
                        category.setTitle(resCartOrder.getString(8));
                        pcart.setCategory(category);
                        pcart.setBrand(resCartOrder.getString(9));
                        pcart.setColor(resCartOrder.getString(10));
                        pcart.setWeight(resCartOrder.getDouble(11));
                        pcart.setDescription(resCartOrder.getString(12));
                        products.put(pcart, resCartOrder.getInt(3));
                    }
                    order.setProducts(products);
                    order.setPaymentMethod(PaymentMethod.fromKey(resOrderWithoutCart.getString(2)));
                    order.setDeliveryMethod(DeliveryMethod.fromKey(resOrderWithoutCart.getString(3)));
                    order.setPaymentState(PaymentState.fromKey(resOrderWithoutCart.getString(4)));
                    orders.add(order);
                }
            }
        }catch (SQLException exc) {
            exc.printStackTrace();
        }
        return orders;
    }

    @Override
    public void changeOrderStatus(Long id, OrderStatus status) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE orders SET orderStatus = (?) where orders.id = (?)")) {
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, status.name());

        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void changePaymentState(Long id, PaymentState state) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE orders SET paymentstate = (?) where orders.id = (?)")) {
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, state.name());
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    private Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/ishop", "admin", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}

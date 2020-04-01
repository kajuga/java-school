package afedorov.dao.impl.jdbc;

import afedorov.dao.interfaces.ProductDao;
import afedorov.entities.Category;
import afedorov.entities.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoJdbcImpl implements ProductDao {
    @Override
    public void add(Product product) {
        try (PreparedStatement prepStat = getConnection().prepareStatement("INSERT INTO product (title, category_id, brand, color, weight, price, description, count) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            prepStat.setString(1, product.getTitle());
            prepStat.setLong(2, product.getCategory().getId());
            prepStat.setString(3, product.getBrand());
            prepStat.setString(4, product.getColor());
            prepStat.setDouble(5, product.getWeight());
            prepStat.setBigDecimal(6, product.getPrice());
            prepStat.setString(7, product.getDescription());
            prepStat.setInt(8, product.getCount());
            prepStat.executeUpdate();
        } catch (SQLException exc) {
        }
    }

    @Override
    public void remove(Long id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM product WHERE id=(?)")) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.executeQuery();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void update(Long id, Product product) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM product WHERE id=(?)")) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.updateString("title", product.getTitle());
            resultSet.updateLong(3, product.getCategory().getId());
            resultSet.updateString("brand", product.getBrand());
            resultSet.updateString("color", product.getColor());
            resultSet.updateDouble("weight", product.getWeight());
            resultSet.updateBigDecimal("price", product.getPrice());
            resultSet.updateString("description", product.getDescription());
            resultSet.updateInt("count", product.getCount());
        } catch (SQLException exc) {

        }
    }

    @Override
    public Product findById(Long id) {
        Product product = new Product();
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("SELECT p.id, p.title, p.category_id, c.title as category_title, p.brand, " +
                        "p.color, p.weight, p.price, p.description, p.count " +
                        "FROM product as p " +
                        "LEFT JOIN category as c ON p.category_id = c.id " +
                        "WHERE p.id=(?)")) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            product.setId(rs.getLong(1));
            product.setTitle(rs.getString(2));
            Category category = new Category();
            category.setId(rs.getLong(3));
            category.setTitle(rs.getString(4));
            product.setCategory(category);
            product.setBrand(rs.getString(5));
            product.setColor(rs.getNString(6));
            product.setWeight(rs.getDouble(7));
            product.setPrice(rs.getBigDecimal(8));
            product.setDescription(rs.getString(9));
            product.setCount(rs.getInt(10));
        } catch (SQLException exc) {

        }
        return product;
    }


    @Override
    public Product findByTitle(String title) {
        Product product = new Product();
        Category category = new Category();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                "SELECT p.id, p.title, p.category_id, c.title AS category_title, p.brand, p.color, p.weight, p.price, p.description " +
                        "FROM product as p LEFT JOIN category c on p.category_id = c.id WHERE p.title=(?)")) {
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            product.setId(resultSet.getLong(1));
            product.setTitle(resultSet.getString(2));
            category.setId(resultSet.getLong(3));
            category.setTitle(resultSet.getString(4));
            product.setCategory(category);
            product.setBrand(resultSet.getString(5));
            product.setColor(resultSet.getNString(6));
            product.setWeight(resultSet.getDouble(7));
            product.setPrice(resultSet.getBigDecimal(8));
            product.setDescription(resultSet.getString(9));
            product.setCount(resultSet.getInt(10));
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        ResultSet resultSet = null;
        Product product = null;

        try (Statement statement = getConnection().createStatement()) {
            resultSet = statement.executeQuery("SELECT p.id, p.title, p.category_id, c.title AS category_title, p.brand, p.color, p.weight, p.price, p.description, p.count\n" +
                    "FROM product as p LEFT JOIN category c on p.category_id = c.id");
            while (resultSet.next()) {
                product = new Product();
                product.setId(resultSet.getLong(1));
                product.setTitle(resultSet.getString(2));
                Category category = new Category();
                category.setId(resultSet.getLong(3));
                category.setTitle(resultSet.getString(4));
                product.setCategory(category);
                product.setBrand(resultSet.getString(5));
                product.setColor(resultSet.getString(6));
                product.setWeight(resultSet.getDouble(7));
                product.setPrice(resultSet.getBigDecimal(8));
                product.setDescription(resultSet.getString(9));
                product.setCount(resultSet.getInt(10));
                products.add(product);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return products;
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

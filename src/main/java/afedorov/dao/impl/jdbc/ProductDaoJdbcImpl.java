package afedorov.dao.impl.jdbc;

import afedorov.dao.interfaces.ProductDao;
import afedorov.entities.Product;

import java.sql.*;
import java.util.List;

public class ProductDaoJdbcImpl implements ProductDao {
    @Override
    public void add(Product product) {
//        try(PreparedStatement prepStat = getConnection().prepareStatement("INSERT INTO product (title, category, brand, color, weight, price, description, count) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
//            prepStat.setString(1, product.getTitle());
////            prepStat.setString(2, product.getCategory().getId());
//            prepStat.setString(3, product.getBrand());
//            prepStat.setString(4, product.getColor());
//            prepStat.setDouble(5, product.getWeight());
//            prepStat.setBigDecimal(6, product.getPrice());
//            prepStat.setString(7, product.getDescription());
//            prepStat.setInt(8, product.getCount());
//            prepStat.executeUpdate();
//        } catch (SQLException exc) {
//        }
    }

    @Override
    public void remove(Long id) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM product WHERE id=(?)")) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            preparedStatement.executeQuery();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }


    @Override
    public void update(Long id, Product product) {
        try(PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM product WHERE id=(?)")) {
            preparedStatement.setInt(1, Math.toIntExact(id));
            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.updateString();
//            resultSet.updateString();
//            resultSet.updateString();
//            resultSet.updateString();
//            resultSet.updateString();
//            resultSet.updateString();
//            resultSet.updateString();
//            resultSet.updateString();
//            resultSet.updateString();




        } catch (SQLException exc) {

        }

    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public Product findByTitle(String title) {
        return null;
    }

    @Override
    public List<Product> findAll() {
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

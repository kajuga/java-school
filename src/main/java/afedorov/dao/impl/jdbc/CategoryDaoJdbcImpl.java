package afedorov.dao.impl.jdbc;

import afedorov.dao.interfaces.CategoryDao;
import afedorov.entities.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoJdbcImpl implements CategoryDao {


    @Override
    public void add(Category category) {
        try (Connection connection = getConnection();
            PreparedStatement prepStat = connection.prepareStatement("INSERT INTO category (title) VALUES (?)")) {
            prepStat.setString(1, category.getTitle());
            prepStat.executeUpdate();
        } catch (SQLException ecx) {
            ecx.printStackTrace();
        }
    }

    @Override
    public void remove(Long id) {
        try(Connection connection = getConnection();
        PreparedStatement prepStat = connection.prepareStatement("DELETE FROM category WHERE id = (?)")) {
            prepStat.setLong(1, id);
            prepStat.executeUpdate();
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
    }


    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        ResultSet resultSet = null;
        try(Connection conn = getConnection();
            Statement statement = conn.createStatement()) {
            resultSet = statement.executeQuery("SELECT * FROM category");
            while (resultSet.next()) {
                Category cat = new Category();
                cat.setId(resultSet.getLong(1));
                cat.setTitle(resultSet.getString(2));
                categories.add(cat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }


    @Override
    public Category findById(Long id) {
        Category category = new Category();
        ResultSet resultSet;
        try (Connection connection = getConnection();
             PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM category WHERE id=(?)")) {
            prepStat.setLong(1, id);
            resultSet = prepStat.executeQuery();
            while (resultSet.next()) {
                Long idCat = resultSet.getLong(1);
                String title = resultSet.getString("title");
                category.setId(idCat);
                category.setTitle(title);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return category;
    }

    @Override
    public Category findByTitle(String title) {
        Category category = new Category();
        ResultSet resultSet = null;
        try (Connection connection = getConnection();
             PreparedStatement prepStat = connection.prepareStatement("SELECT * FROM  category WHERE title=(?)")) {
            resultSet = prepStat.executeQuery(title);
            while (resultSet.next()) {
                category.setId(resultSet.getLong(1));
                category.setTitle(resultSet.getString(2));
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return category;
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

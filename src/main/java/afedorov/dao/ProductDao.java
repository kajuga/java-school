package afedorov.dao;

import afedorov.entities.Product;

import java.util.List;

public interface ProductDao {

    void add(Product product);
    void update(Product product);
    void remove (Product product);
    Product findById (Long id);
    Product findByTitle(String title);
    List<Product> findAll();

}

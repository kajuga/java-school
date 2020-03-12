package afedorov.dao.interfaces;

import afedorov.entities.Product;

import java.util.List;

public interface ProductDao {

    void add(Product product);
    void remove(Long id);
    void update(Long id, Product product);
    Product findById (Long id);
    Product findByTitle(String title);
    List<Product> findAll();

}

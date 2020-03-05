package afedorov.dao;

import afedorov.entities.Product;

public interface ProductDao {

    void add(Product product);

    void update(Product product);
}

package afedorov.dao;

import afedorov.entities.Product;
import java.util.Map;
import java.util.Set;

public interface StorageDao {

    void addProduct(Product product, int count);
    void removeQuantityExistingProduct(Product product, int count);
    int findProductQuantity(Product product);
    Map<Product, Integer> findAll();
    boolean seekProduct(Product product);
}

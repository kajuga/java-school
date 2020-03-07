package afedorov.dao.impl.inmemory;

import afedorov.dao.StorageDao;
import afedorov.entities.Product;

import java.util.HashMap;
import java.util.Map;

public class StorageDaoImpl implements StorageDao {
    private Long id = 1L;
    private Map<Product, Integer> storage = new HashMap<>();

    @Override
    public void addProduct(Product product, int count) {
        if (product != null & count >= 0) {
            if (storage.containsKey(product)) {
                storage.put(product, storage.get(product) + count);
            } else {
                Product newProduct = product;
                newProduct.setId(id);
                id++;
                storage.put(newProduct, count);
            }
        }
    }

    @Override
    public void removeQuantityExistingProduct(Product product, int count) {
        if (product != null & count >= 0) {
            if (seekProduct(product)) {
                if (findProductQuantity(product) >= count) {
                    storage.put(product, storage.get(product) - count);
                } else throw new RuntimeException("not enough products");
            }
        }
    }

    @Override
    public Map<Product, Integer> findAll() {
        return storage;
    }

    public boolean seekProduct(Product product) {
        return storage.containsKey(product);
    }

    @Override
    public int findProductQuantity(Product product) {
        if (product != null & storage.containsKey(product)) {
            return storage.get(product);
        }
        return 0;
    }
}

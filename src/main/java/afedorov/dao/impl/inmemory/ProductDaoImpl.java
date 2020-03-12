package afedorov.dao.impl.inmemory;

import afedorov.dao.interfaces.ProductDao;
import afedorov.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    private Long idGenerator = 1L;
    private List<Product> products = new ArrayList<>();

    @Override
    public void add(Product product) {
        if (product != null) {
            Product exist = findByTitle(product.getTitle());
            if(exist == null) {
                product.setId(idGenerator);
                idGenerator++;
                products.add(product);
            }
        }
    }

    @Override
    public void remove(Long id) {
        Product removed = findById(id);
        if (removed != null){
            products.remove(removed);
        }
    }

    @Override
    public void update(Long id, Product product) {
        if (product != null) {
            Product updatable = findById(id);
            updatable.setTitle(product.getTitle());
            updatable.setCategory(product.getCategory());
            updatable.setBrand(product.getBrand());
            updatable.setColor(product.getColor());
            updatable.setWeight(product.getWeight());
            updatable.setPrice(product.getPrice());
            updatable.setDescription(product.getDescription());
            updatable.setCount(product.getCount());
        }
    }


    @Override
    public Product findById(Long id) {
        if (id != null){
            for (Product product: products){
                if (id.equals(product.getId())){
                    return product;
                }
            }
        }
        return null;
    }

    @Override
    public Product findByTitle(String title) {
        if (title != null) {
            for (Product product : products) {
                if (title.equals(product.getTitle())) {
                    return product;
                }
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        return products;
    }
}

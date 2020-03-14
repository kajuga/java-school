package afedorov.dao.impl.inmemory;

import afedorov.dao.interfaces.ProductDao;
import afedorov.entities.Product;
import afedorov.exceptions.EntityExistException;
import java.util.List;

import static afedorov.dao.impl.inmemory.InMemoryDataBase.PRODUCTS;

public class ProductDaoImpl implements ProductDao {
    private Long idGenerator = 1L;

    @Override
    public void add(Product product) {
        if (product != null) {
            Product exist = findByTitle(product.getTitle());
            if(exist == null) {
                product.setId(idGenerator);
                idGenerator++;
                PRODUCTS.add(product);
            } else {
                throw new EntityExistException("Product with title " + product.getTitle() + " already exist!");
            }
        }
    }


//    @Override
//    public void add(Product product) {      //TODO нахеровертил: не добавляется продукт с разной категорией и идентичный в остальном
//        if (product != null) {
//            Product exist = findByTitle(product.getTitle());
//            if(exist != null) {
//                if (product.getTitle().equals(exist.getTitle()) & product.getCategory().equals(exist.getCategory())
//                        & product.getBrand().equals(exist.getBrand()) & product.getColor().equals(exist.getColor())
//                        & product.getWeight() == exist.getWeight() & product.getPrice().equals(exist.getPrice())
//                        & product.getDescription().equals(exist.getDescription())) {
//                    exist.setCount(exist.getCount() + product.getCount());
//                }
//            } else {
//                product.setId(idGenerator);
//                idGenerator++;
//                products.add(product);
//            }
//            }
//        }

    @Override
    public void remove(Long id) {
        Product removed = findById(id);
        if (removed != null){
            PRODUCTS.remove(removed);
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
            for (Product product: PRODUCTS){
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
            for (Product product : PRODUCTS) {
                if (title.equals(product.getTitle())) {
                    return product;
                }
            }
        }
        return null;
    }

    @Override
    public List<Product> findAll() {
        return PRODUCTS;
    }
}

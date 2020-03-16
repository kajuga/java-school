package afedorov.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
public class ShoppingCart {
    private Map<Long, ShoppingCartItem> products = new HashMap<>();

    public void addProduct(Long idProduct, int count) {
        ShoppingCartItem shoppingCartItem = products.get(idProduct);
        if (shoppingCartItem == null) {
            shoppingCartItem = new ShoppingCartItem(idProduct, count);
            products.put(idProduct, shoppingCartItem);
        } else {
            shoppingCartItem.setCount(shoppingCartItem.getCount() + count);
        }
    }

    public void removeProduct(Long idProduct, int count) {
        ShoppingCartItem shoppingCartItem = products.get(idProduct);
        if (shoppingCartItem != null) {
            if (shoppingCartItem.getCount() > count) {
                shoppingCartItem.setCount(shoppingCartItem.getCount() - count);
            } else {
                products.remove(idProduct);
            }
        }
    }

    public Collection<ShoppingCartItem> getItems() {
        return products.values();
    }
}

package afedorov.entities;

import lombok.Data;

@Data
public class ShoppingCartItem {
    private Long idProduct;
    private int count;

    public ShoppingCartItem(Long idProduct, int count) {
        this.idProduct = idProduct;
        this.count = count;
    }
}
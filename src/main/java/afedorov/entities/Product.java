package afedorov.entities;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private String title;
    private BigDecimal price;
    private Category category;

    private List<ValueOfParameter> parameters;
    private int quantityInStock;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<ValueOfParameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<ValueOfParameter> parameters) {
        this.parameters = parameters;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}

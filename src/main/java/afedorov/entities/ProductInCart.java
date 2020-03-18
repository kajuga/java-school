package afedorov.entities;

import afedorov.entities.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductInCart {
    private Long id;
    private String title;
    private Category category;
    private String brand;
    private String color;
    private double weight;
    private BigDecimal price;
    private String description;
}

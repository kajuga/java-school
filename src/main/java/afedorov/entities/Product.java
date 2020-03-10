package afedorov.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private Long id;
    private String title;
    private Category category;
    private String brand;
    private String color;
    private double weight;
    private BigDecimal price;
    private int count;
    private String description;
}

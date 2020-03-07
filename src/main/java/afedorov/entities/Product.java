package afedorov.entities;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class Product {
    private Long id;
    private String title;
    private Category category;
    private Set<ValueOfParameter> parameters;
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<ValueOfParameter> getParameters() {
        return parameters;
    }

    public void setParameters(Set<ValueOfParameter> parameters) {
        this.parameters = parameters;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getTitle().equals(product.getTitle()) &&
                Objects.equals(getCategory(), product.getCategory()) &&
                Objects.equals(getParameters(), product.getParameters()) &&
                Objects.equals(getPrice(), product.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getCategory(), getParameters(), getPrice());
    }
}

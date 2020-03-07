package afedorov.entities;

import java.util.Map;

public class Storage {
    private Map<Product, Integer> storage;
    private Long id;

    public Map<Product, Integer> getStorage() {
        return storage;
    }

    public void setStorage(Map<Product, Integer> storage) {
        this.storage = storage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

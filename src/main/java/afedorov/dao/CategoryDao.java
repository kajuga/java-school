package afedorov.dao;

import afedorov.entities.Category;

import java.util.List;

public interface CategoryDao {

    void add(Category category);
    void remove (Long id);
    List<Category> findAll();
    Category findById(Long id);
    Category findByTitle(String title);
}

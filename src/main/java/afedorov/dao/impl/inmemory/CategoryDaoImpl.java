package afedorov.dao.impl.inmemory;

import afedorov.dao.interfaces.CategoryDao;
import afedorov.entities.Category;
import afedorov.exceptions.EntityExistException;

import java.util.List;

import static afedorov.dao.impl.inmemory.InMemoryDataBase.CATEGORIES;

public class CategoryDaoImpl implements CategoryDao {

    private Long idGenerator = 1L;

    @Override
    public void add(Category category) {
        if (category != null) {
            Category exist = findByTitle(category.getTitle());
            if (exist == null) {
                category.setId(idGenerator);
                idGenerator++;
                CATEGORIES.add(category);
            } else {
                throw new EntityExistException("Category with title " + category.getTitle() + " already exist!");
            }
        }
    }

    @Override
    public void remove(Long id) {
        Category removed = findById(id);
        if (removed != null){
            CATEGORIES.remove(removed);
        }
    }

    @Override
    public List<Category> findAll() {
        return CATEGORIES;
    }

    @Override
    public Category findById(Long id) {
        if (id != null){
            for (Category category: CATEGORIES){
                if (id.equals(category.getId())){
                    return category;
                }
            }
        }
        return null;
    }

    @Override
    public Category findByTitle(String title) {
        if (title != null){
            for (Category category: CATEGORIES){
                if (title.equals(category.getTitle())){
                    return category;
                }
            }
        }
        return null;
    }
}

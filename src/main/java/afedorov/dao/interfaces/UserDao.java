package afedorov.dao.interfaces;

import afedorov.entities.User;
import java.util.List;

public interface UserDao {

    void add(User user);
    void remove (Long id);
    User findById (Long id);
    void update(User user);
    List<User> findAll();
}

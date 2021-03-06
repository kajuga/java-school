package afedorov.dao.interfaces;

import afedorov.entities.Address;
import afedorov.entities.User;
import java.util.List;

public interface UserDao {

    void add(User user);
    void remove (Long id);
    User findById (Long id);
    User findByMail (String mail);
    void update(Long id, User user);
    List<User> findAll();
}

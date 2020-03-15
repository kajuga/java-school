package afedorov.dao.impl.inmemory;

import afedorov.dao.interfaces.UserDao;
import afedorov.entities.User;
import java.util.List;
import static afedorov.dao.impl.inmemory.InMemoryDataBase.USERS;

public class UserDaoImpl implements UserDao {
    private Long idGenerator = 1l;

    @Override
    public void add(User user) {
        User findedById = findById(user.getId());
        if(findedById != null) {
            throw new RuntimeException("Клиент уже существует");
        }
        user.setId(idGenerator);
        idGenerator++;
        USERS.add(user);
    }

    @Override
    public void remove(Long id) {
        User removed = findById(id);
        if (removed != null) {
            USERS.remove(removed);
        }
    }

    @Override
    public List<User> findAll() {
        return USERS;
    }

    @Override
    public User findById(Long id) {
        if(id != null) {
            for (User finded: USERS) {
                if(finded.getId().equals(id)) {
                    return finded;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Long id, User user) {
        if (user != null) {
            User updated = findById(id);
            if (updated != null) {
                updated.setName(user.getName());
                updated.setLastName(user.getLastName());
                updated.setBirthDate(user.getBirthDate());
                updated.setRole(user.getRole());
                updated.setMail(user.getMail());
                updated.setPassword(user.getPassword());
            }
        }
    }


    public User findByMail (String mail) {
        if (mail != null) {
            for (User finded : USERS) {
                if(mail.equals(finded.getMail())) {
                    return finded;
                }
            }
        }
        return null;
    }
}

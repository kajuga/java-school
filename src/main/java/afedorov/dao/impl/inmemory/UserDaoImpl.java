package afedorov.dao.impl.inmemory;

import afedorov.dao.interfaces.UserDao;
import afedorov.entities.User;

import java.util.List;

import static afedorov.dao.impl.inmemory.InMemoryDataBase.USERS;

public class UserDaoImpl implements UserDao {
    private Long idGenerator = 1l;

    @Override
    public void add(User user) {
        User findedByEmail = findByEmail(user.getMail());
        if(findedByEmail != null) {
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
    public void update(User user) {
        User updated = findByEmail(user.getMail());
        if(updated != null) {
            updated.setName(user.getName());
            updated.setLastName(user.getLastName());
            updated.setBirthDate(user.getBirthDate());
            updated.setPassword(user.getPassword());
            updated.setMail(user.getMail());
                }
            }


    private User findByEmail (String email) {
        if (email != null) {
            for (User finded : USERS) {
                if(email.equals(finded.getMail())) {
                    return finded;
                }
            }
        }
        return null;
    }
}

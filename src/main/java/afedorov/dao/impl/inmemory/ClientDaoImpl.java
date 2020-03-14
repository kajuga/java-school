package afedorov.dao.impl.inmemory;

import afedorov.dao.interfaces.ClientDao;
import afedorov.entities.Client;

import java.util.List;

import static afedorov.dao.impl.inmemory.InMemoryDataBase.CLIENTS;

public class ClientDaoImpl implements ClientDao {
    private Long idGenerator = 1l;

    @Override
    public void add(Client client) {
        Client findedByEmail = findByEmail(client.getMail());
        if(findedByEmail != null) {
            throw new RuntimeException("Клиент уже существует");
        }
        client.setId(idGenerator);
        idGenerator++;
        CLIENTS.add(client);
    }

    @Override
    public void remove(Long id) {
        Client removed = findById(id);
        if (removed != null) {
            CLIENTS.remove(removed);
        }
    }

    @Override
    public List<Client> findAll() {
        return CLIENTS;
    }

    @Override
    public Client findById(Long id) {
        if(id != null) {
            for (Client finded: CLIENTS) {
                if(finded.getId().equals(id)) {
                    return finded;
                }
            }
        }
        return null;
    }

    @Override
    public void update(Client client) {
        Client updated = findByEmail(client.getMail());
        if(updated != null) {
            updated.setName(client.getName());
            updated.setLastName(client.getLastName());
            updated.setBirthDate(client.getBirthDate());
            updated.setPassword(client.getPassword());
            updated.setMail(client.getMail());
                }
            }


    private Client findByEmail (String email) {
        if (email != null) {
            for (Client finded : CLIENTS) {
                if(email.equals(finded.getMail())) {
                    return finded;
                }
            }
        }
        return null;
    }
}

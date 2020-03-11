package afedorov.dao.impl.inmemory;

import afedorov.dao.interfaces.ClientDao;
import afedorov.entities.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDaoImpl implements ClientDao {
    private Long idGenerator = 1l;
    private List<Client> clients = new ArrayList<>();

    @Override
    public void add(Client client) {
        Client findedByEmail = findByEmail(client.getMail());
        if(findedByEmail != null) {
            throw new RuntimeException("Клиент уже существует");
        }
        client.setId(idGenerator);
        idGenerator++;
        clients.add(client);
    }

    @Override
    public void remove(Long id) {
        Client removed = findById(id);
        if (removed != null) {
            clients.remove(removed);
        }
    }

    @Override
    public Client findById(Long id) {
        if(id != null) {
            for (Client finded: clients) {
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
            for (Client finded : clients) {
                if(email.equals(finded.getMail())) {
                    return finded;
                }
            }
        }
        return null;
    }
}

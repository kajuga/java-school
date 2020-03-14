package afedorov.dao.interfaces;

import afedorov.entities.Client;

import java.util.List;

public interface ClientDao {

    void add(Client client);
    void remove (Long id);
    Client findById (Long id);
    void update(Client client);
    List<Client> findAll();
}

package afedorov.dao;

import afedorov.entities.Client;

public interface ClientDao {

    void add(Client client);
    void remove (Long id);
    Client findById (Long id);
    void update(Client client);
}

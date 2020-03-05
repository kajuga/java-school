package afedorov.dao;

import afedorov.entities.Address;

public interface AddressDao {

    Address findByID(Long id);
    void add (Address address);
    void remove (Address address);
    void update (Address address);



}

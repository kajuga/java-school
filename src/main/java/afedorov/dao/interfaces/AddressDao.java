package afedorov.dao.interfaces;

import afedorov.entities.Address;

import java.util.List;

public interface AddressDao {
    void add (Address address);
    void remove (Long id);
    void update (Long id, Address address);
    List<Address> findByUserID(Long id);
    Address findById(Long id);
    List<Address> findAll();
}


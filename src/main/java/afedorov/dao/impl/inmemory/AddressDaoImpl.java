package afedorov.dao.impl.inmemory;

import afedorov.dao.AddressDao;
import afedorov.entities.Address;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl implements AddressDao {
    private Long idGenerator = 1l;
    private List<Address> adresses = new ArrayList<>();

    @Override
    public Address findByID(Long id) {
        if (id != null) {
            for (Address addr : adresses) {
                if (id.equals(addr.getId())) {
                    return addr;
                }
            }
        }
        return null;
    }

    @Override
    public void add(Address address) {
        address.setId(idGenerator);
        idGenerator++;
        adresses.add(address);
    }

    @Override
    public void remove(Address address) {
        Address finded = findByID(address.getId());
        if (finded != null) {
            adresses.remove(finded);
        }
    }

    @Override
    public void update(Address address) {
        Address updated = findByID(address.getId());
        if (updated != null) {
            updated.setCity(address.getCity());
            updated.setCountry(address.getCountry());
            updated.setHouseNumber(address.getHouseNumber());
            updated.setPostcode(address.getPostcode());
            updated.setRoom(address.getRoom());
            updated.setStreet(address.getStreet());
        }
    }
}

package afedorov.dao.impl.inmemory;

import afedorov.dao.interfaces.AddressDao;
import afedorov.entities.Address;
import afedorov.exceptions.EntityExistException;

import java.util.ArrayList;
import java.util.List;
import static afedorov.dao.impl.inmemory.InMemoryDataBase.ADDRESSES;

public class AddressDaoImpl implements AddressDao {
    private Long idGenerator = 1L;

    @Override
    public void add(Address address) {
        if (address != null) {
            Address exist = findById(address.getId());
            if (exist == null) {
                address.setId(idGenerator);
                idGenerator++;
                ADDRESSES.add(address);
            } else {
                throw new EntityExistException("Address id: " + address.getId() + " already exist!");
            }
        }
    }

    @Override
    public void remove(Long id) {
        Address finded = findById(id);
        if (finded != null) {
            ADDRESSES.remove(finded);
        }
    }

    @Override
    public void update(Long id, Address address) {
        Address updated = findById(address.getId());
        if (updated != null) {
            updated.setCity(address.getCity());
            updated.setCountry(address.getCountry());
            updated.setHouseNumber(address.getHouseNumber());
            updated.setPostcode(address.getPostcode());
            updated.setRoom(address.getRoom());
            updated.setStreet(address.getStreet());
        }
    }

    @Override
    public Address findById(Long id) {
        if (id != null) {
            for (Address addr : ADDRESSES) {
                if (id.equals(addr.getId())) {
                    return addr;
                }
            }
        }
        return null;
    }

    @Override
    public List<Address> findByUserID(Long id) {
        if (id != null) {
            List<Address> finded = new ArrayList<>();
            for (Address userAddr : ADDRESSES) {
                if (userAddr.getUser().getId().equals(id)) {
                    finded.add(userAddr);
                }
            }
            return finded;
        } else {
            return null;
        }
    }

    @Override
    public List<Address> findAll() {
        return ADDRESSES;
    }
}

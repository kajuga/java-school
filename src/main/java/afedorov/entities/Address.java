package afedorov.entities;

import lombok.Data;

@Data
public class Address {
    private Long id;
    private Client client;
    private String country;
    private String city;
    private String postcode;
    private String street;
    private int houseNumber;
    private int room;
}

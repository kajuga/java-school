package afedorov.entities;

import lombok.Data;

@Data
public class Address {
    private Long id;
    private User user;
    private String country;
    private String city;
    private Integer postcode;
    private String street;
    private String houseNumber;
    private String room;
    private String phone;
}

package afedorov.entities;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class User {

    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String role;
//    private Address address;
    private String mail;
    private String password;
}

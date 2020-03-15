package afedorov.entities;

import lombok.Data;
import java.time.LocalDate;

@Data
public class User {

    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String role;
    private String mail;
    private String password;
}

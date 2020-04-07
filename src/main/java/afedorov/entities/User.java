package afedorov.entities;

import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Data
public class User {

    private Long id;
    private String name;
    private String lastName;
    private Date birthDate;
    private String role;
    private String mail;
    private String password;
}

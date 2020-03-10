package afedorov.entities;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Client {

    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String mail;
    private String password;
}

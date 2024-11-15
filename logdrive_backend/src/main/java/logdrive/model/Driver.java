package logdrive.model;

import jakarta.persistence.*;
import logdrive.dto.SignupDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String cpf;
    private String password;
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicle> vehicles;

   public Driver(SignupDTO signupDTO) {
       this.email = signupDTO.email();
       this.username = signupDTO.username();
       this.cpf = signupDTO.cpf();
       this.password = new BCryptPasswordEncoder().encode(signupDTO.password());
       this.vehicles = new ArrayList<>();
   }
}

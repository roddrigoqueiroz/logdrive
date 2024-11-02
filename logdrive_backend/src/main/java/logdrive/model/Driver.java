package logdrive.model;

//import jakarta.persistence.*;
import logdrive.dto.SignupDTO;
import lombok.Getter;
import lombok.Setter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
//@Entity
public class Driver {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String email;
    private String username;
    private String cpf;
    private String password;
//    @OneToMany
    private List<InterfaceVehicle> listaVeiculos;

   public Driver(SignupDTO signupDTO) {
       this.email = signupDTO.email();
       this.username = signupDTO.username();
       this.cpf = signupDTO.cpf();
       this.password = new BCryptPasswordEncoder().encode(signupDTO.password());
       this.listaVeiculos = new ArrayList<>();
   }
}

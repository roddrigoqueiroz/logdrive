package logdrive.model;

//import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
//@Entity
public class Driver {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String email;
    private String nome;
    private String cpf;
    private String password;
//    @OneToMany
    private List<InterfaceVehicle> listaVeiculos;
}

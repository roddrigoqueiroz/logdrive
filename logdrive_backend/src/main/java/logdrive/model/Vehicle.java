package logdrive.model;

//import jakarta.persistence.*;

import java.util.List;

//@Entity
public class Vehicle {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String placa;
    private String chassi;
//    @OneToMany
    private List<Maintenance> listaManutencoes;
    private int anoFabricacao;
    private int anoModelo;
    private String cor;
    private String marca;
    private String modelo;
    private double kmRodados;
//    @OneToMany
    private List<Ticket> listaMultas;
//    @OneToMany
    private List<Travel> listaViagem;


}

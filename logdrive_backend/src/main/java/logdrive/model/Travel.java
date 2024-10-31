package logdrive.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;

import java.sql.Time;
import java.util.Date;

//@Entity
public class Travel {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private String destino;
    private double distancia;
    private Date data;
    private Time tempo;

}

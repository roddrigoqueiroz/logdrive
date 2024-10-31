package logdrive.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;

import java.util.Date;

//@Entity
public class Ticket {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private double valor;
    private String descricao;
    private TicketSeverity gravidade;
    private Date data;
    private int pontos;
}

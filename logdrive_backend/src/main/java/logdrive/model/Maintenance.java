package logdrive.model;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;

import java.util.Date;

//@Entity
public class Maintenance implements ChargedService {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    private double oleoKm;
    private double pneuKm;
    private Date dataAbastecimento;
    private Date dataCalibracao;
    private Date dataBateria;
    private Date dataFiltro;
    private Date dataFreio;
    private double valor;

    @Override
    public void cobrar(double valor) {
        this.valor = valor;
    }
}

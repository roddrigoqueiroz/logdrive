package logdrive.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Maintenance implements ChargedService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double oleoKm;
    private double pneuKm;
    private double valor;
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @Temporal(TemporalType.DATE)
    private Date dataAbastecimento;
    @Temporal(TemporalType.DATE)
    private Date dataCalibracao;
    @Temporal(TemporalType.DATE)
    private Date dataBateria;
    @Temporal(TemporalType.DATE)
    private Date dataFiltro;
    @Temporal(TemporalType.DATE)
    private Date dataFreio;

    @Override
    public void cobrar(double valor) {
        this.valor = valor;
    }
}

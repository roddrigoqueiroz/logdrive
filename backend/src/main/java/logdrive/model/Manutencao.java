package logdrive.model;

import java.util.Date;

public class Manutencao implements Servico{
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

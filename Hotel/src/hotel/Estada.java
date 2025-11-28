/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author demba
 */
public class Estada {

    private Client client;
    private Habitacio habitacio;
    private List<Servei> serveis;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSortida;
    private double importActualGastat;
    private double facturaFinal;

    public Estada(Client client, Habitacio habitacio, List<Servei> serveis, LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSortida, double importActualGastat, double facturaFinal) {
        this.client = client;
        this.habitacio = habitacio;
        this.serveis = serveis;
        this.dataHoraEntrada = dataHoraEntrada;
        this.dataHoraSortida = dataHoraSortida;
        this.importActualGastat = importActualGastat;
        this.facturaFinal = facturaFinal;
    }

    public Client getClient() {
        return client;
    }

    public Habitacio getHabitacio() {
        return habitacio;
    }

    public List<Servei> getServeis() {
        return serveis;
    }

    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    public LocalDateTime getDataHoraSortida() {
        return dataHoraSortida;
    }

    public double getImportActualGastat() {
        return importActualGastat;
    }

    public double getFacturaFinal() {
        return facturaFinal;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setHabitacio(Habitacio habitacio) {
        this.habitacio = habitacio;
    }

    public void setServeis(List<Servei> serveis) {
        this.serveis = serveis;
    }

    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
    }

    public void setDataHoraSortida(LocalDateTime dataHoraSortida) {
        this.dataHoraSortida = dataHoraSortida;
    }

    public void setImportActualGastat(double importActualGastat) {
        this.importActualGastat = importActualGastat;
    }

    public void setFacturaFinal(double facturaFinal) {
        this.facturaFinal = facturaFinal;
    }

    @Override
    public String toString() {
        return "Estada{" + "client=" + client + ", habitacio=" + habitacio + ", serveis=" + serveis + ", dataHoraEntrada=" + dataHoraEntrada + ", dataHoraSortida=" + dataHoraSortida + ", importActualGastat=" + importActualGastat + ", facturaFinal=" + facturaFinal + '}';
    }    
}

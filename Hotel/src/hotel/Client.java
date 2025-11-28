/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel;

import java.time.LocalDate;

/**
 *
 * @author demba
 */
public class Client {

    // atributs
    private String nif;
    private String nom;
    private String dataNaixement;
    private String localitat;
    private boolean cobrat;

    public Client(String nif, String nom, String dataNaixement, String localitat, boolean cobrat) {
        this.nif = nif;
        this.nom = nom;
        this.dataNaixement = dataNaixement;
        this.localitat = localitat;
        this.cobrat = cobrat;
    }

    public Client(String nif, String nom, LocalDate data, String loc, boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getNif() {
        return nif;
    }

    public String getNom() {
        return nom;
    }

    public String getDataNaixement() {
        return dataNaixement;
    }

    public String getLocalitat() {
        return localitat;
    }

    public boolean isCobrat() {
        return cobrat;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDataNaixement(String dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public void setLocalitat(String localitat) {
        this.localitat = localitat;
    }

    public void setCobrat(boolean cobrat) {
        this.cobrat = cobrat;
    }

    @Override
    public String toString() {
        return "Client{" + "nif=" + nif + ", nom=" + nom + ", dataNaixement=" + dataNaixement + ", localitat=" + localitat + ", cobrat=" + cobrat + '}';
    }
}

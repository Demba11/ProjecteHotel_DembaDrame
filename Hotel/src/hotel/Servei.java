/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel; //koko

/**
 *
 * @author demba
 */
public class Servei {
    private int id;
    private String descripcio;
    private double preu;

    public Servei(int id, String descripcio, double preu) {
        this.id = id;
        this.descripcio = descripcio;
        this.preu = preu;
    }

    public int getId() {
        return id;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public double getPreu() {
        return preu;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    @Override
    public String toString() {
        return "Servei{" + "id=" + id + ", descripcio=" + descripcio + ", preu=" + preu + '}';
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotel;

/**
 *
 * @author demba
 */
public class Habitacio {
    private int numero;
    private double preuNit;
    private boolean ocupada;

    public Habitacio(int numero, double preuNit, boolean ocupada) {
        this.numero = numero;
        this.preuNit = preuNit;
        this.ocupada = ocupada;
    }

    public int getNumero() {
        return numero;
    }

    public double getPreuNit() {
        return preuNit;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPreuNit(double preuNit) {
        this.preuNit = preuNit;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        return "Habitacio{" + "numero=" + numero + ", preuNit=" + preuNit + ", ocupada=" + ocupada + '}';
    }
}

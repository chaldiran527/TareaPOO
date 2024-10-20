/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Contrato {
    private String numero;
    private Date fechaIngreso;
    private int cantidadDias;
    private double costoPorDia;
    private String nombreDueño;
    private String datoContacto;
    private Mascota mascota;

    public Contrato(String numero, Date fechaIngreso, int cantidadDias, double costoPorDia, String nombreDueño, String datoContacto, Mascota mascota) {
        this.numero = numero;
        this.fechaIngreso = fechaIngreso;
        this.cantidadDias = cantidadDias;
        this.costoPorDia = costoPorDia;
        this.nombreDueño = nombreDueño;
        this.datoContacto = datoContacto;
        this.mascota = mascota;
    }

    public Mascota getMascota() {
        return mascota;
    }

    @Override
    public String toString() {
        return "Contrato{" +
                "numero='" + numero + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", cantidadDias=" + cantidadDias +
                ", costoPorDia=" + costoPorDia +
                ", nombreDueño='" + nombreDueño + '\'' +
                ", datoContacto='" + datoContacto + '\'' +
                ", mascota=" + mascota +
                '}';
    }    
}

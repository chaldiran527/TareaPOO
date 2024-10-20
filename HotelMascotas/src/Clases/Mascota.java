/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Usuario
 */
public abstract class Mascota {
    protected String nombreDueño;
    protected String nombreMascota;
    protected String raza;
    
    public Mascota(String nombreDueño, String nombreMascota, String raza) {
        this.nombreDueño = nombreDueño;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
    }
    
    public abstract String getTipoAlimento();
    public abstract int getCantAlimentacion();
    
    @Override
    public String toString() {
        return "Mascota{" +
                "nombreDueño='" + nombreDueño + '\'' +
                ", nombreMascota='" + nombreMascota + '\'' +
                ", raza='" + raza + '\'' +
                '}';
    }
    
}

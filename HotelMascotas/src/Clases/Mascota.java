/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public abstract class Mascota implements Serializable{
    protected String nombreDueño;
    protected String nombreMascota;
    protected String raza;
    protected String especie;
    
    public Mascota(String nombreDueño, String nombreMascota, String raza, String especie) {
        this.nombreDueño = nombreDueño;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
        this.especie = especie;
    }
    
    public abstract String getTipoAlimento();
    public abstract int getCantAlimentacion();
    public String getEspecie(){
        return especie;
    }
    
    @Override
    public String toString() {
        return "Mascota{" +
                "nombreDueño='" + nombreDueño + '\'' +
                ", nombreMascota='" + nombreMascota + '\'' +
                ", raza='" + raza + '\'' +
                ", especie='" + especie + '\'' +
                '}';
    }
    
}

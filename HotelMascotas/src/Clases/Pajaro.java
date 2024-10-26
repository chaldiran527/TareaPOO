/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Usuario
 */
public class Pajaro extends Mascota{
    public Pajaro(String nombreDueño, String nombreMascota, String raza) {
        super(nombreDueño, nombreMascota, raza);
    }

    @Override
    public String getTipoAlimento() {
        return "Semillas";
    }

    @Override
    public int getCantAlimentacion() {
        return 1; // Una vez al día
    }

  
 
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Usuario
 */
public class Gato extends Mascota{
    public Gato(String nombreDueño, String nombreMascota, String raza) {
        super(nombreDueño, nombreMascota, raza);
    }

    @Override
    public String getTipoAlimento() {
        return "Atun";
    }

    @Override
    public int getCantAlimentacion() {
        return 2; // Dos veces al día
    }

    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author Usuario
 */
public class Perro extends Mascota{
    public Perro(String nombreDueño, String nombreMascota, String raza) {
        super(nombreDueño, nombreMascota, raza);
    }

    @Override
    public String getTipoAlimento() {
        return "Dog Chow";
    }

    @Override
    public int getCantAlimentacion() {
        return 2; //
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import javax.swing.JOptionPane;

/**
 *
 * @author jessi
 */
public class Alimento {
    private String nombre;
    private int cantidadDisponible;

    public Alimento() {
    }

    public Alimento(String nombre, int cantidadDisponible){
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }
    
    public boolean reducirCantidad(int cantidad){
        if (cantidadDisponible >= cantidad){
            cantidadDisponible -= cantidad;
            return true;
        }
        
        else
            return false;
    }
    
    public boolean aumentarCantidad(){
        int cantidadAlimento;
        while (true) {
            String alimento = JOptionPane.showInputDialog("Ingrese la cantidad de alimento que va a agregar:");

            if (alimento == null) {
                JOptionPane.showMessageDialog(null, "Entrada cancelada.");
                return false;
            }

            try {
                cantidadAlimento = Integer.parseInt(alimento);
                break;
            } 
            
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido."); 
            }
        }
        cantidadDisponible += cantidadAlimento;
        return true;
    }

    @Override
    public String toString() {
        return "Alimento{" + "nombre=" + nombre + ", cantidadDisponible=" + cantidadDisponible + '}';
    }
    
    
    
}

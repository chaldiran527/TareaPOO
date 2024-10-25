/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Hotel implements IRegistrable{
    private List<Contrato> listaMascotas;
    private static final int CAPACIDAD_MAXIMA = 10;
    private List<String> inventarioAlimentos;
    private int contadorContratos;

    public Hotel() {
        listaMascotas = new ArrayList<>();
        inventarioAlimentos = new ArrayList<>();
        inventarioAlimentos.add("Dog Chow");
        inventarioAlimentos.add("Atun");
        inventarioAlimentos.add("Semillas");
        inventarioAlimentos.add("Plankton");
        contadorContratos = 1;
    }

    public boolean ingresarMascota(){
        
        String nombreDueño  = JOptionPane.showInputDialog(null, "Introduce el nombre del dueño:");
        if (nombreDueño == null) {
            return false; // El usuario canceló
        }
        
        String telefono;
        while (true) {
            telefono = JOptionPane.showInputDialog(null, "Introduce el número de teléfono:");
            if (telefono == null) {
                return false; // El usuario canceló
            }

            if (telefono.matches("\\d{8}")) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Por favor, introduce un número de teléfono válido.");
            }
        }
        
        int cantidadDias;
        while (true) {
            String dias = JOptionPane.showInputDialog("Ingrese la cantidad de días:");

            if (dias == null) {
                JOptionPane.showMessageDialog(null, "Entrada cancelada.");
                return false;
            }

            try {
                cantidadDias = Integer.parseInt(dias);
                break;
            } 
            
            catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido."); 
            }
        }
        
        String nombreMascota = JOptionPane.showInputDialog(null, "Introduce el nombre de la mascota:");
        if (nombreMascota == null) {
            return false; // El usuario canceló
        }
        
        String raza = JOptionPane.showInputDialog(null, "Introduce la raza de la mascota:");
        if (raza == null) {
            return false; // El usuario canceló
        }
        
        String [] opciones = {"Perro", "Gato", "Pez", "Pájaro"};
        int tipoMascota = JOptionPane.showOptionDialog(null, "Seleccione el tipo de mascota", "Tipo de mascota", JOptionPane.DEFAULT_OPTION, 
                          JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
         
        Mascota nuevaMascota = null;
        
        switch(tipoMascota){
            case 0: 
                nuevaMascota = new Perro(nombreDueño, nombreMascota, raza);
                break;
                
            case 1: 
                nuevaMascota = new Gato(nombreDueño, nombreMascota, raza);
                break;
            
            case 2: 
                nuevaMascota = new Pez(nombreDueño, nombreMascota, raza);
                break;
                
            case 3: 
                nuevaMascota = new Pajaro(nombreDueño, nombreMascota, raza);
                break;
                
            default:
                JOptionPane.showMessageDialog(null, "Tipo de mascota no válido.");
                return false;
        }
        
        Contrato nuevoContrato = new Contrato(String.valueOf(contadorContratos++), new Date(), cantidadDias, 50.0, nombreDueño, telefono, nuevaMascota);

        // Intentar registrar la mascota en el hotel
        if (registrar(nuevoContrato)) {
            JOptionPane.showMessageDialog(null, "Mascota registrada exitosamente.");
            return true;
            
        } else {
            JOptionPane.showMessageDialog(null, "No hay cupo disponible en el hotel.");
            return false;
        }
    }
    
    
    @Override
    public boolean registrar(Object dato) {
        if (dato instanceof Contrato){
            Contrato contrato = (Contrato) dato;
            
            if (listaMascotas.size() < CAPACIDAD_MAXIMA) {
                listaMascotas.add(contrato);
                guardarInquilinos();
                return true;
            }
            return false; // No hay cupo
        }
        return false; 
    }

    private void guardarInquilinos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("inquilinos.dat"))) {
            oos.writeObject(listaMascotas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listarMascotas() {
        for (Contrato contrato : listaMascotas) {
            System.out.println(contrato);
        }
    }

    public void alimentarMascotas(Asistente asistente) throws AlimentacionException {
        for (Contrato contrato : listaMascotas) {
            Mascota mascota = contrato.getMascota();
            String tipoAlimento = mascota.getTipoAlimento();
            if (inventarioAlimentos.contains(tipoAlimento)) {
                System.out.println("Alimentando a la mascota " + mascota.nombreMascota + " con el alimento " + tipoAlimento);
            } else{
                asistente.registrar("No hay alimento para la mascota: " + mascota.nombreMascota);
                //throw new AlimentacionException(ERRORES.ALIMENTO_AGOTADO, mascota.nombreMascota);
            }
            
            if (new Random().nextBoolean()){
                asistente.registrar("La mascota: " + mascota.nombreMascota + " no quiso comer.");
                //throw new AlimentacionException(ERRORES.NO_COMIO, mascota.nombreMascota);
            }
        }
    }
    
    
}

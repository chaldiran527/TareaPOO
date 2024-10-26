/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Usuario
 */
public class Hotel implements IRegistrable{
    private List<Contrato> listaMascotas;
    private static final int CAPACIDAD_MAXIMA = 10;
    private ArrayList<Alimento> inventarioAlimento = new ArrayList <Alimento>();
    private int contadorContratos;

    public Hotel() {
        listaMascotas = new ArrayList<>();
        Alimento perro = new Alimento("Dog Chow", 10);
        Alimento gato = new Alimento("Atun", 10);
        Alimento pajaro = new Alimento("Semillas", 10);
        Alimento pez = new Alimento("Plankton", 10);
        inventarioAlimento.add(perro);
        inventarioAlimento.add(gato);
        inventarioAlimento.add(pajaro);
        inventarioAlimento.add(pez);
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
        if (listaMascotas.isEmpty()){
            JOptionPane.showMessageDialog(null, "Lista vacía.");
            return;
        }
        StringBuilder mensaje = new StringBuilder("Mascotas \n");
        for (Contrato contrato : listaMascotas) {
            mensaje.append(contrato).append("\n").append("\n");
        }
        JDialog dialog = new JDialog((Frame)null, "Lista de mascotas", true);
        dialog.setLayout(new BorderLayout());

        // Crear área de texto y panel de desplazamiento
        JTextArea textArea = new JTextArea(mensaje.toString());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));  // Tamaño preferido del área con scroll
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Añadir componentes al diálogo
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Configurar y mostrar el diálogo
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Centrar la ventana
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
    
    
    
    
    public Alimento buscarAlimento(String tipoAlimento){
        for (Alimento alimento : inventarioAlimento){
            if (tipoAlimento == alimento.getNombre())
                return alimento;
        }
        return null;
    }
    
    
    
    
    public void mostrarInventario(){
        if (inventarioAlimento.isEmpty()){
            JOptionPane.showMessageDialog(null, "Inventario vacío.");
            return;
        }
        StringBuilder mensaje = new StringBuilder("Inventario \n");
        for (Alimento alimento : inventarioAlimento){
            mensaje.append(alimento).append("\n").append("\n");
        }
        JDialog dialog = new JDialog((Frame)null, "Inventario", true);
        dialog.setLayout(new BorderLayout());

        // Crear área de texto y panel de desplazamiento
        JTextArea textArea = new JTextArea(mensaje.toString());
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));  // Tamaño preferido del área con scroll
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Añadir componentes al diálogo
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Configurar y mostrar el diálogo
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Centrar la ventana
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
    
    
    
    
    
    public void aumentarCantidadAlimento(){
        String [] opciones = {"Dog Chow", "Atún", "Semillas", "Plankton"};
        if (opciones == null){
            return;
        }
        
        int tipoAlimento = JOptionPane.showOptionDialog(null, "Seleccione el tipo de alimento", "Tipo de alimento", JOptionPane.DEFAULT_OPTION, 
                          JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
        Alimento alimento;
        switch(tipoAlimento){
            case 0: 
                String perro = "Dog Chow";
                alimento = buscarAlimento(perro);
                break;
                
            case 1: 
                String gato = "Atun";
                alimento = buscarAlimento(gato);
                break;
            
            case 2: 
                String pajaro = "Semillas";
                alimento = buscarAlimento(pajaro);
                break;
                
            case 3: 
                String pez = "Plankton";
                alimento = buscarAlimento(pez);
                break;
                
            default:
                return;
        }
        alimento.aumentarCantidad();
    }
    
    

    public void alimentarMascotas(Asistente  asistente) throws AlimentacionException {
        
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = formato.format(fecha);
        asistente.registrar("Fecha y hora: " + fechaFormateada);
        if (listaMascotas.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay mascotas para alimentar.");
        }
        for (Contrato contrato : listaMascotas) {
            
            Mascota mascota = contrato.getMascota();
            String tipoAlimento = mascota.getTipoAlimento();
            Alimento alimento = buscarAlimento(tipoAlimento);
            
            try {
                boolean mascotaComio = true;
                
                if (mascota == null) {
                    asistente.registrar("No encuentra el expediente de inquilinos, los animales no podrán ser alimentados en esta fecha.");
                    throw new AlimentacionException(ERRORES.EXPEDIENTE_NO_ENCONTRADO, mascota.nombreMascota);
                }
                
                if (new Random().nextDouble() < 0.1){
                    asistente.registrar("La mascota: " + mascota.nombreMascota + " no quiso comer.");
                    mascotaComio = false;
                    throw new AlimentacionException(ERRORES.NO_COMIO, mascota.nombreMascota);
                }
                
                if (!alimento.reducirCantidad(1)) {
                    asistente.registrar("No hay alimento para la mascota: " + mascota.nombreMascota);
                    mascotaComio = false;
                    throw new AlimentacionException(ERRORES.ALIMENTO_AGOTADO, mascota.nombreMascota);
                }

                if (mascotaComio){
                    asistente.registrar("La mascota: " + mascota.nombreMascota + " sí comió.");
                }
            }
            
            catch (AlimentacionException e){
                System.out.println("Error al alimentar a " + mascota.nombreMascota + ": " + e.getMessage());
            }
        }
        asistente.registrar("\n\n");
        JOptionPane.showMessageDialog(null, "Se terminó el proceso de alimentar a las mascotas.");
    }
}

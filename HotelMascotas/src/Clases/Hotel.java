/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Hotel {
    private List<Contrato> listaMascotas;
    private static final int CAPACIDAD_MAXIMA = 10;
    private List<String> inventarioAlimentos;

    public Hotel() {
        listaMascotas = new ArrayList<>();
        inventarioAlimentos = new ArrayList<>();
        inventarioAlimentos.add("Dog Chow");
        inventarioAlimentos.add("Atun");
        inventarioAlimentos.add("Semillas");
        inventarioAlimentos.add("Plankton");
    }

    public boolean ingresarMascota(Contrato contrato) {
        if (listaMascotas.size() < CAPACIDAD_MAXIMA) {
            listaMascotas.add(contrato);
            guardarInquilinos();
            return true;
        }
        return false; // No hay cupo
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

    public void alimentarMascotas(Asistente asistente) {
        for (Contrato contrato : listaMascotas) {
            Mascota mascota = contrato.getMascota();
            String tipoAlimento = mascota.getTipoAlimento();
            if (inventarioAlimentos.contains(tipoAlimento)) {
                System.out.println("Alimentando a la mascota " + mascota.nombreMascota + " con el alimento " + tipoAlimento);
            } else {
                asistente.registrar("No hay almineto para la mascota: " + mascota.nombreMascota);
            }
        }
    }

}

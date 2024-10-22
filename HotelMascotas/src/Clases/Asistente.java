/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Usuario
 */

interface IRegistrable {
    boolean registrar(Object dato);
}

public class Asistente implements IRegistrable {
    private Hotel hotel;

    public Asistente(Hotel hotel) {
        this.hotel = hotel;
    }
    
    
    @Override
    public boolean registrar(Object dato) {
        try (FileWriter fw = new FileWriter("bitacora.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(dato);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    } 
}

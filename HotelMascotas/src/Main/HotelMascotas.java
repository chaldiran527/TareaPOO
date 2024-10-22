/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Clases.*;

/**
 *
 * @author Usuario
 */
public class HotelMascotas {


    public static void main(String[] args) throws AlimentacionException {
        
        Hotel hotel = new Hotel();
        
        hotel.ingresarMascota();
        hotel.ingresarMascota();
        hotel.ingresarMascota();
        
        hotel.listarMascotas();
        Asistente asistente = new Asistente(hotel);
        
        hotel.alimentarMascotas(asistente);
    }
    
}

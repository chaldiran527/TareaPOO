/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author jessi
 */
public class AlimentacionException extends Exception {
    private ERRORES codExcepcion;
    private String nombreMascota;

    public AlimentacionException(ERRORES codExcepcion, String nombreMascota) {
        this.codExcepcion = codExcepcion;
        this.nombreMascota = nombreMascota;
    }

    @Override
    public String getMessage() {
        String mensaje="";
        switch (codExcepcion){
            case EXPEDIENTE_NO_ENCONTRADO : mensaje = "No encuentra el expediente de inquilinos, los animales no podrán ser alimentados en esta fecha."; break;
            case ALIMENTO_AGOTADO : mensaje = "No hay alimento para la mascota: " + nombreMascota; break;
            case NO_COMIO : mensaje = "La mascota: " + nombreMascota + " no quiso comer."; break;
        }
        return mensaje;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import java.util.Date;

/**
 *
 * @author nere1
 */
public class AlquilarObjeto {
    private int idPersona, idObjeto;
    private final float coste;
    private final float porcentajeParaStartup;
    private Date fecha_ini, fecha_fin;
    private final String auxNombre;
    
    public AlquilarObjeto (int idPersona, int idObjeto,float coste ,Date fecha_ini, Date fecha_fin, String auxNombre) {
        this.idPersona = idPersona;
        this.idObjeto = idObjeto;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
        this.auxNombre = auxNombre;
        this.coste = coste;
        
        porcentajeParaStartup = (float) 0.10;
    }
    
    public float calcularCoste (){
        // no se si esta contando con los dias de la fecha o no  si no lo unico que deveriamos har en en una de las dos sumarle dos 
        return ((fecha_fin.getTime() - fecha_ini.getTime()) / 86400000) * this.coste;
    }
    
    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(Date fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
    
    // terminar
    public String ToString(){
        return  "\n\t\tPRÉSTAMOS DEL OBJETO "+ this.idObjeto + 
                "\n\t\t\tNombre del cliente: " + this.auxNombre +
                "\n\t\t\tFechas del préstamo: " + this.fecha_ini + "-" + this.fecha_fin+
                "\n\t\t\tImporte para el propietario: " + calcularCoste() +
                "\n\t\t\tImporte para la Startup: " + (calcularCoste() * porcentajeParaStartup);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author nere1
 */
public class Usuario {
 
    public static int usuarioprox = 1;
    private int idusuario;
    private String nombre;
    private String correo;
    private ArrayList <Objeto> objetosEnAlquiler = new ArrayList();
    
    // aqui igual hay que añadir un parametro que sea array para ir añadiendole los alquileres de cada usuario que arrenda
    
    public Usuario(String nombre, String correo){
        
        this.idusuario = usuarioprox;
        usuarioprox++;
        
        this.nombre = nombre;
        this.correo = correo;
    }

    public void addObjetoAAlquilar(Objeto obj){
        objetosEnAlquiler.add(obj);
        
    }
    
    //tengo que hacer una funcion que propague hacia objeto el aquiler que se quiere realizar
    public void addAlquilerAlObjeto (int idPer, int idObj, float coste,Date fecha_iniTemp, Date fecha_finTemp, String auxNombre){
        // buscamos el objeto de la lista de objetos que se pueden alquilar del usuario
        for(int i = 0; i < objetosEnAlquiler.size(); i++){
            objetosEnAlquiler.get(i).addAlquilerAlObjeto(idPer, idObj, coste,fecha_iniTemp, fecha_finTemp, auxNombre);
        }
       
    }
    
    public int getIdusuario() {
        return idusuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
  
    public ArrayList<Objeto> getObjetosEnAlquiler() {
        return objetosEnAlquiler;
    }

    public void setObjetosEnAlquiler(ArrayList<Objeto> objetosEnAlquiler) {
        this.objetosEnAlquiler = objetosEnAlquiler;
    }
    
    public void mostrarObjetos(){
        for(int i = 0; i < objetosEnAlquiler.size();i++){
            System.out.println(this.objetosEnAlquiler.get(i).ToString());
        }
    }
    
    public boolean tieneObjetos(){
        return !this.objetosEnAlquiler.isEmpty();
    }
    
    // Actualizar esto
    public String ToString(){
       return "\nPROPIETARIO " + this.getIdusuario() + 
                // String.format( "%03d", idusuario) + "\n"+ 
                "\nNombre: " + this.nombre + 
                "\nCorreo: " + this.correo +
                "\n";
               
    }
}

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
    private String correo, direccion, poblacion, provincia;
    private ArrayList <Objeto> objetosEnAlquiler = new ArrayList();
    private boolean baja = false;
    private float sumaPrestamos;
    
    public Usuario(String nombre, String correo, String direccion, String poblacion, String provincia){
        
        this.idusuario = usuarioprox;
        usuarioprox++;
        
        this.nombre = nombre;
        this.correo = correo;
        this.poblacion = poblacion;
        this.provincia = provincia;
        this.direccion = direccion;
    }

    public void addObjetoAAlquilar(Objeto obj){
        objetosEnAlquiler.add(obj);
        
    }
    
    public void addAlquilerAlObjeto (int idPer, int idObj, float coste,Date fecha_iniTemp, Date fecha_finTemp, String auxNombre){
        // buscamos el objeto de la lista de objetos que se pueden alquilar del usuario
        for(int i = 0; i < objetosEnAlquiler.size(); i++){
            objetosEnAlquiler.get(i).addAlquilerAlObjeto(idPer, idObj, coste,fecha_iniTemp, fecha_finTemp, auxNombre);
        }
       
    }
    
    //Creo metodo para meter en el fichero
    public String guardarUsuario(){
        String s;
        
        s = "\nPROPIETARIO " + this.getIdusuario() + 
                "\nNombre: " + this.nombre + 
                "\nCorreo: " + this.correo +
                "\nDireccion: " + this.direccion +
                "\nPoblacion: " + this.poblacion + 
                "\nProvincia: " + this.provincia;
        
        return s;
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

    public boolean isBaja() {
        return baja;
    }

    public void setBaja(boolean baja) {
        this.baja = baja;
    }
    
    public float getSumCoste(){
        
        float suma = 0;
        
        if(this.tieneObjetos()){
            for(int i = 0; i < this.getObjetosEnAlquiler().size(); i++){
                suma = suma + this.getObjetosEnAlquiler().get(i).getCoste();
            }
        }
        else
           suma = 0;
        return suma;
    }
    
    // Actualizar esto
    public String ToString(){
       return "\nPROPIETARIO " + this.getIdusuario() + 
                // String.format( "%03d", idusuario) + "\n"+ 
                "\nNombre: " + this.nombre + 
                "\nCorreo: " + this.correo +
                "\nDireccion: " + this.direccion +
                "\nPoblacion: " + this.poblacion +
                "\nProvincia: " + this.provincia +
                "\n";
               
    }
}

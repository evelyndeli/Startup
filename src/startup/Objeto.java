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
public class Objeto {
    //Yo añadiria un bool para comprobar si el objeto esta disponible, ya que en la opcion 5 dice que tiene que seguir mostrandose
    //pero indicando que no esta disponible para alquilar
    public static int objetoprox = 1;
    private int idobjeto;
    private int propietario;
    private String decripcion;
    private Date fecha_ini;
    private Date fecha_fin;
    private float coste;
    private ArrayList <AlquilarObjeto> alquiler = new ArrayList();
    AlquilarObjeto auxObjAlquiler;
    private boolean disponible = true;
    private boolean tieneAlquiler = false;
    
    public Objeto(int propietario, String descripcion, Date fecha_ini, Date fecha_fin, float coste){
      
        this.idobjeto = objetoprox;
        objetoprox++;
        
        this.propietario = propietario;
        this.decripcion = descripcion;
        this.fecha_ini = fecha_ini;
        this.fecha_fin = fecha_fin;
        this.coste = coste;
    }
    
    public void addAlquilerAlObjeto (int idPer, int idObj,float coste ,Date fecha_iniTemp, Date fecha_finTemp, String auxNombre){
        //comprovamos si el objeto esta disponible en estas fechas
        if(fecha_iniTemp.after(this.fecha_ini) && fecha_finTemp.before(this.fecha_fin)){ // lo malo de esto es que no se si son la misma fecha que pasa si no hay que poner mas condiciones con un equels
            
            tieneAlquiler = true;
            
            // Creamos el objeto ya que la fecha esta bien 
            auxObjAlquiler = new AlquilarObjeto(idPer, idObj, coste ,fecha_iniTemp, fecha_finTemp, auxNombre);
            
            // Añadimos el alquiler al objeto
            alquiler.add(auxObjAlquiler);
            
            // CALCULAMOS EL COSTE Y LO MOSTRAMOS
            //esto no va a qui
            //System.out.println("EL COSTE TOTAL DEL ALQUILER ES DE: " + calcularCoste() + " EUROS" );
            

        }  
        else 
            System.out.println("ERROR: En esta fecha el producto no esta disponible"
                + "|\n Introduzca una fecha correcta");
        
    }

    public int getIdobjeto() {
        return idobjeto;
    }
    
    public int getPropietario() {
        return propietario;
    }

    public void setPropietario(int propietario) {
        this.propietario = propietario;
    }

    public String getDecripcion() {
        return decripcion;
    }

    public void setDecripcion(String decripcion) {
        this.decripcion = decripcion;
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

    public float getCoste() {
        return coste;
    }

    public void setCoste(float coste) {
        this.coste = coste;
    }

    public ArrayList<AlquilarObjeto> getAlquiler() {
        return alquiler;
    }

    public void setAlquiler(ArrayList<AlquilarObjeto> alquiler) {
        this.alquiler = alquiler;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public boolean getTieneAlquiler() {
        return tieneAlquiler;
    }
   
    public String mostrarPrestamos(){
        String cadena;
        for(int i = 0; i < alquiler.size();i++){
            //System.out.println(this.alquiler.get(i).ToString());
            return cadena = this.alquiler.get(i).ToString();
        }
     
        return null;
    }
    
    // actuaalizar faltan cosas
    public String ToString(){
        if(this.tieneAlquiler){
           String p = "";
           for (AlquilarObjeto a: alquiler)
               p = p + a.ToString();

            return "\n\tOBJETOS DEL PROPIETARIO " + this.propietario+ 
                    "\n\n\t\t" + "Código del objeto: " + this.getIdobjeto()+
                    "\n\t\t" + "Descripcion: " + this.decripcion+
                    "\n\t\t" + "Fecha disponibilidad: " + this.fecha_ini + "-" + this.fecha_fin +
                    "\n\t\t" + "Coste del préstamo por día: "+ this.coste
                    + p; //this.mostrarPrestamos();
        }
        else{
             return "\n\tOBJETOS DEL PROPIETARIO " + this.propietario+ 
                    "\n\n\t\t" + "Código del objeto: " + this.getIdobjeto()+
                    "\n\t\t" + "Descripcion: " + this.decripcion+
                    "\n\t\t" + "Fecha disponibilidad: " + this.fecha_ini + "-" + this.fecha_fin +
                    "\n\t\t" + "Coste del préstamo por día: "+ this.coste +
                    "\n\t\t\tEl objeto " + this.getIdobjeto() + " no tiene préstamos \n\n";
        }            
    }
}

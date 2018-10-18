/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author nere1
 */

public class Startup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        // estoy probando como va esto
        
        ArrayList <Usuario> usuarios  = new ArrayList();
        ArrayList <Objeto> auxObjetos = new ArrayList(); // esta nos biene bien para cuando tengamos que listar en la opcion 3 solo los objetos
        ArrayList <AlquilarObjeto> prestamos = new ArrayList();
        
        double coste, forStartup = 0.0;
        int opcion = 0;
        int idPropietarioObjeto;
        String auxNombre;
        float auxCoste;
        boolean inicio = false;
        
        Scanner sc = new Scanner(System.in);
        
        // cariables temporales caso1
        String nom, correo;
        Usuario usuario;
        String emailPattern = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" + "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$";
        Pattern pattern = Pattern.compile(emailPattern);
        boolean correoValido = false, idCorrecto2 = false;
        Matcher matcher ;
        
        //variables temporales caso 2
        Objeto objeto;
        int auxIdPropietario;
        float auxcosteDia;
        String auxDescripcion, auxEntrada2, auxCosteDia, inicio2, fin2;
        Date fecha_ini, fecha_fin;
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        boolean fechacorrecta = false, propietarioExiste = false, fech_ini, fech_fin, costeCorrecto;
        
        // variables temporales caso 3
        AlquilarObjeto alquilarObjeto, prestamo;
        Date fecha_iniTemp, fecha_finTemp;
        boolean fecha_iniTemp3, fecha_finTemp3; 
        int idPer, idObj;
        Objeto objTemp;
        int idObjBaja;
        
        do{
           opcion = ponerMenu(); // comprovamos que la entrada es un numero
          
            switch (opcion){
                case 1:
                    System.out.println("RELLENE LOS SIGUIENTES DATOS \n"
                                        + "\t Nombre de usuario: ");
                    nom = sc.nextLine();
                    System.out.println("\n \t Correo electronico: ");
                    correo = sc.nextLine();
                    
                    if (correo != null) {
                        // Comprovamos que el correo tien la forma adecuada
                        while  (correoValido == false){
                            matcher = pattern.matcher(correo);
                            if (!matcher.matches()){
                                System.out.println("El correo tiene formato ejemplo@algo.eje");
                                System.out.println("\n \t Correo electronico: ");
                                correo = sc.nextLine();
                            }
                            else {
                                correoValido = true;
                            }                               
                        }
                    }
                    
                    correoValido = false; // reiniciar el flag para que se haga correctamente la comprovacion del siguiente usuario en el campo email
                    
                    usuario = new Usuario(nom, correo);
                    usuarios.add(usuario);
                    
                    System.out.println(usuario.ToString());
                    
                    break;
                case 2:
                    System.out.println("RELLENE LOS DATOS DEL OBJETO \n"
                                        + "\t ID del propietario: ");
                    auxEntrada2 = sc.nextLine(); //sc.nextLine();
                    idCorrecto2 = soloNumeros(auxEntrada2);
                    
                    // Comprobar que el id es un numero
                    while (idCorrecto2 == false){
                        System.out.println("RELLENE LOS DATOS DEL OBJETO \n"
                                    + "\t ID del propietario: ");
                        auxEntrada2 = sc.nextLine();// sc.nextLine();

                        idCorrecto2 = soloNumeros(auxEntrada2);
                    }
                    
                    auxIdPropietario = Integer.valueOf(auxEntrada2);
                    
                    // Comprobar que el id del usuario existe, como no se pueden borrar usiarios si el id es menos que el tamaño del array es porque existe
                    while((propietarioExiste == false)){
                            if((auxIdPropietario - 1 < usuarios.size())){
                                propietarioExiste = true;
                            }
                            else{
                                System.out.println("El propietario no existe, por favor introduzcalo de nuevo"
                                                    + "\n ID del propietario: ");
                                auxIdPropietario = sc.nextInt(); //sc.nextLine();
                            }
                    }
                    
                    propietarioExiste = false; // volvemos inicializar la variable para el proximo usuario haga bien la comprobacion
                    
                    System.out.println("\n \t Descripción del objeto: ");
                    auxDescripcion = sc.nextLine();
                    
                    System.out.println("\n \t Disponibilidad con formato dd/mm/yyyy"
                             + "\n \t \t Fecha de inicio: ");
                    fecha_ini = f.parse(sc.nextLine()); // si nos ponen un formato que no sea con barra aqui peta y no se que hacer
                    // igual si lo ponemos en un string y luego lo parseamos a date
                    fech_ini = comprobarFormatoFecha(String.valueOf(fecha_ini));
                    
                    
                    System.out.println("\n \t \t Fecha de fin: ");
                    fecha_fin = f.parse(sc.nextLine()); // si nos ponen un formato que no sea con barra aqui peta y no se que hacer
                    fech_fin = comprobarFormatoFecha(String.valueOf(fecha_fin));
                    
                    do{
                        if(fecha_ini.before(fecha_fin) && fech_ini && fech_fin){    // es correcta la fecha
                            fechacorrecta = true;
                        }
                        else{
                            System.out.println("Las fechas introducidas no son validas, por favor vuelva a introducirlas");
                            System.out.println("\n \t Disponibilidad con formato dd/mm/yyyy"
                                        + "\n\t\tFecha de inicio: ");
                                        fecha_ini = f.parse(sc.nextLine());
                                        System.out.println("\n\t\tFecha de fin: ");
                                        fecha_fin = f.parse(sc.nextLine());
                        }
                    }while(fechacorrecta == false);
                    
                    System.out.println("\n \t Coste por día: ");
                    auxCosteDia = sc.nextLine();
                    
                    costeCorrecto = soloNumeros(auxCosteDia);
                    while (costeCorrecto == false){
                        System.out.println("El coste del objeto no puede ser negativo, por favor introduzcalo de nuevo ");
                        auxCosteDia = sc.nextLine();

                        costeCorrecto = soloNumeros(auxCosteDia);
                    }
                    auxcosteDia = Float.valueOf(auxCosteDia); //pasamos el valor a float
                    
                    while(auxcosteDia < 0 ){
                        System.out.println("El coste del objeto no puede ser negativo, por favor introduzcalo de nuevo ");
                        auxcosteDia = sc.nextFloat();
                    }
                   
                    // como todos los parametros son correctos se crea el objeto
                    objeto = new Objeto(auxIdPropietario, auxDescripcion, fecha_ini, fecha_fin, auxcosteDia);
                    
                    //Añado el objeto creado a la persona que le correcponde para hacer mas facil la implementacion del apartado 4
                    // COMO SE SUPONE QUE NO SE PERMITE BORRAR USUARIOS EL ID DEL USUARIOS SERA EL MISMO QUE EL DEL INDICE DE ARRAY
                    usuarios.get(objeto.getPropietario()-1).addObjetoAAlquilar(objeto);
                    auxObjetos.add(objeto);

                    alquilarObjeto = new AlquilarObjeto(objeto.getPropietario(), objeto.getIdobjeto(), objeto.getCoste(),objeto.getFecha_ini(), objeto.getFecha_fin(), usuarios.get(objeto.getPropietario()-1).getNombre());
                    prestamos.add(alquilarObjeto);
                    
                    break;
                case 3:
                    
                    /**
                     * Creo que aqui no deveria de hacerse la clase hasta que no se confirme que la fecha des producto es correcto por lo que deberiamos gusdarlo en variables 
                     * y luego pasar todo por el constructior
                     * 
                     */
                    System.out.println("LISTA DE USUARIOS QUE PUEDEN ALQUILAR \n");
                    // PONER PARA QUE SE IMPRIMA LA LISTA DE PERNOSA QUE PUEDEN ALQUILAR
                    for(int i = 0; i < usuarios.size(); i++){
                        System.out.println(usuarios.get(i).ToString());
                    }
                    
                    System.out.println("LISTA DE OBJETOS QUE ESTAN DISPONIBLES PARA ALQUILAR \n");
                    // PONER PARA QUE SE IMPRIMA LA LISTA DE LOS OBJETOS QUE SE PUEDEN ALQUILAR
                    // con el array auxObjetos
                    for(int i = 0; i < auxObjetos.size(); i++){
                        
                        if(auxObjetos.get(i).isDisponible())
                            System.out.println(auxObjetos.get(i).ToString());
                    }
                    
                    System.out.println("RELLENE LOS DATOS PARA ALQUILAR \n"
                                        + "\t Id de la persona que quiere alquilar: ");
                    idPer = sc.nextInt();
                    System.out.println("\n \t Id del producto que se quiere alquilar: ");
                    idObj = sc.nextInt();sc.nextLine();
                    System.out.println(" \n \t Fecha de inicio de alquiler: ");
                    fecha_iniTemp = f.parse(sc.nextLine());
                    fecha_iniTemp3 = comprobarFormatoFecha(String.valueOf(fecha_iniTemp));
                    
                    System.out.println("\n \t Fecha de fin de alquiler: ");
                    fecha_finTemp = f.parse(sc.nextLine());
                    fecha_finTemp3 = comprobarFormatoFecha(String.valueOf(fecha_finTemp));
                    
                    do{
                        if(fecha_iniTemp.before(fecha_finTemp) && fecha_iniTemp3 && fecha_finTemp3){    // es correcta la fecha
                            fechacorrecta = true;
                        }
                        else{
                            System.out.println("Las fechas introducidas no son validas, por favor vuelva a introducirlas");
                            System.out.println("\n \t Disponibilidad con formato dd/mm/yyyy");
                                        System.out.println(" \n \t Fecha de inicio de alquiler: ");
                                        fecha_iniTemp = f.parse(sc.nextLine());
                                        System.out.println(" \n \t Fecha de fin de alquiler: ");
                                        fecha_finTemp = f.parse(sc.nextLine());
                        }
                    }while(fechacorrecta == false);
                    
                    
                    // una vez ya tenemos los parametros de forma temporal tenemos que saber a quien pertenece el objeto para añadir la entrada del alquiler
                    // como los objetos SI se pueden borrar no tienen por que cuadrar el idobjeto con el indice del array
                    for(int i = 0; i < auxObjetos.size(); i++){
                        //Comprobar que el objeto esta disponible para alquilar
                        if (auxObjetos.get(i).getIdobjeto() == idObj && auxObjetos.get(i).isDisponible()){
                            idPropietarioObjeto = auxObjetos.get(i).getPropietario();
                            auxCoste = auxObjetos.get(i).getCoste();
                            auxNombre = usuarios.get(idPropietarioObjeto - 1).getNombre(); // aqui tambien un -1 no?
                            
                            // ahora que sabemos de quien es el objeto hay que entrar en ese usuario, buscar el objeto y añadirle el alquiler
                            // le pasamos el nombre del usuario para hacer mas facil el mostrado de ejercicio 4
                            usuarios.get(idPropietarioObjeto - 1).addAlquilerAlObjeto(idPer, idObj, auxCoste,fecha_iniTemp, fecha_finTemp, auxNombre); // aqui tambien -1 no??
                        }
                        else {
                            System.out.println("ERROR: El objeto no existe");
                        }
                    }

                    break;
                    
                case 4:
                    
                    // mostrar la lista de propietarios junto a los articulos que tienen
                    for(int i = 0; i < usuarios.size(); i++){
                        System.out.println(usuarios.get(i).ToString());
                        usuarios.get(i).mostrarObjetos();
                    }
                    
                    break;
                case 5:
                    
                    System.out.println("Introduzca el id del objeto que desea dar de baja: ");
                    idObjBaja = sc.nextInt();
                    for(int i = 0; i < auxObjetos.size(); i++){
                        if (auxObjetos.get(i).getIdobjeto() == idObjBaja){
                            //Para alquilar comprobamos que el objeto esta disponible
                            auxObjetos.get(i).setDisponible(false);
                            System.out.println("El objeto ha sido retirado.");
                        }
                        else
                            System.out.println("El objeto no existe.");
                    }
                    break;
                case 6: 
                    
                    for(int i = 0; i < usuarios.size(); i++){
                        if(usuarios.get(i).tieneObjetos()&auxObjetos.get(i).getTieneAlquiler()){
                            System.out.println(usuarios.get(i).ToString());
                            usuarios.get(i).mostrarObjetos();
                            auxObjetos.get(i).mostrarPrestamos();
                            prestamo = auxObjetos.get(i).getAlquiler().get(i);
                            coste = prestamo.calcularCoste();
                            forStartup = (0.10*coste) + forStartup;
                        }
                    }
                    System.out.println("\nImporte total acumulado para la Startup: " +(forStartup) + " euros");
                    break;
                case 7:

                    System.exit(0);
                    break;
                default:System.out.println("Tiene que ser un número del 1 al 7"); 
                    
            }
            
        }while (opcion != 7);
    }
    
    public static boolean comprobarFormatoFecha(String fecha){
        String[] partes = fecha.split("/");
        boolean fechaCorrecta = true; 
        
        if(partes.length == 3){ // la fecha tien formato dd/mm/yyyy
            if (partes[1].length() == 1){ // d/mm/yyyy
                partes[1] = "0" + partes[1];
            }
            else if (partes[1].length() > 2){ // dddddd/mm/yyyy
            
                return fechaCorrecta = false;
            }
            else if (partes[2].length() == 1){ // dd/m/yyyy
                partes[2] = "0" + partes[2];
            }
            else if (partes[2].length() > 2){ // dd/mmmmmmm/yyyy
                return fechaCorrecta = false;
            }
            else if (partes[3].length() == 1){ // dd/mm/y
                partes[3] = "200" + partes[3];
            }
            else if (partes[3].length() == 2){ // dd/mm/yy
                partes[3] = "20" + partes[3];
            }
            else if (partes[3].length() == 3){ // dd/mm/yyy
                partes[3] = "2" + partes[3];
            }
            else if (partes[3].length() > 4){ // dd/mm/yyyyyyy
                return fechaCorrecta = false;
            }
            
        }
        
        return fechaCorrecta;
    }
  
    
    public static int ponerMenu(){
        String opcion;
        Scanner sc = new Scanner(System.in);
        boolean bien = true;
        
        do{
            mostrarMenu();
            opcion = sc.nextLine();
            bien = true;
            
            if (soloNumeros(opcion) == false ){
                System.out.println("Opcion incorrecta...");
                bien = false;
            }
        }while(bien == false);
        
        return Integer.parseInt(opcion);
    }
    
    
    public static void mostrarMenu(){
        
        System.out.println("MENU \n"
                            + "\t 1- Alta usuario \n"
                            + "\t 2- Alta objeto \n"
                            + "\t 3- Alquiler de objetos \n"
                            + "\t 4- Listar todos los objetos \n"
                            + "\t 5- Baja de objeto \n"
                            + "\t 6- Mostrar saldos \n"
                            + "\t 7- Salir \n"
                            + "Introduzca la opción que desee: " );
    }
    
    public static boolean soloNumeros(String comprobar){
        int i = 0;
        
        while (i < comprobar.length() && Character.isDigit(comprobar.charAt(i))){
            i++;
        }
        
        return i == comprobar.length();
    }
    
}


package logicaWorkFlow;

import java.util.ArrayList;

/**
 * Clase que encaspula todos los datos y métodos para describir 
 * una Tarea
 * 
 * @author Borja Bordel
 * @date Julio 2015
 */

public class Tarea {
    
    //Nombre de la tarea
    private String nombre;
    
    // Numero de tarea, es único en todo el sistema
    private int numero;
    // Estado actual de la tarea
    private String estado;
    //Desripción
    private ArrayList<String> descripcion;
    // Punto de la descripción hasta donde se ha ejecutado
    //la Tarea
    private int indiceEjecuacion;
    // Booleanos sobre si la Tarea ha sido finalizada o existosa
    private boolean finalizada;
    private boolean exitosa;
    
    //Estados en los que puede estar una tarea
    public static String ESTADO_PENDIENTE = "Pendiente";
    public static String ESTADO_EXITO = "Completada";
    public static String ESTADO_FALLO = "Fallida";
    public static String ESTADO_EJECUTANDO = "Ejecutando";
    
    /**
     * Constructor
     * 
     * @param nombre Nombre de la tarea
     * @param numero Número de Tarea, debe ser único
     * @param descripcion Descripción de los eventos que conforman la tarea
     */
   public Tarea (String nombre, int numero, ArrayList<String> descripcion) {
       this.nombre = nombre;
       this.numero = numero;
       estado = ESTADO_PENDIENTE;
       this.descripcion = descripcion;
       indiceEjecuacion = 0;
       finalizada = false;
       exitosa = true;
   }
   
   /**
    * Reinicia la tarea
    */
   public void reinicia () {
       estado = ESTADO_PENDIENTE;
       indiceEjecuacion = 0;
       finalizada = false;
       exitosa = true;
   }
   
   /**
    * Devuelve el numero de la tarea
    * 
    * @return Número de tarea
    */
   public int getNumero () {
       return numero;
   }
   
   /**
    * Devuelve el nombre de la tarea
    * 
    * @return String con el nombre de la tarea
    */
   public String getNombre () {
       return nombre;
   }
   
   /**
    * Devuelve el estado de la Tarea
    * 
    * @return String con el estado de la Tarea
    */
   public String getEstado () {
       return estado;
   }
   
   /**
    * Cambia el estado de la Tarea
    * 
    * @param estado Nuevo estado
    */
   public void setEstado (String estado) {
       this.estado = estado;
   }
   
   /**
    * Confirma si la tarea ha sido ejecutado de forma exitosa hasta
    * el momento
    * 
    * @return True si hasta el momento es exitosa 
    */
   public boolean IsExitosa () {
       return exitosa;
   }
   
   /**
    * Avanza un paso en la ejecuación de la tarea
    * 
    * @param mensaje Evento que se acaba de producir
    * @return True si se ha producido una incidencia que debe ser comunicada al 
    * usuario
    * 
    */
   public boolean avanzaPaso (String mensaje) {
       boolean mandaEvento = false;
       
       if(descripcion.get(indiceEjecuacion).equals(mensaje)) {
           indiceEjecuacion++;           
           if (indiceEjecuacion >= descripcion.size()) {
               setEstado(ESTADO_EXITO);
               finalizada = true;
               mandaEvento = true;
           }
       } else {
           setEstado(ESTADO_FALLO);
           exitosa = false;
           finalizada = true;
           mandaEvento = true;
       }
       return mandaEvento;
   }
   
   /**
    * Comprueba si la tarea ha sido completada
    * 
    * @return True si la tarea ha sido completada 
    */
   public boolean IsFinalizada () {
       return finalizada;
   }
}


package logicaWorkFlow;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Clase que se encargad e gestionar los procesos de generación, verificación y 
 * notificación asociados a los WorkFlow
 * 
 * @author Borja Bordel
 * @date Julio 2015
 */
public class GestorTareas {
    
    // Lisa de tareas que se gestiona
    private ArrayList <Tarea> listaTareas;
    //Lista de los listener interesados en los eventos
    private ArrayList listeners;
    // Intérprete para generar las tareas desde los datos del usuario
    private CompiladorWorkFlow compilador; 
    // índice de la tarea actual
    private int currentTarea;
    // Flag para ver si el control de WorkFlow está activo
    private boolean workflowActivo;
    
    /**
     * Constructor
     */
    public GestorTareas () {
        listaTareas = new ArrayList <Tarea> ();
        listeners = new ArrayList ();
        compilador = new CompiladorWorkFlow ();
        currentTarea = 0;        
        workflowActivo = false;
    }
    
    /**
     * Devuelve el primer número de tarea disponible
     * 
     * @return Número siguiente en tarea
     */
    public int getNextNumero () {
        return listaTareas.size();
    }
    
    /**
     * Añade un listener a la lista de interesados
     * 
     * @param listener Listener interesado en los eventos 
     */
    public void addWorkFlowEventListener (WorkFlowEventListener listener) {
        listeners.add(listener);
    }
    
    /**
     * Valida de la descripción de la tarea realizada por el usuario
     * es adecuada o contiene errores
     * 
     * @param descripcion Descripción de la tarea
     * @return True si la tarea es válida
     */
    public boolean validaTarea (String descripcion) {
        return compilador.valida(descripcion);
    }
    
    /**
     * Reinicia el workflow del sistema
     */
    public void reiniciaWorkFlow () {
        currentTarea = 0;
        for(Tarea tarea : listaTareas) {
            tarea.reinicia();
        }
    }
    
    /**
     * Reinicia el WorkFlow del sistema desde un número de tarea dado
     * 
     * @param inicio Número de tarea a partir del cual se reinicia 
     */
    public void reiniciaWorkFlow (int inicio) {
        currentTarea = inicio;
        for (int i = inicio; i < listaTareas.size() ; i++) {
            listaTareas.get(i).reinicia();
        }
    }
    
    /**
     * Borra todas las tareas e indicadores el WorkFlow
     */
    public void borraWorkFlow () {
        int size = listaTareas.size();
        for (int i =  0; i < size ; i++) {
            listaTareas.remove(0);
        }       
        currentTarea = 0;
    }
    
    /**
     * Inicia la ejecuación de las tareas desde un número dado
     * 
     * @param numeroTarea Número de tarea por la que se empieza
     * @return True si la ejecuación es posible
     */
    public boolean ejecutaDesdeTarea (int numeroTarea) {
        if (numeroTarea >= listaTareas.size()) {
            return false;
        } else {
            reiniciaWorkFlow (numeroTarea);          
            return true;
        }       
    }
    
    /**
     * Actualiza el WorkFlow ante la llegada de un evento
     * 
     * @param mensaje Mensaje del evento
     */
    public void actualizaTareas (String mensaje) {
       boolean endFlow = false;
       if(listaTareas.get(currentTarea).avanzaPaso(mensaje)) {
           if (listaTareas.get(currentTarea).IsFinalizada()) {
                currentTarea++;
            }
            if (currentTarea >= listaTareas.size()) {                
                endFlow = true;
            } else {                
                listaTareas.get(currentTarea).setEstado(Tarea.ESTADO_EJECUTANDO);
            }
            
            WorkFlowEventObject workEvObj = new WorkFlowEventObject (this, listaTareas.get(currentTarea-1), false, endFlow);
            ListIterator li = listeners.listIterator();

            // Recorremos la lista para ejecutar el metodo NombreCambiado de cada manejador almacenado
            while (li.hasNext()) {            
                // Convertimos (CAST) de nuestro objeto
                WorkFlowEventListener listener = (WorkFlowEventListener)li.next(); 
                // Ejecutamos el metodo manejador del evento con los parametros necesarios
                listener.actualizaTareas(workEvObj);
            }            
       }
    }
    
    /**
     * Activa el WorkFlow 
     */
    public void ActivaWorkFlow () {
        workflowActivo = true;
        listaTareas.get(currentTarea).setEstado(Tarea.ESTADO_EJECUTANDO);        
    }
    
    /**
     * Desactiva el workflow
     */
    public void DesactivaWorkFlow () {
        workflowActivo = false;
    }
    
    /**
     * Indica el workflow está activado
     * 
     * @return True sie l WorkFlow está activado 
     */
    public boolean IsWorkFlowActivated () {
        return workflowActivo;
    }   
    
    /**
     * Convierte una descripción textual de una tarea en un Objeto Tarea y lanza
     * el evento correspondiente
     * 
     * @param descripcion Descripción textual de la tarea
     * @param nombre Nombre que le queremos dar a la tarea 
     */
    public void procesaTarea (String descripcion, String nombre) {
     
        Tarea nuevaTarea = new Tarea (nombre, listaTareas.size(), compilador.procesa(descripcion));
        listaTareas.add(nuevaTarea);
        WorkFlowEventObject workEvObj = new WorkFlowEventObject (this, nuevaTarea, true, false);
        ListIterator li = listeners.listIterator();
        
        // Recorremos la lista para ejecutar el metodo NombreCambiado de cada manejador almacenado
        while (li.hasNext()) {            
            // Convertimos (CAST) de nuestro objeto
            WorkFlowEventListener listener = (WorkFlowEventListener)li.next(); 
            // Ejecutamos el metodo manejador del evento con los parametros necesarios
            listener.actualizaTareas(workEvObj);
        }
    }
}

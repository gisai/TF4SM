
package main;

import logicaComunicaciones.Balda;
import logicaComunicaciones.GestionadorComunicacion;
import logicaComunicaciones.Guante;
import logicaComunicaciones.ProtocolEventListener;
import logicaComunicaciones.ProtocolEventObject;
import logicaWorkFlow.GestorTareas;
import logicaWorkFlow.WorkFlowEventListener;
import logicaWorkFlow.WorkFlowEventObject;

/**
 * Clase que representa al sistema ciber-físico inteligente
 * y autoconfigurable de forma virtual
 * 
 * @author Borja Bordel
 * @date Junio 2015
 */
public class TF4SM implements ProtocolEventListener, WorkFlowEventListener {
    
    // Instancia del singlenton
    private static final TF4SM sistema = new TF4SM ();
    
    // Elementos del TF4SM
    private GestionadorComunicacion gestionador;
    private Guante guante;
    private Balda balda;
    private GestorTareas gestionadorTareas;    
    
    /**
     * Constructor privado para implementar
     * el modelo singlenton
     */
    private TF4SM () {
        gestionador = new GestionadorComunicacion ();
        gestionadorTareas = new GestorTareas ();
        gestionador.addProtocolEventListener(this);
        gestionadorTareas.addWorkFlowEventListener(this);
        balda = new Balda ();
        guante = new Guante ();
    }
    
    /**
     * Comprueba si el WorkFlow está activado
     * 
     * @return True si está activado
     */
    public boolean IsWorkFlowActivated () {
        return gestionadorTareas.IsWorkFlowActivated();
    }
    
    /**
     * Activa el workflow y solicita que lo muestre la bandeja
     */
    public void ActivaWorkFlow () {
        gestionadorTareas.ActivaWorkFlow();
        gestionador.comunicaWorkFlowIniciado();
    }
    
    /**
     * Desactiva el workflow y solicita que lo muestre la bandeja
     */
    public void DesactivaWorkFlow () {
        gestionadorTareas.DesactivaWorkFlow();
        gestionador.comunicaWorkFlowFinalizado();
    }
    
    /**
     * Obtiene el primer número de tarea disponible
     * 
     * @return Int con el número de tarea 
     */
    public String getNumeroTareaSiguiente () {
        return String.valueOf(gestionadorTareas.getNextNumero());
    }
    
    /**
     * Procesa una tarea desripta en formato texto y la convierte en un 
     * objeto software
     * 
     * @param descripcion Descripción textual de la tarea
     * @param nombre Nombre de la tarea
     */
    public void procesaTarea (String descripcion, String nombre) {
        gestionadorTareas.procesaTarea(descripcion, nombre);
    }
    
    /**
     * Valida que la descripción textual de la tarea puede generar un 
     * objeto Software tarea
     * 
     * @param descripcion Descripción textual de la tarea
     * @return True si puede generar una tarea
     */
    public boolean validarTarea (String descripcion) {
        return gestionadorTareas.validaTarea(descripcion);
    }
    
    /**
     * Comprueba si hay información pendiente de transmitir  a la balda 
     * 
     * @return True si hay información pendiente
     */
    public boolean pendienteTransmisionBalda () {
        return gestionador.pendienteTransmisionBalda();
    }
    
    /**
     * Cambia el estado de la cola de información hacia la balda
     * 
     * @param pendiente Nuevo estado 
     */
    public void setPendienteBalda (boolean pendiente) {
        gestionador.setPendienteBalda(pendiente);
    }
    
    /**
     * Método que implementa la interfaz ProtocolEventListener 
     * Se utiliza para procesar el evento 
     * 
     * @param args evento
     */
    @Override
    public void cambiaEstado (ProtocolEventObject args) { 
        if (args.getValidez()) {
            if (args.getZona() == 'G' && args.getNumObjeto() != -1) {
                if (args.getNumObjeto() == 0) {
                    BaseDatos.ingresaTraza(guante.retiraObjeto(), "Objeto soltado por el guante");
                } else {
                    guante.ponObjeto(args.getNumObjeto());
                    BaseDatos.ingresaTraza(args.getNumObjeto(), "Objeto cogido por el guante");
                }
            } else {
                if (args.getNumObjeto() != -1) {
                    if (args.getNumObjeto() == 0) {
                        BaseDatos.ingresaTraza(balda.retiraObjeto(args.getZona()), "Objeto retirado de la zona "+args.getZona());
                    } else {                        
                        balda.ponObjeto(args.getZona(),args.getNumObjeto());
                        BaseDatos.ingresaTraza(args.getNumObjeto(), "Objeto puesto en la zona "+args.getZona());
                    }
                }
            }
            if (gestionadorTareas.IsWorkFlowActivated()){
                gestionadorTareas.actualizaTareas (args.getMensaje());
            }            
        }       
    }
    
    /**
     * Porcesa un evento de tipo WorkFlow
     * 
     * @param event  Evento que disparó el procesado
     */
    public void actualizaTareas (WorkFlowEventObject event) {
        if (!event.IsNueva()) {
            if(!event.getTarea().IsExitosa()) {
                gestionador.comunicaWorkFlowFallido();
            } else {
                if (event.IsFisnished()) {
                    gestionador.comunicaWorkFlowCompleto();                    
                }
            }
        }
    }
    
    /**
     * Borra todos los indicadores y tareas del WorkFlow
     */
    public void borraWorkFlow () {
        gestionadorTareas.borraWorkFlow();
    }
    
    /**
     * Reinicia todo el WorkFlow
     */
    public void reiniciaWorkFlow () {
        gestionadorTareas.reiniciaWorkFlow();
    }
    
    /**
     * Reinicia el WorkFlow desde la tarea indicada
     * 
     * @param inicio Número de tarea desde la que se reinicia 
     */
    public void reiniciaWorkFlow (int inicio) {
        gestionadorTareas.reiniciaWorkFlow(inicio);
    }
    
    /**
     * Ejecuta el workFlow desde una tarea dada
     * 
     * @param numeroTarea Número de tarea desde el que se ejecuta
     * @return True si se ha podido iniciar la ejecuación pedida
     */
    public boolean ejecutaDesdeTarea (int numeroTarea) {
        return gestionadorTareas.ejecutaDesdeTarea(numeroTarea);
    }
    
    /**
     * Método Singlenton.
     * Devuelve la instancia del sistema
     * 
     * @return Referencia de la instancia sistema
     */
    public static TF4SM getSistema () {
        return sistema;
    }
    
    /**
     * Método main que inicia la ejecución
     * 
     * @param args 
     */
    public static void main (String [] args) {        
        gui.InicioGUI.main(null);
        while (true) {
            //System.out.println();
        }
    }
    
    /**
     * Cierra la comunicación con los dispositivos cibernéticos
     */
    public void cierraComunicacion () {
        gestionador.cierraComunicacion();
    }
    
    /**
     * Inicia la comunicción con la balda
     */
    public void iniciaComunicacionBalda () {
        gestionador.iniciaComunicacionBalda();
    }
    
    /**
     * Inicia la comunicación con el guante
     */
    public void iniciaComunicacionGuante () { 
        gestionador.iniciaComunicacionGuante();
    }
    
    /**
     * Método para actualizar los puertos del sistema operativo
     * en el que están conectados los dispositivos
     * 
     * @param puertoBalda Nuevo puerto donde está conectada la balda
     * @param puertoGuante Nuevo puerto donde está conectado el guante
     */
    public void cambiaPuertos (String puertoBalda, String puertoGuante) {
        this.gestionador.cambiaPuertos(puertoBalda, puertoGuante);
    }
    
    /**
     * Método para añadir un "escuchador" a la lista
     * 
     * @param listener 
     */
    public void addProtocolEventListener (ProtocolEventListener listener) {
        gestionador.addProtocolEventListener(listener);
    }    
    
    /**
     * Añade un nuevo listener a la lista de los que esperan escuhar 
     * eventos WorkFlow
     * 
     * @param listener Listener nuevo que añadimos 
     */
    public void addWorkFlowEventListener (WorkFlowEventListener listener) {
        gestionadorTareas.addWorkFlowEventListener(listener);
    }
}

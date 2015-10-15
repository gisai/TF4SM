
package logicaWorkFlow;

import java.util.EventObject;

/**
 * Clase que codifica un evento de WorkFlow
 * 
 * @author Borja Bordel Sánchez
 * @date Julio 2015
 */
public class WorkFlowEventObject extends EventObject {
    
    private Tarea tarea;
    private boolean nueva;
    private boolean endFlow;
    
    /**
     * Constructor
     * 
     * @param source Origen del evento
     * @param tarea Tarea a la que se refiere el evento, ya actualizada
     * @param nueva Indica si la tarea es nueva o no
     * @param endFlow Si la modificación supone el fin del WorkFlow
     */
    public WorkFlowEventObject (Object source, Tarea tarea, boolean nueva, boolean endFlow)
    {
        super (source); 
        this.tarea = tarea;
        this.nueva = nueva;
        this.endFlow = endFlow;
    }
    
    /**
     * Método para conocer si e evento supone fin de WorkFlow
     * 
     * @return True si el el evento supone fin de WorkFlow
     */
    public boolean IsFisnished () {
        return endFlow;
    }
    
    /**
     * Recupera la tarea que geenró el evento
     * 
     * @return Tarea que generó le evento 
     */
    public Tarea getTarea () {  
        return tarea;
    }
    
    /**
     * Método para conocer si la tarea
     * desencadenante es nueva
     * 
     * @return True si la tarea es nueva
     */
    public boolean IsNueva () {
        return nueva;
    }
    
}

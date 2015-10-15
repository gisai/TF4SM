package logicaWorkFlow;

import java.util.EventListener;

/**
 * Interfaz que deben implementar todos 
 * los listener de eventos de tipo WorkFlow
 * 
 * @author Borja Bordel
 * @date Julio 2015
 */
public interface WorkFlowEventListener extends EventListener  {
    
    /**
     * Método para tratar el evento Workflow
     * 
     * @param event Evento qque ha desencadenado el aviso
     */
    void actualizaTareas (WorkFlowEventObject event);
}

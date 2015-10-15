
package logicaComunicaciones;

import java.util.EventListener;

/**
 * Interfaz que deben implementar todos los
 * "escuhadores" de eventos relacionados con los
 * puertos COM
 * 
 * @author Borja Bordel
 * @date Junio 2015
 */

public interface ProtocolEventListener extends EventListener {  
    
    /**
     * Método para el procesado del evento
     * 
     * @param args Evento que desencadena el aviso
     */
    void cambiaEstado (ProtocolEventObject args);    
}

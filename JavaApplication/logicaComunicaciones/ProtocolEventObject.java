
package logicaComunicaciones;

import java.util.EventObject;

/**
 * Clase que describe los objetos "evento"
 * para la lectura de puertos COM
 * 
 * @author Borja Bordel
 * @date Junio 2015
 */
public class ProtocolEventObject extends EventObject {
    
    // Zona de la balda o guante
    private char zona;
    // Mensaje que had esencadenado el evento
    private String mensaje;
    // Si el evento conlleva un mensaje valido
    private boolean valido;
    // Objeto 
    private int numObjeto;
    
    /**
     * Constructor
     * 
     * @param source Origen del evento
     * @param zona Zona del sistema donde se ha producido el evento
     * @param numObjeto Número de objeto que se ha movido
     * @param mensaje Mensaje que ha desencadenado el evento
     * @param valido EL mensaje recibido es valido
     */
    public ProtocolEventObject (Object source, char zona, int numObjeto, String mensaje, boolean valido)
    {
        super (source);   
        this.zona = zona;
        this.mensaje = mensaje;
        this.valido = valido;
        this.numObjeto = numObjeto;
    }
 
    /**
     * Método para devolver el mensaje que se ha recibido 
     * como disparo del evento
     * 
     * @return el mensaje del evento
     */
    public String getMensaje ()
    {
        return mensaje;
    } 
    
    /**
     * Método para conocer la zona donde se ha producido el
     * evento
     * 
     * @return  zona del sistema donde se ha producido el evento
     */
    public char getZona () {
        return zona;
    }
    
    /**
     * Método para conocer la validez del mensaje
     * que ha desencadenado el evento
     * 
     * @return booleano que indica la validez del mensaje recibido
     */
    public boolean getValidez () {
        return valido;
    }
    
    /**
     * Método para conocer el objeto
     * al que se refiere el evento
     * 
     * @return  ID del objeto al que se refiere el evento
     */
    public int getNumObjeto () {
        return numObjeto;
    }
}

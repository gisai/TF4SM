
package logicaComunicaciones;

/**
 * Clase que representa el guante cibernético
 * 
 * @author Borja Bordel
 * @date Junio 2015
 */
public class Guante {
    
    /**
     * ID del objeto que sostiene el guante
     */
    private int objetoActual;
    
    /**
     * Constructor
     */
    public Guante () {
        objetoActual = 0;
    }
    
    /**
     * Método para actualizar al colocación de un 
     * objeto por el guante
     * 
     * @param objeto ID del obeto cogido 
     */
    public void ponObjeto (int objeto) {
        objetoActual = objeto;
    }
    
    /**
     * Método para retirar el objeto del 
     * guante
     * 
     * @return ID del objeto retirado
     */
    public int retiraObjeto () {
        int oldObjeto = objetoActual;
        objetoActual = 0;
        return oldObjeto;
    }
    
}

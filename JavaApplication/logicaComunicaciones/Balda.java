package logicaComunicaciones;

import java.util.HashMap;

/**
 * Clase que representa la balda del sistema
 * 
 * @author Borja Bordel
 * @date Junio 2015
 */
public class Balda {
    
    /**
     * Map que mapea el estado actual de la balda
     */
    private HashMap <Character, Integer> balda;
    
    /**
     * Constructor
     */
    public Balda () {
        balda = new HashMap <Character, Integer> ();
        balda.put('A', 0);
        balda.put('B', 0);
        balda.put('C', 0);
        balda.put('D', 0);
    }
    
    /**
     * Método para añadir un objeto nuevo a una
     * zona de la balda
     * 
     * @param zona zona de la balda en la que se coloca el objeto
     * @param objeto Idntificador del objeto que se coloca
     */
    public void ponObjeto (char zona, int objeto) {
        balda.put(zona, objeto);        
    }
    
    /**
     * Método para retirar el objeto colocado en una
     * zona de la balda
     * 
     * @param zona zona de la que se retira el objeto
     * @return Identificador del objeto que se retira
     */
    public int retiraObjeto (char zona) {
        int oldObjeto = balda.get(zona);
        balda.put(zona, 0);
        return oldObjeto;
    }    
}

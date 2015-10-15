package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Clase que aporta las funcionalidades de base
 * de datos para los cinco objetos considerados en 
 * el primer prototipo de sistema
 * 
 * @author Borja Bordel
 * @date Mayo 2015
 */
public class BaseDatos {
       
    private static final ArrayList [] baseDatos = {new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                                                   new ArrayList<>(), new ArrayList<>()};
    private static final HashMap <Long, Integer> baseObjetos = new HashMap <>();
    private static final HashMap <Integer, Long> baseObjetosInversa = new HashMap <>();
    
    /**
     * Iniciliza la base de datos de objetos, cargando los productos
     * descritos en el fichero 
     * 
     * @param listado Fichero con el listado de productos
     * @throws java.lang.Exception En caso de que haya algún error en la 
     * apertura o carga del listado
     */
    public static void inicializa (File listado) throws Exception { 
        
        FileReader fr = null;
        BufferedReader br;
        
        try {         
            fr = new FileReader (listado);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            int objeto = 1;
            //creacion de objetos
            while((linea = br.readLine())!= null) {
               baseObjetos.put(Long.parseLong(linea.trim(),16), objeto);
               baseObjetosInversa.put(objeto, Long.parseLong(linea.trim(),16));
               objeto++;
               //System.out.println(linea);
            }
        }
        catch(IOException | NumberFormatException e){                      
            throw new Exception ("Error con el fichero de productos: " + e.getMessage()); 
        }finally{
            if(fr != null){  fr.close(); }          
        }
    }
    
    /**
     * Ingresa una traza en la base de datos
     * 
     * @param evento String que queremos incluir
     * @param numObjeto numero de objeto al que se refiere
     */
    public static void ingresaTraza (int numObjeto, String evento) {
        baseDatos[numObjeto-1].add(new Date ().toString() + " " + evento);
    }
    
    /**
     * Devuelve una lista con las trazas de un objeto
     * @param numObjeto Numero de objeto
     * @return Lista con las trazas
     */
    public static ArrayList getTrazas (int numObjeto) {
        return baseDatos[numObjeto-1];
    }
    
    /**
     * Devuelve el nombre del objeto dada su referencia
     * @param referencia
     * @return Nombre del objeto o -1 si no existe tal objeto
     */
    public static int getNombreObjeto (long referencia) {
        if (baseObjetos.get(referencia) == null) {
            return -1;
        } else {
            return baseObjetos.get(referencia);
        }
    }
    
    /**
     * Devuelve la referencia de un objeto dado su nombre
     * 
     * @param nombre Nombre del objeto
     * @return Referencia y -1 si no existe ese nombre
     */
    public static long getReferenciaObjeto (int nombre) {
        if (baseObjetosInversa.get(nombre) == null) {
            return -1;
        } else {
            return baseObjetosInversa.get(nombre);
        }
    }
}

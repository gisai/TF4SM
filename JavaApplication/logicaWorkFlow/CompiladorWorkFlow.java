package logicaWorkFlow;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import main.BaseDatos;

/**
 * Clase que permite interpretar las descripciones
 * textuales de las tareas y convertirlas en Objetos software
 * 
 * @author Borja Bordel
 * @date Julio 2015
 */
public class CompiladorWorkFlow {    
    
    // Sentencias permitidas en la descripción
    private Pattern mensaje1;
    private Pattern mensaje2;
    private Pattern mensaje3;
    private Pattern mensaje4;
    
    /**
     * Constructor
     */
    public CompiladorWorkFlow () {
        mensaje1= Pattern.compile("^BALDA [A,B,C,D] [1-5]$");
        mensaje2 = Pattern.compile("^BALDA [A,B,C,D]$");
        mensaje3 = Pattern.compile("^GUANTE [1-5]$");
        mensaje4 = Pattern.compile("^GUANTE$");
    }
    
    /**
     * Valida si la descripción textual puede transformarse en 
     * Objeto software
     * 
     * @param descripcion Descripción textual
     * @return True si la conversión es posible
     */
    public boolean valida (String descripcion) {
        String [] args = descripcion.split("\n");
        boolean validado = true;       
        
        for (int i = 0; i < args.length ; i++) {
            Matcher mat1 = mensaje1.matcher(args[i]);
            Matcher mat2 = mensaje2.matcher(args[i]);
            Matcher mat3 = mensaje3.matcher(args[i]);
            Matcher mat4 = mensaje4.matcher(args[i]);
            
            validado =  validado && (mat1.matches() || mat2.matches() || 
                         mat3.matches() || mat4.matches());
        }
        
        return validado;
    }
    
    /**
     * Procesa una descripción textual y la convierte en una lista de eventos
     * que definen la tarea
     * 
     * @param descripcion Descripción textual
     * @return ArrayList con la lista de eventos
     */
    public ArrayList procesa (String descripcion) {        
        String [] args = descripcion.split("\n");
        ArrayList <String> lista = new ArrayList <String> ();
        
        for (int i = 0; i < args.length ; i++) {
            Matcher mat1 = mensaje1.matcher(args[i]);
            Matcher mat2 = mensaje2.matcher(args[i]);
            Matcher mat4 = mensaje4.matcher(args[i]);
            
            if (mat2.matches() || mat4.matches()) {
                lista.add(args[i]);
            } else {
                if (mat1.matches()) {
                    String [] trozos = args[i].split(" ");
                    String referencia = Long.toHexString(BaseDatos.getReferenciaObjeto(Integer.parseInt(trozos[2])));
                    String linea = (trozos[0]+" "+trozos[1]+" "+referencia).toUpperCase();
                    lista.add(linea);
                } else {
                    String [] trozos = args[i].split(" ");
                    String referencia = Long.toHexString(BaseDatos.getReferenciaObjeto(Integer.parseInt(trozos[1])));
                    String linea = (trozos[0]+" "+referencia).toUpperCase();
                    lista.add(linea);
                }
            }            
        }        
        return lista;
    }
}

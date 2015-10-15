
package logicaComunicaciones;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que representa el punto de gestión de las comunicaciones
 * 
 * @author Borja Bordel
 * @date Junio 2015
 */
public class GestionadorComunicacion {
    
    /**
     * Puertos donde se sitúan los componentes
     * (valores por defecto)
     */
    private String puertoBalda = "COM7";
    private String puertoGuante = "COM8";
    
    /**
     * Comandos que permiten la ejecuación de las conexiones Bluetooth
     */
    private String comandoGuante = ".\\src\\daemon\\plink -serial "+ puertoGuante + " -sercfg 19200,8,n,1,n";
    private String comandoBalda = ".\\src\\daemon\\plink -serial " + puertoBalda + " -sercfg 19200,8,n,1,n";
    
    /**
     * Procesos de comunicación
     */
    private Process procesoBalda;
    private Process procesoGuante;
    
    /**
     * Elementos para establecer comunicación bidireccional
     */
    private volatile boolean pendienteBalda;    
    private String [] mensajeToBalda;    
    private volatile int indiceBalda;
    
    /**
     * Instancia del protocolo
     */
    private final SmartProtocol protocolo;
    
    /**
     * Constructor
     */
    public GestionadorComunicacion () {
        protocolo = new SmartProtocol ();
        pendienteBalda = false;
        mensajeToBalda = new String [4];
        indiceBalda = 0;
    }
    
    /**
     * Método para actualizar los puertos del sistema operativo
     * en el que están conectados los dispositivos
     * 
     * @param puertoBalda Nuevo puerto donde está conectada la balda
     * @param puertoGuante Nuevo puerto donde está conectado el guante
     */
    public void cambiaPuertos (String puertoBalda, String puertoGuante) {
        if (puertoBalda != null) {
            this.puertoBalda = puertoBalda;
            this.comandoBalda = ".\\src\\daemon\\plink -serial " + puertoBalda + " -sercfg 19200,8,n,1,n";
        }
        if (puertoGuante != null) {
            this.puertoGuante = puertoGuante;
            this.comandoGuante = ".\\src\\daemon\\plink -serial "+ puertoGuante + " -sercfg 19200,8,n,1,n";
        }
        //System.out.println(comandoGuante);
        //System.out.println(comandoBalda);
    }
    
    /**
     * Método para comunicar a la bandeja el inicio 
     * del workflow
     */
    public void comunicaWorkFlowIniciado () {        
        mensajeToBalda[indiceBalda++] = protocolo.getMensaje(1);
        pendienteBalda = true;
    }
    
    /**
     * Método para comunicar el fin de un WorkFlow
     */
    public void comunicaWorkFlowFinalizado () {    
        mensajeToBalda[indiceBalda++] = protocolo.getMensaje(4);
        pendienteBalda = true;        
    }
    
    /**
     * Método para comunicar la finalización
     * correcta de un WorkFlow
     */
    public void comunicaWorkFlowCompleto () {        
        mensajeToBalda[indiceBalda++] = protocolo.getMensaje(2);
        pendienteBalda = true;
    }
    
    /**
     * Método para comunicar el follo en el WorkFlow
     */
    public void comunicaWorkFlowFallido () {        
        mensajeToBalda[indiceBalda++] = protocolo.getMensaje(3);
        pendienteBalda = true;
    }
    
    /**
     * Método que permite conocer si hay datos
     * pendientes de transmisicón hacia la bandeja
     * 
     * @return true si hay algún dato pendiente para transmitir
     * hacia la balda
     */
    public boolean pendienteTransmisionBalda () {
        return pendienteBalda;
    }
    
    /**
     * Método para modificar el estado de "pendiente"
     * en una balda
     * 
     * @param pendiente nuevo estado de pendiente
     */
    public void setPendienteBalda (boolean pendiente) {
        pendienteBalda = pendiente;
    }
        
    /**
     * Método para añadir un listener a la lista
     * 
     * @param listener  "escuchador que queremos añadir"
     */
    public void addProtocolEventListener (ProtocolEventListener listener) {
        protocolo.addProtocolEventListener(listener);
    }
    
    /**
     * Método que abre una comunicación con el guante
     */
    public void iniciaComunicacionGuante () {
        
        try {           
            Runtime r = Runtime.getRuntime ();
            procesoGuante = r.exec (comandoGuante);            
            InputStream std = procesoGuante.getInputStream ();
            OutputStream out = procesoGuante.getOutputStream ();
            InputStream err = procesoGuante.getErrorStream ();     
            
            Thread T = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            if (std.available () > 0) {
                                String linea = "";
                                int value = std.read ();                                
                                linea = linea+ (char) value;                                
                                while ((char)value != '\n') {
                                    if (std.available () > 0) {
                                        value = std.read ();
                                        linea = linea+ (char) value;
                                    }
                                } 
                                linea = linea.substring(0, linea.length()-2);
                                //System.out.print(linea);
                                protocolo.procesaEvento(linea.trim());
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(GestionadorComunicacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } );
            T.start();
        } catch (Exception e) {
            Logger.getLogger(GestionadorComunicacion.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    /**
     * Método que abre una comunicación con la balda
     */
    public void iniciaComunicacionBalda () {
        try {
            Runtime r = Runtime.getRuntime ();
            procesoBalda = r.exec (comandoBalda);
            InputStream std = procesoBalda.getInputStream ();
            OutputStream out = procesoBalda.getOutputStream ();
            InputStream err = procesoBalda.getErrorStream ();
            
            Thread T = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true) {
                        try {
                            if (std.available () > 0) { 
                                String linea = "";
                                int value = std.read ();
                                linea = linea+ (char) value;                                
                                while ((char)value != '\n') {
                                    if (std.available () > 0) {
                                        value = std.read ();
                                        linea = linea+ (char) value;
                                    }
                                }
                                //System.out.print(linea);
                                linea = linea.substring(0, linea.length()-2);                                                                  
                                protocolo.procesaEvento(linea);
                            }
                            // Si hay algo pendiente se transmite
                            if (pendienteBalda) {
                                for (int i = 0 ; i < indiceBalda ; i++) {
                                    out.write(mensajeToBalda[i].getBytes());
                                    out.flush ();
                                }
                                indiceBalda = 0;
                                pendienteBalda = false;
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(GestionadorComunicacion.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            } );
            T.start();
        } catch (Exception e) {
            Logger.getLogger(GestionadorComunicacion.class.getName()).log(Level.SEVERE, null, e);
        }
    }
     
    /**
     * Método que cierra las comunicaciones
     */
    public void cierraComunicacion () {
        if (procesoGuante!= null) {
            procesoGuante.destroy();
        }
        if (procesoBalda != null) {
            procesoBalda.destroy();
        }
        //System.out.println("Puertos cerrados");
    }    
}


package gui;

import java.util.ArrayList;
import javax.swing.JLabel;
import main.BaseDatos;

/**
 * Clase para crear el lienzo en el que mostrar 
 * las trazas
 * 
 * @author Borja Bordel
 * @date Mayo 2015
 */
public class LienzoTrazas extends javax.swing.JFrame {
    
    /**
     * Constructor
     * 
     * Creates new form LienzoTrazas
     * @param numObjeto Número de objeto del que queremos las trazas
     */
    public LienzoTrazas(int numObjeto) {
        initComponents();
        trazas[0] = traza1;
        trazas [1] =traza2;
	trazas[2] = traza3;
	trazas [3] =traza4;
	trazas[4] = traza5;
	trazas [5] =traza6;
	trazas[6] = traza7;
	trazas [7] =traza8;
	trazas[8] = traza9;
	trazas [9] =traza10;
                
        titulo.setText("Trazas del objeto "+ numObjeto);
        ArrayList  lista = BaseDatos.getTrazas(numObjeto);
        if (lista.isEmpty()) {
                // no se pinta nada
        } else {
            int limite = lista.size()-10 >= 0 ? lista.size()-10 : 0;
            int j = 0;
            for (int i=lista.size()-1; i >= limite; i--) {
                trazas[j].setText(lista.get(i).toString());
                j++;
            }
        }        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        traza1 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        traza2 = new javax.swing.JLabel();
        traza4 = new javax.swing.JLabel();
        traza3 = new javax.swing.JLabel();
        traza8 = new javax.swing.JLabel();
        traza7 = new javax.swing.JLabel();
        traza6 = new javax.swing.JLabel();
        traza5 = new javax.swing.JLabel();
        traza10 = new javax.swing.JLabel();
        traza9 = new javax.swing.JLabel();

        traza1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        traza1.setText("----");

        titulo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        traza2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        traza2.setText("----");

        traza4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        traza4.setText("----");

        traza3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        traza3.setText("----");

        traza8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        traza8.setText("----");

        traza7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        traza7.setText("----");

        traza6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        traza6.setText("----");

        traza5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        traza5.setText("----");

        traza10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        traza10.setText("----");

        traza9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        traza9.setText("----");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(traza1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(traza2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(traza3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(traza4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(traza5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(traza6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(traza7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(traza8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(traza9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(traza10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(traza1)
                .addGap(18, 18, 18)
                .addComponent(traza2)
                .addGap(18, 18, 18)
                .addComponent(traza3)
                .addGap(18, 18, 18)
                .addComponent(traza4)
                .addGap(18, 18, 18)
                .addComponent(traza5)
                .addGap(18, 18, 18)
                .addComponent(traza6)
                .addGap(18, 18, 18)
                .addComponent(traza7)
                .addGap(18, 18, 18)
                .addComponent(traza8)
                .addGap(18, 18, 18)
                .addComponent(traza9)
                .addGap(18, 18, 18)
                .addComponent(traza10)
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>                        

    /**
     * Método main
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LienzoTrazas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LienzoTrazas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LienzoTrazas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LienzoTrazas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LienzoTrazas(Integer.parseInt(args[0])).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel traza1;
    private javax.swing.JLabel traza10;
    private javax.swing.JLabel traza2;
    private javax.swing.JLabel traza3;
    private javax.swing.JLabel traza4;
    private javax.swing.JLabel traza5;
    private javax.swing.JLabel traza6;
    private javax.swing.JLabel traza7;
    private javax.swing.JLabel traza8;
    private javax.swing.JLabel traza9;
    // End of variables declaration                   
    private JLabel [] trazas = new JLabel [10];
}
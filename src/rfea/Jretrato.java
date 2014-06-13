/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rfea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author sotoxx
 */
public class Jretrato extends javax.swing.JFrame {
    
     private jface_panel lienzo;
    private JButton btn[][];
    ActionListener actionListener ;

    /**
     * Creates new form panel
     */
    
    public Jretrato() {
        initComponents();
        //Se inicia la interfaz
        setTitle( "jFace - by Mouse" );
        setLocationRelativeTo(null);
        //se crea instancia a panel y se añade a la interfaz
        lienzo =new jface_panel( this.jPanel1.getSize() );
        this.jPanel1.add(lienzo);
        //alineacion para jpanel de controles
        jPanel2.setLayout(new java.awt.GridLayout(8, 3, 5, 5));//alineacion
        //acciones de los controles "jbutton" para cambiar partes del rostro
        actionListener = new ActionListener() {
          public void actionPerformed(ActionEvent e) {
                String comando = e.getActionCommand();
                lienzo.rostro.cambiar_parte_del_rostro(comando);
                lienzo.repaint();
          }
        };
        //matriz de controles de JButton
        btn = new JButton[8][3];
        botones();
    }
        //Crea la matriz de controlos y añade propiedades
    private void botones()
    {
        int index=jComboBox1.getSelectedIndex();
        String action="";
       // for ( int i=0; i < 4; i++ )//filas
       // {
        jPanel2.removeAll();
            for ( int j=0; j < 3; j++ )//columnas
            {
                btn[index][j] = new JButton();
                btn[index][j].setSize( new Dimension(70,80) );
                btn[index][j].setPreferredSize( new Dimension(70,80) );
                btn[index][j].setText("");
                btn[index][j].setBackground(Color.white);
                switch (index){//Nombres de los eventos
                    case 0: action = "ojos"; break;
                    case 1: action = "labios"; break;
                    case 2: action = "nariz"; break;
                    //case 3: action = "cabello"; break;
                    case 3: action = "cabeza"; break;
                   // case 5: action = "mandibula"; break;
                   // case 6: action = "cejas"; break;
                   // case 7: action = "bigote"; break;
                }
                btn[index][j].setToolTipText(action);
                btn[index][j].setActionCommand(action + "_" + (j+1) );//se crea comando
                btn[index][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/"+action+"/jface_"+(j+1)+"_t.png")));
                btn[index][j].addActionListener(actionListener);
                jScrollPane2.setVisible(false);
                jScrollPane2.setVisible(true);
                jPanel2.add(btn[index][j]);//se añade al JPanel contenedor
            }
        //}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ventana = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        Buscar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 230, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel2);

        jLabel1.setText("Parte:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ojos", "Boca", "Nariz" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        Buscar.setText("Buscar");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ventanaLayout = new javax.swing.GroupLayout(ventana);
        ventana.setLayout(ventanaLayout);
        ventanaLayout.setHorizontalGroup(
            ventanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ventanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ventanaLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(ventanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ventanaLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 73, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)))
                    .addComponent(Buscar))
                .addContainerGap())
        );
        ventanaLayout.setVerticalGroup(
            ventanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ventanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ventanaLayout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Buscar))
                    .addGroup(ventanaLayout.createSequentialGroup()
                        .addGroup(ventanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)))
                .addContainerGap())
        );

        jScrollPane1.setViewportView(ventana);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        botones();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscarActionPerformed

    /**
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
            java.util.logging.Logger.getLogger(Jretrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jretrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jretrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jretrato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jretrato().setVisible(true);
            }
        });
    }
    //hola Dios te Bemdiga

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Buscar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel ventana;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.leopard.imagen;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Demostracion de uso de <code>SelectorAreaImagen</code>
 * @author pedro
 */
public class FrameDemo extends JFrame {
    
    private static final long serialVersionUID = 8937119421588637012L;
    private SelectorAreaImagen selector;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                FrameDemo frame = new FrameDemo();
                frame.setVisible(true);
            }
        });
    }

    public FrameDemo() {
        super();
        initGUI();
    }
    
    private void initGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        selector = new SelectorAreaImagen();
        try {
            System.out.println(rutaDeURL("/bmw_bobber.jpg"));
            selector.setImagen(ImageIO.read(getClass().getClassLoader().getResource("bmw_bobber.jpg")));
        } catch (IOException ex) {
            Logger.getLogger(FrameDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        add(selector, BorderLayout.CENTER);
        selector.addSelectorListener(new SelectorAreaListener() {

            public void areaSeleccionada(BufferedImage imagen) {
                SelectorAreaImagen s = new SelectorAreaImagen(imagen);
                JDialog d = new JDialog(FrameDemo.this);
                d.add(s);
                d.pack();
                d.setVisible(true);
            }
        });
        setTitle("Demo " + selector.getClass().getName());
        pack();
        setResizable(false);
    }
    public String rutaDe (String recurso){
        String ruta = getClass().getResource(recurso).getPath();
        //System.out.println(ruta);
        try {
            ruta=URLDecoder.decode(ruta, "UTF-8"); //this will replace %20 with spaces
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FrameDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    return ruta;
    }
           public URL rutaDeURL(String recurso){
        URL ruta = getClass().getResource(recurso);
    return ruta;
    }
}

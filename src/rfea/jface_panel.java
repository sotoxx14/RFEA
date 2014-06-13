/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rfea;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author sotoxx
 */
public class jface_panel extends JPanel {

    private BufferedImage Imagen_en_memoria;
    private Graphics2D g2d;
    public jface_rostro rostro;    

    //constructor principal
    //Input: Dimension del contenedor padre
    public jface_panel(Dimension d) {
        this.setSize(d);
        this.setPreferredSize(d);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        rostro = new jface_rostro( this.getSize() );        
    }    

    @Override
    public void paintComponent(Graphics g) {        
        Graphics2D g2 = (Graphics2D)g;
        //imagen en memoria para dibujar en segundo plano
        Imagen_en_memoria = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        g2d = Imagen_en_memoria.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.white);
        g2d.fill(new Rectangle2D.Double(0,0,this.getWidth(), this.getHeight()));        
        rostro.dibujar(g2d);
        g2d.setFont( new Font("Arial",Font.BOLD, 26) );
        g2d.setColor(Color.black);
        g2d.drawString("jFace - Programado por Mouse ", 20, 30);
        g2d.drawString("Visitame: http://www.jc-mouse.net/ ", 20, 60);
        //dibuja toda la imagen
        g2.drawImage(Imagen_en_memoria, 0, 0, this);
        guardaRetrato();
    }
    public void guardaRetrato() {
        File file = new File("retrato.png");
        System.out.println("Guardando imagen: "+file.getAbsolutePath());
        try {
            
            ImageIO.write(Imagen_en_memoria, "png", file); // Salvar la imagen en el fichero
            
        } catch (IOException ex) {
            System.out.println("Error al guardar archivo: "+file.getAbsolutePath());
        }
    }

}

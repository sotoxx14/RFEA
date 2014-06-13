/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rfea;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author sotoxx
 */

public class jface_miembro_rostro {

   private BufferedImage[] imagen;
   private URL _url;   
   private int index=1;

    public jface_miembro_rostro( String miembro){
        load_images(miembro);        
    }

    //carga todas las imagenes en memoria
   private void load_images( String miembro){
        imagen = new BufferedImage[4];
        //se llena el buffer con la imagen
        for(int i=1; i<=3;i++){
            try {
                _url = new URL(getClass().getResource("/imagenes/"+ miembro +"/jface_" + i + ".png").toString());
                imagen[i] = ImageIO.read(_url);                
            } catch (IOException ex) {System.out.println(ex); }
        }
    }

   public void setImagen(int index){
    this.index = index;
   }

   public Image getImagen(){
        return imagen[this.index];
   }

}

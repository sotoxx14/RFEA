/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rfea;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author sotoxx
 */
public class jface_rostro {
    //Partes del rostro
  //  private jface_miembro_rostro cabello;
    private jface_miembro_rostro cabeza;
    private jface_miembro_rostro nariz;
    private jface_miembro_rostro ojos;
    private jface_miembro_rostro labios;
  //  private jface_miembro_rostro mandibula;
  //  private jface_miembro_rostro cejas;
  //  private jface_miembro_rostro bigote;

    public jface_rostro(Dimension contendor){
        //cabello     = new jface_miembro_rostro("cabello");
        cabeza      = new jface_miembro_rostro("cabeza");
        nariz       = new jface_miembro_rostro("nariz" );
        ojos        = new jface_miembro_rostro("ojos");
        labios      = new jface_miembro_rostro("labios" );
        //mandibula   = new jface_miembro_rostro("mandibula" );
        //cejas       = new jface_miembro_rostro("cejas" );
        //bigote      = new jface_miembro_rostro("bigote" );
    }

    //Cambia una parte del rostro
    //Entrada: Parte-del-rostro_imagen de tipo String
    public void cambiar_parte_del_rostro(String value){
        String[] val = value.split("_");//divide el comando en dos
        //valores, uno literal y el otro entero
        if( val[0].equals("nariz"))        
            nariz.setImagen( Integer.valueOf(val[1]));
        else if( val[0].equals("ojos"))
            ojos.setImagen( Integer.valueOf(val[1]));
        else if( val[0].equals("labios"))
            labios.setImagen( Integer.valueOf(val[1]));
       // else if( val[0].equals("cabello"))
        //    cabello.setImagen( Integer.valueOf(val[1]));
        else if( val[0].equals("cabeza"))
            cabeza.setImagen( Integer.valueOf(val[1]));
      /*  else if( val[0].equals("mandibula"))
            mandibula.setImagen( Integer.valueOf(val[1]));
        else if( val[0].equals("cejas"))
            cejas.setImagen( Integer.valueOf(val[1]));
        else if( val[0].equals("bigote"))
            bigote.setImagen( Integer.valueOf(val[1]));        */
    }

    public void dibujar(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawImage(cabeza.getImagen(),0,0, null);
       // g2.drawImage(mandibula.getImagen(), 0,0, null);
        g2.drawImage(nariz.getImagen(),0,0, null);
        g2.drawImage(ojos.getImagen(), 0,0, null);
        g2.drawImage(labios.getImagen(), 0,0, null);
      //  g2.drawImage(cabello.getImagen(), 0,0, null);
       // g2.drawImage(cejas.getImagen(), 0,0, null);
        //g2.drawImage(bigote.getImagen(), 0,0, null);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rfea;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sotoxx
 */
public class RFEA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Setea el las librerias nativas
        setearLibPath("/usr/local/lib/:/usr/local/share/OpenCV/java/");
        new Principal().setVisible(true);
        //new capturaImg().run();

         
    }
    
 static void setearLibPath(String path){
       /**   segmento de codigo Tomado de:http://blog.cedarsoft.com/2010/11/setting-java-library-path-programmatically/
        * este codigo setea las librerias nativas del SO
        * @author Desconocido -2/11/2010
        **/
            //inicio de codigo
         try {
            System.setProperty( "java.library.path", path );
            Field fieldSysPath = ClassLoader.class.getDeclaredField( "sys_paths" );
            fieldSysPath.setAccessible( true );
            try {
                fieldSysPath.set( null, null );
                
            } catch (    IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(RFEA.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(RFEA.class.getName()).log(Level.SEVERE, null, ex);
        }
 //fin de codigo
    }
    
}

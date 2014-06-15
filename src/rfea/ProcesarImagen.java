/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rfea;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.MatOfRect;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import static org.opencv.imgproc.Imgproc.equalizeHist;
//import recursos.*;


/**
 *
 * @author sotoxx
 */
class ProcesarImagen {
    
    //inicio configuracion de variables basicas
    private String imagenEntrada="",imagenSalida="rostro_detectado.png";
    private String rcDir="recursos";//directorio de recursos
    int contRostros=0,cont=0;
    static Mat image,imgRostro,imgBoca,tmp;
    Rect roiRostro= new Rect(),roiNariz=new Rect(),roiBoca=new Rect();
    Rect[] roiOjos=new Rect[2];
    //private String cascadasrostro[] = new String[10]=;
    //fin configuracion de variables basicas
    //inicio configuracion de constantes basicas

    private final String sepDir=System.getProperty("path.separator");//separador de directorios en windows es "\" 
                                                                     //y en unix y sus derivados es "/"
    	float EYE_SX = 0.16f;
        float EYE_SY = 0.26f;
        float EYE_SW = 0.30f;
        float EYE_SH = 0.28f;
    //fin configuracion de constantes basicas
        
        public static Mat getImage() {
        return image;
    }
    
     public ProcesarImagen() {
         rcDir=sepDir+rcDir+sepDir;
        
    }

    public String getImagenSalida() {
        return imagenSalida;
    }

    public void setImagenSalida(String imagenSalida) {
        this.imagenSalida = imagenSalida;
    }
    

    public String getImagenEntrada() {
        return imagenEntrada;
    }

    public void setImagenEntrada(String imagenEntrada) {
        this.imagenEntrada = imagenEntrada;
    }
    public String rutaDe (String recurso){
        String ruta = getClass().getResource(recurso).getPath();
        //System.out.println(ruta);
        try {
            ruta=URLDecoder.decode(ruta, "UTF-8"); //this will replace %20 with spaces
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ProcesarImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
    return ruta;
    }


  public void DetectarRostro() {
    System.out.println("\nDetectando rostros");

    // Create a face detector from the cascade file in the resources
    // directory.

   // System.out.println(rutaDe("/recursos/haarcascade_frontalface_alt.xml"));
    CascadeClassifier faceDetector = new CascadeClassifier(rutaDe("/recursos/haarcascade_frontalface_alt.xml"));
    
    //
    
    image = Highgui.imread(imagenEntrada);
    Mat temp = new Mat();
    Imgproc.cvtColor(image, temp, Imgproc.COLOR_BGRA2GRAY,0);
    Mat temp_rgba = new Mat();
    Imgproc.cvtColor(temp, temp_rgba, Imgproc.COLOR_GRAY2BGRA,0);
    temp_rgba.copyTo(image);
    temp.copyTo(image);
    equalizeHist(image, image);
   
    tmp=image;
    //    

    // Detect faces in the image.
    // MatOfRect is a special container class for Rect.
    MatOfRect faceDetections = new MatOfRect();
    faceDetector.detectMultiScale(image, faceDetections);
    contRostros=faceDetections.toArray().length;
   if (contRostros<1){
        System.out.println("No se an detectado rostros, profavor acerquese a la camara en una posicion adecuada");
        return;
    }else
        if(contRostros>1){
            System.out.println("se dectactaron mas de 1 rostros, seleecione 1 para evaluar");
            for (int i=0; i<faceDetections.toArray().length;i++){
            
            roiRostro=faceDetections.toArray()[i];
        Core.rectangle(image, new Point(roiRostro.x, roiRostro.y), new Point(roiRostro.x + roiRostro.width, roiRostro.y + roiRostro.height), new Scalar(0, 255, 0));
    }
            Principal.setRostro(image);
           new SeleccionImg().setVisible(true);
           
    }else{

    System.out.println(String.format("%s rostro Detectado, en la iteracion: "+cont, faceDetections.toArray().length));

    // Draw a bounding box around each face.
    //for (Rect roiRostro : faceDetections.toArray()) {
        for (int i=0; i<faceDetections.toArray().length;i++){
            
            roiRostro=faceDetections.toArray()[i];
        Core.rectangle(image, new Point(roiRostro.x, roiRostro.y), new Point(roiRostro.x + roiRostro.width, roiRostro.y + roiRostro.height), new Scalar(0, 255, 0));
    }


    // Save the visualized detection.
    String filename = imagenSalida;
    System.out.println(String.format("Guardado %s", filename));
    Highgui.imwrite(filename,image);
    recortarRostro();    
    this.detectarOjos();
        this.detectarNariz();
        detectarBoca();
        }
    


  }

    
  
  public void detectarOjos(){
      System.out.println("\nDetectando Ojos");

    // Create a face detector from the cascade file in the resources
    // directory.

    //System.out.println(rutaDe("/recursos/haarcascade_eye_tree_eyeglasses.xml"));
    //CascadeClassifier faceDetector = new CascadeClassifier(rutaDe("/recursos/haarcascade_eye.xml"));
    CascadeClassifier faceDetector = new CascadeClassifier(rutaDe("/recursos/haarcascade_eye.xml"));
    
    //
    
//    image = Highgui.imread(imagenEntrada);
    tmp=null;
    tmp=imgRostro.clone();

    //    
    

//vector<Rect> leftEye, rightEye;
 
int leftX = (int)Math.round(tmp.cols() * EYE_SX);
int topY = (int)Math.round(tmp.rows() * EYE_SY);
int widthX = (int)Math.round(tmp.cols() * EYE_SW);
int heightY = (int)Math.round(tmp.rows() * EYE_SH);
int rightX = (int)Math.round(tmp.cols() * (1.0-EYE_SX-EYE_SW));
//Mat topLeftOfFace = tmp.clone().submat(new Rect(leftX, topY, widthX, heightY));
//Mat topRightOfFace = tmp.clone().submat(new Rect(rightX, topY, widthX, heightY));
//Imgproc.cvGetSize();
System.out.println("columnas: "+tmp.cols()+" filas: "+tmp.rows());
System.out.println("leftx: "+leftX +" topy: "+topY+" anchox: "+widthX+" alto: "+heightY+" rightx"+rightX);
Mat topLeftOfFace = tmp.clone().submat(new Rect(leftX, topY, widthX, heightY));
Mat topRightOfFace = tmp.clone().submat(new Rect(rightX, topY, widthX, heightY));

    // Detect faces in the image.
    // MatOfRect is a special container class for Rect.
    MatOfRect deteccionOjos = new MatOfRect();
    faceDetector.detectMultiScale(topLeftOfFace, deteccionOjos);
    faceDetector.detectMultiScale(topRightOfFace, deteccionOjos);
    contRostros=deteccionOjos.toArray().length;
    if (contRostros<1&&cont<3){
        cont++;
        detectarOjos();
        return;
    }

    System.out.println(String.format("%s ojos Detectado, en la iteracion: "+cont, deteccionOjos.toArray().length));

    // Draw a bounding box around each face.
    //for (Rect roiRostro : faceDetections.toArray()) {
        for (int i=0; i<deteccionOjos.toArray().length;i++){
            
            roiOjos[i]=deteccionOjos.toArray()[i];
            switch(i){
                case 0:
                    Core.rectangle(topLeftOfFace, new Point(roiOjos[i].x, roiOjos[i].y), new Point(roiOjos[i].x + roiOjos[i].width, roiOjos[i].y + roiOjos[i].height), new Scalar(0, 255, 0));
                    Core.rectangle(tmp, new Point(roiOjos[i].x+leftX, roiOjos[i].y+topY), new Point(roiOjos[i].x + roiOjos[i].width+leftX, roiOjos[i].y + roiOjos[i].height+topY), new Scalar(0, 255, 0));
                        String filename = "ojos_0.png";
    System.out.println(String.format("Guardado %s", filename));
    Highgui.imwrite(filename,topLeftOfFace);
                    break;
                case 1:
                    Core.rectangle(topRightOfFace, new Point(roiOjos[i].x-(roiOjos[i].width/2), roiOjos[i].y), new Point(roiOjos[i].x + roiOjos[i].width, roiOjos[i].y + roiOjos[i].height), new Scalar(0, 255, 0));
                    Core.rectangle(tmp, new Point(roiOjos[i].x+rightX-(roiOjos[i].width/2), roiOjos[i].y+topY), new Point(roiOjos[i].x + roiOjos[i].width+rightX-(roiOjos[i].width/2), roiOjos[i].y + roiOjos[i].height+topY), new Scalar(0, 255, 0));

                        filename = "ojos_1.png";
    System.out.println(String.format("Guardado %s", filename));
    Highgui.imwrite(filename,topRightOfFace);
                default:
        
        }
            
            
        
    }


    // Save the visualized detection.
    String filename = "ojos.png";
    System.out.println(String.format("Guardado %s", filename));
    Highgui.imwrite(filename,tmp);
   // recortaOjos();
      
  }
  public void detectarBoca(){//haarcascade_mcs_nose.xml
      System.out.println("\nDetectando Boca");
      int contboc=0;
      

    // Create a face detector from the cascade file in the resources
    // directory.

    //System.out.println(rutaDe("/recursos/haarcascade_eye.xml"));
    CascadeClassifier faceDetector = new CascadeClassifier(rutaDe("/recursos/haarcascade_mcs_mouth.xml"));
    
    //
    
//    image = Highgui.imread(imagenEntrada);
    tmp=null;
    tmp=imgRostro.clone();
    /*int bX,bY,bTopX,bTopY;
    int startX = (int)Math.round(tmp.cols() * 0.25);
    int startY = (int)Math.round((tmp.rows()/2) + (tmp.rows()/2)*0.25);
    int widthX = (int)Math.round(tmp.cols() - startX);
    int heightY = (int)Math.round(tmp.rows() - (tmp.rows()/2)*0.25);
    System.out.println("columnas: "+tmp.cols()+" filas: "+tmp.rows());
    System.out.println("ancho: "+tmp.width()+" filas: "+tmp.height());
    System.out.println("x: "+startX+" y: "+startY+" anchox: "+widthX+" alto: "+heightY);
    Mat regionBoca = tmp.clone().submat(startX, startY, widthX, heightY);*/
    //    

    // Detect faces in the image.
    // MatOfRect is a special container class for Rect.
    MatOfRect deteccionBoca = new MatOfRect();
    faceDetector.detectMultiScale(tmp, deteccionBoca);
    contRostros=deteccionBoca.toArray().length;
 


    // Draw a bounding box around each face.
    //for (Rect roiRostro : faceDetections.toArray()) {
        for (int i=0; i<deteccionBoca.toArray().length;i++){
            
           
            if(deteccionBoca.toArray()[i].y>tmp.height()+(tmp.height()/2)){//tomala boca detectada en la region dela itad de la cara hacia abajo
                roiBoca=deteccionBoca.toArray()[i];
                contboc++;
        Core.rectangle(tmp, new Point(roiBoca.x, roiBoca.y), new Point(roiBoca.x + roiBoca.width, roiBoca.y + roiBoca.height), new Scalar(0, 255, 0));
            }
    }
    System.out.println(String.format("%s Boca procesada, en la iteracion: "+cont, contboc));


    // Save the visualized detection.
    String filename = "boca.png";
    System.out.println(String.format("Guardado %s", filename));
    Highgui.imwrite(filename,tmp);
    recortaBoca();
        
      
  }
  public void detectarNariz(){//
      System.out.println("\nDetectando Nariz");
      int contnar=0;

    // Create a face detector from the cascade file in the resources
    // directory.

    //System.out.println(rutaDe("/recursos/haarcascade_eye.xml"));
    CascadeClassifier faceDetector = new CascadeClassifier(rutaDe("/recursos/haarcascade_mcs_nose.xml"));
    
    //
    
//    image = Highgui.imread(imagenEntrada);
    tmp=null;
    tmp=imgRostro.clone();

    //    

    // Detect faces in the image.
    // MatOfRect is a special container class for Rect.
    MatOfRect detectarNariz = new MatOfRect();
    faceDetector.detectMultiScale(tmp, detectarNariz);
    contRostros=detectarNariz.toArray().length;
    if (contRostros<1&&cont<3){
        cont++;
        detectarOjos();
        return;
    }



    // Draw a bounding box around each face.
    //for (Rect roiRostro : faceDetections.toArray()) {
        for (int i=0; i<detectarNariz.toArray().length;i++){
            
            roiNariz=detectarNariz.toArray()[i];
                        if(roiNariz.y>tmp.height()/4){//tomala boca detectada en la region del centro de la cara hacia abajo
                contnar++;
        Core.rectangle(tmp, new Point(roiNariz.x, roiNariz.y), new Point(roiNariz.x + roiNariz.width, roiNariz.y + roiNariz.height), new Scalar(0, 255, 0));
                        }
    }
    System.out.println(String.format("%s Nariz Detectado, en la iteracion: "+cont, contnar));

    // Save the visualized detection.
    String filename = "nariz.png";
    System.out.println(String.format("Guardado %s", filename));
    Highgui.imwrite(filename,tmp);
    recortaNariz();
      
  }
  
    public void recortarRostro(){
      System.out.println("\nRecortando rostro");
      //cortar la imagen
    Rect roi = new Rect(new Point(roiRostro.x+1, roiRostro.y+1), new Point(roiRostro.x + roiRostro.width, roiRostro.y + roiRostro.height));

     //apilcanco cany
    //Imgproc.Canny(imgRostro, imgRostro, 80, 90);
    /*Mat gris=new Mat(tmp.width(),tmp.height(),tmp.type());
Mat blur=new Mat(tmp.width(),tmp.height(),tmp.type());
Mat canny = new Mat(tmp.width(),tmp.height(),tmp.type());
Imgproc.cvtColor(tmp, gris, Imgproc.COLOR_RGB2GRAY);
Size s = new Size(3,3);
int min_threshold=30;
float ratio = 1.1f;
Imgproc.blur(gris, blur,s);
Imgproc.Canny(blur, canny, min_threshold,min_threshold*ratio);
canny.copyTo(tmp);*/
    imgRostro=tmp.submat(roi);//region de interes

    //cvShowImage("Lena Image", src);
        // Save the visualized detection.
    String filename = "rostro.png";
    System.out.println(String.format("Guardado %s", filename));
    Highgui.imwrite(filename,imgRostro);
  }
  
    
      public void recortaOjos(){
      System.out.println("\nRecortando Ojos");

            for (int i=0; i<2;i++){
                      //cortar la imagen
    Rect roi = new Rect(new Point(roiOjos[i].x+1, roiOjos[i].y+1), new Point(roiOjos[i].x + roiOjos[i].width, roiOjos[i].y + roiOjos[i].height));
    tmp=imgRostro.clone().submat(roi);//region de interes
    //cvShowImage("Lena Image", src);
        // Save the visualized detection.
            
            
    String filename = "Ojos_d"+(i+1)+".png";
    System.out.println(String.format("Guardado %s", filename));
    Highgui.imwrite(filename,tmp);
            }
  }
      public void recortaNariz(){
      System.out.println("\nRecortando Nariz");
      //cortar la imagen
    Rect roi = new Rect(new Point(roiNariz.x+1, roiNariz.y+1), new Point(roiNariz.x + roiNariz.width, roiNariz.y + roiNariz.height));
    tmp=imgRostro.clone().submat(roi);//region de interes
    //cvShowImage("Lena Image", src);
        // Save the visualized detection.            
            
    String filename = "Nariz_d.png";
    System.out.println(String.format("Guardado %s", filename));
    Highgui.imwrite(filename,tmp);
            
  }
            public void recortaBoca(){
      System.out.println("\nRecortando Boca");
      //cortar la imagen
    Rect roi = new Rect(new Point(roiBoca.x+1, roiBoca.y+1), new Point(roiBoca.x + roiBoca.width, roiBoca.y + roiBoca.height));
    tmp=imgRostro.clone().submat(roi);//region de interes
    //cvShowImage("Lena Image", src);
        // Save the visualized detection.            
            
    String filename = "Boca_d.png";
    System.out.println(String.format("Guardado %s", filename));
    Highgui.imwrite(filename,tmp);
            
  }
    public static BufferedImage matABfIMg(Mat image) {

        MatOfByte bytemat = new MatOfByte();
        Highgui.imencode(".jpg", image, bytemat);
        byte[] bytes = bytemat.toArray();
        InputStream in = new ByteArrayInputStream(bytes);
        BufferedImage img;
        img = null;
        try {
            img = ImageIO.read(in);
        } catch (IOException ex) {
            Logger.getLogger(ProcesarImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;

    }
}

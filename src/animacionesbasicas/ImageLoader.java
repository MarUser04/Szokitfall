package animacionesbasicas;

import java.net.URL;
import javax.swing.ImageIcon;
/**  clase que sirven para inicializar las imagenes */
public class ImageLoader {
    
    private static ImageLoader instance=null;

    private static final int MAX_IMAGES=31;
    public static final int BACKGROUND=0;
    public static final int BACKGROUND2=1;
    public static final int SHELL_HOR_BEGIN=1;
    public static final int SHELL_HOR_END=11;

    
    private ImageIcon images[], imagenFondo[], estrellaIcon;
   /** constructor por defencto de la clase ImageLoader para indicar las direcciones de las imagenes  */
    private ImageLoader(){
        String filenames[]= new String[]{"fondo1", "indi1"
                                       , "indi2", "indi3", "indi4", "indi5","indi6", "indi8", "indi9", "indi10"
                                        , "indi11", "indi12", "-indi1"
                                       , "-indi2", "-indi3", "-indi4", "-indi5","-indi6", "-indi8", "-indi9", "-indi10"
                                        , "-indi11", "-indi12",
                                        "tronco_2", "tronco_3", "Plataforma", "scorpion1", "scorpion2", "tortu1", "tortu2", "Image_122"};   
        
        
        String mundos[]= new String[]{"fondo1", "fondo2", "fondo3", "fondo4"};
        
       
        
        URL urlE = this.getClass().getResource("/imagenes/Image_116.png");
        estrellaIcon= new ImageIcon(urlE);
        
        
       
        

        images = new ImageIcon[MAX_IMAGES];
        imagenFondo= new ImageIcon[4];
        for(int i=0;i<MAX_IMAGES;i++){
            URL url = this.getClass().getResource("/imagenes/"+filenames[i]+".png");
            
            images[i] = new ImageIcon(url);
        }
        
        for (int i = 0; i < 4; i++) {
            
            URL url2= this.getClass().getResource("/imagenes/"+mundos[i]+".png");
            imagenFondo[i]= new ImageIcon(url2);
            
            
        }
        
        
        
        
    }

    /**  metodo que sirve para cargar la instancia de cada objeto*/
    public static ImageLoader getInstance(){
        if(instance==null)
          instance = new ImageLoader();

        return instance;
    
    }
 /** metodo que retorna las imagenes del vector images*/ 
    public ImageIcon getImage(int imageId){
        if(imageId<0 || imageId>=MAX_IMAGES)
            return null;
        
        return images[imageId];
    }
 /** metodo que retorna las imagenes del vector imagenFondo*/   
    public ImageIcon getMundo(int imageId){
        if(imageId<0 || imageId>3)
            return null;
       
        return imagenFondo[imageId];
        
    }
    
    /** metodo que retorna la estrella de puntos*/
    public ImageIcon getEstrella()
    {
        return estrellaIcon;
    }
    
}

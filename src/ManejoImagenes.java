import java.net.URL;
import javax.swing.ImageIcon;

public class ManejoImagenes{
URL url;    
int NUMBEROFCARDS;
ImageIcon images[];

public ManejoImagenes(){
    NUMBEROFCARDS = 67;
    images = new ImageIcon[NUMBEROFCARDS];
}

public void cargarCartas(){
    for(int i=0;i<NUMBEROFCARDS;i++){
       /** Obtener la ruta de la imagen i como una URL*/
       url = images.getClass().getResource("images/"+(i+1)+".PNG");
       /** Crear la imagen y guardarla en el vector*/
       images[i] =  new ImageIcon(url);
    }
}

public static void main(String [] args){
    ManejoImagenes aplicacion = new ManejoImagenes();
    aplicacion.cargarCartas();
}
}


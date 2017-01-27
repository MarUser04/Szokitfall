
package animacionesbasicas;


import java.io.File;
import javazoom.jlgui.basicplayer.BasicPlayer;
import javazoom.jlgui.basicplayer.BasicPlayerException;
public class Musica  {
 /** clase que se encarga de los efectos de sonido del juego*/
    public static void play(int accion)
    {
        if(accion==0)
        {
              try {
            BasicPlayer player= new BasicPlayer(); // Llamo la clase de la libreria Basic Player, que reproduce
            player.open(new File("sonido/Indiana8bits.mp3"));// Dentro las "" va la                     ruta de tu archivo mp3.
            player.play();// Llama al método Reproducir también existen los métodos  stop,resume.           
        } catch (BasicPlayerException ex) {
          //  System.out.print("-------Error-----"+ex.getMessage());
        }// Fin try
              
        }
        
        if(accion==1)
        {
              try {
            BasicPlayer player= new BasicPlayer(); // Llamo la clase de la libreria Basic Player, que reproduce
            player.open(new File("sonido/salto.mp3"));// Dentro las "" va la                     ruta de tu archivo mp3.
            player.play();// Llama al método Reproducir también existen los métodos  stop,resume.           
        } catch (BasicPlayerException ex) {
          //  System.out.print("-------Error-----"+ex.getMessage());
        }// Fin try
              
        }
        
        
          if(accion==2)
        {
              try {
            BasicPlayer player= new BasicPlayer(); // Llamo la clase de la libreria Basic Player, que reproduce
            player.open(new File("sonido/choque.mp3"));// Dentro las "" va la                     ruta de tu archivo mp3.
            player.play();// Llama al método Reproducir también existen los métodos  stop,resume.           
        } catch (BasicPlayerException ex) {
          //  System.out.print("-------Error-----"+ex.getMessage());
        }// Fin try
              
        }
      
            if(accion==3)
        {
              try {
            BasicPlayer player= new BasicPlayer(); // Llamo la clase de la libreria Basic Player, que reproduce
            player.open(new File("sonido/muerte.mp3"));// Dentro las "" va la                     ruta de tu archivo mp3.
            player.play();// Llama al método Reproducir también existen los métodos  stop,resume.           
        } catch (BasicPlayerException ex) {
          //  System.out.print("-------Error-----"+ex.getMessage());
        }// Fin try
              
        }  
          
        if(accion==4)
        {
              try {
            BasicPlayer player= new BasicPlayer(); // Llamo la clase de la libreria Basic Player, que reproduce
            player.open(new File("sonido/moneda.mp3"));// Dentro las "" va la                     ruta de tu archivo mp3.
            player.play();// Llama al método Reproducir también existen los métodos  stop,resume.           
        } catch (BasicPlayerException ex) {
          //  System.out.print("-------Error-----"+ex.getMessage());
        }// Fin try
              
        }       
            
    }// Fin Main
}// Fin Clase
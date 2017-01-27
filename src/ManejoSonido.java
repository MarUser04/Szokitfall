import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.Clip;

public class ManejoSonido{
   public ManejoSonido()throws UnsupportedAudioFileException, 
                            IOException, LineUnavailableException {
      URL url = getClass().getResource("sonidos/imagine.mid");
      AudioInputStream flujo = AudioSystem.getAudioInputStream(url);
      AudioFormat format = flujo.getFormat();
      DataLine.Info info;
      info= new DataLine.Info (Clip.class, format, (int) (flujo.getFrameLength () * format.getFrameSize ()));
        
      Clip reprod = (Clip)AudioSystem.getLine(info);
      reprod.open(flujo);
        
      reprod.start(); //reprod.loop(Clip.LOOP_CONTINUOUSLY);     
      System.in.read();
    }
public static void main(String[] args) throws UnsupportedAudioFileException, 
                                              IOException, LineUnavailableException {
    ManejoSonido aplicacion = new ManejoSonido();
}
}


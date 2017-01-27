
package animacionesbasicas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
/** esta clase sirve para guardar el top 10 en el archivo*/
public class Top10 {

    public String []nombre= new String[11];
    public int []puntos= new int[11];
    public int auxP;
    public String auxN;
    
    /**constructor que recibe el metodo*/
    public Top10()
    {
        
    }
/**constructor parametrico que recibe las estadisticas de las partidas*/
    public Top10(int auxP, String auxN) throws IOException {
        this.auxP = auxP;
        this.auxN = auxN;
        
        leer();
        //buscarMenor();
        //escribir();
    }

   
    
    /** metodo que se encarga de escribir el archivo*/
       public void escribir() throws IOException
    {
        String arch= "top10.txt";
        

        FileWriter fw= new FileWriter (arch);
        BufferedWriter bw= new BufferedWriter(fw);
        PrintWriter archivo= new PrintWriter(bw);
 
        for (int i = 0; i < 10; i++) {
           archivo.print(nombre[i]+" ");
           archivo.println(puntos[i]);
        }
        
        

        archivo.close();
        
       
        
    }
       
           
    /**metodo que se encarga de leer el archivo*/
    public void leer() throws IOException
    {
        String arch= "top10.txt";
        String linea;
        StringTokenizer tokenizer;

        FileReader fr= new FileReader(arch);
        BufferedReader br= new BufferedReader(fr);
        
        linea= br.readLine();
        
        int i=0;
        
        while(linea !=null)
        {
            tokenizer= new StringTokenizer(linea);
            auxN=tokenizer.nextToken("-");
           auxP=Integer.parseInt(tokenizer.nextToken());
            linea= br.readLine();
            System.out.println(""+auxN+" ola:  "+auxP);
           
            i++;
            System.out.println("hola");
        }
        

        br.close();
        
    }

 
/** metodo utilizado para obtener el peor puntaje del top 10 anterior*/
    private void buscarMenor()
    {
        int menor=99999;
        String nombreMenor;
        for (int i = 0; i < 10; i++) {
            if(menor>puntos[i])
            {
                menor=puntos[i];
                nombreMenor=nombre[i];
                
                puntos[i]=auxP;
                nombre[i]=auxN;

            }
        }
        
        for (int i = 0; i < 10; i++) {
            
            System.out.println("puntos "+puntos[i]);
            System.out.println("nombre "+nombre[i]);
        }
    }
    
    
       
    
    
    
    
}

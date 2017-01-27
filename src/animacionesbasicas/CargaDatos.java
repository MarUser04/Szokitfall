
package animacionesbasicas;

import java.io.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/** clase que se encarga de cargar la partida anteriormente guardada*/
public class CargaDatos extends MainFrame {
    
    public int x, y, escenario;
    public boolean mov=true, caida=false, salto=false, vertical=false, escalera=false, pasar=false, evitarSalto=false;
    public int minutos, segundos, vida, puntos;
    public String nombre;
    public CargaDatos()
    {
        
    }
    /** constructor que recibe todos los datos que obtiene de la partida en juego*/
    public CargaDatos(int x, int y, int escenario, boolean mov, boolean caida, boolean salto, boolean vertical,boolean escalera, 
                      boolean pasar, boolean evitarPasar, int minutos, int segundos, int vida, int puntos, String nombre) throws IOException
    {
        this.x=x;
        this.y=y;
        this.escenario=escenario;
        this.mov=mov;
        this.caida= caida;
        this.salto=salto;
        this.vertical=vertical;
        this.escalera=escalera;
        this.pasar=pasar;
        this.evitarSalto=evitarSalto;
        this.minutos=minutos;
        this.segundos=segundos;
        this.vida=vida;
        this.puntos=puntos;
        this.nombre=nombre;
       
    }
    
    /** guarda la partida en el archivo*/
    public void escribir() throws IOException
    {
        String arch= "partida.txt";
        
        
        
        FileWriter fw= new FileWriter (arch);
        BufferedWriter bw= new BufferedWriter(fw);
        PrintWriter archivo= new PrintWriter(bw);
        
      
      
        
        
        archivo.print(x+" ");
        archivo.print(y+" ");
        archivo.print(escenario+" ");
        archivo.print(mov+" ");
        archivo.print(caida+" ");
        archivo.print(salto+" ");
        archivo.print(vertical+ " ");
        archivo.print(escalera+" ");
        archivo.print(pasar+" ");
        archivo.print(evitarSalto+" ");
        archivo.print(minutos+" ");
        archivo.print(segundos+" ");
        archivo.print(vida+" ");
        archivo.print(puntos+" ");
        archivo.print(nombre);
        
        
        archivo.println();
        archivo.close();
        
        System.out.println("El archivo de salida ha sido creado: "+arch);
        
    }
    
    
    
    
    
   /** carga la partida del archivo*/
    public void leer() throws IOException
    {
        String arch= "partida.txt";
        String linea;
        StringTokenizer tokenizer;

        FileReader fr= new FileReader(arch);
        BufferedReader br= new BufferedReader(fr);
        
        linea= br.readLine();
        
        while(linea !=null)
        {
            tokenizer= new StringTokenizer(linea);
            x=Integer.parseInt(tokenizer.nextToken());
            y= Integer.parseInt(tokenizer.nextToken());
            escenario= Integer.parseInt(tokenizer.nextToken());
            mov= Boolean.parseBoolean(tokenizer.nextToken());
            caida= Boolean.parseBoolean(tokenizer.nextToken());
            salto=Boolean.parseBoolean(tokenizer.nextToken());
            vertical= Boolean.parseBoolean(tokenizer.nextToken());
            escalera= Boolean.parseBoolean(tokenizer.nextToken());
            pasar= Boolean.parseBoolean(tokenizer.nextToken());
            evitarSalto=Boolean.parseBoolean(tokenizer.nextToken());
            minutos=Integer.parseInt(tokenizer.nextToken());
            segundos=Integer.parseInt(tokenizer.nextToken());
            vida=Integer.parseInt(tokenizer.nextToken());
            puntos=Integer.parseInt(tokenizer.nextToken());
            nombre=tokenizer.nextToken();
            
            linea= br.readLine();
        }
        

        br.close();
        
    }

 
    

}

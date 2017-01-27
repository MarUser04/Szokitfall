package animacionesbasicas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
/** clase que se encarga de la jugabilidad del juego*/
public class MainFrame extends JFrame {

    private int shell_frame=0, shell2_frame=0, escenario=0, cambiari=0;
    private JLabel shell, shell2, background, tronco1, alacran1, coco1, coco2, coco3, estrella, plataforma;
    private final int LLEFT=0, LRIGHT=790;
    private int DIR = -1;
    private JLabel puntuacion, vidas, tiempoM, tiempoS, name, victoria, Score;
    private String nombre;
    private boolean mov=true, caida=false, salto=false, vertical=false, escalera=false, pasar=false;
    private boolean evitarSalto=false, partida=false, star=false, Dplataforma=false, juntos=false, laguna=false;
    private int inicial=0, vida=2000, vida2=3, x=23, y=390, minutos=2, segundos= 59, accion=0, score=0;
    Timer timerShell,timerShell2, timersalto, timerTiempo;
    Timer timercaida, timercambiar;
    JTextField puntosVida;
     ImageIcon fondo;
    CargaDatos cd;
    /** constructor que crea una partida nueva*/
    public MainFrame() {
        super("Szokitfall");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

         accion=0;
         Musica.play(accion);
        
        Font fuente= new Font("Arial", 2, 30);
  
      nombre=Menu.nombre;

        name= new JLabel(nombre);
        name.setFont(fuente);
        name.setForeground(Color.white);
        name.setBounds(300, 0, 400, 50);
        name.setVisible(true);
        add(name);
        
        puntuacion= new JLabel(String.valueOf(vida));
       puntuacion.setForeground(Color.white);
        puntuacion.setFont(fuente);
        puntuacion.setBounds(0, 0, 300, 50);
        puntuacion.setVisible(true);
        add(puntuacion);
        
        
        
        vidas= new JLabel(String.valueOf(vida2));
        vidas.setFont(fuente);
        vidas.setForeground(Color.white);
        vidas.setBounds(0, 30, 150, 50);
        vidas.setVisible(true);
        add(vidas);
        
        
         tiempoM= new JLabel(String.valueOf(minutos)+":"+String.valueOf(segundos));
         tiempoM.setFont(fuente);
         tiempoM.setBounds(680, 0, 150, 50);
         tiempoM.setForeground(Color.white);
         tiempoM.setVisible(true);
         
          add(tiempoM);
           
    
          
        timerShell = new Timer(45, new TimerShell());
        timerShell.start();

        timerShell2 = new Timer(25, new TimerShell2());
        
        timersalto= new Timer(40, new timersalto());
       
        timercaida= new Timer(8, new timercaida());
        
        timerTiempo= new Timer(1000, new timerTiempo());
        timerTiempo.start();

        timercambiar= new Timer(4500, new timercambiar());
        timercambiar.start();
        CargarFondo(escenario);
       this.addWindowListener(new WindowsEvents());
     
        
       jugar();
        
             }
  
    ////
    
    /** constructor parametrico que se encarga de recibir los datos de la partida guardada anteriormente*/
    public MainFrame(CargaDatos cd) {
        super("Szokitfall");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            accion=0;
         Musica.play(accion);
        
        mov= cd.mov;
        caida= cd.caida;
        salto= cd.salto;
        vertical= cd.vertical;
        escalera= cd.escalera;
        pasar=cd.pasar;
        x=cd.x;
        y=cd.y;
       
        escenario=cd.escenario;
        partida=true;
        evitarSalto=cd.evitarSalto;
        minutos=cd.minutos;
        segundos=cd.segundos;
        vida2=cd.vida;
        vida=cd.puntos;
        nombre=cd.nombre;
        
        
        

        
        Font fuente= new Font("Arial", 2, 30);
        
         name= new JLabel(nombre);
        name.setFont(fuente);
        name.setForeground(Color.white);
        name.setBounds(300, 0, 400, 50);
        name.setVisible(true);
        add(name);
        
  
        puntuacion= new JLabel(String.valueOf(vida));
       puntuacion.setForeground(Color.white);
        puntuacion.setFont(fuente);
        puntuacion.setBounds(0, 0, 300, 50);
        puntuacion.setVisible(true);
        add(puntuacion);
        
        
        
        vidas= new JLabel(String.valueOf(vida2));
        vidas.setFont(fuente);
        vidas.setForeground(Color.white);
        vidas.setBounds(0, 30, 150, 50);
        vidas.setVisible(true);
        add(vidas);
        
        
         tiempoM= new JLabel(String.valueOf(minutos)+":"+String.valueOf(segundos));
         tiempoM.setFont(fuente);
         tiempoM.setBounds(680, 0, 150, 50);
         tiempoM.setForeground(Color.white);
         tiempoM.setVisible(true);
         
          add(tiempoM);
           
        timerShell = new Timer(45, new TimerShell());
        timerShell.start();

        timerShell2 = new Timer(25, new TimerShell2());
        
        timersalto= new Timer(40, new timersalto());
       
        timercaida= new Timer(8, new timercaida());
      
        timercambiar= new Timer(4500, new timercambiar());
        timercambiar.start();
        
        timerTiempo= new Timer(1000, new timerTiempo());
        timerTiempo.start();
        
                CargarFondo(escenario);
       this.addWindowListener(new WindowsEvents());
     
        
       jugar();
        
    }
    /** metodo que se encarga de inicializar el personaje en pantalla*/
    private void initComponents(){
  
        this.getContentPane().setLayout(null);
            
       
        setFocusable(true);
        ImageLoader loader = ImageLoader.getInstance();

        ImageIcon icon;
        star=false;
        
        icon=loader.getEstrella();
        estrella= new JLabel(icon);
        
        int random= (int) (Math.random()*700+50);
        
        estrella.setBounds(random,410, icon.getIconWidth(), icon.getIconHeight());
        this.getContentPane().add(estrella);
        
        icon=loader.getImage(ImageLoader.SHELL_HOR_BEGIN);
        
        
        
        
        
        shell2 = new JLabel(icon);
        if(mov==true && pasar==false){
            
            if(partida==false)
            {
              x=23;
                 y=390;  
            }
            
             shell2.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        this.getContentPane().add(shell2);
        }
       
        if(mov==false && pasar==false){
            
            if(partida==false)
            {
                x=780;
                y=390; 
            }
            
           
            shell2.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        this.getContentPane().add(shell2);
            
        }
        
        if(mov==true && pasar==true){
            
            if(partida==false)    
            {
                x=23;
                 y=546;
            }
            
            shell2.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        this.getContentPane().add(shell2);
        }
       
        if(mov==false && pasar==true){
            
            if(partida==false)
            {
                x=780;
                y=546;
            }
            
            shell2.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
        this.getContentPane().add(shell2);
            
        }
        
       

        setResizable(false);
        
      
        
          
      
}
    /** metodo que permite el desplazamiento del personaje*/
    private void jugar(){

         this.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e)
            {   
                if(caida==false)
                {
                
                if(e.getKeyCode()==e.VK_SPACE)
                  { 
                        
                      if(salto==false && evitarSalto==false)
                      {
                          accion=1;
                         Musica.play(accion);
                          
                         timersalto.start();
                         
                         inicial=shell2.getY();
                          
                         salto=true;
                         
                         
                      }
                }
                if(e.getKeyCode()==e.VK_RIGHT)
                  {
                      if(escalera==false  && (escenario==0 || escenario==2 || escenario==3 || (escenario==1 && shell2.getY()<566))){
                        shell2.setBounds(shell2.getX()+5, shell2.getY(), shell2.getWidth(), shell2.getHeight());
                   
                    timerShell2.start();
                    mov=true;
                      }
                      
                      if(escenario==1 && shell2.getY()==566 && shell2.getX()<=118 || escenario==1 && shell2.getY()==566 && shell2.getX()>=246 ){
                      shell2.setBounds(shell2.getX()+5, shell2.getY(), shell2.getWidth(), shell2.getHeight());
                     timerShell2.start();      
                    mov=true;
                      
                  }
                      
                      
                  }
                if(e.getKeyCode()==e.VK_LEFT)
                  { 
                  if(escalera==false && (escenario==0 || escenario==2 || escenario==3 || (escenario==1 && shell2.getY()<566))){
                shell2.setBounds(shell2.getX()-5, shell2.getY(), shell2.getWidth(), shell2.getHeight());
                  timerShell2.start();      
                    mov=false;
                  }
                  
                  if(escenario==1 && shell2.getY()<=566 && shell2.getX()<=123 || escenario==1 && shell2.getY()==566 && shell2.getX()>=251){
                      shell2.setBounds(shell2.getX()-5, shell2.getY(), shell2.getWidth(), shell2.getHeight());
                  timerShell2.start();      
                    mov=false;
                      
                  }
                  
                  }
                
                if(e.getKeyCode()==e.VK_UP)
                {
                if(escenario==0){
                    if(salto==false) {
                   if((shell2.getX()>356 && shell2.getX()<426) && shell2.getY()>390 ){
                       escalera=true;
                       shell2.setBounds(shell2.getX(), shell2.getY()-4, shell2.getWidth(), shell2.getHeight());
                       evitarSalto=true;
                  }
                        System.out.println(""+shell.getY());
                   if(shell2.getY()==390 || shell2.getY()==546){
                       escalera=false;
                       evitarSalto=false;
                   }
                    }
                }
                
                if(escenario==1){
                 if(salto==false) {
                   if((shell2.getX()>=558 && shell2.getX()<658) && shell2.getY()>390 ){
                       escalera=true;
                       shell2.setBounds(shell2.getX(), shell2.getY()-4, shell2.getWidth(), shell2.getHeight());
                       evitarSalto=true;
                  }
                   
                   if(shell2.getY()==390 || shell2.getY()==546){
                       escalera=false;
                       evitarSalto=false;
                   }
                    }  
                    
                }
                if(escenario==2){
                    if(salto==false) {
                   if((shell2.getX()>=58 && shell2.getX()<=98) && shell2.getY()>390 ){
                       escalera=true;
                       shell2.setBounds(shell2.getX(), shell2.getY()-4, shell2.getWidth(), shell2.getHeight());
                       evitarSalto=true;
                  }
                   
                   if(shell2.getY()==390 || shell2.getY()==546){
                       escalera=false;
                       evitarSalto=false;
                   }
                    } 
                    
                    
                }
                }
                if(e.getKeyCode()==e.VK_DOWN){
                  if(escenario==0){
                    if(salto==false) {
                    if((shell2.getX()>356 && shell2.getX()<426) && shell2.getY()<545 ){
                       escalera=true;
                       shell2.setBounds(shell2.getX(), shell2.getY()+4, shell2.getWidth(), shell2.getHeight());
                                
                   }
                  if(shell2.getY()==390 || shell2.getY()==546){
                       escalera=false;
                   }
                   }
                }
                  
                    if(escenario==1){
                 if(salto==false) {
                   if((shell2.getX()>=558 && shell2.getX()<658) && shell2.getY()<545 ){
                       escalera=true;
                       shell2.setBounds(shell2.getX(), shell2.getY()+4, shell2.getWidth(), shell2.getHeight());
                  }
                   
                  
                    }  
                     if(shell2.getY()==390 || shell2.getY()==546){
                       escalera=false;
                   }
                 
                }
                  
                           if(escenario==2){
                 if(salto==false) {
                   if((shell2.getX()>=58 && shell2.getX()<98) && shell2.getY()<545 ){
                       escalera=true;
                       shell2.setBounds(shell2.getX(), shell2.getY()+4, shell2.getWidth(), shell2.getHeight());
                  }
                   
                   if(shell2.getY()==390 || shell2.getY()==546){
                       escalera=false;
                   }
                    }  
                    
                }       
                 
                           
                                      
                }
                
                   if(e.getKeyCode()==e.VK_G)
                {
                    try {
                        cd= new CargaDatos(shell2.getX(), shell2.getY(), escenario, mov, caida, salto,vertical, escalera, pasar, evitarSalto, minutos, segundos, vida2, vida, nombre);
                        cd.escribir();
                    
                        
                    } catch (IOException ex) {
                        Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }
            }//if
            
            }
       
         });
       
    }
   /** metodo utilizado para cargar el fondo de la pantalla*/
    public void CargarFondo(int escenario){
        
        ImageLoader loader = ImageLoader.getInstance();
        
        ImageIcon icon= loader.getMundo(0+escenario);
        
        ImageIcon icono2= loader.getImage(2);
         
         initComponents();
         
         cargarPlataforma();
         
         
       Enemigos obj= new Enemigos();  
       background = new JLabel(icon);
        
       background.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
       MainFrame.this.getContentPane().add(background);
   
       
      
        
    }
    /** metodo que se encarga de modificar el valor del puntaje*/ 
   public void puntajeRestar(int vida)
    {       puntuacion.setText(String.valueOf((vida)));
    }
   /** metodo que se encarga de disminuir el numero de vidas*/
   public void vidaRestar(int vida2)
   {
   vidas.setText(String.valueOf(vida2));
       
   }
   
   
    
    /** clase que inicializa la ventana*/
    class WindowsEvents extends WindowAdapter{
        @Override
       
        public void windowOpened(WindowEvent e) {
            Insets inset = getInsets();
            setSize(800+inset.left+inset.right,600+inset.bottom+inset.top); //Adecuar el tamaño de la ventana a abrir tomando en cuenta 
                                                                            //las dimensiones de la imagen de fondo y de los 4 bordes
            setLocationRelativeTo(null); //Para centrar la ventana en la pantalla
        }
       
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
        
    }
    /** metodo utilizado para eliminar objetos de la pantalla*/
    public void Borrar(JLabel obj){
        
        MainFrame.this.getContentPane().remove(obj);
    }
/** metodo que se encarga de eliminar todo lo presente en la pantalla*/
   public void BorraTodo(int escenario)
   {
       if(escenario==0)
       {
          Borrar(shell);
          Borrar(shell2);
          Borrar(background);
          
       }
       else if(escenario==1)
       {
           Borrar(shell2);
           Borrar(alacran1);
           Borrar(shell);
           Borrar(background);
           Borrar(tronco1);
       }
       else if(escenario==2){
           
           Borrar(shell2);
            Borrar(background);
            Borrar(coco1);
            Borrar(coco2);
            Borrar(coco3);
            Borrar(alacran1);
       }
       
       else if(escenario==3){
           Borrar(shell2);
           Borrar(plataforma);
           Borrar(background);
          
           
       }
   }
/** clase encargada de controlar el salto del personaje*/
    class timersalto implements ActionListener{
        int bandera=0; 
    public void actionPerformed(ActionEvent e) {
                ImageLoader loader = ImageLoader.getInstance();
           if(vertical==false) {  
            if(mov){
            if(shell2.getY()>(inicial-58) && bandera==0){
              
                shell2.setBounds(shell2.getX()+2, shell2.getY()-5, shell2.getWidth(), shell2.getHeight());
            }
            
            if(shell2.getY()<(inicial-58)){
                if(bandera==0){
                timersalto.stop();    
                } 
                bandera=1;
            }
           
            if(bandera==1){
              
                timersalto.start();
                shell2.setBounds(shell2.getX()+2, shell2.getY()+5, shell2.getWidth(), shell2.getHeight());
                
            }
                 if(bandera==1 && shell2.getY()==inicial ){
                
                timersalto.stop();
                bandera=0;
                  salto=false;
                if(escenario==0){
                if( (shell2.getX()>118 && shell2.getX()<=184 && shell2.getY()<545) || (shell2.getX()>341 && shell2.getX()<=436 && shell2.getY()<545) || (shell2.getX()>=594 && shell2.getX()<668 && shell2.getY()<545) ){
                   timercaida.start();
                    caida=true;
                }
                }
                
                if(escenario==1){
                if((shell2.getX()>=558 && shell2.getX()<=673 && shell2.getY()<545) ){
                    timercaida.start();
                    caida=true;
                }
                }
                if(escenario==2){
                if((shell2.getX()>=53 && shell2.getX()<=108 && shell2.getY()<545) || (shell2.getX()>=676 && shell2.getX()<=735 && shell2.getY()<545)  ){
                    timercaida.start();
                    caida=true;
                               
                }
                }
                if(escenario==3){
                if((shell2.getX()>=173 && shell2.getX()<=603 && shell2.getY()<545)){
                    timercaida.start();
                    caida=true;
                }
                
                
                
            }
            }
           
                
    
        }//if
        else if(mov==false)
        {
                if(shell2.getY()>(inicial-58) && bandera==0){
                shell2.setBounds(shell2.getX()-2, shell2.getY()-5, shell2.getWidth(), shell2.getHeight());
            }
            
              
            
            if(shell2.getY()<(inicial-58)){
                if(bandera==0){
                timersalto.stop();
              
                }
             bandera=1;
            }
           
            if(bandera==1){
                timersalto.start();
                shell2.setBounds(shell2.getX()-2, shell2.getY()+5, shell2.getWidth(), shell2.getHeight());
                
            }
                 if(bandera==1 && shell2.getY()==inicial ){
                
                timersalto.stop();
                bandera=0;
                  salto=false;
                if(escenario==0){
                if((shell2.getX()>118 && shell2.getX()<=184 && shell2.getY()<565) || (shell2.getX()>341 && shell2.getX()<=436 && shell2.getY()<565) || (shell2.getX()>=594 && shell2.getX()<=668 && shell2.getY()<565)){
                    timercaida.start();
                    caida=true;
                }
                           }

                
                if(escenario==1){
                if((shell2.getX()>=558 && shell2.getX()<=673 && shell2.getY()<565) ){
                    timercaida.start();
                    caida=true;
                }
                }
                if(escenario==2){
                if((shell2.getX()>=53 && shell2.getX()<=108 && shell2.getY()<565) || (shell2.getX()>=676 && shell2.getX()<=735 && shell2.getY()<565)  ){
                    timercaida.start();
                    caida=true;
                               
                }
                }
                if(escenario==3){
                if((shell2.getX()>=173 && shell2.getX()<=603 && shell2.getY()<565)){
                    timercaida.start();
                    caida=true;
                }
                
                
                
            }
        }
            
         
     }
    
    

}
    }
    }
    /** clase que se encarga de controlar el movimiento de los enemigos*/
    class TimerShell implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            ImageLoader loader = ImageLoader.getInstance();
            shell_frame++;
              if(escenario==0 || escenario==1){
           

           
            if(shell_frame+23>24)
                shell_frame = 0;
            
           
            shell.setIcon(loader.getImage(23+shell_frame));
           
            Point p = shell.getLocation();

            p.x-=10;
        
            if(p.x<=LLEFT){
                p.x=LRIGHT;
            }
            
            shell.setLocation(p);
            
            
            Rectangle kill=shell.getBounds();
       Rectangle hola=shell2.getBounds();
       
      
      if(kill.intersects(hola)){
           accion=2;
          Musica.play(accion);
         vida-=100;
       puntajeRestar(vida);
      }  
              }      
      if(escenario==1){
          
         
          
            if(shell_frame+23>24){
                
                shell_frame = 0;
            }
                
            
            
            tronco1.setIcon(loader.getImage(23+shell_frame));
            
            Point k = tronco1.getLocation();

            k.x-=10;
        
            if(k.x<=LLEFT){
                k.x=LRIGHT;
            }
            
            tronco1.setLocation(k);
            
            
            Rectangle kill2=tronco1.getBounds();
       Rectangle hola2=shell2.getBounds();
       
       
      if(kill2.intersects(hola2)){
           accion=2;
          Musica.play(accion);
         vida-=100;
         puntajeRestar(vida);
      }  
          
                
          ImageIcon icon= loader.getImage(26);
          
      Point l= alacran1.getLocation();
      
      if(pasar==true && l.x> shell2.getX()){
          icon=loader.getImage(26);
          alacran1.setIcon(icon);
          l.x-=2;
      }
      
      if(pasar==true && l.x<shell2.getX()){
          icon=loader.getImage(27);
          alacran1.setIcon(icon);
          l.x+=2;
      }
      
      alacran1.setLocation(l);
      
      Rectangle player, esc;
      
      player= shell2.getBounds();
      esc=alacran1.getBounds();
      
      if(player.intersects(esc))
      {
          accion=3;
          Musica.play(accion);
          vida=0;
          puntajeRestar(vida);
          
      } 
      
      
      }      
          
      
      if(escenario==2 || escenario==3){
          ImageIcon icon= loader.getImage(26);
          
          Point l= alacran1.getLocation();
      if(pasar==true && l.x> shell2.getX()){
          icon=loader.getImage(26);
          alacran1.setIcon(icon);
          l.x-=2;
      }
      
      if(pasar==true && l.x<shell2.getX()){
          icon=loader.getImage(27);
          alacran1.setIcon(icon);
          l.x+=2;
      }
      
      alacran1.setLocation(l);
          
          
          
          Rectangle player, esc;
      
      player= shell2.getBounds();
      esc=alacran1.getBounds();
      
      if(player.intersects(esc))
      {accion=3;
          Musica.play(accion);
          vida=0;
          puntajeRestar(vida);
          
          
      } 
          
          
          
          
          
      }
      
      if(escenario==2){
          
          if(shell2.getY()==390 && ((shell2.getX()>206 && shell2.getX()<280) || (shell2.getX()>329 && shell2.getX()<389) || (shell2.getX()>428 && shell2.getX()<483) || (shell2.getX()>530 && shell2.getX()<574) )){
               accion=3;
          Musica.play(accion);
              vida2--;
          vidaRestar(vida2);
          pasar=false;
          mov=true;
           JOptionPane.showMessageDialog(null, "HAS PERDIDO UNA VIDA");
          BorraTodo(escenario);
          
          if(star== false){
              
              Borrar(estrella);
          }
          CargarFondo(escenario);
          vida=2000;
          puntajeRestar(vida);

          }
          
          Rectangle a= coco1.getBounds();
          Rectangle b= coco2.getBounds(); 
          Rectangle c= coco3.getBounds();
          Rectangle d= shell2.getBounds();
          
          if((a.intersects(d) || b.intersects(d) || c.intersects(d)) && laguna==true){
               accion=3;
          Musica.play(accion);
              vida2--;
          vidaRestar(vida2);
          pasar=false;
          mov=true;
           JOptionPane.showMessageDialog(null, "HAS PERDIDO UNA VIDA");
          BorraTodo(escenario);
          
          if(star== false){
              
              Borrar(estrella);
          }
          CargarFondo(escenario);
          vida=2000;
          puntajeRestar(vida);

              
          }
      }
      
      if(escenario==3){
          
          if(plataforma.getX()<150){
              
              Dplataforma=false;
          }
          
          if(plataforma.getX()>500 && juntos==false){
              
              Dplataforma=true;
              
          }
          
          
          if(Dplataforma==false && plataforma.getX()<603){
              
              plataforma.setBounds(plataforma.getX()+5, plataforma.getY(), plataforma.getWidth(), plataforma.getHeight());
             
              if(juntos==true){
                  
                  shell2.setBounds(shell2.getX()+5, shell2.getY(), shell2.getWidth(), shell2.getHeight());
                  
                  if(shell2.getX()>590){
                      juntos=false;
                     timercaida.start();
                             Borrar(plataforma);
                      
                  }
                  
                  
              }
          }
          
          if(Dplataforma==true){
              
              plataforma.setBounds(plataforma.getX()-5, plataforma.getY(), plataforma.getWidth(), plataforma.getHeight());
              if(juntos==true){
                  
                  shell2.setBounds(shell2.getX()-5, shell2.getY(), shell2.getWidth(), shell2.getHeight());
                  
                
              }
          }
          
          Rectangle a= plataforma.getBounds();
          Rectangle b= shell2.getBounds();
           
          if(a.intersects(b)){
              //timercaida.stop();
              
                  
                   shell2.setBounds(shell2.getX(),shell2.getY()-8 ,shell2.getWidth() ,shell2.getHeight());
                   salto=false;
             
              juntos=true;
          }
          
      }
      
      
      if(vida<=0){
          accion=3;
          Musica.play(accion);
          vida2--;
          vidaRestar(vida2);
          pasar=false;
          mov=true;
           JOptionPane.showMessageDialog(null, "HAS PERDIDO UNA VIDA");
          BorraTodo(escenario);
            if(star==false){
                
                Borrar(estrella);        
            } 
              
          CargarFondo(escenario);
          vida=2000;
          puntajeRestar(vida);
         
          
      }
      
      if(vida2==0){
            timerShell.stop();
              JOptionPane.showMessageDialog(null, "HAS PERDIDO!");
                hide();
                Menu menu= new Menu();
              menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
          }
            
        }
    }
    /** metodo que modifica la imagen del personaje*/
    public void personajeImagen()
    {
        ImageLoader loader = ImageLoader.getInstance();
            
            if(mov==true)
            {
                shell2_frame++;
            if(shell2_frame+ImageLoader.SHELL_HOR_BEGIN>ImageLoader.SHELL_HOR_END)
                shell2_frame = 0;
            shell2.setIcon(loader.getImage(ImageLoader.SHELL_HOR_BEGIN+shell2_frame));
            }
            else if(mov==false)
            {
                shell2_frame++;
                if(shell2_frame+13>22)
                shell2_frame = 0;
                shell2.setIcon(loader.getImage(13+shell2_frame));
            }
            
    }
/** clase que actua sobre las acciones del personaje principal*/ 
    class TimerShell2 implements ActionListener{
   
        public void actionPerformed(ActionEvent e) {
            ImageLoader loader = ImageLoader.getInstance();
            
            
            shell2_frame++;
            if(shell2_frame+ImageLoader.SHELL_HOR_BEGIN>ImageLoader.SHELL_HOR_END)
                shell2_frame = 0;
            shell2.setIcon(loader.getImage(ImageLoader.SHELL_HOR_BEGIN+shell2_frame));
            
             personajeImagen();
                    
                    
            if(shell2.getY()==390)
            {
                pasar=false;
            }
            else if(shell2.getY()==546)
            {
                pasar=true;
            }
          
          if(escenario==0){
          if( ((shell2.getX()>118 && shell2.getX()<=184) && shell2.getY()==390) || ((shell2.getX()>341 && shell2.getX()<=436) && shell2.getY()==390) || ((shell2.getX()>=594 && shell2.getX()<=668) && shell2.getY()==390) ){
           
              timercaida.start();
              caida=true;
          }
          }
           
          
           if(escenario==1){
          if( ((shell2.getX()>=558 && shell2.getX()<=673) && shell2.getY()==390) ){
           
              timercaida.start();
              caida=true;
          }
          }
          
          if(escenario==2){
          if( ((shell2.getX()>=53 && shell2.getX()<=108) && shell2.getY()==390)  || ((shell2.getX()>=676 && shell2.getX()<=735) && shell2.getY()==390)  ){
           
              timercaida.start();
              caida=true;
          }
          }
          if(escenario==3){
              if( ((shell2.getX()>=173 && shell2.getX()<=603) && shell2.getY()==390) ){
           
              timercaida.start();
              caida=true;
          }
              
          }
          
           Rectangle player, estre;
          player=shell2.getBounds();
          estre=estrella.getBounds();
          
          if(player.intersects(estre))
          {
                    accion=4;
                 Musica.play(accion);
              vida+=250;
              puntajeRestar(vida);
              Borrar(estrella);
              star=true;
          }
          
          if(shell2.getX()>=LRIGHT && escenario<3){
              
              escenario++;timerShell2.stop();
            MainFrame.this.getContentPane().remove(shell2);
          //  MainFrame.this.getContentPane().remove(shell);
            MainFrame.this.getContentPane().remove(background);
              
            if(star==false){
                
       Borrar(estrella);        
            }
            
            CargarFondo(escenario);
            
                       }
          
          if(shell2.getX()<=LLEFT && escenario>0){
              
              escenario--;timerShell2.stop();
            MainFrame.this.getContentPane().remove(shell2);
           // MainFrame.this.getContentPane().remove(shell);
            MainFrame.this.getContentPane().remove(background);
           
           if(star==false){
                
       Borrar(estrella);        
            } 
              CargarFondo(escenario);
              
                       }
          
          
          if(escenario==3 && shell2.getX()>LRIGHT){
              
                hide();
                
                   fondo= new ImageIcon();
         URL url = this.getClass().getResource("fondo/fondoPitfall.png");
         fondo= new  ImageIcon(url);
         
        getImage();
        
        background= new JLabel(fondo);
        background.setBounds(0, 0, 613, 334);
                
                
                    JFrame frame= new JFrame();
                 
                    frame.setTitle("Szokitfall");
                   frame.setLayout(null);
                   frame.setSize(500, 250);
                   frame.setResizable(false);
                   frame.setLocationRelativeTo(null);
                   
                   
                   
                   JLabel titulo= new JLabel("\tVICTORIA!!!");
                   Font fuente = new Font("Bounds", 3, 15);
                   titulo.setFont(fuente);
                   titulo.setBounds(122, 2, 100, 20);
                   titulo.setForeground(Color.white);
                   titulo.setVisible(true);
                   
                  JLabel nombre= new JLabel("\nJugador: "+Menu.nombre);
                   nombre.setFont(fuente);
                   nombre.setBounds(10, 10, 500, 50);
                   nombre.setForeground(Color.white);
                   nombre.setVisible(true);
                   
                   JLabel texto= new JLabel("\n\tEstas son tus estadisticas: ");
                   texto.setFont(fuente);
                   texto.setBounds(10, 35, 500, 50);
                   texto.setForeground(Color.white);
                   texto.setVisible(true);
                   
                    JLabel texto2= new JLabel("\n\tPuntos: "+String.valueOf(vida));
                   texto2.setFont(fuente);
                   texto2.setBounds(10, 50, 500, 50);
                   texto2.setForeground(Color.white);
                   texto2.setVisible(true);
                   
                    JLabel texto3= new JLabel("\n\tVidas restantes: "+String.valueOf(vida2));
                   texto3.setFont(fuente);
                   texto3.setBounds(10, 65, 500, 50);
                   texto3.setForeground(Color.white);
                   texto3.setVisible(true);
                   
                   
                   JLabel texto4= new JLabel("\n\tTiempo restante: "+String.valueOf(minutos)+":"+String.valueOf(segundos));
                   texto4.setFont(fuente);
                   texto4.setBounds(10, 80, 500, 50);
                   texto4.setForeground(Color.white);
                   texto4.setVisible(true);
                   
                   JButton vJugar= new JButton("VOLVER A JUGAR");
                   vJugar.setBounds(150, 150, 150, 50);
                   vJugar.setVisible(true);
                   
                   calcularPuntos();
                   Score=new JLabel("Puntaje Acumulado: "+String.valueOf(score));
                   Score.setFont(fuente);
                   Score.setBounds(10, 95, 500, 50);
                   Score.setForeground(Color.white);
                   Score.setVisible(true);
                   
              /*
                try {
                    Top10 top= new Top10(score, Menu.nombre);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
               */
                   
                   vJugar.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent e) {
                        hide();
                        Menu menu= new Menu();
                        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    }
                });
                   
                   
                   frame.add(titulo);
                   frame.add(nombre);
                   frame.add(texto);
                   frame.add(texto2);
                   frame.add(texto3);
                   frame.add(texto4);
                   frame.add(Score);
                   frame.add(vJugar);
                          frame.add(background);
                   frame.setVisible(true);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                   
          }
          
          
          
          timerShell2.stop();
        }  
    }
    /** metodo que retorna la imagen de fondo*/
       public ImageIcon getImage(){
     
  
        return fondo;
    }
    /** clase que modifica el estado de las tortugas de la laguna en el escenario 3*/
    class timercambiar implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            ImageLoader img= ImageLoader.getInstance();
            ImageIcon icon;
            if(escenario==2){
                
                  cambiari++;
            if(cambiari+28>29)
                cambiari = 0;
            
            
            if(cambiari==0){
                
                laguna=false;
            }
            if(cambiari==1){
                
                laguna=true;
            }
                System.out.println(""+laguna);
            coco1.setIcon(img.getImage(28+cambiari));
            coco2.setIcon(img.getImage(28+cambiari));
            coco3.setIcon(img.getImage(28+cambiari));
            }
          
            
        }
        
        
    }
    /** clase que limita la caida del personaje*/
    class timercaida implements ActionListener{
       
        public void actionPerformed(ActionEvent e) {
                ImageLoader loader = ImageLoader.getInstance();
        
        
                shell2.setBounds(shell2.getX(), shell2.getY()+1, shell2.getWidth(), shell2.getHeight());
              
                if(shell2.getY()>545){
                    
                    timercaida.stop();
                    caida=false;
                }
                
                if(escenario==3){
                    
                    if(shell2.getY()==390){
                        
                        timercaida.stop();
                        caida=false;
                        
                    }
                }
        
    }
    }
    /** clase que se encarga de disminuir el tiempo*/
    class timerTiempo implements ActionListener
    {

        public void actionPerformed(ActionEvent e) {
            
            segundos-=1;
                 
            if(segundos==-1)
            {
                minutos--;
                segundos=59;
              
            }
            
            if(segundos>=10)
            {
                tiempoM.setText(String.valueOf(minutos)+":"+String.valueOf(segundos));
            }
            else if(segundos<10)
            {
                tiempoM.setText(String.valueOf(minutos)+":0"+String.valueOf(segundos));
            }
            
            if(minutos==0 && segundos==0)
            {
                 accion=3;
                Musica.play(accion);
                vida=0;
                puntajeRestar(vida);
                JOptionPane.showMessageDialog(null, "HAS PERDIDO!");
                hide();
                Menu menu= new Menu();
                menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        }
        
    }
    /** metodo que carga la plataforma*/
    public void cargarPlataforma(){
        
        if(escenario==3){
            ImageLoader img= ImageLoader.getInstance();
            ImageIcon icon= img.getImage(25);
            
            
            
            plataforma= new JLabel(icon);
            plataforma.setBounds(350, 420, icon.getIconWidth(), icon.getIconHeight());
            MainFrame.this.getContentPane().add(plataforma);
        }
        
        
    }
    
    /** metodo que calcula el puntaje total de la partida*/
    public void calcularPuntos()
    {
        score=(vida2*1000)+(minutos*60+segundos)+vida;
    }
    
    
    /** que se encarga de los enemigos*/
    class Enemigos{
    
    
    Enemigos(){
        
        
        iniciar();
    }    
        
    /** que se encarga de inicializar los enemigos*/ 
    public void iniciar(){
        
        if(escenario==0){
            
            if(mov==false && partida==false){
                
               Borrar(tronco1);
               Borrar(alacran1);
               Borrar(shell);
            }
            
            
          ImageLoader loader= ImageLoader.getInstance();
        ImageIcon icon = loader.getImage(23);
        shell = new JLabel(icon);
        
        shell.setBounds(700, 425, icon.getIconWidth(), icon.getIconHeight());
        MainFrame.this.getContentPane().add(shell);

       partida=false;
        }
        
        if(escenario==1){
            if(mov==false && partida== false){
               
           Borrar(coco2);
           Borrar(alacran1);
           Borrar(coco3);
           Borrar(coco1);
            }
            
            if(mov==true && partida==false)
            {
                 Borrar(shell);
            }
             
            ImageLoader loader= ImageLoader.getInstance();
            
        ImageIcon icon = loader.getImage(23);
                
        shell = new JLabel(icon);
        
        shell.setBounds(700, 425, icon.getIconWidth(), icon.getIconHeight());
        MainFrame.this.getContentPane().add(shell);
            
  
        tronco1= new JLabel(icon);
        tronco1.setBounds(600, 425, icon.getIconWidth(), icon.getIconHeight());
        MainFrame.this.getContentPane().add(tronco1);
         icon=loader.getImage(26);
        alacran1= new JLabel(icon);
        alacran1.setBounds(600, 576, icon.getIconWidth(), icon.getIconHeight());
        MainFrame.this.getContentPane().add(alacran1);
        
        partida=false;
        
        }
        
        if(escenario==2){
           if(mov==true && partida==false){
            
           Borrar(shell);
           Borrar(alacran1);
           Borrar(tronco1);
           }
           
           if(mov== false && partida==false){
               Borrar(alacran1);
               Borrar(plataforma);
               
           }
            
            ImageLoader loader= ImageLoader.getInstance();
        ImageIcon icon = loader.getImage(28);
            coco1= new JLabel(icon);
            coco1.setBounds(300, 425, icon.getIconWidth(), icon.getIconHeight());
            MainFrame.this.getContentPane().add(coco1);
            
            coco2= new JLabel(icon);
            coco2.setBounds(400, 425, icon.getIconWidth(), icon.getIconHeight());
            MainFrame.this.getContentPane().add(coco2);
            
            coco3= new JLabel(icon);
            coco3.setBounds(500, 425, icon.getIconWidth(), icon.getIconHeight());
             MainFrame.this.getContentPane().add(coco3);
              partida=false;
              icon=loader.getImage(26);
            alacran1= new JLabel(icon);
            alacran1.setBounds(400, 576, icon.getIconWidth(), icon.getIconHeight());
            MainFrame.this.getContentPane().add(alacran1);
            
        }
        
        if(escenario==3){
            if(mov==true && partida==false)
            {
            Borrar(coco1);
            Borrar(coco2);
            Borrar(coco3);
            }
           partida=false;  
        }
        
    }
}
    
} 
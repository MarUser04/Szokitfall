package animacionesbasicas;

import animacionesbasicas.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/** clase que sirve para crear el menu del juego*/
public class Menu extends JFrame{
    
    private JButton jugar,cargarPartida, mejores, ayuda, creditos;
   private ImageIcon fondo;
   private JLabel background;
   public static String nombre;
   
   CargaDatos cd;
   /** constructor que se encarga de crear los botones del menu*/
    public Menu()
    {
       int accion=0;
       Musica.play(accion);
       
        setTitle("Szokitfall");
        setLayout(null);
        setSize(400, 360);
        setResizable(false);
        setLocationRelativeTo(null);
        
        
            
        fondo= new ImageIcon();
         URL url = this.getClass().getResource("fondo/fondoPitfall.png");
         fondo= new  ImageIcon(url);
         
        getImage();
        
        background= new JLabel(fondo);
        background.setBounds(0, 0, 613, 334);
        
        
        jugar= new JButton("JUGAR");
        jugar.setBounds(150, 40, 100, 40);
        jugar.setVisible(true);
        
        jugar.addActionListener(new ActionListener() {

      
            public void actionPerformed(ActionEvent e) {
                
                hide();
                   nombre=JOptionPane.showInputDialog(null,"INGRESE SU NOMBRE: ");
                 MainFrame frame = new MainFrame();

                 frame.setVisible(true);
                 
            }
        });
        
        cargarPartida= new JButton("CARGAR PARTIDA");
        cargarPartida.setBounds(125, 100, 150, 40);
        cargarPartida.setVisible(true);
        
        
            cargarPartida.addActionListener(new ActionListener() {
          
            public void actionPerformed(ActionEvent e) {
                
                cd= new CargaDatos();
                
                try {
                    cd.leer();
                       hide();
                     MainFrame frame = new MainFrame(cd);

                 frame.setVisible(true);
                    
                } catch (IOException ex) {
                    Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            }
        });
        
        
        mejores= new JButton("MEJORES 10 PUNTAJES");
        mejores.setBounds(110, 160, 180, 40);
        mejores.setVisible(true);
        
        ayuda= new JButton("AYUDA");
        ayuda.setBounds(150, 220, 100, 40);
        ayuda.setVisible(true);
        
        ayuda.addActionListener(new ActionListener() {
     
            public void actionPerformed(ActionEvent e) {
            
                hide();
                    JFrame frame= new JFrame();
                 
                    frame.setTitle("Szokitfall");
                   frame.setLayout(null);
                   frame.setSize(500, 250);
                   frame.setResizable(false);
                   frame.setLocationRelativeTo(null);
                   
                   JButton atras= new JButton("ATRAS");
                   atras.setBounds(150, 150, 150, 50);
                   atras.setVisible(true);
                   
                   
                          
                   JLabel titulo= new JLabel("\tAYUDA");
                   Font fuente = new Font("Bounds", 3, 15);
                   titulo.setFont(fuente);
                   titulo.setBounds(122, 2, 100, 20);
                   titulo.setForeground(Color.white);
                   titulo.setVisible(true);
                   
                   JLabel texto= new JLabel("\n\tBIENVENIDOS A SZOKITFALL");
                   texto.setFont(fuente);
                   texto.setBounds(10, 10, 500, 50);
                   texto.setForeground(Color.white);
                   texto.setVisible(true);
                   
                  JLabel texto2= new JLabel("\n\tLa mecanica del juego es muy sencilla");
                   texto2.setFont(fuente);
                   texto2.setBounds(10, 35, 500, 50);
                   texto2.setForeground(Color.white);
                   texto2.setVisible(true);
                   
                    JLabel texto3= new JLabel("\n\tPara mover al personaje utilizas las teclas direccionales.");
                   texto3.setFont(fuente);
                   texto3.setBounds(10, 50, 500, 50);
                   texto3.setForeground(Color.white);
                   texto3.setVisible(true);
                   
                   JLabel texto4= new JLabel("\n\tPara saltar presiona la tecla espaciadora.");
                   texto4.setFont(fuente);
                   texto4.setBounds(10, 65, 500, 50);
                   texto4.setForeground(Color.white);
                   texto4.setVisible(true);
                   
                   JLabel texto5= new JLabel("\n\tSi quieres guardar partida presiona la tecla G");
                   texto5.setFont(fuente);
                   texto5.setBounds(10, 80, 500, 50);
                   texto5.setForeground(Color.white);
                   texto5.setVisible(true);
                   
                   JLabel texto6= new JLabel("\n\tDisfruta el juego!");
                   texto6.setFont(fuente);
                   texto6.setBounds(10, 95, 500, 50);
                   texto6.setForeground(Color.white);
                   texto6.setVisible(true);
                   
                   
                   
                   frame.add(titulo);
                   frame.add(texto);
                    frame.add(texto2);
                    frame.add(texto3);
                    frame.add(texto4);
                   frame.add(texto5);
                    frame.add(texto6);
                   frame.add(atras);
                   frame.add(background);
                   frame.setVisible(true);
                   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                   
                   atras.addActionListener(new ActionListener() {
                       
                       public void actionPerformed(ActionEvent e) {
                          hide();
                           Menu menu= new Menu();
                           menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                       }
                   });
            }
        });
        
        creditos= new JButton("CREDITOS");
        creditos.setBounds(150, 0, 100, 40);
        creditos.setVisible(true);
        
        creditos.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                
                hide();
                   JFrame frame= new JFrame();
                 
                    frame.setTitle("Szokitfall");
                   frame.setLayout(null);
                   frame.setSize(500, 250);
                   frame.setResizable(false);
                   frame.setLocationRelativeTo(null);
                
                
                JButton atras= new JButton("ATRAS");
                   atras.setBounds(150, 150, 150, 50);
                   atras.setVisible(true);
                   
                   
                          
                   JLabel titulo= new JLabel("\tCREDITOS");
                   Font fuente = new Font("Bounds", 3, 15);
                   titulo.setFont(fuente);
                   titulo.setBounds(122, 2, 100, 20);
                   titulo.setForeground(Color.white);
                   titulo.setVisible(true);
                   
                   JLabel texto= new JLabel("\n\t----Juan Manuel Gómez Szoke----");
                   texto.setFont(fuente);
                   texto.setBounds(10, 10, 500, 50);
                   texto.setForeground(Color.white);
                   texto.setVisible(true);
                   
                  JLabel texto2= new JLabel("\n\t----Marco José Useche Rivera----");
                   texto2.setFont(fuente);
                   texto2.setBounds(10, 35, 500, 50);
                   texto2.setForeground(Color.white);
                   texto2.setVisible(true);
                   
                     frame.add(titulo);
                   frame.add(texto);
                    frame.add(texto2);
                        frame.add(atras);
                   frame.add(background);
                   frame.setVisible(true);
                 
               
                         atras.addActionListener(new ActionListener() {
                       
                       public void actionPerformed(ActionEvent e) {
                        
                           hide();
                           Menu menu= new Menu();
                          
                           menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                       }
                   });
              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                   
            }
            
            
        });
    
        
        setVisible(true);
        add(jugar);  creditos.setBounds(150, 300, 100, 40);
        add(cargarPartida);
        add(mejores);
        add(ayuda);
        add(creditos);
        this.getContentPane().add(background);
    }
    /** retorna el fondo del menu*/
     public   ImageIcon getImage(){
     
  
        return fondo;
    }
    
    
}

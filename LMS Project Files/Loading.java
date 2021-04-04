package librarymanagementsystem;

/**
 *
 * @author Mukul Mahajan
 */

/* IMPORTING CLASSES AND PACKAGES FOR THIS CLASS*/
import javax.swing.*;
import java.awt.*;


/* LOADING CLASS : THIS IS A CLASS WHICH REPRESENTS A FRAME IN WHICH LOADING OF APPLICATION
OCCURS . IT ALSO EXTENDS JFrame and implements Runnable */
public class Loading extends JFrame implements Runnable{
    
    /* INSTANCE MEMBER VARIABLES : ATTRIBUTES OF THIS CLASS*/
    JPanel p1,progressBar;
    JLabel title,percent,version;
   
    /* CONSTRUCTOR OF LOADING CLASS*/
    public Loading(){
    
        /* PROPERTIES OF LOADING FRAME : MEMBER METHODS INVOKED FOR LOADING FRAME */
        setSize(600,350);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        
        p1  = new JPanel();
        p1.setBounds(0, 0, 600,700);
        p1.setLayout(null);
        p1.setBackground(new Color(100,100,200));
           
        /*SETTING TITLE OF LOADING WINDOW */
        title = new JLabel("LMS @SISTec");
        title.setFont(new Font("Verdana",Font.BOLD,60));
        title.setBounds(60,100,500,60);
        title.setForeground(Color.white);
        p1.add(title);
        
        version = new JLabel("v1.0.0");
        version.setFont(new Font("Verdana",Font.PLAIN,20));
        version.setForeground(Color.black);
        version.setBounds(250,170,200,20);
        p1.add(version);
      
        progressBar = new JPanel();
        progressBar.setBackground(Color.yellow);
        progressBar.setBounds(145,200,0,15);
        p1.add(progressBar);
        
        percent = new JLabel("");
        percent.setBounds(550,320,50,20);
        percent.setForeground(Color.white);
        p1.add(percent);

        add(p1);
        setVisible(true);
    }
   
    /* OVERRIDDEN ABSTRACT METHOD run() : SINCE LOADING CLASS HAS IMPLEMENTED Runnable() interface
    WE HAVE TO OVERRIDE THE ABSTRACT METHOD*/
    public void run(){
        
        int i;
        
       /* LOOP IMPLEMENTING LOGIC FOR PROGRESS_BAR */
        for(i=0; i<=100 ; i++)
        {
           percent.setText(i+"%");
           progressBar.setBounds(145,250,i+2*i,15);     
            
            try{
                Thread.sleep(40);
            }catch(Exception e){
                
            }
        }      
        this.setVisible(false); 
        new Home().setVisible(true); 
    }
    
         /* ** MAIN METHOD ***/
    public static void main(String args[]){
        Loading l1 = new Loading();
       Thread t = new Thread(l1);
       t.start();          
       new Home().setVisible(true);
    }
    
}

/* *************************** END OF LOADING CLASS ****************************/
package librarymanagementsystem;

/**
 *
 * @author Mukul Mahajan
 */

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.border.*;


/* LOGIN CLASS : THIS IS A CLASS FOR LOGIN FRAME. USER CAN LOGIN USING THE UID AND PWD HERE.*/
public class Login extends JFrame  implements ActionListener{

    private  JPanel p1,p2;
    private JLabel  userName, password, l1;
    private JTextField t1;
    private JPasswordField t2;
    private JButton login, signUp;
    private ImageIcon icon,logo;
   

    /* CONSTRUCTOR OF LOGIN FRAME : ALL THE CODING OF THE FRAME IS DONE IN CONSTRUCTOR OF RESPECTIVE CLASS */ 
    public Login() {
     
        // FRAME PROPERTIES
        setTitle("User Login @LMS SISTec");
        setSize(500, 400);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        icon  = new ImageIcon("img/SISTec_Logo.png"); //SISTec_Logo.png
        setIconImage(icon.getImage());
        
        // LOGO ON THE FRAME
        logo = new ImageIcon("img/logo.jpeg");
        Image i1 = logo.getImage().getScaledInstance(299,95,NORMAL);
        ImageIcon logo1 = new ImageIcon(i1);
        JLabel pic = new JLabel(logo1);
        pic.setBounds(0,0,500,100);
                  
        // CONTENT PANEL 1
        p2  = new JPanel();
        p2.setLayout(null);
        p2.setBounds(0,0,500,400);
        p2.setBackground(Color.WHITE);
        p2.add(pic);
        add(p2);
 
        // CONTENT PANEL 2
        p1 = new JPanel();
        p1.setBounds(50, 110, 390, 180);
        p1.setLayout(null);        
        Border panelBorder = BorderFactory.createBevelBorder(1, Color.GRAY, Color.LIGHT_GRAY);
        p1.setBorder(panelBorder);
        p2.add(p1);

        userName = new JLabel("USERNAME");
        userName.setBounds(70, 50, 100, 20);
        userName.setFont(new Font("Arial", Font.BOLD, 15));
        password = new JLabel("PASSWORD");
        password.setBounds(70, 90, 100, 20);
        password.setFont(new Font("Arial", Font.BOLD, 15));
        p1.add(userName);
        p1.add(password);

        t1 = new JTextField();
        t2 = new JPasswordField();
        t1.setBounds(210, 48, 100, 20);
        t2.setBounds(210, 88, 100, 20);
        t1.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.lightGray));
        t2.setBorder(BorderFactory.createBevelBorder(1, Color.gray, Color.lightGray));
        p1.add(t1);
        p1.add(t2);

        login = new JButton("Sign In");
        login.setBounds(150, 130, 90, 25);
        login.setFocusable(false);
        p1.add(login);

        l1 = new JLabel("Don't have an account, Register here");
        l1.setFont(new Font("Tahoma", Font.ITALIC, 13));
        l1.setBounds(80, 310, 250, 20);
        l1.setForeground(Color.red);
        p2.add(l1);

        signUp = new JButton("Register");
        signUp.setBounds(330, 310, 90, 23);
        signUp.setFocusable(false);
        p2.add(signUp);
        
        login.addActionListener(this);
        signUp.addActionListener(this);
    }

    
    /* EVENT HANDLER CODE  : SINCE, WE IMPLEMENTED ActionListener INTERFACE, WE NEED TO IMPLEMENT ITS ABSTRACT METHOD actionPerformed(ActionEvent e)*/
    public void actionPerformed(ActionEvent e) {
       
       /* IF CLICKED BUTTON IS LOGIN*/
        if(e.getSource() == login){
            
           if(t1.getText().isEmpty() || t2.getText().isEmpty())
           {
               JOptionPane.showMessageDialog(this,"Please fill all fields","Alert",2);
           }
           else
           {
              String sql = "select *from account where UID=? and PWD=?";
                            
            try{
                connectionClass conn = new connectionClass();
                PreparedStatement pst = conn.c.prepareStatement(sql);
                
                pst.setString(1,t1.getText());
                pst.setString(2,t2.getText());
                
                ResultSet rs = pst.executeQuery();
               
                
                if(rs.next()){               
                    rs.close();
                    pst.close();
                    this.setVisible(false);
                    Thread t = new Thread(new Loading());
                    t.start();                     
                }                 
                else{
                    JOptionPane.showMessageDialog(this,"Invalid Credentials","Alert",0);
                }             
                       
             }  catch(Exception exp){                
                 System.out.print(exp);
               } 
           }                     
        }
        /* IF CLICKED BUTTON IS SIGNUP*/
        if(e.getSource() == signUp){
            
            new RegisterNow().setVisible(true);
            setVisible(false);          
        }      
    }

               /* MAIN METHOD*/
    public static void main(String args[]) {
        new Login().setVisible(true);
   }
}
/**************************** END OF LOGIN CLASS ********************************/
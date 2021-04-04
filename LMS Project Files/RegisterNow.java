package librarymanagementsystem;

/**
 *
 * @author Mukul Mahajan
 */


/* IMPORTING PACKAGES AND CLASSES FOR THIS CLASS*/
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import javax.swing.*;


/* REGISTERNOW CLASS : IT IS THE CLASS WHICH EXTENDS A FRAME, HERE A NEW USER FOR THIS APPLICATION CAN BE REGISTERED */
/* IT EXTENDS JFrame and implements ActionListener and KeyListener*/

public class RegisterNow extends JFrame implements ActionListener,KeyListener{
     
    /* INSTANCE MEMBER VARIABLES : THESE ARE THE GUI COMPONENTS FOR THI FRAME*/
    Font labels = new Font("Verdana",Font.PLAIN,15);
    JPanel p1,p2;
    JLabel l1,l2,l3,l4,l5,l6,l7;
    private JTextField name,email,address,age,mobile,username;
    private JPasswordField pwd;
    ImageIcon icon;
    JCheckBox check ;
    JButton confirm,back;
    
    
    /* CONSTRUCTOR OF RegisterNow Class*/
    public RegisterNow(){
        
        /* PROPERTIES OF THIS FRAME*/
        setSize(800,600);
        setTitle("Register New User Account");
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        icon = new ImageIcon("img/SISTec_Logo.png");
        setIconImage(icon.getImage());
        
        p2 = new JPanel();
        p2.setLayout(null);
        p2.setBackground(Color.WHITE);
        p2.setBounds(0,0,800,600);
        add(p2);
        
        ImageIcon logo = new ImageIcon("img/logo.jpeg");
        Image img = logo.getImage().getScaledInstance(299, 95,Image.SCALE_DEFAULT);
        logo = new ImageIcon(img);
        JLabel pic = new JLabel(logo);
        pic.setLayout(null);
        pic.setBounds(0, 0, 800, 100);
        p2.add(pic);
        
       
        p1 = new JPanel();
        p1.setBounds(90,120,600,400);
        p1.setLayout(null);
        p1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 1), "New User Registeration"));
        p2.add(p1);
        
        l1  = new JLabel("Name : ");
        l1.setBounds(50,50,70,20);
        l1.setFont(labels);
        p1.add(l1);
        
        l2 = new JLabel("Email ID : ");
        l2.setBounds(50,80,100,20);
        l2.setFont(labels);
        p1.add(l2);
        
        l3 = new JLabel("Age : ");
        l3.setBounds(50,110,70,20);
        l3.setFont(labels);
        p1.add(l3);
        
        l4 = new JLabel("Address : ");
        l4.setBounds(50,140,100,20);
        l4.setFont(labels);
        p1.add(l4);
        
        l5 = new JLabel("Mobile : ");
        l5.setBounds(50,170,100,20);
        l5.setFont(labels);
        p1.add(l5);
        
        l6 = new JLabel("Create Username : ");
        l6.setBounds(50,200,160,20);
        l6.setFont(labels);
        p1.add(l6);
        
        l7 = new JLabel("Create Password : ");
        l7.setBounds(50,230,160,20);
        l7.setFont(labels);
        p1.add(l7);
        
        name = new JTextField(null);
        name.setBounds(210,50,150,20);
        p1.add(name);
        
        email  = new JTextField(null);
        email.setText(null);
        email.setBounds(210,80,150,20);
        p1.add(email);
        
        age = new JTextField(null);
        age.addKeyListener(this);
        age.setBounds(210,110,60,20);
        p1.add(age);
        
        address = new JTextField(null);
        address.setBounds(210,140,150,20);
        p1.add(address);
        
        mobile = new JTextField(null);
        mobile.setText(null);
        mobile.addKeyListener(this);
        mobile.setBounds(210,170,150,20);
        p1.add(mobile);
        
        username = new JTextField(null);
        username.setBounds(210,200,150,20);
        p1.add(username);
        
        pwd = new JPasswordField(null);
        pwd.setBounds(210,230,150,20);
        p1.add(pwd);
        
        check = new JCheckBox("I confirm that all the information given by me is purely authentic",false);
        check.setBounds(50,280,500,30);
        check.setFocusPainted(false);
        p1.add(check);
        
        back = new JButton("Back");
        back.setBounds(340, 330, 100, 25);
        back.setFocusPainted(false);
        p1.add(back);
        back.addActionListener(this);
        
        confirm = new JButton("Confirm & Submit");
        confirm.setBounds(100,330,160,25);
        confirm.setFocusPainted(false);
        p1.add(confirm);
        confirm.addActionListener(this);
    }
 
    
    /* OVERRIDDEN actionPerformed() METHOD */
    public void actionPerformed(ActionEvent e){
            
        if(e.getSource() == back){
            
            this.setVisible(false);
            new Login().setVisible(true);
        }
        else
        {
            if(check.isSelected() == false)
            {
                JOptionPane.showMessageDialog(this, "Please tick the check Box to confirm","Alert",0);
            }
            else
            {
                if(name.getText().isEmpty() || age.getText().isEmpty() || email.getText().isEmpty() || address.getText().isEmpty() || mobile.getText().isEmpty() || username.getText().isEmpty() || pwd.getText().isEmpty())
                {
                   JOptionPane.showMessageDialog(this,"Please fill all fields","Alert",0);                  
                }
                else
                {
                    String sql = "insert into account values(?,?,?,?,?,?)";
                    
                    try{
                        connectionClass con = new connectionClass();
                        PreparedStatement pt = con.c.prepareStatement(sql);
                        
                        pt.setString(1, name.getText());
                        pt.setString(2, email.getText());
                        pt.setString(3, address.getText());
                        pt.setString(4, mobile.getText());
                        pt.setString(5, username.getText());
                        pt.setString(6, pwd.getText());
                        
                        if(pt.executeUpdate() > 0)  
                        {
                            JOptionPane.showMessageDialog(this,"SuccessFully registered","Message",1);
                            name.setText(null);
                            age.setText(null);
                            email.setText(null);
                            address.setText(null);
                            mobile.setText(null);
                            username.setText(null);
                            pwd.setText(null); 
                            check.setSelected(false);   
                        }
                    }catch(Exception exp){
                        JOptionPane.showMessageDialog(this,"This Username is taken, Please add another one","Alert",0);
                    }                                                  
                }                  
            }            
        }            
    }
    
   /***** MAIN METHOD *****/
    public static void main(String ar[]){           
        new RegisterNow().setVisible(true);      
    }                                        

   
    /* SINCE THIS CLASS IMPLEMENTS KEYLISTENER, ABSTRACT METHODS NEEDS TO BE IMPLEMENTED*/
    public void keyTyped(KeyEvent e) {
       /* throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.*/
        char c  = e.getKeyChar();
        if(Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE)
        {            
        }
        else
        {
            e.consume();
        }
    }
    
    public void keyPressed(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.      
    }
}

/*************************** END OF REGISTERNOW CLASS ******************************/
package librarymanagementsystem;
/**
 *
 * @author Mukul Mahajan
 */

import java.sql.*;
import javax.swing.JOptionPane;

/* CONNECTIONCLASS : IT IS THE CLASS FOR SETTING UP THE CONNECTION BETWEEN DATABASE (SQLITE) AND APPLICATION*/
public class connectionClass {

    Connection c;   // REFERENCE OF Connection INTERFACE 
    Statement s;    // REFERENCE OF Statement INTERFACE
     
    
    /* CONSTRUCTOR OF connectionClass CLASS : ALL THE CODING OF THIS CLASS IS DONE IN THE CONSTRUCTOR*/
    public connectionClass(){
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:db\\project.db");
            s = c.createStatement();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e,"", 0);
    }
    }
    
                /* MAIN METHOD*/
    public static void main(String args[]){
        new connectionClass();
    }
}

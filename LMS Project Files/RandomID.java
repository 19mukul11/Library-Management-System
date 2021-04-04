package librarymanagementsystem;

/**
 *
 * @author Mukul Mahajan
 */

/* CLASSES AND PACKAGES IMPORTED FOR THIS CLASS*/
import java.util.Random;

/* RANDONID CLASS : THIS CLASS IS MEANT FOR GENERATING RANDOM NUMBERS*/
public class RandomID {
    
    String id;      // MEMBER VARIABLE STRING
    
    /*CONSTRUCTOR OF RANDOMID CLASS*/
    RandomID(){
    }
   
    /* getID() METHOD : THIS METHOD RETURNS A RANDOM MULTIPLE DIGIT NUMBER IN THE FORM OF STRING */
    public String getID(){
        Random rd = new Random();
        id = ""+rd.nextInt(100000);
        return(id);
    } 
    
    
    /* MAIN METHOD */
    public static void main(String[] args) {
        RandomID obj = new RandomID();
        String ID = obj.getID();
    }
}

/********************************* END OF RANDOMID CLASS *****************************/
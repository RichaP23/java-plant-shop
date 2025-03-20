
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is the driver class of the program
 * It calls the function to read file data and save in the array list.
 * It calls the menu function to display menu.
 * @author Shantanu Kulkarni
 * Student ID : 12222483 
 */
public class Project {
    static ArrayList<Plant> plants=new ArrayList<>();
    public static void main(String[] args) throws IOException{
        //reading from the file first
        FileHandling fileHandling=new FileHandling();
        fileHandling.readFromFile();
        //calling menu to display the menu and take user input .
        Menu menu=new Menu();
        menu.displayMenu();
    }
    
}

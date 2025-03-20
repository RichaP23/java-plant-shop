import java.io.IOException;
import java.util.Scanner;
/**
 * This function displays the menu option to the user
 * asks the user for the input
 * calls the necessary function from PlantShop class to render the user's request
 * @author Shantanu Kulkarni
 * Student ID : 12222483 
 */
public class Menu {
    /**
     * displays menu options to the user and takes input and applies functions.
     * @throws IOException if file is not found while saving changes on exit.
     */
    public void displayMenu() throws IOException{
        final int EXIT=0;
        PlantShop plantShop =new PlantShop();
        FileHandling files=new FileHandling();
        Scanner scanner=new Scanner(System.in);
        int choice;
        do{
            printMenu();
            choice=Integer.parseInt(scanner.nextLine());
            switch(choice){
                case 1:
                    plantShop.addPlantsToInventory();
                    break;
                case 2:
                    plantShop.displayAllPlants();
                    break;
                case 3:
                    plantShop.sellPlants();
                    break;
                case 4:
                    plantShop.sortPlantsByPrice();
                    break;
                case 5:
                    plantShop.displayAllPlants();
                    break;
                case 6:
                    //for searching the plant.
                    System.out.print("Enter the plant name");
                    String name=scanner.nextLine();
                    Plant plant=plantShop.searchPlantByName(name);
                    if(plant==null){
                        System.out.println("No such plant exists in the invenetory.");
                    }else{
                        plant.printPlantObject();
                    }
                    break;
                case 0:
                    System.out.println("Saving new changes to the file");
                    files.saveToFile(PlantShop.startIndex);
                    System.out.println("Thankyou for using the application.");
                    System.out.println("Program written by 12222483 ");
                    break;
                default:
                    System.out.println("Please enter a correct choice(1-6).");
                    
                    break;
                   
            }
        }while(choice!=EXIT);
    }
    /**
     * This prints the menu header for the user
     */
    public void printMenu(){
        System.out.println("\n                PLANT SELLING SHOP MENU");
        System.out.println("-----------------------------------------------------");
        System.out.println("1. Add plants to inventory.");
        System.out.println("2. Display all plants.");
        System.out.println("3. Sell plant");
        System.out.println("4. Search plant by name");
        System.out.println("5. Sort plants by price");
        System.out.println("6. Display inventory statistics");
        System.out.println("0. Exit");
        System.out.print("Enter your choice : ");
    }
}

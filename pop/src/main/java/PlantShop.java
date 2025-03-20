
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with all the functions that work on the plant array list
 * This class takes the plant input, sells plants, displays plant, sorts plants by price, searches plants by name, displays statistics
 * calculates and applies discount.
 * @author Shantanu Kulkarni
 * Student ID : 12222483 
 */
public class PlantShop {
    Scanner scanner=new Scanner(System.in);
    //index that shows the new changes that will start in the exisitng array list.
    static int startIndex;
    //this is the higheset digit of the 
    final int N=8;
    //creating a reference for the plants 
    ArrayList<Plant> plants=Project.plants;
    public void addPlantsToInventory(){
        //the start index is the index from which changes start taking place in teh file
        startIndex=plants.size();
        final int MIN_QUANTITY=1;
        final int MAX_QUANTITY=100;
        final int MIN_PRICE_ALLOWED=0;
        final int INVALID_QUANTITY=0;
        int iteration=0;
        do{
            // Get plant details from the user
            System.out.print("Enter plant name :");
            String plantName = scanner.nextLine();

            int quantity = INVALID_QUANTITY;
            while (quantity < MIN_QUANTITY || quantity > MAX_QUANTITY) {
                System.out.print("Enter plant quantity (between " + MIN_QUANTITY + " and " + MAX_QUANTITY + "):");
                if (scanner.hasNextInt()) {
                    quantity = scanner.nextInt();
                } else {
                    System.out.println("Invalid input. Please enter a valid quantity.");
                    scanner.nextLine();  // Consume invalid input
                }
            }

            double plantPrice = MIN_PRICE_ALLOWED;
            while (plantPrice <= MIN_PRICE_ALLOWED) {
                System.out.println("Enter plant price (greater than 0):");
                if (scanner.hasNextDouble()) {
                    plantPrice = scanner.nextDouble();
                } else {
                    System.out.println("Invalid input. Please enter a valid price.");
                    scanner.nextLine();  // Consume invalid input
                }
            }

            // Create a new Plant object with user inputs and add it to the inventory
            Plant newPlant = new Plant();
            newPlant.setName(plantName);
            newPlant.setQuantity(quantity);
            newPlant.setPlantPrice(plantPrice);

            plants.add(newPlant);

            System.out.println("Plant added to inventory:");
            newPlant.printPlantObject();
        }while(++iteration<N);
    }
    /**
     * calculates the total price for a single plant to sell.
     * updates the quantity value if the plant is sold.
     */
    public void sellPlants(){
       Plant plantToSell;
       //inputing the plant name.
       do{
        System.out.print("Enter the plant name to sell : ");
        String inputName=scanner.nextLine();
        plantToSell=searchPlantByName(inputName);
       }while(plantToSell==null);
       System.out.print("Enter the quantity to sell: ");
       int quantity=Integer.parseInt(scanner.nextLine());
       
       if (quantity <= 0) {
            System.out.println("Invalid quantity. Please enter a positive quantity.");
            return;
        }
        

        if (quantity <= plantToSell.getQuantity()) {
            double totalPrice = calculateTotalPrice(plantToSell.getPlantPrice(), quantity);
            double discountedPrice = applyDiscount(totalPrice, quantity);
            // Implement the rest of the logic for selling plants, updating quantities, etc.
            System.out.println("Selling " + quantity + " plants of " + plantToSell.getName() +
                    " for a total price of $" + discountedPrice);
        } else {
            System.out.println("Insufficient quantity in inventory. Available quantity: " + plantToSell.getQuantity());
            return;
        }
        System.out.print("Confirm selling this plant [yes/no] : ");
        String ans=scanner.nextLine();
        if(ans.equalsIgnoreCase("yes")){
            plantToSell.setQuantity(plantToSell.getQuantity()-quantity);
        }
    }
    /**
     * returns the calculated total price of the plant.
     * @param plantPrice is the per unit price
     * @param quantity the quantity that will be bought
     * @return 
     */
    private double calculateTotalPrice(double plantPrice, int quantity) {
        return plantPrice * quantity;
    }
    /**
     * calculates the applicable discount based on the quantity of plants bought to encourage bulk purchasing.
     * @param totalPrice the price on which discount is applied
     * @param quantity the quantity to find the discount amount
     * @return the applied discount.
     */
    private double applyDiscount(double totalPrice, int quantity) {
        double discountPercentage = 0.0;
        final int DISCOUNT_THRESHOLD_1 = 15; 
        final int DISCOUNT_THRESHOLD_2 = 25;
        final double DISCOUNT_PERCENTAGE_1 = 0.01; // 10%
        final double DISCOUNT_PERCENTAGE_2 = 0.15; // 15%
        if (quantity >= DISCOUNT_THRESHOLD_2) {
            discountPercentage = DISCOUNT_PERCENTAGE_2;
        } else if (quantity >= DISCOUNT_THRESHOLD_1) {
            discountPercentage = DISCOUNT_PERCENTAGE_1;
        }

        return totalPrice * (1 - discountPercentage);
    }
    /**
     * Sorts plants based on their price through selection sort.
     */
    public void sortPlantsByPrice(){
        final int START_INDEX=0;
        int arrayListSize = plants.size();
        final int SECOND_LAST_INDEX=arrayListSize-1;
        

        for (int i = START_INDEX; i < SECOND_LAST_INDEX; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arrayListSize; j++) {
                if (plants.get(j).getPlantPrice() < plants.get(minIndex).getPlantPrice()) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the element at i
            Plant temp = plants.get(minIndex);
            plants.set(minIndex, plants.get(i));
            plants.set(i, temp);
        }
    }
    /**
     * searches plants by name
     * @param name the name to be searched in inventory
     * @return the plant object which is found null if nothing found.
     */
    public Plant searchPlantByName(String name){
        Plant plant=new Plant();
        for(Plant existingPlant : plants){
            if(name.equalsIgnoreCase(existingPlant.getName())){
                return plant;
            }
        }
        return null;
    }
    
    /**
     * displaying all plants in tabular format.
     */
    public void displayAllPlants(){
        
        System.out.println("                THE INVENTORY : ");
        System.out.printf("%-25s%10s%10s","Plant Name ","Quantity","Price per unit.");
        for(Plant plant : plants){
            System.out.printf("%-25s%10d%10s%.2f",plant.getName(),plant.getQuantity(),"$",plant.getPlantPrice());
        }
    }
    /**
     * display statistics of the plants : the plant with minimum and maximum price and the average price of all plants.
     */
    public void displayStatistics(){
        final int MIN_PRICE_INDEX=0;
        final int ZERO=0;
        final int MAX_PRICE_INDEX=plants.size()-1;
        sortPlantsByPrice();
        Plant minPricePlant=plants.get(MIN_PRICE_INDEX);
        Plant maxPricePlant=plants.get(MAX_PRICE_INDEX);
        double averagePrice;
        double sum=ZERO;
        for(Plant plant : plants){
            sum+=plant.getPlantPrice();
        }
        averagePrice=sum/plants.size();
        //printing the statistics
        System.out.println("                A simple statistics");
        System.out.println("------------------------------------------------------------------");
        System.out.println("PLANT WITH MINIMUM PRICE : ");
        minPricePlant.printPlantObject();
        System.out.println("PLANT WITH MAXIMUMPRICE : ");
        maxPricePlant.printPlantObject();
        System.out.printf("The average plant price : %.2f"+averagePrice);
        System.out.println("------------------------------------------------------------------");
    }
    
}

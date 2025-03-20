/**
 * This function deals with the plant data.
 * It encapsulates the name, quantity and price of the plant in a single entity.
 * @author Shantanu Kulkarni
 * Student ID : 12222483 
 */
public class Plant {
    private String plantName;
    private int quantity;
    private double plantPrice;
    /**
     * setter for plant name
     * @param inputName the name of the plant in the object
     */
    public void setName( String inputName){
        plantName=inputName;
    }
    /**
     * setter for quantity
     * @param quantity the quantity that will be set
     */
    public void setQuantity(int quantity){
        this.quantity=quantity;
    }
    /**
     * setter for price
     * @param price the price that will be set for the object
     */
    public void setPlantPrice(double price){
        plantPrice=price;
    }
    /**
     * getter for plant name
     * @return the name of the plant
     */
    public String getName(){
        return plantName;
    }
    /**
     * getter for plant quantity
     * @return the quantity of the plant
     */
    public int getQuantity(){
        return quantity;
    }
     /**
     * getter for plant price
     * @return the price of the plant
     */
    public double getPlantPrice(){
        return plantPrice;
    }
    /**
     * prints the details of the object.
     */
    public void printPlantObject(){
        System.out.println("---------------------------------");
        System.out.println("Plant Name     : "+plantName);
        System.out.println("Plant Quantity : "+quantity);
        System.out.println("Plant Price    : "+plantPrice);
        System.out.println("---------------------------------");
    }
}



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class deals with the file handling section of the program
 * It reads from files to upload data and saves to file to save the data.
 * @author Shantanu Kulkarni
 * Student ID : 12222483 
 */
public class FileHandling {
    final String FILE_NAME="plantsData.txt";
    /**
     * Saves the file from the new index that is added.
     * @param startIndex
     * @throws IOException 
     */
    public void saveToFile(int startIndex) throws IOException{
        File file=new File(FILE_NAME);
        FileWriter writer;
        if(file.exists()){
            writer=new FileWriter(FILE_NAME,true);
        }else{
            writer=new FileWriter(FILE_NAME);
        }
        //writing to file
        ArrayList<Plant> plants=Project.plants;
        for(int index=startIndex;index<plants.size();index++){
            Plant newPlant=plants.get(index);
            writer.write(newPlant.getName()+";");
            writer.write(newPlant.getQuantity()+";");
            writer.write(newPlant.getPlantPrice()+"\n");
        }
        writer.close();
    }
    /**
     * This method reads the contents of the files and saves it in the array list plant so that
     * the previous plant data can be used.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void readFromFile() throws FileNotFoundException, IOException{
        final int NAME_INDEX=0;
        final int QUANTITY_INDEX=1;
        final int PRICE_INDEX=2;
        FileReader reader=new FileReader(FILE_NAME);
        BufferedReader bufferReader=new BufferedReader(reader);
        String line;
        Plant readPlant=new Plant();
        while((line=bufferReader.readLine())!=null){
            String[] parts=line.split(";");
            //reading plant from the file
            readPlant.setName(parts[NAME_INDEX]);
            readPlant.setQuantity(Integer.parseInt(parts[QUANTITY_INDEX]));
            readPlant.setPlantPrice(Double.parseDouble(parts[PRICE_INDEX]));
            //adding plant to the plant list.
            Project.plants.add(readPlant);
        }
        
    }
}

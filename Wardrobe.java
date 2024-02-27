import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Wardrobe {
    private Clothing[] wardrobe;
    private int wardrobeSize;
    
    public Wardrobe(int capacity) throws IllegalArgumentException{
        if (capacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity");
        }
        this.wardrobe = new Clothing[capacity];
        this.wardrobeSize = 0;
    }

    public Clothing getClothing(String descritopn, String brand) {
        for (Clothing targetclothes : this.wardrobe){
            if (wardrobeSize != 0){
                if (targetclothes.getBrand().equalsIgnoreCase(brand) && targetclothes.getDescription().equalsIgnoreCase(descritopn)){
                    return targetclothes;
                }else{
                    throw new NoSuchElementException("Clothing not found");
                }
            }
            else{
                throw new NoSuchElementException("wardrobe is empty");
        }
        
    }
    throw new NoSuchElementException("Clothing not found");
}
    public void addClothing(Clothing toAdd) throws IllegalArgumentException{
        Clothing[] newClothes = new Clothing[this.wardrobe.length * 2];
        if (this.wardrobeSize == this.wardrobe.length) {
            for (int i = 0; i < this.wardrobe.length; i++){
                newClothes[i] = this.wardrobe[i];
            }
            this.wardrobe = newClothes;
        }
        if (wardrobe[0] != null){
            for (int i = 0; i < wardrobeSize; i++){
                if (wardrobe[i].equals(toAdd)){
                    throw new IllegalArgumentException("Clothing already exists");
                }
            }
        }
        this.wardrobe[this.wardrobeSize] = toAdd;
        this.wardrobeSize++;
    }
    public void wearClothing(Clothing toWear, int year, int month, int day) throws IllegalArgumentException{
        if (year < 1 || month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid date");
        }
        for (int i = 0; i < wardrobeSize; i++){
            if (wardrobe[i].equals(toWear)){
                wardrobe[i].wearClothing(year, month, day);
            }
        }
    }
    public int capacity(){
        return this.wardrobe.length;
    }
    public int size(){
        return this.wardrobeSize;
    }
    public void removeClothing(String description, String brand) {
        int comparesize = wardrobeSize;
        Clothing compareClothing = new Clothing(description, brand);
        if (this.wardrobeSize == 0) {
            throw new IllegalStateException("Wardrobe is empty");
        }
        for (int i = 0; i < this.wardrobeSize; i++){
            if (wardrobe[i].equals(compareClothing) && wardrobe[i].equals(compareClothing)){
                for (int j = i; j < this.wardrobeSize - 1; j++){
                    wardrobe[j] = wardrobe[j + 1];
                }

                this.wardrobeSize--;
                wardrobe[wardrobeSize] = null;
            }
        }
        if (comparesize == wardrobeSize){
            throw new NoSuchElementException("Clothing not found");
        }
    }
    

    public void removeAllClothingWornBefore(int year, int month, int day){
        LocalDate comparedate = LocalDate.of(year, month, day);
        for (int i = 0; i < wardrobeSize; i++){
            if (wardrobe[i].getLastWornDate() != null){
            if (wardrobe[i].getLastWornDate().isBefore(comparedate)){
                removeClothing(wardrobe[i].getDescription(), wardrobe[i].getBrand());
            }
        }
        }
    } 
    public void removeAllClothingWornNumTimes(int threshold){
        for (int i = 0; i < wardrobeSize; i++){
            if (wardrobe[i].getNumOfTimesWorn() < threshold){
                removeClothing(wardrobe[i].getDescription(), wardrobe[i].getBrand());
                
            }
            i = i - 1;
        }

    }
    public static Clothing parseClothing(String str) throws ParseException{
        try{
            String[] element = str.split(",");
            String[] date = new String[3];
            LocalDate newClothesDate = null;
            Clothing newClothes = null;
            if (element.length != 4){
                throw new ParseException("Invalid Input", 0);
            }
            if(!element[2].equals("null")){
                date = element[2].split("/");
                newClothesDate = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[0]), Integer.parseInt(date[1]));
                newClothes = new Clothing(element[0], element[1], Integer.parseInt(element[3]), newClothesDate);
            }else{
                newClothes = new Clothing(element[0], element[1]);
            }
            return newClothes;
        }catch(IllegalArgumentException e){
            throw new ParseException("Invalid Input", 0);
        }
    }
    public boolean loadFromFile(File saveFile){
        try{
            Scanner file = new Scanner(saveFile);
            while (file.hasNextLine()){
                String line = file.nextLine();
                Clothing newClothes = parseClothing(line);
                addClothing(newClothes);
            }
            file.close();
            return true;
        }
        catch(ParseException e){
            PrintWriter consoleOutPut = new PrintWriter(System.out);
            consoleOutPut.println("Cannot parse line to Clothing object");
            return false;
        }
        catch(FileNotFoundException e){
            return false;
        }
    }
    public boolean saveToFile(File saveFile){
        try {
            FileWriter file = new FileWriter(saveFile);
            for (int i = 0; i < wardrobeSize; i++) {
                Clothing clothes = this.wardrobe[i];
                String line = clothes.toString();
                file.write(line);
                if (i != wardrobeSize - 1){ // if not the last line
                    file.write("\n");}
                
            }
            
            file.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
    public Clothing[] getArray(){
        return this.wardrobe;
    }
    @Override
    public String toString(){
        String result = "";
        for (int i = 0; i < wardrobeSize; i++){
            result += "[" + wardrobe[i].toString() + "]\n";
        }
        return result;
    }
}


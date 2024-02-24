import java.text.ParseException;
import java.time.LocalDate;
import java.util.NoSuchElementException;

public class Wardrobe {
    private Clothing[] clothes;
    private int wardrobeSize;
    
    public Wardrobe(int capacity) throws IllegalArgumentException{
        if (capacity <= 0) {
            throw new IllegalArgumentException("Invalid capacity");
        }
        this.clothes = new Clothing[capacity];
        this.wardrobeSize = 0;
    }

    public Clothing getClothing(String descritopn, String brand) throws NoSuchElementException{
        for (Clothing clothes : this.clothes){
            if (clothes.getBrand().equals(brand) && clothes.getDescription().equals(descritopn)){
                return clothes;
            }
        }
        throw new NoSuchElementException("Clothing not found");
    }
    public void addClothing(Clothing toAdd) throws IllegalArgumentException{
        if (this.wardrobeSize == this.clothes.length) {
            throw new IllegalArgumentException("Wardrobe is full");
        }
        for (Clothing clothes : this.clothes){
            if (clothes.equals(toAdd)){
                throw new IllegalArgumentException("Clothing already exists");
            }
        }
        this.clothes[this.wardrobeSize] = toAdd;
        this.wardrobeSize++;
    }
    public void wearClothing(Clothing toWear, int year, int month, int day) throws IllegalArgumentException{
        if (year < 1 || month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid date");
        }
        toWear.wearClothing(year, month, day);
    }
    public int capacity(){
        return this.clothes.length;
    }
    public int size(){
        return this.wardrobeSize;
    }
    public void removeClothing(String description, String brand) throws IllegalStateException, NoSuchElementException{
        if (this.wardrobeSize == 0) {
            throw new IllegalStateException("Wardrobe is empty");
        }
        for (int i = 0; i < this.wardrobeSize; i++){
            if (clothes[i].getDescription().equals(description) && clothes[i].getBrand().equals(brand)){
                for (int j = i; j < this.wardrobeSize - 1; j++){
                    clothes[j] = clothes[j + 1];
                }
                this.wardrobeSize--;
            }else {
                throw new NoSuchElementException("Clothing not found");
            }
        }
    }

    public void removeAllClothingWornBefore(int year, int month, int day){
        LocalDate comparedate = LocalDate.of(year, month, month);
        for (int i = 0; i < wardrobeSize; i++){
            if (clothes[i].getLastWornDate().isBefore(comparedate)){
                clothes[i] = null;
                wardrobeSize--;
                for (int j = i; j < clothes.length; i++){
                    clothes[j] = clothes[j + 1];
                }
            }
        }
    } 
    public void removeAllClothingWornNumTimes(int threshold){
        for (int i = 0; i < wardrobeSize; i++){
            if (clothes[i].getNumOfTimesWorn() < threshold){
                clothes[i] = null;
                wardrobeSize--;
                for (int j = i; j < clothes.length; i++){
                    clothes[j] = clothes[j + 1];
                }
            }
        }
    }
    public static Clothing parseClothing(String str) throws ParseException{
        try{
            String[] element = str.split(",");
            String[] date = element[2].split("/");
            if (element.length != 4){
                throw new ParseException("Invalid Input");
            }
            LocalDate newClothesDate = LocalDate.of(0, 0, 0);
            Clothing newClothes = new Clothing(element[0], element[1], Integer.parseInt(element[3]), );
        }
    }
}

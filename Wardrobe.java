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
        for (Clothing clothes : clothes){
            
        }
    } 
}

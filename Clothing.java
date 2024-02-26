import java.time.LocalDate;

public class Clothing {
    private String brand;
    private String description;
    private LocalDate lastWornDate;
    private int timesWorn;

    public Clothing(String description, String brand) throws IllegalArgumentException{
        this.description = description;
        this.brand = brand;
        this.lastWornDate = null;
        this.timesWorn = 0;
        if (this.description.isBlank() || this.brand.isBlank()) {
            throw new IllegalArgumentException("Description and brand cannot be null");
        }
    }
    public Clothing(String description, String brand, int timesWorn, LocalDate lastWornDate){
        this.description = description;
        this.brand = brand;
        this.timesWorn = timesWorn;
        this.lastWornDate = lastWornDate;
        if (this.description.isBlank() || this.brand.isBlank()) {
            throw new IllegalArgumentException("Description and brand cannot be null");
        }
    }
    public void wearClothing(int year, int month, int day) throws IllegalArgumentException{
        if (year < 1 || month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid date");
        }
        LocalDate setDate = LocalDate.of(year, month, day);
        this.lastWornDate = setDate;
        this.timesWorn++;
    }
    public String getDescription(){
        return this.description;
    }
    public String getBrand(){
        return this.brand;
    }
    public LocalDate getLastWornDate(){
        return this.lastWornDate;
    }
    public int getNumOfTimesWorn(){
        return this.timesWorn;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof Clothing){
            if (this.description.equalsIgnoreCase(((Clothing) o).getDescription()) && this.brand.equalsIgnoreCase(((Clothing) o).getBrand())) {
                return true;
            }
        }else {
            return false;
        }
        return false;
    }
    @Override
    public String toString(){
        if (this.lastWornDate == null){
            return this.description + "," + this.brand + ",null," + this.timesWorn;
        }else{
        return this.description + "," + this.brand + "," + this.lastWornDate.getMonthValue() + "/" +
               this.lastWornDate.getDayOfMonth() + "/" + this.lastWornDate.getYear() + "," + this.timesWorn;
        }
    }
}

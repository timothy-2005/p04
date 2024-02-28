import java.time.LocalDate;
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: wardrobe manager 2.0
// Course: CS 300 Spring 2024
//
// Author: hao zhou
// Email: hzhou375@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Null
// Partner Email: Null
// Partner Lecturer's Name: Null
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons: Null
// Online Sources: Null
//
///////////////////////////////////////////////////////////////////////////////

/**
 * A class to represent a piece of clothing.
 */
public class Clothing {
    private String brand;
    private String description;
    private LocalDate lastWornDate;
    private int timesWorn;

    /**
     * Constructs a new Clothing with the given description and brand.
     * @param description the description of the clothing
     * @param brand the brand of the clothing
     * @throws IllegalArgumentException if the description or brand is null or empty
     */
    public Clothing(String description, String brand){
        this.description = description;
        this.brand = brand;
        this.lastWornDate = null;
        this.timesWorn = 0;
        if (this.description.isBlank() || this.brand.isBlank()) {
            throw new IllegalArgumentException("Description and brand cannot be null");
        }
    }

    /**
     * Constructs a new Clothing with the given description, brand, times worn, and last worn date.
     * @param description the description of the clothing
     * @param brand the brand of the clothing
     * @param timesWorn the number of times the clothing has been worn
     * @param lastWornDate the date the clothing was last worn
     */
    public Clothing(String description, String brand, int timesWorn, LocalDate lastWornDate){
        this.description = description;
        this.brand = brand;
        this.timesWorn = timesWorn;
        this.lastWornDate = lastWornDate;
        if (this.description.isBlank() || this.brand.isBlank()) {
            throw new IllegalArgumentException("Description and brand cannot be null");
        }
        if (lastWornDate.getYear() < 1 || lastWornDate.getMonthValue() < 1 || lastWornDate.getMonthValue() > 12) {
            throw new IllegalArgumentException("Invalid date");
        }
    }

    /**
     * Wears the clothing on the given date.
     * @param year the year the clothing was worn
     * @param month the month the clothing was worn
     * @param day the day the clothing was worn
     * @throws IllegalArgumentException if the date is invalid
     */
    public void wearClothing(int year, int month, int day) throws IllegalArgumentException{
        if (year < 1 || month < 1 || month > 12) {
            throw new IllegalArgumentException("Invalid date");
        }
        LocalDate setDate = LocalDate.of(year, month, day);
        this.lastWornDate = setDate;
        this.timesWorn++;
    }

    /**
     * Returns the description of the clothing.
     * @return the description of the clothing
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * Returns the brand of the clothing.
     * @return the brand of the clothing
     */
    public String getBrand(){
        return this.brand;
    }

    /**
     * Returns the date the clothing was last worn.
     * @return the date the clothing was last worn
     */
    public LocalDate getLastWornDate(){
        return this.lastWornDate;
    }

    /**
     * Returns the number of times the clothing has been worn.
     * @return the number of times the clothing has been worn
     */
    public int getNumOfTimesWorn(){
        return this.timesWorn;
    }

    /**
     * Returns true if the clothing has been worn in the last 30 days, false otherwise.
     * @param o the object to compare
     * @return true if the clothing has been worn in the last 30 days, false otherwise
     */
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

    /**
     * @return a string representation of the clothing.
     */
    @Override
    public String toString(){
        if(lastWornDate == null){

            return description + "," + brand + "," + "null" + "," + timesWorn;
            }else{
                String month = String.valueOf(lastWornDate.getMonthValue());
            
            if(Integer.parseInt(month) < 10){
            month = "0" + month;
            }
            String day = String.valueOf(lastWornDate.getDayOfMonth());
            if(Integer.parseInt(day) < 10)
            day = "0" + day;
            return description + "," + brand + "," + month + '/' + day + "/"+lastWornDate.getYear() + "," + timesWorn;
}
    }
}

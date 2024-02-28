import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;
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
 * A class to represent a wardrobe of clothing.
 */
public class Wardrobe {
  private Clothing[] wardrobe;
  private int wardrobeSize;

  /**
   * Constructs a new Wardrobe with the given capacity.
   * 
   * @param capacity the maximum number of clothing items that can be stored in the wardrobe
   * @throws IllegalArgumentException if the capacity is less than or equal to 0
   */
  public Wardrobe(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Invalid capacity");
    }
    this.wardrobe = new Clothing[capacity];
    this.wardrobeSize = 0;
  }

  /**
   * Constructs a new Wardrobe with the given capacity and fills it with the clothing items from the
   * given file.
   * 
   * @param descritopn the description of the clothing item
   * @param brand      the brand of the clothing item
   * @return the clothing item with the given description and brand
   */
  public Clothing getClothing(String descritopn, String brand) {
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i].getDescription().equalsIgnoreCase(descritopn)
          && wardrobe[i].getBrand().equalsIgnoreCase(brand)) {
        return wardrobe[i];
      }
    }
    throw new NoSuchElementException("Clothing not found");
  }

  /**
   * Adds the given clothing item to the wardrobe.
   * 
   * @param toAdd the clothing item to add
   * @throws IllegalArgumentException if the clothing item is already in the wardrobe
   */
  public void addClothing(Clothing toAdd) throws IllegalArgumentException {
    Clothing[] newClothes = new Clothing[this.wardrobe.length * 2];
    if (this.wardrobeSize == this.wardrobe.length) {
      for (int i = 0; i < this.wardrobe.length; i++) {
        newClothes[i] = this.wardrobe[i];
      }
      this.wardrobe = newClothes;
    }
    if (wardrobe[0] != null) {
      for (int i = 0; i < wardrobeSize; i++) {
        if (wardrobe[i].equals(toAdd)) {
          throw new IllegalArgumentException("Clothing already exists");
        }
      }
    }
    this.wardrobe[this.wardrobeSize] = toAdd;
    this.wardrobeSize++;
  }

  /**
   * Wears the given clothing item on the given date.
   * 
   * @param toWear the clothing item to wear
   * @param year   the year to wear the clothing item
   * @param month  the month to wear the clothing item
   * @param day    the day to wear the clothing item
   * @throws IllegalArgumentException if the year is less than 1, the month is less than 1 or
   *                                  greater than 12, or the day is less than 1
   */
  public void wearClothing(Clothing toWear, int year, int month, int day) {
    if (year < 1 || month < 1 || month > 12) {
      throw new IllegalArgumentException("Invalid date");
    }
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i].equals(toWear)) {
        wardrobe[i].wearClothing(year, month, day);
      }
    }
  }

  /**
   * Saves the wardrobe to the given file.
   * 
   * @return the file to save the wardrobe to
   */
  public int capacity() {
    return this.wardrobe.length;
  }

  /**
   * Returns the number of clothing items in the wardrobe.
   * 
   * @return the number of clothing items in the wardrobe
   */
  public int size() {
    return this.wardrobeSize;
  }

  /**
   * Saves the wardrobe to the given file.
   * 
   * @param description the description of the clothing item
   * @param brand       the brand of the clothing item
   */
  public void removeClothing(String description, String brand) {
    boolean clothExist = false;
    int Index = -1;
    if (size() == 0) {
      throw new IllegalStateException("the wardrobe is empty");
    }

    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i].getDescription().equalsIgnoreCase(description)
          && wardrobe[i].getBrand().equalsIgnoreCase(brand)) {
        clothExist = true;
        wardrobe[i] = null;
        Index = i;
        break;
      }
    }

    if (clothExist) {
      for (int i = Index; i < wardrobeSize - 1; i++) {
        wardrobe[i] = wardrobe[i + 1];
      }
      wardrobe[wardrobeSize - 1] = null;
      wardrobeSize--;
    } else {
      throw new NoSuchElementException("Clothing not found");
    }
  }

  /**
   * Saves the wardrobe to the given file.
   * 
   * @param year  the year to save the wardrobe to
   * @param month the month to save the wardrobe to
   * @param day   the day to save the wardrobe to
   */
  public void removeAllClothingWornBefore(int year, int month, int day) {
    LocalDate compareDate = LocalDate.of(year, month, day);
    for (int i = 0; i < wardrobeSize; i++) {
      LocalDate originalDate = wardrobe[i].getLastWornDate();
      if (originalDate == null || originalDate.isBefore(compareDate)) {
        removeClothing(wardrobe[i].getDescription(), wardrobe[i].getBrand());
        i--;
      }
    }
  }

  /**
   * Saves the wardrobe to the given file.
   * 
   * @param threshold the threshold to save the wardrobe to
   */
  public void removeAllClothingWornNumTimes(int threshold) {
    for (int i = 0; i < wardrobeSize; i++) {
      if (wardrobe[i].getNumOfTimesWorn() < threshold) {
        removeClothing(wardrobe[i].getDescription(), wardrobe[i].getBrand());
        i--;
      }
    }
  }


  /**
   * Returns the clothing item at the given index in the wardrobe.
   * 
   * @param str the string to parse
   * @return the clothing item parsed from the string
   * @throws ParseException if the string is not in the correct format
   */
  public static Clothing parseClothing(String str) throws ParseException {
    try {
      String[] element = str.split(",");
      String[] date = new String[3];
      LocalDate newClothesDate = null;
      Clothing newClothes = null;
      if (element.length != 4) {
        throw new ParseException("Invalid Input", 0);
      }
      if (!element[2].equals("null")) {
        date = element[2].split("/");
        if (date.length != 3) {
          throw new ParseException("Invalid Input", 0);
        }
        newClothesDate = LocalDate.of(Integer.parseInt(date[2]), Integer.parseInt(date[0]),
            Integer.parseInt(date[1]));
        if (newClothesDate.getYear() < 1 || newClothesDate.getMonthValue() < 1
            || newClothesDate.getMonthValue() > 12) {
          throw new IllegalArgumentException("Invalid date");
        }
        newClothes =
            new Clothing(element[0], element[1], Integer.parseInt(element[3]), newClothesDate);
      } else {
        newClothes = new Clothing(element[0], element[1]);
      }
      return newClothes;
    } catch (IllegalArgumentException e) {
      throw new ParseException("Invalid Input", 0);
    }
  }

  /**
   * Loads the wardrobe from the given file.
   * 
   * @param saveFile the file to load the wardrobe from
   * @return true if the wardrobe was successfully loaded, false otherwise
   */
  public boolean loadFromFile(File saveFile) {
    boolean result = false;
    try {
      Scanner file = new Scanner(saveFile);
      while (file.hasNextLine()) {
        try {
          String line = file.nextLine();
          Clothing newClothes = parseClothing(line);
          addClothing(newClothes);
          result = true;
        } catch (ParseException e) {
          PrintWriter consoleOutPut = new PrintWriter(System.out);
          consoleOutPut.println("Cannot parse line to Clothing object");
        } catch (IllegalArgumentException e) {
          PrintWriter consoleOutPut = new PrintWriter(System.out);
          consoleOutPut.println("Cannot parse line to Clothing object");
        } catch (Exception e) {
          PrintWriter consoleOutPut = new PrintWriter(System.out);
          consoleOutPut.println("Cannot parse line to Clothing object");
        }
      }
      file.close();
    } catch (FileNotFoundException e) {
      PrintWriter consoleOutPut = new PrintWriter(System.out);
      consoleOutPut.println("Cannot parse line to Clothing object");
    }
    return result;
  }

  /**
   * Saves the wardrobe to the given file.
   * 
   * @param saveFile the file to save the wardrobe to
   * @return true if the wardrobe was successfully saved, false otherwise
   */
  public boolean saveToFile(File saveFile) {
    try {
      FileWriter file = new FileWriter(saveFile);
      for (int i = 0; i < wardrobeSize; i++) {
        Clothing clothes = this.wardrobe[i];
        String line = clothes.toString();
        file.write(line);
        if (i != wardrobeSize - 1) { // if not the last line
          file.write("\n");
        }

      }

      file.close();
      return true;
    } catch (FileNotFoundException e) {
      return false;
    } catch (IOException e) {
      return false;
    }
  }

  /**
   * Returns the clothing item at the given index in the wardrobe.
   * 
   * @return the clothing item at the given index in the wardrobe
   */
  protected Clothing[] getArray() {
    return this.wardrobe;
  }

  /**
   * @return the clothing item at the given index in the wardrobe.
   */
  @Override
  public String toString() {
    String result = "";
    for (int i = 0; i < wardrobeSize - 1; i++) {
      result += "[" + wardrobe[i].toString() + "]\n";
    }
    result += "[" + wardrobe[wardrobeSize - 1].toString() + "]";
    return result;
  }
}


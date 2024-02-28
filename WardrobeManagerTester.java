import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.text.ParseException;

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

// TODO: ADD FILE HEADER HERE

/**
 * A tester class for the Wardrobe Manager project. It contains various tests
 * to check the correctness of the Wardrobe and Clothing classes.
 */
public class WardrobeManagerTester {

  /**
   * Tests both of the Clothing constructors and all getters for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   * 
   * @author Michelle
   */
  public static boolean testClothingConstructorAndGetters() {
    
    // in case we get an unexpected exception from a broken implementation
    // we handle it with a try-catch to avoid our tester from crashing
    try { 
      // test the 2-argument constructor
      Clothing c = new Clothing("black t-shirt", "Gildan");
      
      // check that the four instance data fields have been initialized correctly
      // using the getters to do this we are also checking their correctness
      // in a bad implementation either 1) the constructor didn't intialize a data field correctly
      // OR 2) the getter doesn't return the correct value
      if (!c.getDescription().equals("black t-shirt")) return false;
      if (!c.getBrand().equals("Gildan")) return false;
      if (c.getNumOfTimesWorn() != 0) return false;
      if (c.getLastWornDate() != null) return false;
      
      // test the 4 argument constructor
      // same idea as the previous test case
      LocalDate date = LocalDate.of(2024,2,14); // create a date object for last worn
      c = new Clothing("jeans", "Levi", 3, date);
      if (!c.getDescription().equals("jeans")) return false;
      if (!c.getBrand().equals("Levi")) return false;
      if (c.getNumOfTimesWorn() != 3) return false;
      if (!c.getLastWornDate().equals(date)) return false;

    } catch (Exception e) { // we encounter an exception when we should not, it is a bad implementation
      e.printStackTrace();
      return false;
    }
    return true;
  }
  
  /**
   * Tests that both of the Clothing constructors throw the correct type of exception(s) 
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   * 
   * @author Michelle and Hobbes
   */
  public static boolean testClothingConstructorExceptions() {
    // Here we call constructors with input that should lead to an IllegalArgumentException
    // on a correct (good) implementation. ALL of these cases SHOULD throw exceptions,
    // so we test them in separate try-catch statements to verify that each individual
    // case throws an exception.
    
    try {
      // test the 2 argument constructor with a blank description
      new Clothing(" ", "Gildan");

      return false; // no exception was thrown when it should have been; it's a broken implementation
    } catch (IllegalArgumentException e) {
      // check if the exception has any message; if there is NO message it's a broken implementation
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good, it's a broken implementation
      e.printStackTrace();
      return false;
    }

    try {
      // and make sure a blank brand will also throw an exception
      new Clothing("black t-shirt", "  ");

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }
      
    try {
      // test the 4 argument constructor with a blank description
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing(" ", "Gildan", 4, date);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }

    try {
      // and verifying that a blank brand will also throw an exception
      LocalDate date = LocalDate.of(2021, 12, 25);
      new Clothing("black t-shirt", "  ", 6, date);

      return false; // no exception was thrown
    } catch (IllegalArgumentException e) {
      // check if the exception has a message,
      if (e.getMessage() == null || e.getMessage().isBlank())
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      return false;
    }
    
    // passed all the tests!
    return true;
  }
  
  /**
   * Tests for the correctness of the Clothing classes' wearClothing() method.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   * 
   */
  public static boolean testClothingWear() {

    // in case we get an unexpected exception from a broken implementation
    try{
      Clothing c = new Clothing("black t-shirt", "gildan"); // create a Clothing object
      c.wearClothing(-1, 01, 29); // wear the clothing on a date which is invalid
      return false; // no exception was thrown return false
    }catch(IllegalArgumentException e){
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
      // if there is NO message it's a broken implementation
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      // if any other type of exception is thrown return false
      return false;
    }
    try{
      // wear the clothing on a date which is invalid
      Clothing c = new Clothing("black t-shirt", "gildan"); // create a Clothing object
      c.wearClothing(2021, 13, 29); // wear the clothing on a date which is invalid
      return false; // no exception was thrown return false
    }catch(IllegalArgumentException e){
      // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank()) // if there is NO message it's a broken implementation
      // if there is NO message return false
        return false;
    } catch (Exception e) {// any other type of exception is not good
      e.printStackTrace();
      // if any other type of exception is thrown
      return false;
    }
    // passed all the tests!
    return true;
  }

  /**
   * Tests the Wardrobe constructor and all getters for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */

  public static boolean testWardrobeConstructorAndGetters() {
    // in case we get an unexpected exception from a broken implementation
    try{
      // create a Wardrobe object
      Wardrobe w = new Wardrobe(5);
      // test the getter for capacity
      if (w.capacity() != 5) return false;
      // test the getter for size
      if (w.size() != 0) return false;
      
    }catch(Exception e){ // any other type of exception is not good
      e.printStackTrace();
      // if any other type of exception is thrown
      return false;
    }
    // passed all the tests!
  return true;
}
  
  /**
   * Tests that the Wardrobe constructor throws the correct type of exception(s) 
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testWardrobeConstructorExceptions() {
    try{
      // test the constructor with a negative capacity
      Wardrobe w = new Wardrobe(0); // create a Wardrobe object
      // if no exception was thrown return false
      return false;
    
    }catch(IllegalArgumentException e){// check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank()) // if there is NO message it's a broken implementation
      // if there is NO message return false
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      // if any other type of exception is thrown return false
      return false;
    }
    // passed all the tests!
    return true;
  }
  
  /**
   * Tests that the Wardrobe's addClothing() method throws the correct type of exception(s) 
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddClothingExceptions() {
    // in case we get an unexpected exception from a broken implementation
    Wardrobe w = new Wardrobe(5); // create a Wardrobe object
    Clothing c = new Clothing("black t-shirt", "gildan"); // create a Clothing object
    Clothing c_copy = new Clothing("black t-shirt", "gildan"); // create a Clothing object
    w.addClothing(c); // add the Clothing object to the Wardrobe
    try{
      // test adding a duplicate clothing
      w.addClothing(c_copy);
      // if no exception was thrown return false
      return false;
    }catch(IllegalArgumentException e){ // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank())
      // if there is NO message it's a broken implementation
        return false;
    } catch (Exception e) { // any other type of exception is not good
      e.printStackTrace();
      // if any other type of exception is thrown return false
      return false;
    }
    // passed all the tests!
    return true;
  }
  
  /**
   * Tests the Wardrobe's addClothing() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testAddClothing() {
    // create a Wardrobe object
    Wardrobe w = new Wardrobe(5);
    Clothing c = new Clothing("black t-shirt", "gildan");
    Clothing c2 = new Clothing("black jeans", "Levi");
    Clothing c3 = new Clothing("red t-shirt", "gildan");
    Clothing c4 = new Clothing("jeans", "Levi");
    Clothing c5 = new Clothing("blue t-shirt", "gildan");
    Clothing c6 = new Clothing("jean", "Levi");
    w.addClothing(c);
    w.addClothing(c2);
    w.addClothing(c3);
    w.addClothing(c4);
    w.addClothing(c5);
    w.addClothing(c6);
    // test the extended capacity
    if (w.capacity() != 10) return false; // if the capacity is not doubled return false
    if(w.size() != 6) return false; // if the size is not 6 return false
    // passed all the tests!
    return true;
  }
  
  
  /**
   * Tests the Wardrobe's getClothing() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetClothing() {
    // create a Wardrobe object
    Wardrobe w = new Wardrobe(5);
    Clothing c = new Clothing("black jeans", "Levi"); // create a Clothing object
    w.addClothing(c); // add the Clothing object to the Wardrobe
    Clothing compareClothing = w.getClothing("BLACK JEANS", "LEVI"); // compare the Clothing object from the Wardrobe(case insensitive)
    Clothing compareClothing2 = w.getClothing("black jeans", "Levi"); // get the Clothing object from the Wardrobe
    if (!compareClothing.equals(c)) return false; // if the Clothing object is not the same return false
    if (!compareClothing2.equals(c)) return false; // if the Clothing object is not the same return false
    // passed all the tests!
    return true;
  }
  
  
  /**
   * Tests that the Wardrobe's getClothing() method throws the correct type of exception(s) 
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testGetClothingExceptions() {
    try{
      // test getting a clothing that is not in the wardrobe
      Wardrobe w = new Wardrobe(5);
      // if no exception was thrown return false
      Clothing c = new Clothing("black jeans", "Levi");
      // if no exception was thrown return false
      w.addClothing(c);
      w.getClothing("black t-shirt", "gildan");
      return false;
    }catch(NoSuchElementException e){ // check if the exception has a message
      if (e.getMessage() == null || e.getMessage().isBlank()) // if there is NO message it's a broken implementation
      // if there is NO message return false
        return false;
    } catch (Exception e) {
      e.printStackTrace(); // any other type of exception is not good
      // if any other type of exception is thrown return false
      return false;
    }
    try{
      // test getting a clothing that is not in the wardrobe
      Wardrobe w = new Wardrobe(5); // create a Wardrobe object
      w.getClothing("black jeans", "gildan"); // get the Clothing object from the Wardrobe
      return false; // if no exception was thrown return false
  }catch(NoSuchElementException e){
    if (e.getMessage() == null || e.getMessage().isBlank()) // if there is NO message it's a broken implementation
      return false; // if there is NO message return false
  } catch (Exception e) { // any other type of exception is not good
    e.printStackTrace(); // if any other type of exception is thrown return false
    return false;
  }
  // passed all the tests!
  return true;
}
  /**
   * Tests that the Wardrobe's removeClothing() method throws the correct type of exception(s) 
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveClothingExceptions() {
    { // test case when the wardrobe is empty
      try{
        Wardrobe w = new Wardrobe(5);
        w.removeClothing("purse", "gucci");
        return false; // no exception was thrown when it should have been, broken implementation
      }catch(IllegalStateException e){
        // check if the exception has any message; return false if there is NO message
        if (e.getMessage() == null || e.getMessage().isBlank())
          return false;
      }catch (Exception e){ // any other type of exception is not good, return false
        e.printStackTrace();
        return false;
      }
    }

    { // test case when the piece of clothing is not in the wardrobe
      try{
        Wardrobe w = new Wardrobe(5);
        Clothing c = new Clothing("black t-shirt", "gildan");
        w.addClothing(c);
        w.removeClothing("purse", "gucci");
        return false; // no exception was thrown when it should have been, broken implementation
      }catch(NoSuchElementException e){
        // check if the exception has any message; return false if there is NO message
        if (e.getMessage() == null || e.getMessage().isBlank())
          return false;
      }catch (Exception e){ // any other type of exception is not good, return false
        e.printStackTrace();
        return false;
      }
    }
    return true;
  }
  
  /**
   * Tests the Wardrobe's removeClothings() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveClothing() {
    try {
      // test removing a clothing from the wardrobe
      Wardrobe w = new Wardrobe(5);
      Clothing c1 = new Clothing("black t-shirt", "gildan");
      Clothing c2 = new Clothing("blue hoodie", "gildan");
      Clothing c3 = new Clothing("purse", "gucci");
      // if no exception was thrown return false
      w.addClothing(c1);
      w.addClothing(c2);
      w.addClothing(c3);
      // if no exception was thrown return false
      w.removeClothing("blue hoodie", "gildan");
      // if the size is not 2 return false
      String expected = "[black t-shirt,gildan,null,0]\n" + "[purse,gucci,null,0]";
      String actual = w.toString();
      // if the expected and actual are not the same return false
      if (w.size() != 2)
        return false;
      // if the expected and actual are not the same return false
      if (!expected.equals(actual))
        return false;
    }
    catch (Exception e){
      e.printStackTrace();
      // if any other type of exception is thrown return false
      return false;
    }
    // passed all the tests!
    return true;

  }
  
  /**
   * Tests the Wardrobe's removeAllClothingWornBefore() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornBefore() {
    try{
      // test removing all clothing worn before a certain date
      Wardrobe w = new Wardrobe(5);
      Clothing c1 = new Clothing("black t-shirt", "gildan");
      Clothing c2 = new Clothing("blue hoodie", "gildan");
      Clothing c3 = new Clothing("purse", "gucci");
      // if no exception was thrown return false
      w.addClothing(c1);
      w.addClothing(c2);
      w.addClothing(c3);
      w.wearClothing(c3, 2024, 2, 19);
      w.wearClothing(c2, 2023, 10, 10);
      // if no exception was thrown return false
      w.removeAllClothingWornBefore(2024, 1, 1);
      // if the size is not 1 return false
      String expected = "[purse,gucci,02/19/2024,1]";
      String actual = w.toString();
      // if the expected and actual are not the same return false
      if (w.size() != 1)
        return false;
      // if the expected and actual are not the same return false
      if (!expected.equals(actual))
        return false;

    }catch (Exception e){
      e.printStackTrace();
      // if any other type of exception is thrown return false
      return false;
    }
    // passed all the tests!
    return true;
  }
  
  /**
   * Tests the Wardrobe's removeAllClothingWornNumTimes() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornNumTimes() {
    try{
      // test removing all clothing worn a certain number of times
      Wardrobe w = new Wardrobe(5);
      Clothing c1 = new Clothing("black t-shirt", "gildan");
      Clothing c2 = new Clothing("blue hoodie", "gildan");
      Clothing c3 = new Clothing("purse", "gucci");
      Clothing c4 = new Clothing("red hoodie", "gucci");
      w.addClothing(c1);
      w.addClothing(c2);
      w.addClothing(c3);
      w.addClothing(c4);// if no exception was thrown return false
      w.wearClothing(c2, 2023, 10, 10);
      w.wearClothing(c2, 2024, 5, 15);
      w.wearClothing(c2, 2023, 12, 25);
      w.wearClothing(c3, 2024, 2, 19);
      w.wearClothing(c3, 2024, 1, 8);
      w.wearClothing(c4, 2023, 8, 20);
      w.removeAllClothingWornNumTimes(2);
      // if the size is not 2 return false
      String expected = "[blue hoodie,gildan,12/25/2023,3]\n" + "[purse,gucci,01/08/2024,2]";
      String actual = w.toString();
      // if the expected and actual are not the same return false
      if (w.size() != 2)
      // if the expected and actual are not the same return false
        return false;
      if (!expected.equals(actual))
      // if the expected and actual are not the same return false
        return false;

    }catch (Exception e){
      e.printStackTrace();
      // if any other type of exception is thrown return false
      return false;
    }
    // passed all the tests!
    return true;
  }
  
  /**
   * Tests that the Wardrobe's parseClothing() method throws the correct type of exception(s) 
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothingExceptions() {
    { // test case without the 4 required pieces of information
      try {
        String str = "black t-shirt,gildan,null";
        Clothing cloth = Wardrobe.parseClothing(str);
        // no exception was thrown when it should have been; it's a broken implementation
        return false;

      } catch (ParseException e) {
        // check if the exception has any message; return false if there is NO message
        if (e.getMessage() == null || e.getMessage().isBlank())
          return false;

      } catch (Exception e) { // any other type of exception is not good, return false
        e.printStackTrace();
        return false;
      }
    }

    { // test case of invalid date format
      try {
        String str = "blue hoodie,gildan,10/10,1";
        Clothing cloth = Wardrobe.parseClothing(str);
        // no exception was thrown when it should have been; it's a broken implementation
        return false;

      } catch (ParseException e) {
        // check if the exception has any message; return false if there is NO message
        if (e.getMessage() == null || e.getMessage().isBlank())
          return false;

      } catch (Exception e) { // any other type of exception is not good, return false
        e.printStackTrace();
        return false;
      }
    }

    { //test case with invalid cloth argument
      try {
        String str = "blue hoodie,gildan,10/10/2023,s";
        Clothing cloth = Wardrobe.parseClothing(str);
        // no exception was thrown when it should have been; it's a broken implementation
        return false;

      } catch (ParseException e) {
        // check if the exception has any message; return false if there is NO message
        if (e.getMessage() == null || e.getMessage().isBlank())
          return false;

      } catch (Exception e) { // any other type of exception is not good, return false
        e.printStackTrace();
        return false;
      }
    }
    return true;
  }
  
  /**
   * Tests the Wardrobe's parseClothing method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothing() {
    try{
      { //test case with null date in string
        String str = "black t-shirt,Gildan,null,0";
        Clothing c = Wardrobe.parseClothing(str);

        if (!c.getDescription().equals("black t-shirt"))
          return false;
        if (!c.getBrand().equals("Gildan"))
          return false;
        if (c.getNumOfTimesWorn() != 0)
          return false;
        if (c.getLastWornDate() != null)
          return false;
      }

      { //test case with valid date in string
        String str = "jeans,levi,02/14/2024,1";
        Clothing c = Wardrobe.parseClothing(str);
        LocalDate date = LocalDate.of(2024, 2, 14);
        // if the expected and actual are not the same return false
        if (!c.getDescription().equals("jeans"))
          return false;// if the expected and actual are not the same return false
        if (!c.getBrand().equals("levi"))
          return false;// if the expected and actual are not the same return false
        if (c.getNumOfTimesWorn() != 1)
          return false;// if the expected and actual are not the same return false
        if (!c.getLastWornDate().equals(date))
          return false;// if the expected and actual are not the same return false
      }

    }
    catch(Exception e){
      e.printStackTrace();
      // if any other type of exception is thrown return false
      return false;
    }
    // passed all the tests!
    return true;
  }
  
  /**
   * Runs all testing methods and prints out their results.
   * @return true if and only if all the tests return true, false otherwise
   */
  public static boolean runAllTests() {
    boolean test1 = testClothingConstructorExceptions();
    System.out.println("testClothingConstructorExceptions(): " + (test1 ? "pass" : "FAIL"));
    
    boolean test2 = testClothingConstructorAndGetters();
    System.out.println("testClothingConstructorAndGetters(): " + (test2 ? "pass" : "FAIL"));
    
    boolean test3 = testClothingWear();
    System.out.println("testClothingWear(): " + (test3 ? "pass" : "FAIL"));
    
    boolean test4 = testWardrobeConstructorAndGetters();
    System.out.println("testWardrobeConstructorAndGetters(): " + (test4 ? "pass" : "FAIL"));
    
    boolean test5 = testWardrobeConstructorExceptions();
    System.out.println("testWardrobeConstructorExceptions(): " + (test5 ? "pass" : "FAIL"));
    
    boolean test6 = testAddClothingExceptions();
    System.out.println("testAddClothingExceptions(): " + (test6 ? "pass" : "FAIL"));
    
    boolean test7 = testAddClothing();
    System.out.println("testAddClothing(): " + (test7 ? "pass" : "FAIL"));
    
    boolean test8 = testGetClothing();
    System.out.println("testGetClothing(): " + (test8 ? "pass" : "FAIL"));
    
    boolean test9 = testGetClothingExceptions();
    System.out.println("testGetClothingExceptions(): " + (test9 ? "pass" : "FAIL"));
    
    boolean test10 = testRemoveClothing();
    System.out.println("testRemoveClothing(): " + (test10 ? "pass" : "FAIL"));
    
    boolean test11 = testRemoveClothingExceptions();
    System.out.println("testRemoveClothingExceptions(): " + (test11 ? "pass" : "FAIL"));
    
    boolean test12 = testRemoveAllClothingWornBefore();
    System.out.println("testRemoveAllClothingWornBefore(): " + (test12 ? "pass" : "FAIL"));
    
    boolean test13 = testRemoveAllClothingWornNumTimes();
    System.out.println("testRemoveAllClothingWornNumTimes(): " 
        + (test13 ? "pass" : "FAIL"));

    boolean test14 = testParseClothingExceptions();
    System.out.println("testParseClothingExceptions(): " + (test14 ? "pass" : "FAIL"));

    boolean test15 = testParseClothing();
    System.out.println("testParseClothing(): " + (test15 ? "pass" : "FAIL"));

    return test1 && test2 && test3 && test4 && test5 && test6 && test7 && test8 && test9 && test10
            && test11 && test12 && test13 && test14 && test15;
  }
  
  public static void main(String[] args) {
    System.out.println("runAllTests(): " + runAllTests());
  }
}

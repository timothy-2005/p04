import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.io.File;
import java.util.Arrays;

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
    return false;
  }
  
  /**
   * Tests the Wardrobe's removeClothings() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveClothing() {
    return false;
  }
  
  /**
   * Tests the Wardrobe's removeAllClothingWornBefore() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornBefore() {
    return false;
  }
  
  /**
   * Tests the Wardrobe's removeAllClothingWornNumTimes() method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testRemoveAllClothingWornNumTimes() {
    return false;
  }
  
  /**
   * Tests that the Wardrobe's parseClothing() method throws the correct type of exception(s) 
   * with a message in situations where an exception is expected.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothingExceptions() {
    return false;
  }
  
  /**
   * Tests the Wardrobe's parseClothing method for correctness.
   * This test accounts for the fact a bad implementation may throw an Exception.
   *
   * @return true if all tests pass, false otherwise
   */
  public static boolean testParseClothing() {
    return false;
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

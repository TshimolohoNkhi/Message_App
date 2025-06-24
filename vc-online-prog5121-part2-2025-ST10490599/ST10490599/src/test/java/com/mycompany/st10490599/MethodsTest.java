/* package com.mycompany.st10490599;

import java.util.Map;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;


public class MethodsTest {

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    

    /**
     * Test of checkUsername method, of class Methods.
     */
    /* @org.junit.jupiter.api.Test
    public void testCheckUsername() {
        System.out.println("checkUsername");

        // Test 1: Correctly formatted - The username contains an underscore and is no more than 5 characters long
        String username = "kyl_1";
        Methods instance = new Methods();
        boolean expResult = true;
        boolean result = instance.checkUsername(username);
        assertEquals(expResult, result, "Welcome <user first name>, <user last name> it is great to see you"); 

        // Test 2: Incorrectly formatted - The username does not contain an underscore and is more than 5 characters long
        username = "kyle!!!!!!!!";
        expResult = false; 
        result = instance.checkUsername(username);
        assertEquals(expResult, result, "Username is not formatted correctly. Please ensure that your username contains an underscore and is no more than 5 characters in length"); 
    }


    /**
     * Test of checkPasswordComplexity method, of class Methods.
     */
    /* @org.junit.jupiter.api.Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        
        // Test 1: The password meets the complexity requirements
        String password = "Ch&&sec@ke99!";
        Methods instance = new Methods();
        boolean expResult = true;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(expResult, result, "Password successfully captured");
        
        // Test 2: The password does not meet the complexity requirements
        //String password2 = "password";
        //boolean expResult2 = false;
        //boolean result2 = instance.checkPasswordComplexity(password);
        //assertEquals(expResult2, result2, "Password is not correctly formatted. Please ensure that your password contains at least 8 characters, a capital letter, a number and a special character");
    }

    /**
     * Test of checkCellPhoneNumber method, of class Methods.
     */
    /* @org.junit.jupiter.api.Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        
        // Test 1: Cellphone is correctly formatted
        String phoneNumber = "+27838968976";
        Methods instance = new Methods();
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber(phoneNumber);
        assertEquals(expResult, result, "Cell number is successfully captured");
        
        // Test 2: Cellphone is incorrectly formatted
        //String phoneNumber2 = "08966553";
        //Methods instance2 = new Methods();
        //boolean expResult2 = true;
        //boolean result2 = instance.checkCellPhoneNumber(phoneNumber);
        //assertEquals(expResult2, result2, "Cell number is successfully captured");
    }

    /**
     * Test of returnLoginStatus method, of class Methods.
     */
    /* @org.junit.jupiter.api.Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");

        Methods instance = new Methods();

        String loginResult = "Login Successful";

        // Prepare user info
        Map<String, String> userInformation = new HashMap<>();
        userInformation.put("username", "kyl_1");
        userInformation.put("password", "Ch&&sec@ke99!");

        instance.returnLoginStatus(loginResult, userInformation);
    }

} */

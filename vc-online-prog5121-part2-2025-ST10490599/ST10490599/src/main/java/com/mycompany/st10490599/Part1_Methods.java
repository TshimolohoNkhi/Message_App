package com.mycompany.st10490599;

// import libraries that will be used later on
import javax.swing.JOptionPane;
import java.util.Map;
import java.util.HashMap;

// Defined class methods
public class Part1_Methods {
    
    // Initialises a map that will hold key-pair values where both the key and the pair are Strings to store the user's information
    public Map<String, String> userInformation = new HashMap<>();
    
    // Defines a public method that returns a boolean and has String username as an argument for further use 
    public boolean checkUsername(String username) {
        // username conatains an underscore and is no more than 5 characters long, return true or false
        return (username.contains("_") && username.length() <= 5); 
    }
    
    // Defines a public method that returns a boolean and has String password as an arguement
    public boolean checkPasswordComplexity(String password) {
        // password is equal to or greater than 8 characters and contains a captital letter, a number and a special character, return true or false
        return (password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*\\d.*") && password.matches(".*[!@#$%^&*()_+].*"));
    }
    
    // Defines a public method that returns a boolean and has String phoneNumber as an arguement
    public boolean checkCellPhoneNumber (String phoneNumber) {
        // phoneNumber contains an international code and is 10 digits long, return true or false
        return (phoneNumber.matches("^\\+\\d{1,3}\\d{10}$"));
    }

    // public method that returns no specific data type and holds Map<String, String> username as an arguement
    public void logInUser(Map<String, String> userInformation) {
        
        String username = JOptionPane.showInputDialog(null, "Please enter your username\n"); // Prompts user for their username
        String password = JOptionPane.showInputDialog(null, "Please enter your password\n"); // Prompts user for their password

        String loginResult; // Defines an empty String variable that will store the result of the log in attempt
        
        String storedUsername = userInformation.get("Username"); // Retrieve username from userInformation
        String storedPassword = userInformation.get("Password"); // Retrieve password from userInformation

        if (storedUsername == null || !storedUsername.equals(username)) { // If storedUsername is null or if the value in storedUsername and the value that the user inputted does not match then ...
            loginResult = "userNotFound"; // Display the userNotFound message
        } else if (storedPassword == null || !storedPassword.equals(password)) { // If storedPassword is null or if the value in storedPassword and the value that the user inputted does not match then ...
            loginResult = "wrongPassword"; // Display the wrongPassword message
        } else {
            loginResult = "success"; // Otherwise, if username and password is not null and they match then display a success message
        }

        returnLoginStatus(loginResult, userInformation); // loginResult and userInformation is passed to the returnLoginResulr method for later usage
    }
    
    // public method that returns no specific data type and has String loginResult and Map<String, String> userinformation as arguements
    // This method takes loginResult from logInUser, if the outcome of loginResult is "userNotFound" then a specific messages must be displayed, and the same goes for "wrongPassword" and "success". 
    public void returnLoginStatus(String loginResult, Map<String, String> userInformation) {
        switch (loginResult) { // Uses switch to compare a single variable to multiple values
            case "userNotFound" -> // If the outcome from loginResult is "userNotFound", display "User is not registered..." message
                JOptionPane.showMessageDialog(null, "User is not registered. Please create an account.", "Error", JOptionPane.ERROR_MESSAGE);
            // break while loop after a case has been selected
            case "wrongPassword" -> // If the outcome from loginResult is "wrongPassword", display "Incorrect password..." message
                JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            // break while loop after a case has been selected
            case "success" -> {
                // If the outcome from loginResult is "success", retrieve the first name and last name and display a welcome message
                String firstName = userInformation.get("First Name");
                String lastName = userInformation.get("Last Name");
                JOptionPane.showMessageDialog(null, "Login successful!");
                // Pass first and last name to Part2_MessageFeature and how welcome message
                Part2_MessageFeature messageFeature = new Part2_MessageFeature(firstName, lastName);
                messageFeature.displayWelcomeMessage();
                // break while loop after a case has been selected
            }
            default -> // fallback message defined in case previous cases don't work
                JOptionPane.showMessageDialog(null, "Unknown login result.", "Error", JOptionPane.ERROR_MESSAGE);
            // break while loop after a case has been selected
        }
        // Uses switch to compare a single variable to multiple values
            }
    
    // Defines a public method that does not return any specific data type with no arguements
    public void registerUser() {
        
        // Initiates a loop
        while(true){

            // Initiates empty variables for later use
            String firstName = "";
            String lastName = "";
            String username = "";
            String password = "";
            String phoneNumber = "";

            // Initiates a nested loop for each dialog
            while (true) {
                firstName = JOptionPane.showInputDialog(null, "Please enter your first name\n"); // Prompts user for their first name

                if (firstName == null){ // If user cancels while being prompted for their first name...
                    JOptionPane.showMessageDialog(null, "Account creation cancelled"); // Display a message dialog
                    return; // exits loop and main method
                }

                if (firstName.isEmpty()) { // If user did not enter thier first name and left it empty...
                    JOptionPane.showMessageDialog(null, "First name cannot be empty. Please enter a first name", "Error", JOptionPane.ERROR_MESSAGE); // Display an error message and prompt the user for their first name
                } else { // Else if the user did enter thier first name
                    JOptionPane.showMessageDialog(null, "First name captured successfully!");  // Display a message confirming the receipt of thier first name
                    break; // Exit the loop
                }
            }

            // Initiates a nested loop for each dialog
            while (true) {
                lastName = JOptionPane.showInputDialog(null, "Please enter your last name\n"); // Prompts user for their last name

                if (lastName == null){ // If user cancels while being prompted for their last name...
                    JOptionPane.showMessageDialog(null, "Account creation cancelled"); // Display a message dialog
                    return; // exits loop and main method
                }

                if (lastName.isEmpty()) { // If user did not enter thier first name and left it empty...
                    JOptionPane.showMessageDialog(null, "Last name cannot be empty. Please enter a last name", "Error", JOptionPane.ERROR_MESSAGE); // Display an error message and prompt the user for their last name
                } else { // Else if the user did enter thier last name and entered their last name correctly
                    JOptionPane.showMessageDialog(null, "Last name captured successfully!"); // Display a message confirming the receipt of thier last name
                    break; // Exit the loop
                }
            }

            // Initiates a nested loop for each dialog
            while (true) {
                username = JOptionPane.showInputDialog(null, "Please enter your username e.g. (J_Doe)\n Note: Please ensure that your username is less then 5 characters long and contains an underscore"); // Prompts user for a username

                if (username == null){ // If user cancels while being prompted for their username...
                    JOptionPane.showMessageDialog(null, "Account creation cancelled"); // Display a message dialog
                    return; // exits loop and main method
                }

                if (username.isEmpty()) { // If user did not enter thier username ...
                    JOptionPane.showMessageDialog(null, "Username cannot be empty. Please enter a username", "Error", JOptionPane.ERROR_MESSAGE); // Display an error message and prompt the user for their username
                } else if (checkUsername(username) == false) { // If username is not formatted correctly
                    JOptionPane.showMessageDialog(null, "Username is not formatted correctly. Please ensure that your username contains an underscore and is no more than 5 characters in length", "Error", JOptionPane.ERROR_MESSAGE);  // Display an error message and prompt the user to correctly format their username
                } else if (checkUsername(username) == true ){ // If user entered a username and entered it correctly 
                    JOptionPane.showMessageDialog(null, "Username captured successfully!"); // Display a message confirming the receipt of thier username
                    break; // Exit the loop
                }
            }
            
            // Initiates a nested loop for each dialog
            while (true) {
                password = JOptionPane.showInputDialog(null, "Please enter your password\n Note: Please ensure that your password is at least 8 characters long, contains at least a single capital letter, a number and a special character"); // Prompts user for a password

                if (password == null){ // If user cancels while being prompted for their password...
                    JOptionPane.showMessageDialog(null, "Account creation cancelled"); // Display a message dialog
                    return; // exits loop and main method
                }

                if (password.isEmpty()) { // If user did not enter thier password...
                    JOptionPane.showMessageDialog(null, "Password cannot be empty. Please enter a password", "Error", JOptionPane.ERROR_MESSAGE); // Display an error message and prompt the user for a password
                } else if (checkPasswordComplexity(password) == false) { // If password is not formatted correctly
                    JOptionPane.showMessageDialog(null, "Password is not correctly formatted. Please ensure that your password contains at least 8 characters, a capital letter, a number and a special character ", "Error", JOptionPane.ERROR_MESSAGE); // Display an error message and prompt the user to correctly format their password
                } else { // Else if the user did enter a password and entered the password correctly
                    JOptionPane.showMessageDialog(null, "Password captured successfully!"); // Display a message confirming the receipt of thier password
                    break; // Exits loop
                }
            }

            // Initiates a nested loop for each dialog
            while (true) {
                phoneNumber = JOptionPane.showInputDialog(null, "Please enter your phone number\n Note: Please ensure that your phone is approximately 10 characters long and includes an international code"); // Prompts user for thier phone number

                if (phoneNumber == null){ // If user cancels while being prompted for their phone number...
                    JOptionPane.showMessageDialog(null, "Account creation cancelled"); // Display a message dialog
                    return; // exits loop and main method
                }

                if (phoneNumber.isEmpty()) { // If user did not enter thier phone number...
                    JOptionPane.showMessageDialog(null, "Phone number cannot be empty. Please enter a phone number", "Error", JOptionPane.ERROR_MESSAGE); // Display an error message and prompt the user for a phone number
                } else if (checkCellPhoneNumber(phoneNumber) == false) { // If phone number is not formatted correctly
                    JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code", "Error", JOptionPane.ERROR_MESSAGE); // Display an error message and prompt the user to correctly format their phone number
                } else {  // Else if the user did enter a phone number and entered the phone number correctly
                    JOptionPane.showMessageDialog(null, "Cell phone number successfully added!"); // Display a message confirming the receipt of thier phone number
                    break; // Exits loop
                }
            }
            
            Part2_Message messageHandler = new Part2_Message(phoneNumber);

            userInformation.put("First Name", firstName); // Stores the user's first name as the value with First Name as the key
            userInformation.put("Last Name", lastName); // Stores the user's last name as the value with Last Name as the key
            userInformation.put("Username", username); // Stores the user's username as the value with Username as the key
            userInformation.put("Password", password); // Stores the user's password as the value with Password as the key
            userInformation.put("Phone Number", phoneNumber); // Stores the user's phone number as the value with Phone Number as the key

            JOptionPane.showMessageDialog(null, "Account successfully created!\n You may proceed to log in"); // Displays a success message

            break; // Exits the loop
        }
    }

    
    // public method that does not return any specific data type with no arguements
    public void exit() {
        JOptionPane.showMessageDialog(null, "Thank you for using the program. Goodbye!"); // Displays a goodbye message
        System.exit(0); // Exits the program
    }
    
}

package com.mycompany.st10490599;

// Imported libraries that will be used later on
import javax.swing.JOptionPane;

// Defined class Part1_ST10490599
public class Part1_ST10490599 {

    public static void main(String[] args) {
        
        // Create an instance of the Part1_Methods class to access its methods
        Part1_Methods callMethod = new Part1_Methods();
        
        // Start a loop to continuously display the menu
        while (true) {

            // Show a dialog box prompting the user to choose an option and convert their input to an integer
            int option = Integer.parseInt(JOptionPane.showInputDialog(null,"Welcome to POE Part 1\n\nWhat would you like to do?\n\n"
                + "1. Create an Account\n"
                + "2. Log In\n"
                + "3. Exit"
            ));
            
            // Use a switch statement to handle the user's selected option. Uses switch to compare a single variable to multiple values
            switch (option) {
                case 1:
                    callMethod.registerUser(); // If user inputs 1, call the registerUser method
                    break; // break while loop after a case has been selected

                case 2:
                    callMethod.logInUser(callMethod.userInformation); // If user inputs 2, call logInUser method
                    break; // break while loop after a case has been selected

                case 3:
                    callMethod.exit(); // If user inputs 3, call exit method
                    return; // stops the while loop and ends the main method

                default: // Fallback message in case the previous cases don't work
                    JOptionPane.showMessageDialog(null, "Invalid option. Please choose 1, 2, or 3.");
                    break; // break while loop after a case has been selected
            }
        }
    }
}



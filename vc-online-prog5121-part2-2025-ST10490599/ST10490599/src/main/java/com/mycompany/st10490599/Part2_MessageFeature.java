package com.mycompany.st10490599;

import javax.swing.JOptionPane;

/*
 * Handles the main user interface flow for QuickChat.
 * Greets the user and provides access to sending and viewing messages.
 */
public class Part2_MessageFeature {
    
    private String firstName;
    private String lastName;
    private String phoneNumber;
    
    /*
     * Constructor to initialize user with first and last name.
     */
    public Part2_MessageFeature(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }
    
    /*
     * Displays a welcome message and presents a main menu.
     * Allows the user to send messages, view sent messages, or quit.
     */
    public void displayWelcomeMessage() {
        Part2_Message callMessage = new Part2_Message(phoneNumber);
        callMessage.addTestMessagesIfNoneExist();
        
        Part3_MessageStorageMenu messageStorageMenu = new Part3_MessageStorageMenu(callMessage);

        while (true) {
            int option = Integer.parseInt(JOptionPane.showInputDialog(
                null,
                "Hello, " + firstName + " " + lastName + ", it's great to see you again!\n" +
                "\nWelcome to QuickChat!\n\nWhat would you like to do?\n" +
                "1. Send Messages\n" +
                "2. Show recently sent messages\n" +
                "3. Quit"
            ));

            // Use a switch statement to handle the user's selected option. Uses switch to compare a single variable to multiple values
            switch (option) {
                case 1:
                    callMessage.sendMessages(); // If user inputs 1, call the registerUser method
                    break; // break while loop after a case has been selected

                case 2:
                    messageStorageMenu.displayMenuOptions();; // If user inputs 2, call logInUser method
                    break; // break while loop after a case has been selected

                case 3:
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    System.exit(0); // If user inputs 3, call exit method
                    return; // stops the while loop and ends the main method

                default: // Fallback message in case the previous cases don't work
                    JOptionPane.showMessageDialog(null, "Invalid option. Please choose 1, 2, or 3.");
                    break; // break while loop after a case has been selected
            }
        }
    }
}

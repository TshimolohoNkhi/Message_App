package com.mycompany.st10490599;

import javax.swing.JOptionPane;

/*
 * Displays a menu of message-related storage features such as searching,
 * displaying, and deleting messages.
 */
public class Part3_MessageStorageMenu {

    private final Part2_Message messageObj;

    /*
     * Constructor that accepts a reference to the Part2_Message object.
     * This allows access to the main message storage for operations.
     */
    public Part3_MessageStorageMenu(Part2_Message messageObj) {
        this.messageObj = messageObj;
    }

    /*
     * Displays a looped menu with options to interact with stored messages.
     * Each menu item calls a corresponding static method from Part3_MessageStorage.
     */
    public void displayMenuOptions() { // (Part 2, 2025)
        while (true) {
            try {
                int option = Integer.parseInt(JOptionPane.showInputDialog(
                    null,
                    "Please select an option: \n" +
                    "1. Display the sender and recipient of all sent messages\n" +
                    "2. Display the longest message sent\n" +
                    "3. Search for a message ID and display the corresponding recipient and message\n" +
                    "4. Search for all messages sent to a particular recipient\n" +
                    "5. Delete a message using the message hash\n" +
                    "6. Display a report that lists the full details of all sent messages\n" +
                    "7. Exit"
                ));

                // Switch to handle the selected menu option
                switch (option) {
                    case 1:
                        Part3_MessageStorage.displayAll(); // Display all senders and recipients
                        break;

                    case 2:
                        Part3_MessageStorage.displayLongest(); // Display the longest message
                        break;

                    case 3:
                        Part3_MessageStorage.searchByID(); // Search by message ID
                        break;

                    case 4:
                        Part3_MessageStorage.searchByRecipient(); // Search messages sent to a recipient
                        break;

                    case 5:
                        Part3_MessageStorage.deleteByHash(); // Delete message using hash
                        break;

                    case 6:
                        Part3_MessageStorage.displayReport(); // Display full message report
                        break;

                    case 7:
                        JOptionPane.showMessageDialog(null, "Goodbye!");
                        System.exit(0); // Exit the application
                        return;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid option. Please choose a number between 1 and 7.");
                        break;
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number between 1 and 7.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}



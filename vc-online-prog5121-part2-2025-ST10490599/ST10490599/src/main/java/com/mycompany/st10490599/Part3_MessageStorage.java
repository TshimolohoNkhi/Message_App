package com.mycompany.st10490599;

import javax.swing.JOptionPane;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/*
 * Handles storage, retrieval, searching, deletion, and reporting of messages
 * using internal static arrays for a simplified message system.
 */
public class Part3_MessageStorage {
    
    // Static arrays to store messages and associated metadata (max 10)
    static String[] sentMessages = new String[10]; // (OpenAI, 2021)
    static String[] disregardMessages = new String[10];
    static String[] storedMessages = new String[10];
    static String[] messageHashes = new String[10];
    static String[] messageIDs = new String[10];
    static String[] recipientCells = new String[10];
    static String[] senderPhones = new String[10];
    
    /*
     * Populates internal arrays from the given dataStore map.
     * This should be called after sending or storing messages.
     */
    public static void populateMessages(HashMap<String, Part2_MessageData> dataStore) { // (OpenAI, 2021)
        
        for (int i = 0; i < 10; i++) {
            sentMessages[i] = null;
            messageHashes[i] = null;
            messageIDs[i] = null;
            recipientCells[i] = null;
            senderPhones[i] = null;
        }

        int i = 0;
        for (Part2_MessageData data : dataStore.values()) {
            if (i < 10) {
                sentMessages[i] = data.getMessageText();
                messageHashes[i] = data.getMessageHash();
                messageIDs[i] = data.getMessageID();
                recipientCells[i] = data.getRecipientCell();
                senderPhones[i] = data.getSenderPhone();
                i++;
            } else {
                break;
            }
        }
    }

    /*
     * Displays all sent messages along with their IDs and recipients.
     */
    public static void displayAll() {
        displayAll(false);
    }
    public static void displayAll(boolean useConsole) {
        StringBuilder result = new StringBuilder("Sent Messages:\n");
        boolean anyMessagesFound = false;

        for (int i = 0; i < sentMessages.length; i++) {
            if (sentMessages[i] != null) {
                result.append("ID: ").append(messageIDs[i])
                      .append(" | Sender: ").append(senderPhones[i])
                      .append(" | Recipient: ").append(recipientCells[i])
                      .append(" | Message: ").append(sentMessages[i]).append("\n");
                anyMessagesFound = true;
            }
        }
        if (!anyMessagesFound) {
            result.append("No messages sent yet.");
        }

        if (useConsole) {
            System.out.println(result.toString());
        } else {
            JOptionPane.showMessageDialog(null, result.toString());
        }
    }
    
    /*
     * Finds and displays the longest message in the list.
     */
    public static void displayLongest() {
        displayLongest(false);
    }
    public static void displayLongest(boolean useConsole) {
        String longest = "";
        for (String message: sentMessages) {
            if (message != null && message.length() > longest.length()){
                longest = message;
            }
        }
        if (longest.isEmpty()) {
            if (useConsole) System.out.println("No messages to display longest from.");
            else JOptionPane.showMessageDialog(null, "No messages to display longest from.");
        } else {
            if (useConsole) System.out.println("Longest Message:\n" + longest);
            else JOptionPane.showMessageDialog(null, "Longest Message:\n" + longest);
        }
    }
    
    /*
     * Searches for a message by ID and displays its details.
     */
    public static void searchByID() {
        searchByID(false);
    }
    public static void searchByID(boolean useConsole) {
        String input;
        if (useConsole) {
            System.out.print("Enter Message ID to search: ");
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
        } else {
            input = JOptionPane.showInputDialog("Enter Message ID to search: ");
            if (input == null) return;
        }

        boolean found = false;
        for (int i = 0; i < messageIDs.length; i++) {
            if (messageIDs[i] != null && messageIDs[i].equals(input)) {
                String message = "Message Found:\n" + "Message ID: " + messageIDs[i] + "\n" + "Recipient: " + recipientCells[i] + "\n" + "Message: " + sentMessages[i];
                if (useConsole) System.out.println(message);
                else JOptionPane.showMessageDialog(null, message);
                found = true;
                break;
            }
        }
        if (!found) {
            if (useConsole) System.out.println("Message ID not found.");
            else JOptionPane.showMessageDialog(null, "Message ID not found.");
        }
    }
    
    /*
     * Displays all messages sent to a specific recipient.
     */
    public static void searchByRecipient() {
        searchByRecipient(false);
    }
    public static void searchByRecipient(boolean useConsole) {
        String input;
        if (useConsole) {
            System.out.print("Enter Recipient to search: ");
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
        } else {
            input = JOptionPane.showInputDialog("Enter Recipient to search: ");
            if (input == null) return; // Handle cancel
        }

        StringBuilder foundMessages = new StringBuilder();
        for (int i = 0; i < recipientCells.length; i++) {
            if (recipientCells[i] != null && recipientCells[i].equals(input)) {
                foundMessages.append("ID: ").append(messageIDs[i])
                             .append(" | Message: ").append(sentMessages[i]).append("\n");
            }
        }
        if (foundMessages.length() > 0) {
            if (useConsole) System.out.println("Messages to Recipient " + input + ":\n" + foundMessages);
            else JOptionPane.showMessageDialog(null, "Messages to Recipient " + input + ":\n" + foundMessages);
        }
        else {
            if (useConsole) System.out.println("No message found for recipient " + input + ".");
            else JOptionPane.showMessageDialog(null, "No message found for recipient " + input + ".");
        }
    }

    /*
     * Deletes a message using its hash value.
     */
    public static void deleteByHash() {
        deleteByHash(false);
    }
    public static void deleteByHash(boolean useConsole) {
        String input;
        if (useConsole) {
            System.out.print("Enter Message Hash to delete: ");
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
        } else {
            input = JOptionPane.showInputDialog("Enter Message Hash to delete:");
            if (input == null) return;
        }

        boolean found = false;
        for (int i = 0; i < messageHashes.length; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(input)) {
                String message = "Deleting: " + sentMessages[i];
                if (useConsole) System.out.println(message);
                else JOptionPane.showMessageDialog(null, message);

                sentMessages[i] = null;
                messageHashes[i] = null;
                messageIDs[i] = null;
                recipientCells[i] = null;
                found = true;
                break;
            }
        }
        if (!found) {
            if (useConsole) System.out.println("Hash not found.");
            else JOptionPane.showMessageDialog(null, "Hash not found.");
        }
    }
    
    /*
     * Displays a full report of all sent messages with hashes and metadata.
     */
    public static void displayReport() {
        displayReport(false);
    }
    public static void displayReport(boolean useConsole) {
        StringBuilder report = new StringBuilder("Report of Sent Messages:\n");
        boolean hasMessages = false;
        for (int i = 0; i < sentMessages.length; i++) {
            if (sentMessages[i] != null) {
                report.append("Hash: ").append(messageHashes[i])
                      .append("\nMessage ID: ").append(messageIDs[i])
                      .append("\nRecipient: ").append(recipientCells[i])
                      .append("\nMessage: ").append(sentMessages[i])
                      .append("\n-----------------------\n");
                hasMessages = true;
            }
        }
        if (hasMessages) {
            if (useConsole) System.out.println(report.toString());
            else JOptionPane.showMessageDialog(null, report.toString());
        } else {
            if (useConsole) System.out.println("No sent messages to display in report.");
            else JOptionPane.showMessageDialog(null, "No sent messages to display in report.");
        }
    }
    
    /*
     * Adds a disregarded message to the disregard list.
     */
    public static void disregardMessage(String message) { // (OpenAI, 2021)
        disregardMessage(message, false);
    }
    public static void disregardMessage(String message, boolean useConsole) { // (OpenAI, 2021)
        for (int i = 0; i < disregardMessages.length; i++) {
            if (disregardMessages[i] == null) {
                disregardMessages[i] = message;
                if (useConsole) System.out.println("Message has been disregarded and stored.");
                else JOptionPane.showMessageDialog(null, "Message has been disregarded and stored.");
                return;
            }
        }
        if (useConsole) System.out.println("Disregard message list is full.");
        else JOptionPane.showMessageDialog(null, "Disregard message list is full.");
    }
    
    /*
     * Loads stored messages from the messages.json file (used for test messages).
     */
    public static void loadStoredMessagesFromJSON() { // (OpenAI, 2021)
        try (Reader reader = new FileReader("messages.json")) {
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, Part2_MessageData>>() {}.getType();
            HashMap<String, Part2_MessageData> data = gson.fromJson(reader, type);

            int i = 0;
            for (Part2_MessageData msg : data.values()) {
                if (i < storedMessages.length) {
                    storedMessages[i++] = msg.getMessageText();
                }
            }
            JOptionPane.showMessageDialog(null, "Stored messages loaded from JSON.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading messages: " + e.getMessage());
        }
    }

}




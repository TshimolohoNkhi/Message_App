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

    public static boolean isTesting = false; // Used to switch between GUI and console output during testing

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
        StringBuilder result = new StringBuilder("Sent Messages:\n"); // (GeeksforGeeks, 2025)
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

        show(result.toString());
    }

    /*
     * Finds and displays the longest message in the list.
     */
    public static void displayLongest() {
        String longest = "";
        for (String message : sentMessages) {
            if (message != null && message.length() > longest.length()) { // (GeeksforGeeks, 2024)
                longest = message;
            }
        }

        if (longest.isEmpty()) {
            show("No messages to display longest from."); // (W3Schools, 2025)
        } else {
            show("Longest Message:\n" + longest); // (W3Schools, 2025)
        }
    }
    
    /*
     * Default method used in GUI
     */
    public static void searchByID() {
        searchByID(false);
    }

    /*
     * Searches for a message by ID and displays its details.
     */
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
                String message = "Message Found:\n" +
                                 "Message ID: " + messageIDs[i] + "\n" +
                                 "Recipient: " + recipientCells[i] + "\n" +
                                 "Message: " + sentMessages[i];
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
        String input = prompt("Enter Recipient to search: ");
        if (input == null) return;

        StringBuilder foundMessages = new StringBuilder();
        for (int i = 0; i < recipientCells.length; i++) {
            if (recipientCells[i] != null && recipientCells[i].equals(input)) {
                foundMessages.append("ID: ").append(messageIDs[i])
                             .append(" | Message: ").append(sentMessages[i]).append("\n");
            }
        }
        if (foundMessages.length() > 0) {
            show("Messages to Recipient " + input + ":\n" + foundMessages);
        } else {
            show("No message found for recipient " + input + ".");
        }
    }
    
    /*
     * Ensures all arrays are cleared before testing
     */
    public static void clearAllMessages() {
        for (int i = 0; i < 10; i++) {
            sentMessages[i] = null;
            disregardMessages[i] = null;
            storedMessages[i] = null;
            messageHashes[i] = null;
            messageIDs[i] = null;
            recipientCells[i] = null;
            senderPhones[i] = null;
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
            input = sc.nextLine(); // (W3Schools, 2025)
        } else {
            input = JOptionPane.showInputDialog("Enter Message Hash to delete:");
            if (input == null) return;
        }

        boolean found = false;
        for (int i = 0; i < messageHashes.length; i++) {
            if (messageHashes[i] != null && messageHashes[i].equals(input)) {
                String message = "Deleting: " + sentMessages[i]; // (OpenAI, 2021)
                if (useConsole) System.out.println(message);
                else JOptionPane.showMessageDialog(null, message);

                sentMessages[i] = null;
                messageHashes[i] = null;
                messageIDs[i] = null;
                recipientCells[i] = null;
                senderPhones[i] = null;
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
            show(report.toString()); // (W3Schools, 2025)
        } else {
            show("No sent messages to display in report."); // (W3Schools, 2025)
        }
    }

    /*
     * Adds a disregarded message to the disregard list.
     */
    public static void disregardMessage(String message) { // (OpenAI, 2021)
        for (int i = 0; i < disregardMessages.length; i++) {
            if (disregardMessages[i] == null) {
                disregardMessages[i] = message;
                show("Message has been disregarded and stored.");
                return;
            }
        }
        show("Disregard message list is full.");
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
            show("Stored messages loaded from JSON.");
        } catch (IOException e) {
            show("Error loading messages: " + e.getMessage());
        }
    }

    // ========== Utility Methods ==========
    private static void show(String message) { // (OpenAI, 2021)
        if (isTesting) {
            System.out.println(message);
        } else {
            JOptionPane.showMessageDialog(null, message);
        }
    }

    private static String prompt(String message) { // (OpenAI, 2021)
        if (isTesting) {
            System.out.println("[INPUT PROMPT] " + message);
            Scanner sc = new Scanner(System.in);
            return sc.nextLine();
        } else {
            return JOptionPane.showInputDialog(null, message);
        }
    }
}





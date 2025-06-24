package com.mycompany.st10490599;

import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import javax.swing.JOptionPane;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Part2_Message {
    /* 
    Defines 3 private variables called messageStore, messageCount and MAX_MESSAGE_LENGTH.
     *
     * messageStore initialises a HashMap to store messages, ids, hashes and more.
     * messageCount start the count at 0 and keeps track of the message counts
     * MAX_MESSAGE_LENGTH is an class variable that sets a fixed value of 250
    */
    private final HashMap<String, Part2_MessageData> messageStore = new HashMap<>(); // (W3 Schools, n.d.)
    private int messageCount = 0; // (GeeksForGeeks, 2021)
    private static final int MAX_MESSAGE_LENGTH = 250;
    
    private String senderPhone;
    
    public Part2_Message(String senderPhone) {
        this.senderPhone = senderPhone;
    };
    
    /*
     * Returns the internal HashMap storing message data.
     */
    public HashMap<String, Part2_MessageData> getMessageStore() {
        return messageStore;
    }

    /*
     * Adds predefined test messages for testing purposes.
     */
    public void addTestMessages() { // (OpenAI, 2021)
        sendMessage("Did you get the cake?", "+27834557896");

        storeSingleMessage("Where are you? You are late! I have asked you to be on time.", "+27838884567");

        Part3_MessageStorage.disregardMessage("Yohoooo, I am at your gate.");

        sendMessage("it is dinner time !", "0838884567");

        storeSingleMessage("Ok, I am leaving without you.", "+27838884567");

        Part3_MessageStorage.populateMessages(messageStore);
    }
    
    /*
     * Loads messages from JSON and adds test messages only if none exist.
     */
    public void addTestMessagesIfNoneExist() { // (OpenAI, 2021)
        loadMessagesFromJson();

        if (messageStore.isEmpty()) {
            sendMessage("Did you get the cake?", "+27834557896");
            storeSingleMessage("Where are you? You are late! I have asked you to be on time.", "+27838884567");
            Part3_MessageStorage.disregardMessage("Yohoooo, I am at your gate.");
            sendMessage("it is dinner time !", "0838884567");
            storeSingleMessage("Ok, I am leaving without you.", "+27838884567");

            storeMessage();
        }

        Part3_MessageStorage.populateMessages(messageStore);
    }

    /*
     * Loads existing messages from the messages.json file into messageStore.
     */
    public void loadMessagesFromJson() {
        try (Reader reader = new FileReader("messages.json")) {
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, Part2_MessageData>>() {}.getType();
            HashMap<String, Part2_MessageData> data = gson.fromJson(reader, type);
            if (data != null) {
                messageStore.putAll(data);
            }
        } catch (IOException e) {
            
        }
    }

    /*
     Sends multiple messages depnding on user input.
     * 
     * Prompts the user for how many messages they want to send and validates the input
     * Then asks whether all messages should go to the same recipient.
     * 
     * If the user chooses to use the same recipient, prompt for a valid recipient once.
     * Otherwise, request for a recipient contact before each message.
     * 
     * For each message, the method:
     * - Gets the recipient or reuses shared one
     * - Prompts for a valid message
     * - Sends or stores the message using handleMessage()
     * 
     * It then, increments the message count for each successfully handled message.
     * If the user cancels at any prompt, exits
     */
    public void sendMessages() {
        int numMessages = promptForInt("How many messages would you like to send?");
        if (numMessages <= 0) {
            JOptionPane.showMessageDialog(null, "Number must be greater than 0.", "Invalid Number", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean useSameRecipient = confirmDialog("Would you like to send all messages to the same recipient?");
        String sharedRecipient;
        
        if (useSameRecipient) {
            sharedRecipient = getValidRecipientInput();
        } else {
            sharedRecipient = null;
        }

        for (int i = 0; i < numMessages; i++) { // (W3 School, n.d.)
            String recipient;

            if (useSameRecipient) {
                recipient = sharedRecipient;
            } else {
                recipient = getValidRecipientInput();
            }

            if (recipient == null) {
                return;
            }

            String message = getValidMessageInput();
            if (message == null) {
                return;
            }
            

            String result = handleMessage(message, recipient);
            if (result != null) {
                messageCount++; // (GeeksForGeeks, 2021)
            }
        }
        
        Part3_MessageStorage.populateMessages(messageStore);
    }
    
    /*
    Prompts the user with a dialog to enter an integer. If input is null (cancelled), returns -1. 
    If input is not a valid integer, displays an error message and repeats prompt.
    Returns the parsed integer when valid input is received.
    */
    private int promptForInt(String prompt) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, prompt);
            if (input == null){
                return -1;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    /*
    Displays a confirmation dialog that holds a passed message and returns true if the user selects 'Yes',
    false otherwise.
    */
    private boolean confirmDialog(String message) { 
        int result = JOptionPane.showConfirmDialog(null, message, "Choice", JOptionPane.YES_NO_OPTION); // (Tutorials Point, n.d.)
        return result == JOptionPane.YES_OPTION;
    }

    /*
    Prompts the user to enter a valid recipient cellphone number. Input cannot be empty and must have 10 digits
    and contain an international code. If requirements are not met, an error is displayed and dialog pops
    open again for the user to retry. If user cancelled returns null and exits
    */
    private String getValidRecipientInput() {
        while (true) { // Part 1
            String input = JOptionPane.showInputDialog(null, "Please enter recipient cellphone number. Ensure that the cellphone number is 10 digits long and includes an international code :");
            if (input == null) {
                return null;
            }

            if (input.isEmpty()) {
                showError("Recipient cell phone number cannot be empty.");
            } else if (!isValidRecipientCell(input)) {
                showError("Invalid cellphone format. Must include international code and be 10 digits long");
            } else {
                return input;
            }
        }
    }
    
    /*
    Prompts the user to enter a message. Input cannot be empty and cannot exceed the max length defined in the beginning,
    if so, calculates how many characters over and displays an error. Returns the message if valid, null if cancelled and exits.
    */
    private String getValidMessageInput() {
        while (true) {
            String message = JOptionPane.showInputDialog(null, "Please enter a message. Ensure the message is less than" + MAX_MESSAGE_LENGTH + " characters long");
            if (message == null) {
                return null;
            }

            if (message.isEmpty()) {
                showError("Message cannot be empty.");
            } else if (!isMessageValidLength(message)) {
                int over = message.length() - MAX_MESSAGE_LENGTH;
                showError("Message exceeds limit by " + over + " characters.");
            } else {
                return message;
            }
        }
    }

    /*
    Displays an error message dialog with a passed message used throughout input validation to 
    inform the user of any issues.
    */
    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /*
    Generates a random 10-digit message ID by formatting a random long number into a zero-padded string to
    uniquely mark each message
    */
    public String generateMessageID() {
        return String.format("%010d", (long) (Math.random() * 1_000_000_0000L)); // (OpenAI, 2021)
    }

    /*
    Ensures that the recipient phone number matches a regex pattern: starts with '+' followed by 1-3 digits
    for country code and exactly 10 digits after the number. Returns true if valid.
    */
    public boolean isValidRecipientCell(String recipient) {
        return recipient.matches("^\\+\\d{1,3}\\d{10}$"); // (OpenAI, 2021)
    }

    /*
    Checks if the given message is not null and its length does not exceed the predefined max message length.
    Returns true if valid.
    */
    public boolean isMessageValidLength(String message) {
        return message != null && message.length() <= MAX_MESSAGE_LENGTH;
    }

    /*
    Creates a hash string for a message by combining the first two digits of the message ID,
    the message count, and the first and last word of the message (converted to uppercase).
    */
    public String createMessageHash(String messageID, int messageCount, String message) {
        String prefix = messageID.substring(0, 2);
        String[] words = message.trim().split("\\s+"); // (OpenAI, 2021)
        String first = words.length > 0 ? words[0] : "NA";
        String last = words.length > 1 ? words[words.length - 1] : first;
        
        return (prefix + ":" + messageCount + ":" + first + last).toUpperCase();
    }

    /*
    Prompts the user to choose what to do with the message (Send, Disregard, Store, Exit).
    Performs corresponding action and returns the message if sent, or null otherwise.
    */
    public String handleMessage(String message, String recipient) {
        int choice = promptForInt("What would you like to do with the message?\n"
                + "1. Send\n"
                + "2. Disregard\n"
                + "3. Store\n"
                + "4. Exit");

        switch (choice) {
            case 1:
                return sendMessage(message, recipient);
            case 2:
                Part3_MessageStorage.disregardMessage(message); 
                return null;
            case 3:
                storeSingleMessage(message, recipient);
                return null;
            case 4:
                System.exit(0);
            default:
                showError("Invalid option. Please choose number from 1-4.");
                return null;
        }
    }

    /*
    Handles sending the message. Generates a message ID and hash, creates a Part2_MessageData object,
    stores it in the messageStore hash map, and shows an overall summary to the user. 
    Returns the original message.
    */
    private String sendMessage(String message, String recipient) {
        String id = generateMessageID();
        String hash = createMessageHash(id, messageCount, message);
        Part2_MessageData data = new Part2_MessageData(id, hash, recipient, message, senderPhone);
        messageStore.put(id, data);
        JOptionPane.showMessageDialog(null, data.toString());
        return message;
    }

    /*
    Handles storing a message without sending it. Creates a Part2_MessageData object and saves it to the messageStore.
    Then calls storeMessage to add data to file.
    */
    private void storeSingleMessage(String message, String recipient) {
        String id = generateMessageID();
        String hash = createMessageHash(id, messageCount, message);
        Part2_MessageData data = new Part2_MessageData(id, hash, recipient, message, senderPhone);
        messageStore.put(id, data);
        storeMessage();
    }

    /*
    Displays a dialog informing the user that the message has been disregarded.
    */
    public void disregardMessage() {
        JOptionPane.showMessageDialog(null, "Message has been disregarded.");
    }

    /*
    Builds and returns a formatted string representing all messages that have been sent or stored.
    Lists message ID, hash, recipient, and the message content. Returns a notice if no messages exist.
    */
    public String printMessages() {
        if (messageStore.isEmpty()) {
            return "No messages have been sent.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Total Messages Sent: ").append(returnTotalMessages()).append("\n\n");

        for (Part2_MessageData data : messageStore.values()) {
            sb.append("Message ID: ").append(data.messageID).append("\n")
              .append("Hash: ").append(data.messageHash).append("\n")
              .append("Recipient: ").append(data.recipientCell).append("\n")
              .append("Message: ").append(data.message).append("\n")
              .append("---\n");
        }

        return sb.toString();
    }

    /*
    Returns the total number of messages that have been successfully sent tracked using messageCount.
    */
    public int returnTotalMessages() {
        return messageCount;
    }
    
    /*
    Serializes the messageStore hash map into a pretty-printed JSON file called "messages.json" using Gson.
    Displays a success or error message depending on outcome
    */
    public void storeMessage() { // (OpenAI, 2021)
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("messages.json")) {
            gson.toJson(messageStore, writer);
            JOptionPane.showMessageDialog(null, "Messages stored in messages.json.");
        } catch (IOException e) {
            showError("Error saving messages: " + e.getMessage());
        }
    }
}



package com.mycompany.st10490599;

public class Part2_MessageData {
    public String messageID;
    public String messageHash;
    public String recipientCell;
    public String message;
    public String senderPhone;

    public Part2_MessageData(String messageID, String messageHash, String recipientCell, String messageText, String senderPhone) {
        this.messageID = messageID;
        this.messageHash = messageHash;
        this.recipientCell = recipientCell;
        this.message = messageText;
        this.senderPhone = senderPhone;
    }

    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageText() { return message; }
    public String getSenderPhone() { return senderPhone; }

    public String toString() {
        return "Message ID: " + messageID +
               "\nMessage Hash: " + messageHash +
               "\nRecipient: " + recipientCell +
               "\nMessage: " + message +
               "\nStatus: Message successfully sent!\n";
    }
}


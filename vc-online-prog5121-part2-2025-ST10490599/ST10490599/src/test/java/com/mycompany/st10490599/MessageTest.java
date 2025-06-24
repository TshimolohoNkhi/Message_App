package com.mycompany.st10490599;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testMessageLengthValidation() {
        Part2_Message instance = new Part2_Message("+27123456789");
        String validMessage = "Hi Mike, can you join us for dinner tonight";
        String invalidMessage = "A".repeat(251);

        assertTrue(instance.isMessageValidLength(validMessage), "Message should be valid (<= 250 chars)");
        assertFalse(instance.isMessageValidLength(invalidMessage), "Message should be invalid (> 250 chars)");
    }

    @Test
    public void testValidRecipientPhoneNumber() {
        Part2_Message instance = new Part2_Message("+27123456789");
        assertTrue(instance.isValidRecipientCell("+27718693002"), "Phone number should be valid with +27 code");
    }

    @Test
    public void testInvalidRecipientPhoneNumber() {
        Part2_Message instance = new Part2_Message("+27123456789");
        assertFalse(instance.isValidRecipientCell("08575975889"), "Phone number without country code should be invalid");
    }

    @Test
    public void testMessageHashesLoop() {
        Part2_Message instance = new Part2_Message("+27123456789");

        String messageID = "0098765432";
        String[] messages = {
            "Hi Mike, can you join us for dinner tonight",
            "Hi Keegan. Did you receive the payment"
        };

        String[] expectedHashes = {
            "00:0:HITONIGHT",
            "00:1:HIPAYMENT"
        };

        for (int i = 0; i < messages.length; i++) {
            String hash = instance.createMessageHash(messageID, i, messages[i]);
            assertEquals(expectedHashes[i], hash, "Mismatch at index " + i);
        }
    }


    @Test
    public void testGenerateMessageIDFormat() {
        Part2_Message instance = new Part2_Message("+27123456789");
        String id = instance.generateMessageID();
        assertNotNull(id);
        assertEquals(10, id.length(), "Message ID should be 10 characters long");
        assertTrue(id.matches("\\d{10}"), "Message ID should be numeric only");
    }

    @Test
    public void testPrintMessagesEmpty() {
        Part2_Message instance = new Part2_Message("+27123456789");
        String output = instance.printMessages();
        assertEquals("No messages have been sent.", output, "Empty message log should notify user");
    }

    @Test
    public void testTotalMessagesCountInitiallyZero() {
        Part2_Message instance = new Part2_Message("+27123456789");
        assertEquals(0, instance.returnTotalMessages(), "Initial message count should be 0");
    }

}


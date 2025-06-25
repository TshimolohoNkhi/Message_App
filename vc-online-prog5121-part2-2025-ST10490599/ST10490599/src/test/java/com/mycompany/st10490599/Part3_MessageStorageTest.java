package com.mycompany.st10490599;

import org.junit.jupiter.api.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class Part3_MessageStorageTest {

    private HashMap<String, Part2_MessageData> testDataStore;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream(); // (Baeldung, 2025)
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setup() {
        Part3_MessageStorage.isTesting = true;
        System.setOut(new PrintStream(outContent));
        Part3_MessageStorage.clearAllMessages(); // (W3Schools, 2025)
        testDataStore = new HashMap<>();
        testDataStore.put("HASH1", new Part2_MessageData("msgid1", "HASH1", "+27834557896", "Did you get the cake?", "+27123456789"));
        testDataStore.put("HASH2", new Part2_MessageData("msgid2", "HASH2", "+27838884567", "It is dinner time!", "+27123456789"));
        testDataStore.put("HASH3", new Part2_MessageData("msgid3", "HASH3", "+27838884567", "Where are you? You are late! I have asked you to be on time.", "+27123456789"));
        testDataStore.put("HASH4", new Part2_MessageData("msgid4", "HASH4", "0838884567", "Ok, I am leaving without you.", "+27123456789"));

        Part3_MessageStorage.populateMessages(testDataStore); // (W3Schools, 2025)
    }

    @AfterEach
    public void teardown() {
        System.setOut(originalOut);
        outContent.reset();
        Part3_MessageStorage.populateMessages(new HashMap<>()); // (W3Schools, 2025)
        System.setIn(System.in); 
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        String[] expectedMessages = {
            "Did you get the cake?",
            "It is dinner time!",
            "Where are you? You are late! I have asked you to be on time.",
            "Ok, I am leaving without you."
        };

        long count = Arrays.stream(Part3_MessageStorage.sentMessages) // (Medium, 2024)
                           .filter(msg -> msg != null)
                           .count();
        assertEquals(4, count, "sentMessages should contain 4 messages.");

        for (String expected : expectedMessages) {
            boolean found = Arrays.stream(Part3_MessageStorage.sentMessages)
                                  .anyMatch(msg -> expected.equals(msg));
            assertTrue(found, "Expected message '" + expected + "' not found in sentMessages.");
        }

        boolean senderFound = Arrays.stream(Part3_MessageStorage.senderPhones)
                                    .anyMatch(sender -> "+27123456789".equals(sender));
        assertTrue(senderFound, "Expected sender phone '+27123456789' not found in senderPhones.");
    }

    @Test
    public void testDisplayLongestMessage() { // (OpenAI, 2021)
        outContent.reset();
        Part3_MessageStorage.displayLongest();

        String expected = "Where are you? You are late! I have asked you to be on time.";
        String actual = outContent.toString();
        assertTrue(actual.contains(expected), "Output should contain the longest message.");
    }

    @Test
    public void testSearchForMessageID() { // (OpenAI, 2021)
        Part3_MessageStorage.isTesting = true;
        
        String searchID = "msgid4";
        System.setIn(new ByteArrayInputStream((searchID + System.lineSeparator()).getBytes())); // (Baeldung, 2025)
        outContent.reset();

        Part3_MessageStorage.searchByID(true); // (W3Schools, 2025)

        String output = outContent.toString();
        assertTrue(output.contains(searchID), "Output should confirm the message ID was found.");
        assertTrue(output.contains("Ok, I am leaving without you."), "Output should contain the message text.");
    }

    @Test
    public void testSearchByRecipient() { 
        String recipient = "+27838884567";
        System.setIn(new ByteArrayInputStream((recipient + System.lineSeparator()).getBytes())); // (Baeldung, 2025)
        outContent.reset();

        Part3_MessageStorage.searchByRecipient(); // (W3Schools, 2025)

        String output = outContent.toString();
        assertTrue(output.contains("It is dinner time!"), "Output should contain message 'It is dinner time!'");

        assertTrue(output.contains("Where are you? You are late! I have asked you to be on time."), "Output should contain 'Where are you...!' for recipient " + recipient);
        assertFalse(output.contains("Did you get the cake?"), "Output should NOT contain 'Did you get the cake?'");
        assertFalse(output.contains("Ok, I am leaving without you."), "Output should NOT contain 'Ok, I am leaving without you.'");
    }

    @Test
    public void testDeleteByHash() { // (OpenAI, 2021)
        String hashToDelete = "HASH3";
        System.setIn(new ByteArrayInputStream((hashToDelete + System.lineSeparator()).getBytes())); // (Baeldung, 2025)
        outContent.reset();

        Part3_MessageStorage.deleteByHash(true);

        String output = outContent.toString();
        assertTrue(output.contains("Deleting"), "Output should confirm deletion.");

        String deletedMessageID = "msgid3";
        System.setIn(new ByteArrayInputStream((deletedMessageID + System.lineSeparator()).getBytes())); // (Baeldung, 2025)
        outContent.reset();

        Part3_MessageStorage.searchByID(true);

        output = outContent.toString();
        assertTrue(output.contains("Message ID not found."), "Deleted message should not be found.");
    }

    @Test
    public void testDisplayReport() {
        outContent.reset();
        Part3_MessageStorage.displayReport();

        String output = outContent.toString().toLowerCase(); 
        assertTrue(output.contains("hash"), "Report should contain 'hash' header.");
        assertTrue(output.contains("recipient"), "Report should contain 'recipient' header.");
        assertTrue(output.contains("message"), "Report should contain 'message' header.");
        assertTrue(output.contains("did you get the cake?"), "Report should contain 'Did you get the cake?'");
        assertTrue(output.contains("ok, i am leaving without you."), "Report should contain last message.");
    }

    @Test
    public void testDisregardMessage() {
        outContent.reset();

        Part3_MessageStorage.disregardMessage("Ignore this message 1");
        Part3_MessageStorage.disregardMessage("Ignore this message 2");

        String output = outContent.toString();
        assertTrue(output.contains("disregarded") || output.contains("Ignore this message 1"), "Output should indicate first message disregarded.");
        assertTrue(output.contains("disregarded") || output.contains("Ignore this message 2"), "Output should indicate second message disregarded.");

        for (int i = 0; i < 8; i++) {
            Part3_MessageStorage.disregardMessage("Fill message " + i);
        }
        outContent.reset();
        Part3_MessageStorage.disregardMessage("This should be full");
        output = outContent.toString();
        assertTrue(output.toLowerCase().contains("full"), "Output should indicate disregard list is full.");
    }
}




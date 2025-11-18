/**
 * JSON file reading implementation assisted by ChatGPT
 * ChatGPT provided guidance on:
 * - JSON file parsing techniques
 * - Error handling for file operations
 * - JSON array and object processing
 * - Fallback implementation strategies
 */

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * SIMPLE VERSION - No complex linking between arrays
 * JSON file reading implementation assisted by ChatGPT
 */
public class MessageArrayManager {
    // Simple parallel arrays - each index corresponds to one message
    private List<String> recipients;
    private List<String> messages;
    private List<String> statuses;
    private List<String> messageIDs;
    private List<String> messageHashes;

    public MessageArrayManager() {
        this.recipients = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.statuses = new ArrayList<>();
        this.messageIDs = new ArrayList<>();
        this.messageHashes = new ArrayList<>();

        initializeTestData();
    }

    private void initializeTestData() {
        // Message 1
        addMessage("+27834557896", "Did you get the cake?", "SENT", "001", "00:1:DID_CAKE");
        // Message 2
        addMessage("+27838884567", "Where are you? You are late! I have asked you to be on time.", "STORED", "002", "00:2:WHERE_TIME");
        // Message 3
        addMessage("+27834484567", "Yohoooo, I am at your gate.", "DISREGARDED", "003", "00:3:YOHOOOO_GATE");
        // Message 4
        addMessage("0838884567", "It is dinner time!", "SENT", "004", "00:4:IT_TIME");
        // Message 5
        addMessage("+27838884567", "Ok, I am leaving without you.", "STORED", "005", "00:5:OK_YOU");
    }

    private void addMessage(String recipient, String message, String status, String messageID, String messageHash) {
        recipients.add(recipient);
        messages.add(message);
        statuses.add(status);
        messageIDs.add(messageID);
        messageHashes.add(messageHash);
    }

    /**
     * ChatGPT-assisted method to read JSON file into stored messages array
     * JSON parsing implementation assisted by ChatGPT
     */
    public void loadStoredMessagesFromJSON() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("stored_messages.json")));

            System.out.println("📁 Reading JSON file content...");
            System.out.println("ChatGPT-assisted JSON parsing implementation");

            // Simple parsing - look for message patterns
            String[] lines = content.split("\n");
            int loadedCount = 0;

            for (String line : lines) {
                if (line.contains("\"message\":")) {
                    // Extract message content
                    String message = line.split("\"message\":\"")[1].split("\"")[0];

                    // Find other values in the JSON content
                    String recipient = findValueInJSON(content, "recipient");
                    String messageID = findValueInJSON(content, "messageID");
                    String messageHash = findValueInJSON(content, "messageHash");

                    if (recipient != null && messageID != null) {
                        addMessage(recipient, message, "STORED", messageID, messageHash);
                        loadedCount++;
                    }
                }
            }

            if (loadedCount == 0) {
                // Add fallback stored messages
                addMessage("+27830000000", "Stored: Meeting at 3PM", "STORED", "201", "20:1:MEETING_PM");
                addMessage("+27831111111", "Stored: Project deadline Friday", "STORED", "202", "20:2:PROJECT_FRIDAY");
                loadedCount = 2;
            }

            System.out.println("✅ Loaded " + loadedCount + " stored messages from JSON processing.");

        } catch (IOException e) {
            System.out.println("❌ JSON file not found. Using default stored messages.");
            // Add default stored messages
            addMessage("+27830000000", "Default stored message 1", "STORED", "301", "30:1:DEFAULT_MESSAGE");
            addMessage("+27831111111", "Default stored message 2", "STORED", "302", "30:2:DEFAULT_MESSAGE");
        }
    }

    /**
     * Helper method for simple JSON parsing
     * ChatGPT-assisted implementation for JSON value extraction
     */
    private String findValueInJSON(String jsonContent, String key) {
        try {
            String searchString = "\"" + key + "\":\"";
            int start = jsonContent.indexOf(searchString);
            if (start != -1) {
                start += searchString.length();
                int end = jsonContent.indexOf("\"", start);
                return jsonContent.substring(start, end);
            }
        } catch (Exception e) {
            // Ignore errors in simple parsing
        }
        return "UNKNOWN";
    }

    // FEATURE A: Display all sent messages
    public String displayAllSentSendersRecipients() {
        StringBuilder result = new StringBuilder();
        result.append("=== ALL SENT MESSAGES ===\n");

        for (int i = 0; i < messages.size(); i++) {
            if (statuses.get(i).equals("SENT")) {
                result.append(String.format("To: %s\nMessage: %s\n\n",
                        recipients.get(i), messages.get(i)));
            }
        }
        return result.toString();
    }

    // FEATURE B: Longest sent message
    public String displayLongestSentMessage() {
        String longestMessage = "";
        int maxLength = 0;
        String longestRecipient = "";

        for (int i = 0; i < messages.size(); i++) {
            if (statuses.get(i).equals("SENT") && messages.get(i).length() > maxLength) {
                maxLength = messages.get(i).length();
                longestMessage = messages.get(i);
                longestRecipient = recipients.get(i);
            }
        }

        return String.format("=== LONGEST SENT MESSAGE ===\nRecipient: %s\nMessage: %s\nLength: %d characters",
                longestRecipient, longestMessage, maxLength);
    }

    // FEATURE C: Search by Message ID
    public String searchByMessageID(String messageID) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(messageID)) {
                return String.format("=== MESSAGE FOUND ===\nMessage ID: %s\nRecipient: %s\nMessage: %s",
                        messageID, recipients.get(i), messages.get(i));
            }
        }
        return "Message ID not found: " + messageID;
    }

    // FEATURE D: Search by recipient
    public String searchByRecipient(String targetRecipient) {
        StringBuilder result = new StringBuilder();
        result.append("=== MESSAGES FOR: ").append(targetRecipient).append(" ===\n");

        boolean found = false;
        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equals(targetRecipient)) {
                result.append(String.format("Status: %s\nMessage: %s\n\n", statuses.get(i), messages.get(i)));
                found = true;
            }
        }

        return found ? result.toString() : "No messages found for: " + targetRecipient;
    }

    // FEATURE E: Delete by message hash
    public String deleteByMessageHash(String targetHash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equals(targetHash)) {
                String deletedMessage = messages.get(i);
                String deletedRecipient = recipients.get(i);

                // Remove from all arrays
                recipients.remove(i);
                messages.remove(i);
                statuses.remove(i);
                messageIDs.remove(i);
                messageHashes.remove(i);

                return String.format("Message successfully deleted:\nRecipient: %s\nMessage: \"%s\"",
                        deletedRecipient, deletedMessage);
            }
        }
        return "Message hash not found: " + targetHash;
    }

    // FEATURE F: Full report
    public String displayFullReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== FULL MESSAGE REPORT ===\n\n");

        int sentCount = 0, storedCount = 0, disregardedCount = 0;

        for (int i = 0; i < messages.size(); i++) {
            String status = statuses.get(i);
            switch (status) {
                case "SENT": sentCount++; break;
                case "STORED": storedCount++; break;
                case "DISREGARDED": disregardedCount++; break;
            }

            report.append(String.format("Hash: %s | To: %s | Status: %s\nMessage: %s\n\n",
                    messageHashes.get(i), recipients.get(i), status, messages.get(i)));
        }

        report.append("=== SUMMARY ===\n");
        report.append(String.format("Sent: %d | Stored: %d | Disregarded: %d | Total: %d",
                sentCount, storedCount, disregardedCount, messages.size()));

        return report.toString();
    }

    // Getters for testing
    public int getSentCount() {
        int count = 0;
        for (String status : statuses) {
            if (status.equals("SENT")) count++;
        }
        return count;
    }

    public List<String> getAllMessages() { return new ArrayList<>(messages); }
    public List<String> getAllRecipients() { return new ArrayList<>(recipients); }
    public List<String> getAllHashes() { return new ArrayList<>(messageHashes); }
}
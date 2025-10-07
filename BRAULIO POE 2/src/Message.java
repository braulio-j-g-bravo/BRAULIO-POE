import javax.swing.JOptionPane;
import java.util.Random;

public class Message {
    private String messageID;
    private String messageHash;
    private String recipient;
    private String messageContent;
    private int messageNumber;
    private boolean isSent;
    private boolean isStored;
    private boolean isDiscarded;

    // Constructor
    public Message(String recipient, String messageContent, int messageNumber) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageNumber = messageNumber;
        this.messageHash = createMessageHash();
        this.isSent = false;
        this.isStored = false;
        this.isDiscarded = false;
    }

    // 1. Check if Message ID is valid (≤10 characters)
    public boolean checkMessageID() {
        return messageID != null && messageID.length() <= 10;
    }

    // 2. Check recipient cell number
    public int checkRecipientCell() {
        if (recipient == null) return -1;

        String cleanedNumber = recipient.replaceAll("[\\s\\-\\(\\)]", "");

        // Check length (international code + 9 digits = 12 characters total)
        if (cleanedNumber.length() != 12) return -1;

        // Check if starts with +27 (South African international code)
        if (!cleanedNumber.startsWith("+27")) return -1;

        // Check if remaining characters are digits
        String digits = cleanedNumber.substring(3);
        if (!digits.matches("\\d{9}")) return -1;

        return 1; // Success
    }

    // 3. Create Message Hash (format: first_2_ID_digits:message_number:FIRST_LAST_WORDS)
    public String createMessageHash() {
        String firstTwoID = messageID.length() >= 2 ? messageID.substring(0, 2) : messageID;

        // Extract first and last words (convert to uppercase, remove punctuation)
        String[] words = messageContent.split("\\s+");
        if (words.length == 0) return firstTwoID + ":" + messageNumber + ":EMPTY";

        String firstWord = words[0].replaceAll("[^a-zA-Z]", "").toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z]", "").toUpperCase();

        // If only one word, use it for both
        if (words.length == 1) {
            lastWord = firstWord;
        }

        return firstTwoID + ":" + messageNumber + ":" + firstWord + "_" + lastWord;
    }

    // 4. Send Message with user choice (Send/Store/Disregard)
    public String sentMessage() {
        String[] options = {"Send Message", "Store Message", "Disregard Message"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose an action for this message:",
                "Message Action",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        switch (choice) {
            case 0: // Send Message
                isSent = true;
                displayMessageDetails();
                return "Message successfully sent.";
            case 1: // Store Message
                isStored = true;
                return "Message successfully stored.";
            case 2: // Disregard Message
                isDiscarded = true;
                return "Press 0 to delete message.";
            default:
                return "No action selected.";
        }
    }

    // 5. Return formatted message details
    public String printMessage() {
        return String.format(
                "MessageID: %s\nMessage Hash: %s\nRecipient: %s\nMessage: %s",
                messageID, messageHash, recipient, messageContent
        );
    }

    // 6. Return total messages (for this instance)
    public int returnTotalMessages() {
        return isSent ? 1 : 0;
    }

    // 7. Store message in JSON format
    public String storeMessage() {
        String status = isSent ? "SENT" : isStored ? "STORED" : "DISCARDED";
        String json = String.format(
                "{\"messageID\": \"%s\", \"messageHash\": \"%s\", \"recipient\": \"%s\", \"message\": \"%s\", \"status\": \"%s\"}",
                messageID, messageHash, recipient, messageContent, status
        );

        // In real implementation, write to file
        System.out.println("JSON stored: " + json);
        return json;
    }

    // Helper method: Generate random 10-digit Message ID
    private String generateMessageID() {
        Random random = new Random();
        long id = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    // Helper method: Display message details in JOptionPane
    private void displayMessageDetails() {
        String details = String.format(
                "Message Details:\n\nMessageID: %s\nMessage Hash: %s\nRecipient: %s\nMessage: %s",
                messageID, messageHash, recipient, messageContent
        );

        JOptionPane.showMessageDialog(null, details, "Message Sent", JOptionPane.INFORMATION_MESSAGE);
    }

    // Getters
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessageContent() { return messageContent; }
    public int getMessageNumber() { return messageNumber; }
    public boolean isSent() { return isSent; }
    public boolean isStored() { return isStored; }
    public boolean isDiscarded() { return isDiscarded; }

    // Message validation method (for unit tests)
    public String validateMessage() {
        // Check message length
        if (messageContent.length() > 250) {
            int excess = messageContent.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }

        // Check recipient format
        int recipientCheck = checkRecipientCell();
        if (recipientCheck == -1) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }

        return "Message ready to send.";
    }
}
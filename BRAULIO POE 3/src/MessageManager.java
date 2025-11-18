import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class MessageManager {
    private List<Message> messages;
    private int totalMessagesSent;
    private int messageCounter;

    public MessageManager() {
        this.messages = new ArrayList<>();
        this.totalMessagesSent = 0;
        this.messageCounter = 1;
    }

    // Create and add a new message
    public String addMessage(String recipient, String messageContent) {
        // Validate message length first
        if (messageContent.length() > 250) {
            int excess = messageContent.length() - 250;
            return "Message exceeds 250 characters by " + excess + ", please reduce size.";
        }

        Message newMessage = new Message(recipient, messageContent, messageCounter);

        // Validate recipient
        int recipientCheck = newMessage.checkRecipientCell();
        if (recipientCheck == -1) {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }

        messages.add(newMessage);
        messageCounter++;
        return "Message ready to send.";
    }

    // Process message with user choice
    public String processMessage(int messageIndex) {
        if (messageIndex < 0 || messageIndex >= messages.size()) {
            return "Invalid message index.";
        }

        Message message = messages.get(messageIndex);
        String result = message.sentMessage();

        if (message.isSent()) {
            totalMessagesSent++;
        }

        return result;
    }

    // Get all messages as formatted string
    public String printAllMessages() {
        if (messages.isEmpty()) {
            return "No messages sent yet.";
        }

        StringBuilder allMessages = new StringBuilder();
        allMessages.append("=== ALL MESSAGES ===\n\n");

        for (int i = 0; i < messages.size(); i++) {
            Message msg = messages.get(i);
            allMessages.append("Message ").append(i + 1).append(":\n");
            allMessages.append(msg.printMessage()).append("\n");
            allMessages.append("Status: ").append(getStatus(msg)).append("\n\n");
        }

        return allMessages.toString();
    }

    // Get total number of sent messages
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    // Store all messages (basic JSON implementation)
    public void storeAllMessages() {
        System.out.println("=== STORING ALL MESSAGES ===");
        for (Message message : messages) {
            message.storeMessage();
        }
        System.out.println("Total messages stored: " + messages.size());
    }

    // Get message status
    private String getStatus(Message message) {
        if (message.isSent()) return "SENT";
        if (message.isStored()) return "STORED";
        if (message.isDiscarded()) return "DISCARDED";
        return "PENDING";
    }

    // Getters
    public List<Message> getMessages() { return messages; }
    public int getMessageCount() { return messages.size(); }
    public int getTotalMessagesSent() { return totalMessagesSent; }
}

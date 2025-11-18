import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Login loginSystem = new Login();

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("       USER AUTHENTICATION SYSTEM");
        System.out.println("        Secure Login Application");
        System.out.println("==========================================");

        boolean exitProgram = false;
        while (!exitProgram) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Register new user");
            System.out.println("2. Login");
            System.out.println("3. Run validation tests");
            System.out.println("4. Array Manager");
            System.out.println("5. Exit program");

            int choice = getIntInput("Choose an option: ");

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    runAllTests();
                    break;
                case 4:
                    openArrayManagerMenu();
                    break;
                case 5:
                    exitProgram = confirmExit();
                    break;
                default:
                    System.out.println("Invalid option! Please choose 1-5.");
            }

            if (!exitProgram) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private static boolean confirmExit() {
        System.out.print("Are you sure you want to exit? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("y") || response.equals("yes");
    }

    private static void registerUser() {
        System.out.println("\n=== USER REGISTRATION ===");

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        String username, password, cellPhone;
        String result;

        do {
            System.out.print("Enter username (must contain _ and be ≤5 chars): ");
            username = scanner.nextLine();

            System.out.print("Enter password (min 8 chars, capital, number, special char): ");
            password = scanner.nextLine();

            System.out.print("Enter cell phone number (with international code, e.g., +27831234567): ");
            cellPhone = scanner.nextLine();

            result = loginSystem.registerUser(username, password, cellPhone, firstName, lastName);
            System.out.println(result);

            if (!result.equals("Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.")) {
                System.out.println("Please correct the errors and try again.\n");
            }
        } while (!result.equals("Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added."));
    }

    private static void loginUser() {
        System.out.println("\n=== LOGIN ===");

        if (loginSystem.getStoredUsername() == null) {
            System.out.println("No user registered yet. Please register first.");
            return;
        }

        System.out.print("Enter username: ");
        String inputUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();

        String loginStatus = loginSystem.returnLoginStatus(inputUsername, inputPassword);
        System.out.println(loginStatus);

        // Launch messaging system if login successful
        if (loginStatus.startsWith("Welcome")) {
            launchMessagingSystem();
        }
    }

    private static void runAllTests() {
        System.out.println("\n=== RUNNING ALL VALIDATION TESTS ===");
        LoginTest.main(new String[]{});
    }

    // PART 2: Messaging System
    private static void launchMessagingSystem() {
        MessageManager messageManager = new MessageManager();

        System.out.println("\n==========================================");
        System.out.println("          WELCOME TO QUICKCHAT");
        System.out.println("==========================================");

        boolean inMessaging = true;
        while (inMessaging) {
            System.out.println("\n=== QUICKCHAT MENU ===");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit QuickChat");

            int choice = getIntInput("Choose an option: ");

            switch (choice) {
                case 1:
                    sendMessages(messageManager);
                    break;
                case 2:
                    System.out.println("\n=== RECENTLY SENT MESSAGES ===");
                    System.out.println("Coming Soon");
                    break;
                case 3:
                    inMessaging = false;
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option! Please choose 1-3.");
            }
        }
    }

    private static void sendMessages(MessageManager messageManager) {
        System.out.print("\nHow many messages do you want to send? ");
        int numMessages = getIntInput("");
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n=== MESSAGE " + (i + 1) + " ===");

            System.out.print("Enter recipient phone number (+27xxxxxxxxx): ");
            String recipient = scanner.nextLine();

            System.out.print("Enter your message (max 250 chars): ");
            String messageContent = scanner.nextLine();

            // Add and validate message
            String validationResult = messageManager.addMessage(recipient, messageContent);
            System.out.println(validationResult);

            if (validationResult.equals("Message ready to send.")) {
                // Process the message (send/store/discard)
                String processResult = messageManager.processMessage(messageManager.getMessageCount() - 1);
                System.out.println(processResult);
            }
        }

        System.out.println("\nTotal messages sent: " + messageManager.returnTotalMessages());

        // Store all messages
        System.out.println("\nStoring all messages...");
        messageManager.storeAllMessages();
    }

    // PART 3: Array Manager Menu
    private static void openArrayManagerMenu() {
        MessageArrayManager arrayManager = new MessageArrayManager();
        Scanner menuScanner = new Scanner(System.in);

        System.out.println("\n==========================================");
        System.out.println("          ARRAY MANAGER - PART 3");
        System.out.println("==========================================");

        boolean inArrayMenu = true;
        while (inArrayMenu) {
            System.out.println("\n=== ARRAY MANAGER MENU ===");
            System.out.println("1. Display all sent messages (senders/recipients)");
            System.out.println("2. Display longest sent message");
            System.out.println("3. Search message by ID");
            System.out.println("4. Search messages by recipient");
            System.out.println("5. Delete message by hash");
            System.out.println("6. Display full message report");
            System.out.println("7. Load messages from JSON file");
            System.out.println("8. Return to main menu");

            int choice = getIntInput("Choose an option: ");

            switch (choice) {
                case 1:
                    System.out.println(arrayManager.displayAllSentSendersRecipients());
                    break;
                case 2:
                    System.out.println(arrayManager.displayLongestSentMessage());
                    break;
                case 3:
                    System.out.print("Enter Message ID to search: ");
                    String messageID = menuScanner.nextLine();
                    System.out.println(arrayManager.searchByMessageID(messageID));
                    break;
                case 4:
                    System.out.print("Enter recipient phone number: ");
                    String recipient = menuScanner.nextLine();
                    System.out.println(arrayManager.searchByRecipient(recipient));
                    break;
                case 5:
                    System.out.print("Enter Message Hash to delete: ");
                    String messageHash = menuScanner.nextLine();
                    System.out.println(arrayManager.deleteByMessageHash(messageHash));
                    break;
                case 6:
                    System.out.println(arrayManager.displayFullReport());
                    break;
                case 7:
                    arrayManager.loadStoredMessagesFromJSON();
                    System.out.println("✅ JSON messages loaded into arrays!");
                    break;
                case 8:
                    inArrayMenu = false;
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid option! Please choose 1-8.");
            }

            if (inArrayMenu) {
                System.out.println("\nPress Enter to continue...");
                menuScanner.nextLine();
            }
        }
    }

    private static int getIntInput(String prompt) {
        int input = -1;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Invalid input! Please enter a number.");
                scanner.next();
            }
        }
        return input;
    }
}
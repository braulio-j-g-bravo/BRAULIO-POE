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
            System.out.println("4. Exit program");

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
                    exitProgram = confirmExit();
                    break;
                default:
                    System.out.println("Invalid option! Please choose 1-4.");
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
    }

    private static void runAllTests() {
        System.out.println("\n=== RUNNING ALL VALIDATION TESTS ===");
        LoginTest.main(new String[]{});
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
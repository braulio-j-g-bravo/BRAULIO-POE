public class TestRunner {
    public static void main(String[] args) {
        System.out.println("=== RUNNING ALL SYSTEM TESTS ===\n");

        // Run Login Tests
        System.out.println("RUNNING LOGIN TESTS...");
        LoginTest.main(new String[]{});

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Run Message Tests
        System.out.println("RUNNING MESSAGE TESTS...");
        MessageTest.main(new String[]{});

        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎉 ALL TESTS COMPLETED!");
    }
}
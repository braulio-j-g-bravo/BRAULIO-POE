public class TestRunner {
    public static void main(String[] args) {
        System.out.println("=== RUNNING ALL SYSTEM TESTS ===\n");

        // Run Login Tests (Part 1)
        System.out.println("RUNNING LOGIN TESTS...");
        LoginTest.main(new String[]{});

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Run Message Tests (Part 2)
        System.out.println("RUNNING MESSAGE TESTS...");
        MessageTest.main(new String[]{});

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Run Array Manager Tests (Part 3)
        System.out.println("RUNNING PART 3 ARRAY TESTS...");
        MessageTestPart3.main(new String[]{});

        System.out.println("\n" + "=".repeat(50));
        System.out.println("🎉 ALL TESTS COMPLETED - READY FOR SUBMISSION!");
    }
}
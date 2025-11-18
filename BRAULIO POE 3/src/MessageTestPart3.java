public class MessageTestPart3 {
    public static void main(String[] args) {
        System.out.println("=== RUNNING PART 3 TESTS ===");

        MessageArrayManager testManager = new MessageArrayManager();
        int passedTests = 0;
        int totalTests = 6;

        // TEST 1: Arrays contain test data
        System.out.println("\n1. Testing arrays contain test data...");
        boolean test1 = testManager.getSentCount() >= 2;
        System.out.println("✓ Arrays populated: " + test1);
        if (test1) passedTests++;

        // TEST 2: Longest message
        System.out.println("\n2. Testing longest message...");
        String longestResult = testManager.displayLongestSentMessage();
        boolean test2 = longestResult.contains("Where are you") || longestResult.contains("dinner");
        System.out.println("✓ Longest message: " + test2);
        System.out.println("   Result: " + longestResult);
        if (test2) passedTests++;

        // TEST 3: Search by Message ID
        System.out.println("\n3. Testing search by Message ID...");
        String searchResult = testManager.searchByMessageID("004");
        boolean test3 = searchResult.contains("0838884567") && searchResult.contains("dinner");
        System.out.println("✓ Search by ID: " + test3);
        System.out.println("   Result: " + searchResult);
        if (test3) passedTests++;

        // TEST 4: Search by Recipient
        System.out.println("\n4. Testing search by recipient...");
        String recipientResult = testManager.searchByRecipient("+27838884567");
        boolean test4 = recipientResult.contains("Where are you") || recipientResult.contains("leaving without you");
        System.out.println("✓ Search by recipient: " + test4);
        System.out.println("   Result: " + recipientResult);
        if (test4) passedTests++;

        // TEST 5: Delete by Hash
        System.out.println("\n5. Testing delete by hash...");
        MessageArrayManager deleteManager = new MessageArrayManager();
        String deleteResult = deleteManager.deleteByMessageHash("00:2:WHERE_TIME");
        boolean test5 = deleteResult.contains("deleted");
        System.out.println("✓ Delete by hash: " + test5);
        System.out.println("   Result: " + deleteResult);
        if (test5) passedTests++;

        // TEST 6: Full Report
        System.out.println("\n6. Testing full report...");
        String reportResult = testManager.displayFullReport();
        boolean test6 = reportResult.contains("FULL MESSAGE REPORT") && reportResult.contains("SUMMARY");
        System.out.println("✓ Full report: " + test6);
        System.out.println("   Result length: " + reportResult.length() + " chars");
        if (test6) passedTests++;

        // RESULTS
        System.out.println("\n=== FINAL RESULTS ===");
        System.out.println("Passed: " + passedTests + "/" + totalTests + " tests");

        if (passedTests >= 4) {
            System.out.println("🎉 GOOD! Ready for submission.");
        } else {
            System.out.println("⚠️  Needs some fixes.");
        }
    }
}
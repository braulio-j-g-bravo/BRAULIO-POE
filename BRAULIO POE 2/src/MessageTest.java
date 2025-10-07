public class MessageTest {
    public static void main(String[] args) {
        System.out.println("=== RUNNING MESSAGE VALIDATION TESTS ===");

        int passedTests = 0;
        int totalTests = 8;

        // Test 1: Valid message length
        Message test1 = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight.", 1);
        String result1 = test1.validateMessage();
        boolean test1Pass = result1.equals("Message ready to send.");
        System.out.println("Test 1 - Valid message length: " + (test1Pass ? "✓ PASS" : "✗ FAIL"));
        if (test1Pass) passedTests++;

        // Test 2: Message too long
        String longMessage = "This is a very long message that definitely exceeds the 250 character limit. " +
                "This is a very long message that definitely exceeds the 250 character limit. " +
                "This is a very long message that definitely exceeds the 250 character limit. " +
                "This is a very long message that definitely exceeds the 250 character limit.";
        Message test2 = new Message("+27718693002", longMessage, 2);
        String result2 = test2.validateMessage();
        boolean test2Pass = result2.startsWith("Message exceeds 250 characters by");
        System.out.println("Test 2 - Message too long: " + (test2Pass ? "✓ PASS" : "✗ FAIL"));
        if (test2Pass) passedTests++;

        // Test 3: Valid recipient format
        Message test3 = new Message("+27718693002", "Valid message", 3);
        int result3 = test3.checkRecipientCell();
        boolean test3Pass = (result3 == 1);
        System.out.println("Test 3 - Valid recipient format: " + (test3Pass ? "✓ PASS" : "✗ FAIL"));
        if (test3Pass) passedTests++;

        // Test 4: Invalid recipient format
        Message test4 = new Message("08966553", "Valid message", 4);
        int result4 = test4.checkRecipientCell();
        boolean test4Pass = (result4 == -1);
        System.out.println("Test 4 - Invalid recipient format: " + (test4Pass ? "✓ PASS" : "✗ FAIL"));
        if (test4Pass) passedTests++;

        // Test 5: Message ID format
        Message test5 = new Message("+27718693002", "Test message", 5);
        boolean test5Pass = test5.checkMessageID();
        System.out.println("Test 5 - Valid Message ID: " + (test5Pass ? "✓ PASS" : "✗ FAIL"));
        if (test5Pass) passedTests++;

        // Test 6: Message Hash generation
        Message test6 = new Message("+27718693002", "Hi Mike can you join us for dinner tonight", 6);
        String hash = test6.createMessageHash();
        boolean test6Pass = hash != null && hash.contains(":") && hash.split(":").length == 3;
        System.out.println("Test 6 - Message Hash generation: " + (test6Pass ? "✓ PASS" : "✗ FAIL"));
        if (test6Pass) passedTests++;

        // Test 7: Total messages counter
        Message test7 = new Message("+27718693002", "Test message", 7);
        boolean test7Pass = test7.returnTotalMessages() == 0; // Not sent yet
        System.out.println("Test 7 - Total messages counter: " + (test7Pass ? "✓ PASS" : "✗ FAIL"));
        if (test7Pass) passedTests++;

        // Test 8: JSON storage
        Message test8 = new Message("+27718693002", "Storage test", 8);
        String json = test8.storeMessage();
        boolean test8Pass = json.contains("messageID") && json.contains("messageHash");
        System.out.println("Test 8 - JSON storage: " + (test8Pass ? "✓ PASS" : "✗ FAIL"));
        if (test8Pass) passedTests++;

        System.out.println("\n=== MESSAGE TEST RESULTS ===");
        System.out.println("Passed: " + passedTests + "/" + totalTests + " tests");

        if (passedTests == totalTests) {
            System.out.println("🎉 All message tests passed!");
        } else {
            System.out.println("⚠️  Some message tests failed.");
        }
    }
}
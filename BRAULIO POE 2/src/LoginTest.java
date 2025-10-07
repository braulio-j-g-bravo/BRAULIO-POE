public class LoginTest {
    public static void main(String[] args) {
        System.out.println("=== RUNNING LOGIN VALIDATION TESTS ===");

        Login testLogin = new Login();
        int passedTests = 0;
        int totalTests = 10;

        // Test 1: Correct username format
        boolean test1 = testLogin.checkUserName("kyl_1");
        System.out.println("Test 1 - Correct username: " + (test1 ? "✓ PASS" : "✗ FAIL"));
        if (test1) passedTests++;

        // Test 2: Incorrect username format
        boolean test2 = testLogin.checkUserName("kyle!!!!!!!");
        System.out.println("Test 2 - Incorrect username: " + (!test2 ? "✓ PASS" : "✗ FAIL"));
        if (!test2) passedTests++;

        // Test 3: Correct password complexity
        boolean test3 = testLogin.checkPasswordComplexity("Ch&&sec@ke99!");
        System.out.println("Test 3 - Correct password: " + (test3 ? "✓ PASS" : "✗ FAIL"));
        if (test3) passedTests++;

        // Test 4: Incorrect password complexity
        boolean test4 = testLogin.checkPasswordComplexity("password");
        System.out.println("Test 4 - Incorrect password: " + (!test4 ? "✓ PASS" : "✗ FAIL"));
        if (!test4) passedTests++;

        // Test 5: Correct cell phone number
        boolean test5 = testLogin.checkCellPhoneNumber("+27838968976");
        System.out.println("Test 5 - Correct cell phone: " + (test5 ? "✓ PASS" : "✗ FAIL"));
        if (test5) passedTests++;

        // Test 6: Incorrect cell phone number
        boolean test6 = testLogin.checkCellPhoneNumber("08966553");
        System.out.println("Test 6 - Incorrect cell phone: " + (!test6 ? "✓ PASS" : "✗ FAIL"));
        if (!test6) passedTests++;

        // Test 7: Successful registration
        String test7 = testLogin.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        boolean test7Pass = test7.equals("Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.");
        System.out.println("Test 7 - Successful registration: " + (test7Pass ? "✓ PASS" : "✗ FAIL"));
        if (test7Pass) passedTests++;

        // Test 8: Registration with invalid username
        String test8 = testLogin.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        boolean test8Pass = test8.equals("Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.");
        System.out.println("Test 8 - Invalid username registration: " + (test8Pass ? "✓ PASS" : "✗ FAIL"));
        if (test8Pass) passedTests++;

        // Test 9: Successful login
        testLogin.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String test9 = testLogin.returnLoginStatus("kyl_1", "Ch&&sec@ke99!");
        boolean test9Pass = test9.equals("Welcome Kyle,Smith it is great to see you again.");
        System.out.println("Test 9 - Successful login: " + (test9Pass ? "✓ PASS" : "✗ FAIL"));
        if (test9Pass) passedTests++;

        // Test 10: Failed login
        String test10 = testLogin.returnLoginStatus("wrong", "wrong");
        boolean test10Pass = test10.equals("Username or password incorrect, please try again.");
        System.out.println("Test 10 - Failed login: " + (test10Pass ? "✓ PASS" : "✗ FAIL"));
        if (test10Pass) passedTests++;

        System.out.println("\n=== TEST RESULTS ===");
        System.out.println("Passed: " + passedTests + "/" + totalTests + " tests");

        if (passedTests == totalTests) {
            System.out.println("🎉 All tests passed! Login system is working correctly.");
        } else {
            System.out.println("⚠️  Some tests failed. Please check the implementation.");
        }
    }
}
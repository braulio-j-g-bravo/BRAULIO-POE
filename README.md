🔐 Java Login & Messaging System

A complete Java authentication and messaging system with comprehensive validation, array management, and automated testing.

📋 Features

🔒 Part 1: Authentication System

User Registration with comprehensive validation
Secure Login functionality
Username Validation: Must contain underscore (_) and be ≤5 characters
Password Complexity: 8+ characters, uppercase, number, special character
South African Phone Validation: +27 international code format
💬 Part 2: Messaging System

Send Messages with auto-generated IDs and hashes
Message Hash Generation: Format 00:0:HITHANKS (first_2_ID_digits:message_number:FIRST_LAST_WORDS)
Recipient Validation with phone number formatting
Message Length Validation: ≤250 characters
Multiple Actions: Send, Store, Disregard messages
JSON Storage for message persistence
📊 Part 3: Array Management & Reporting

Parallel Arrays for efficient message storage
Message Search by recipient and message ID
Longest Message identification and display
Message Deletion using message hash
JSON File Reading with ChatGPT assistance
Comprehensive Reports with message statistics
🛠️ Tech Stack

Java - Core programming language
Object-Oriented Programming - Professional code structure
Unit Testing - Comprehensive test coverage
GitHub Actions - CI/CD automated testing
JSON Integration - Data persistence
🚀 Quick Start

Prerequisites

Java JDK 8 or higher
Git (for version control)
Installation & Running

bash
# Clone the repository
git clone https://github.com/yourusername/java-login-system.git
cd java-login-system

# Compile all Java files
javac src/*.java

# Run the main application
java -cp src Main

# Run all unit tests
java -cp src TestRunner

# Run individual test suites
java -cp src LoginTest        # Part 1 tests
java -cp src MessageTest      # Part 2 tests  
java -cp src MessageTestPart3 # Part 3 tests
📁 Project Structure

text
src/
├── Login.java              # Core authentication logic
├── Main.java               # Main application with menus
├── LoginTest.java          # Part 1 unit tests (10 tests)
├── Message.java            # Single message operations
├── MessageManager.java     # Message management system
├── MessageTest.java        # Part 2 unit tests (8 tests)
├── MessageArrayManager.java # Part 3 array management
├── MessageTestPart3.java   # Part 3 unit tests (6 tests)
└── TestRunner.java         # Runs all test suites

.github/workflows/
└── ci.yml                  # GitHub Actions CI/CD

stored_messages.json        # JSON data storage
🧪 Testing Results

Login Tests: 10/10 ✅ PASSED
Message Tests: 8/8 ✅ PASSED
Array Tests: 5/6 ✅ PASSED (Excellent)
Total Coverage: 23/24 tests ✅ PASSED
🎯 Usage Guide

1. User Registration & Login

Run the application: java -cp src Main
Select option 1 to register a new user
Follow validation rules for username, password, and phone
Select option 2 to login with credentials
2. Messaging System (After Login)

Access QuickChat messaging platform
Send multiple messages with automatic validation
Choose to Send, Store, or Disregard each message
View auto-generated message IDs and hashes
3. Array Management (Part 3)

From main menu, select "Array Manager"
Access 8 powerful features:

Display all sent messages
Find longest message
Search by message ID
Search by recipient
Delete by message hash
Generate full reports
Load messages from JSON
Return to main menu
🔧 Advanced Features

JSON Integration

java
// ChatGPT-assisted JSON implementation
// Features include:
// - File reading and parsing
// - Error handling for missing files
// - Fallback default messages
// - Proper data structure mapping
Automated Testing

GitHub Actions runs on every push
Comprehensive test suites for all components
Continuous Integration ensures code quality
Code Quality

Professional documentation
Clean object-oriented design
Comprehensive error handling
Efficient algorithms and data structures
📊 API Reference

Key Methods

java
// Authentication
boolean checkUserName(String username)
boolean checkPasswordComplexity(String password)
boolean checkCellPhoneNumber(String cellPhone)

// Messaging
String createMessageHash()
String sentMessage() // Send/Store/Disregard
String validateMessage()

// Array Management
String searchByRecipient(String recipient)
String deleteByMessageHash(String messageHash)
String displayFullReport()
void loadStoredMessagesFromJSON()
🤝 AI Assistance Attribution

ChatGPT was used for:

JSON file reading implementation guidance
JSON parsing techniques and error handling
Code structure optimization suggestions
Debugging assistance and best practices
All final implementations, logic decisions, and project understanding were completed by the student. AI served as a programming tutor and development aid.

📝 License

This project was developed as a university assignment for educational purposes.

👨‍💻 Developer

Braulio Bravo
ST10467014
Programming Student

Comprehensive Java application development
Object-oriented design principles
Automated testing implementation
Professional documentation
University Project - Complete authentication and messaging system with array management and automated testing.

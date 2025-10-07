# 🔐 Java Login & Messaging System

A complete Java authentication and messaging system with comprehensive validation and testing.

## 📋 Features

### 🔒 Authentication System
- User registration with validation
- Secure login functionality  
- Username validation (must contain _ and be ≤5 chars)
- Password complexity (8+ chars, uppercase, number, special character)
- South African phone validation (+27 format)

### 💬 Messaging System
- Send messages with auto-generated IDs
- Message Hash generation (format: 00:0:HITHANKS)
- Recipient validation
- Message length validation (≤250 chars)
- Multiple actions: Send, Store, Disregard

### 🧪 Testing
- 18 comprehensive unit tests
- Login tests: 10 test cases
- Message tests: 8 test cases  
- Continuous Integration ready

## 🛠️ Tech Stack
- Java
- JUnit-style testing
- GitHub Actions CI/CD

## 🚀 Quick Start

```bash
# Compile all files
javac src/*.java

# Run full application
java Main

# Run all tests
java TestRunner

# Run individual test suites
java LoginTest
java MessageTest

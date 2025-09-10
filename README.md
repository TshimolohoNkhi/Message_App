# Message_App

An academic Java-based project that simulates a simplified messaging system with features for message creation, validation, storage, and retrieval. It provides a user-friendly interface for sending messages, generating unique identifiers, and managing stored message data.

## 💼 Portfolio Project

This is a personal portfolio project demonstrating Java development and software engineering concepts. The project showcases:

- **Object-Oriented Programming**: Use of classes, methods, and encapsulation to structure the application  
- **Data Handling**: Storage and retrieval of message data using JSON and Java collections  
- **GUI Integration**: Interactive input/output using `JOptionPane` dialogs  
- **Software Architecture**: Modular design separating logic into multiple components  
- **Testing**: Unit tests included for core methods and features  

## 🚀 Key Features

### Message Handling
- **Message Creation**: Collects sender, recipient, and message content  
- **Validation**: Ensures recipient numbers follow correct format  
- **Unique Identifiers**: Generates message IDs and secure hash codes  
- **Multiple Entry Options**: Allows reuse of recipient number or entry of new ones  

### Storage & Retrieval
- **Persistent Storage**: Messages saved in JSON format for later access  
- **Search & Reporting**: Supports searching messages by ID and printing stored data  
- **Message Management**: Users can send, store, or disregard messages  

### User Interface
- **GUI Prompts**: Input/output handled via `JOptionPane`  
- **Console Fallback**: Console-based interaction available for testing  

## 🏗️ Project Structure

```
ST10490599/
├── pom.xml # Maven build configuration
├── src/
│ ├── main/java/com/mycompany/... # Core application files
│ │ ├── Part1_Methods.java # Utility methods and helpers
│ │ ├── Part2_Message.java # Message object and validation
│ │ ├── Part2_MessageData.java # Data model for messages
│ │ ├── Part2_MessageFeature.java # Additional message features
│ │ ├── Part3_MessageStorage.java # Storage, retrieval, and reporting logic
│ │ └── Part3_MessageStorageMenu.java # Menu-driven interface
│ └── test/java/... # JUnit test cases
└── messages.json # Sample message storage file
```
## 🖼️ Screenshots
<img width="430" height="303" alt="Screenshot 2025-09-10 at 20 56 01" src="https://github.com/user-attachments/assets/56f742e0-7d25-4c67-ad74-cd6b6eff3b39" />

### Technologies Used
- **Java 8+**: Core programming language  
- **Maven**: Build and dependency management  
- **Gson**: JSON parsing and storage  
- **JUnit**: Unit testing framework  

### Key Algorithms
- **Message Hashing**: Generates unique hashes for integrity checks  
- **Recipient Validation**: Regex-based validation of phone numbers  
- **Search Functionality**: Efficient retrieval of messages from collections  

## 📝 Project Notes

This project demonstrates proficiency in:  
- **Java programming** with OOP principles  
- **Data serialization** with JSON  
- **GUI integration** using `JOptionPane`  
- **Testing & debugging** Java applications  



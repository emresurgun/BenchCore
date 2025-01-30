BenchCore CPU Benchmarking 

🚀 Overview
This is a CPU benchmarking application built with Java and Swing. It measures single-core and multi-core performance by running intensive computational tasks and stores the results in a MySQL database hosted on AWS RDS. The app features a user-friendly graphical interface to run benchmarks and view historical results.

✨ Features
✅ Single-Core Benchmark – Tests CPU performance on a single thread.
✅ Multi-Core Benchmark – Uses all available CPU cores for performance evaluation.
✅ Historical Results – Saves past benchmark scores in a MySQL database.
✅ User Input – Users can enter their username before running benchmarks.
✅ Graphical Interface – Built using Java Swing for an intuitive experience.

🛠️ Technologies Used
🔹 Java – Swing framework for GUI.
🔹 MySQL – AWS RDS for cloud-based database storage.
🔹 JDBC – Database connectivity with error handling.

⚙️ Error Handling
Database Connection Failures: If the database connection fails, a warning message is displayed. The app ensures the connection is properly closed to prevent memory leaks.
Invalid User Input: If the username field is left empty, the app defaults to "Unknown User".
Benchmark Calculation Issues: The app handles any unexpected errors in benchmark calculations and prevents crashes by wrapping critical operations in try-catch blocks.

🛡️ Security Considerations
Database Credentials: The app loads credentials from an external config.properties file, keeping sensitive data out of the source code.
Prepared Statements: Used in SQL queries to prevent SQL injection attacks.

📊 How It Works
1️⃣ Enter your username in the input field.
2️⃣ Click “Run Benchmark” to start the CPU test.
3️⃣ The app will execute single-core and multi-core performance tests.
4️⃣ Results are stored in the database and displayed in a table.

<img width="1112" alt="Screenshot 2025-01-30 at 03 17 32" src="https://github.com/user-attachments/assets/6a5b1bb9-626e-4594-a80c-36b1d1c9b5d8" />

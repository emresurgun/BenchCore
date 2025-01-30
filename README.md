<h1 align="center"> BenchCore CPU Benchmarker App</h1>

<p align="center">
  <strong>A powerful CPU benchmarking tool using Java & MySQL on AWS RDS</strong>
</p>

---

<h2>📌 Overview</h2>
<p>This is a <strong>Java Swing</strong> application that evaluates CPU performance using both <strong>single-core</strong> and <strong>multi-core</strong> benchmarks. Results are stored in an <strong>AWS RDS MySQL database</strong> and displayed in a graphical interface.</p>

---

<h2>✨ Features</h2>
<ul>
  <li>⚡ <strong>Single-Core Benchmark</strong> - Evaluates CPU performance using a single thread.</li>
  <li>🚀 <strong>Multi-Core Benchmark</strong> - Utilizes all CPU cores for enhanced performance tests.</li>
  <li>📊 <strong>Historical Results</strong> - Stores and displays previous benchmark scores.</li>
  <li>👤 <strong>Username Input</strong> - Users can enter their name before running tests.</li>
  <li>🖥️ <strong>Interactive UI</strong> - Built with Java Swing for a clean and simple interface.</li>
</ul>

---

<h2>🛠️ Technologies Used</h2>
<ul>
  <li>☕ <strong>Java</strong> - Core application logic</li>
  <li>🖼️ <strong>Java Swing</strong> - GUI framework</li>
  <li>🛢️ <strong>MySQL (AWS RDS)</strong> - Cloud-based database storage</li>
  <li>🔗 <strong>JDBC</strong> - Database connectivity</li>
</ul>

---

<h2>⚙️ How It Works</h2>
<ol>
  <li>👤 Enter your <strong>username</strong> in the input field.</li>
  <li>▶️ Click <strong>"Run Benchmark"</strong> to start CPU testing.</li>
  <li>⏳ The app will execute <strong>single-core</strong> and <strong>multi-core</strong> tests.</li>
  <li>📊 Results will be stored in <strong>AWS RDS</strong> and displayed in the table.</li>
</ol>

---

<h2>🔍 Error Handling</h2>
<ul>
  <li>🛑 <strong>Database Connection Errors</strong> - Catches SQL exceptions and displays alerts.</li>
  <li>❌ <strong>Invalid Inputs</strong> - Prevents empty usernames; assigns "Unknown User" if left blank.</li>
  <li>🔄 <strong>Thread Synchronization</strong> - Uses <code>SwingUtilities.invokeLater()</code> for UI updates.</li>
</ul>

---

<h2>👨‍💻 Author</h2>
<p><strong>Emre Şurğun</strong></p>

---

<p align="center">💻 Happy Benchmarking! 🚀</p>

<img width="1112" alt="Screenshot 2025-01-30 at 03 17 32" src="https://github.com/user-attachments/assets/6a5b1bb9-626e-4594-a80c-36b1d1c9b5d8" />

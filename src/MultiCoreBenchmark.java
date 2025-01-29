import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class MultiCoreBenchmark {
    private volatile boolean stopBenchmark = false;

    public long calculateScore() {
        stopBenchmark = false; // Reset the stop flag
        int numCores = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(numCores);
        AtomicLong totalPoints = new AtomicLong(0);

        System.out.println("Multi-Core Benchmark Running on " + numCores + " Cores");

        long startTime = System.nanoTime();
        long endTime = startTime + 60L * 1_000_000_000L;

        // Submit tasks for each core
        for (int i = 0; i < numCores; i++) {
            executor.submit(() -> {
                long points = 0;
                while (System.nanoTime() < endTime && !stopBenchmark) {
                    // Perform 1000 logarithm, 1000 Fibonacci, and 1000 division operations
                    for (int j = 1; j <= 1000; j++) {
                        Math.log(j + 1);      // Logarithm operation
                        fibonacci(j % 30);    // Fibonacci calculation
                        double division = 1000.0 / j; // Division operation
                    }
                    points++; // Count this set of 3000 operations as 1 point
                }
                totalPoints.addAndGet(points); // Add to the global total
                System.out.println("Thread " + Thread.currentThread().getId() + " Points: " + points);
            });
        }

        executor.shutdown(); // Request orderly shutdown
        while (!executor.isTerminated()) {
            // Wait for all threads to finish
        }

        long elapsedTime = System.nanoTime() - startTime; // Actual elapsed time
        System.out.println("Multi-Core Elapsed Time: " + elapsedTime + "ns");

        // Normalize the points to the elapsed time
        long score = (totalPoints.get() * 1_000_000_000L) / elapsedTime;
        System.out.println("Multi-Core Final Score: " + score);
        return score;
    }

    public void stop() {
        stopBenchmark = true;
    }

    // Efficient Fibonacci calculation
    private long fibonacci(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            long temp = a + b;
            a = b;
            b = temp;
        }
        return b;
    }
}
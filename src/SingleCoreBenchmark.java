public class SingleCoreBenchmark {

    public long calculateScore() {
        long startTime = System.nanoTime(); // Start timer in nanoseconds
        long endTime = startTime + 60L * 1_000_000_000L; // 15 seconds in nanoseconds
        long points = 0;

        while (System.nanoTime() < endTime) {
            // Perform 1000 logarithm, 1000 Fibonacci, and 1000 division operations
            for (int i = 1; i <= 1000; i++) {
                Math.log(i + 1);      // Logarithm operation
                fibonacci(i % 30);    // Fibonacci calculation
                double division = 1000.0 / i; // Division operation
            }
            points++; // Count this set of 3000 operations as 1 point
        }

        long elapsedTime = System.nanoTime() - startTime; // Actual elapsed time
        System.out.println("Single-Core Elapsed Time: " + elapsedTime + "ns");

        // Normalize the points to the elapsed time
        long score = (points * 1_000_000_000L) / elapsedTime;
        System.out.println("Single-Core Final Score: " + score);
        return score;
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
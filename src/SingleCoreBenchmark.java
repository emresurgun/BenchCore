public class SingleCoreBenchmark {

    public long calculateScore() {
        long startTime = System.nanoTime(); // Start timer in nanoseconds
        long endTime = startTime + 60L * 1_000_000_000L;
        long points = 0;

        while (System.nanoTime() < endTime) {
            // Perform 1000 logarithm, 1000 Fibonacci, and 1000 division operations
            for (int i = 1; i <= 1000; i++) {
                Math.log(i + 1);
                fibonacci(i % 30);
                double division = 1000.0 / i;
            }
            points++; // Count this set of 3000 operations as 1 point
        }

        long elapsedTime = System.nanoTime() - startTime; // Actual elapsed time

        // Normalize the points to the elapsed time
        long score = (points * 1_000_000_000L) / elapsedTime;
        return score;
    }

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
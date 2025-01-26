public class SingleCoreBenchmark {

    /**
     * Calculates the single-core score by performing mathematical operations.
     * This method runs the benchmark for 3 minutes and returns the score.
     */
    public long calculateScore() {
        long startTime = System.nanoTime();
        long score = 0;

        // Run operations for 3 minutes
        long endTime = System.nanoTime() + 3 * 60 * 1_000_000_000L;
        while (System.nanoTime() < endTime) {
            // Perform 1000 square root calculations
            for (int i = 1; i <= 1000; i++) {
                Math.sqrt(i);
            }

            // Perform 1000 Fibonacci calculations
            for (int i = 1; i <= 1000; i++) {
                fibonacci(i % 30); // Limit Fibonacci index to prevent overflow
            }

            // Perform 1000 division operations
            for (int i = 1; i <= 1000; i++) {
                double result = (double) i / (i + 1);
            }

            // Increment score for each full cycle of operations
            score++;
        }

        return score;
    }

    /**
     * Calculates the Fibonacci number for a given index.
     */
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

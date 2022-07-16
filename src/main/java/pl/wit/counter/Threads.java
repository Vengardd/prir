package pl.wit.counter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Threads implements Calculator{

    private static final int THREADS_NUMBER = 12;

    @Override
    public double calculate(double x1, double x2, double delta) {
        double singleThreadDif = Math.abs(x2 - x1) / THREADS_NUMBER;
        ExecutorService es = Executors.newFixedThreadPool(THREADS_NUMBER);
        List<ThreadCalculator> threadCalculators = new ArrayList<>(THREADS_NUMBER);
        for (int curThread = 0; curThread < THREADS_NUMBER; curThread++) {
            double start = x1 + curThread * singleThreadDif;
            double end =  x1 + (curThread+1) * singleThreadDif;
            ThreadCalculator threadCalculator = new ThreadCalculator(start, end, delta);
            threadCalculators.add(threadCalculator);
            es.execute(threadCalculator);
        }
        try {
            es.shutdown();
            es.awaitTermination(250, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Problem with threads: " + e.getMessage());
        }
        double sum = threadCalculators.stream().map(ThreadCalculator::getSum).reduce(0d, Double::sum);
//        System.out.printf("Sum for x1: %f x2: %f delta: %f equals %f%n", x1, x2, delta, sum);
        return sum;
    }

    class ThreadCalculator implements Runnable {

        private double sum = 0;

        private final double x1;
        private final double x2;
        private final double delta;

        public ThreadCalculator(double x1, double x2, double delta) {
            this.x1 = x1;
            this.x2 = x2;
            this.delta = delta;
        }

        @Override
        public void run() {
            Sequential sequential = new Sequential();
            sum = sequential.calculate(x1, x2, delta);
        }

        public double getSum() {
            return sum;
        }
    }
}

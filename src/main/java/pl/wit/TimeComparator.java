package pl.wit;

import pl.wit.counter.Calculator;

public class TimeComparator {

    public long compare(double x1, double x2, double delta, Calculator calculator) {
        var startTime = System.nanoTime();

        calculator.calculate(x1, x2, delta);

        var endTime = System.nanoTime();

        var timeDifference = endTime - startTime;
//        System.out.printf("Time difference for x1: %f x2: %f delta: %f equals %d nanoseconds %n", x1, x2, delta, timeDifference);
        return timeDifference;
    }
}

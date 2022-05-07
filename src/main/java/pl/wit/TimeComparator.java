package pl.wit;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;

public class TimeComparator {

    public long compareSequential(double x1, double x2, double delta) {
        Sequential sequential = new Sequential();

        var startTime = System.nanoTime();

        sequential.calculate(x1, x2, delta);

        var endTime = System.nanoTime();

        var timeDifference = endTime - startTime;
        System.out.printf("Time difference for x1: %f x2: %f delta: %f equals %d nanoseconds \n", x1, x2, delta, timeDifference);
        return timeDifference;
    }
}

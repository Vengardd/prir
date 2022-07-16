package pl.wit;

import pl.wit.counter.Sequential;
import pl.wit.counter.Threads;

public class Main {

    public static void main(String[] args) {
        TimeComparator timeComparator = new TimeComparator();
        Sequential sequential = new Sequential();
        long time1seq = timeComparator.compare(1d, 9d, 0.0001d, sequential);
        long time2seq = timeComparator.compare(1d, 9d, 0.000001d, sequential);
        long time3seq = timeComparator.compare(1d, 9d, 0.0000001d, sequential);
        System.out.println(time1seq + " " + time2seq + " " + time3seq);
        Threads threads = new Threads();
        long time1thread = timeComparator.compare(1d, 9d, 0.0001d, threads);
        long time2thread = timeComparator.compare(1d, 9d, 0.000001d, threads);
        long time3thread = timeComparator.compare(1d, 9d, 0.0000001d, threads);
        System.out.println(time1thread + " " + time2thread + " " + time3thread);
    }


}

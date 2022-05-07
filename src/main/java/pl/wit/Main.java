package pl.wit;

public class Main {

    public static void main(String[] args) {
        TimeComparator timeComparator = new TimeComparator();
        long time1 = timeComparator.compareSequential(1d, 9d, 0.0001d);
        long time2 = timeComparator.compareSequential(1d, 9d, 0.000001d);
        long time3 = timeComparator.compareSequential(1d, 9d, 0.0000001d);
        System.out.println(time1 + " " + time2 + " " + time3);
    }


}

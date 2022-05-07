package pl.wit;

public class FunctionCalculator {

    // f(x) = sin(x^2) * x + 1/3 * e^x - cos ln(x)
    public double calculateValue(double x) {
        double firstPart = Math.sin(x*x) * x;
        double secondPart = 1d/3d * Math.exp(x);
        double thirdPart = Math.cos(Math.log(x));
        return firstPart + secondPart - thirdPart;
    }

    public double calculateDistance(double x1, double x2) {
        double y1 = calculateValue(x1);
        double y2 = calculateValue(x2);
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

}

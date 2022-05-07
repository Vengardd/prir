package pl.wit;

public class Sequential {

    private FunctionCalculator calculator = new FunctionCalculator();

    public double calculate(double x1, double x2, double delta) {
        double sum = 0;
        for (double curValue = x1; curValue + delta <= x2; curValue += delta) {
            sum += calculator.calculateDistance(x1, x1 + delta);
        }
        System.out.printf("Sum for x1: %f x2: %f delta: %f equals %f\n", x1, x2, delta, sum);
        return sum;
    }

}

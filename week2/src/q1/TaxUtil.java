package q1;

public class TaxUtil {
    double rate = 0.15;

    public double calculateTax(double amount) {
        return amount * rate;
    }

    public double calculateTax1(double amount, double rate) {
        return amount * rate;
    }

    public double calculateTax2(double amount) {
        final double rate = 0.15;
        return amount * rate;
    }
}

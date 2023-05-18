package Lab1;

public class Ex1{
    private double real;
    private double imag;

    public Ex1(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    public Ex1 add(Ex1 other) {
        double realPart = this.real + other.real;
        double imagPart = this.imag + other.imag;
        return new Ex1(realPart, imagPart);
    }

    public Ex1 multiply(Ex1 other) {
        double realPart = this.real * other.real - this.imag * other.imag;
        double imagPart = this.real * other.imag + this.imag * other.real;
        return new Ex1(realPart, imagPart);
    }

    @Override
    public String toString() {
        if (imag < 0) {
            return real + " - " + (-imag) + "i";
        }
        return real + " + " + imag + "i";
    }
    public static void main(String[] args) {
        Ex1 c1 = new Ex1(2, 5);
        Ex1 c2 = new Ex1(4, -1);

        System.out.println("c1 = " + c1);
        System.out.println("c2 = " + c2);

        Ex1 sum = c1.add(c2);
        System.out.println("c1 + c2 = " + sum);

        Ex1 product = c1.multiply(c2);
        System.out.println("c1 * c2 = " + product);
    }
}




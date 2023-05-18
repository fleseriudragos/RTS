package ex1;

public class ComplexNumber {
    private double real;
    private double imag;
    
    public ComplexNumber(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }
    
    public ComplexNumber add(ComplexNumber other) {
        double realPart = this.real + other.real;
        double imagPart = this.imag + other.imag;
        return new ComplexNumber(realPart, imagPart);
    }
    
    public ComplexNumber multiply(ComplexNumber other) {
        double realPart = this.real * other.real - this.imag * other.imag;
        double imagPart = this.real * other.imag + this.imag * other.real;
        return new ComplexNumber(realPart, imagPart);
    }
    
    public String toString() {
        return real + (imag < 0 ? " - " : " + ") + Math.abs(imag) + "i";
    }
    
    public static void main(String[] args) {
        ComplexNumber num1 = new ComplexNumber(2, 5);
        ComplexNumber num2 = new ComplexNumber(4, -1);
        
        ComplexNumber sum = num1.add(num2);
        ComplexNumber product = num1.multiply(num2);
        
        System.out.println("Sum: " + sum);
        System.out.println("Product: " + product);
    }
}
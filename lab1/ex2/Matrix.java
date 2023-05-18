package ex2;

public class Matrix {
    private int[][] elements;
    
    public Matrix(int[][] elements) {
        this.elements = elements;
    }
    
    public Matrix add(Matrix other) {
        int[][] result = new int[elements.length][elements[0].length];
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[0].length; j++) {
                result[i][j] = elements[i][j] + other.elements[i][j];
            }
        }
        return new Matrix(result);
    }
    
    public Matrix multiply(Matrix other) {
        int[][] result = new int[elements.length][other.elements[0].length];
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < other.elements[0].length; j++) {
                for (int k = 0; k < elements[0].length; k++) {
                    result[i][j] += elements[i][k] * other.elements[k][j];
                }
            }
        }
        return new Matrix(result);
    }
    
    public void print() {
        for (int i = 0; i < elements.length; i++) {
            for (int j = 0; j < elements[0].length; j++) {
                System.out.print(elements[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] arr1 = {{2, 3, 1}, {7, 1, 6}, {9, 2, 4}};
        int[][] arr2 = {{8, 5, 3}, {3, 9, 2}, {2, 7, 3}};
        
        Matrix matrix1 = new Matrix(arr1);
        Matrix matrix2 = new Matrix(arr2);
        
        System.out.println("Matrix 1:");
        matrix1.print();
        System.out.println("Matrix 2:");
        matrix2.print();
        
        Matrix sum = matrix1.add(matrix2);
        Matrix product = matrix1.multiply(matrix2);
        
        System.out.println("Sum:");
        sum.print();
        System.out.println("Product:");
        product.print();
    }
}

package Lab1;

// Ex 2
public class matrix_Calculator {
	
	private double[][] matrix;
	
	public matrix_Calculator(double[][] matrix)
	{
	    this.matrix=matrix;
	}
	
   public matrix_Calculator addition(matrix_Calculator element)
   {
	   double[][] final_Matrix=new double[matrix.length][element.matrix[0].length];
	   for(int i=0;i<matrix.length;i++)
		   for(int j=0;j<element.matrix[0].length;j++)
			   final_Matrix[i][j]=matrix[i][j]+element.matrix[i][j];
	   
	   return new matrix_Calculator(final_Matrix);
			   
	   
   }
   public matrix_Calculator multiply(matrix_Calculator element) {
       double[][] final_Matrix = new double[matrix.length][element.matrix[0].length];
       for (int i = 0; i < matrix.length; i++) {
           for (int j = 0; j < element.matrix[0].length; j++) {
               for (int k = 0; k < matrix[0].length; k++) {
                   final_Matrix[i][j] += matrix[i][k] * element.matrix[k][j];
               }
           }
       }
       return new matrix_Calculator(final_Matrix);
   }
   
   public void print() {
       for (int i = 0; i < matrix.length; i++) {
           for (int j = 0; j < matrix[0].length; j++) {
               System.out.print(matrix[i][j] + " ");
           }
           System.out.println();
       }
   }
	
	
	public static void main(String[] args)
	{
		double[][] m1= {{2,3,1},{7,1,6},{9,2,4}};
		double[][] m2= {{8,5,3},{3,9,2},{2,7,3}};
				
		matrix_Calculator matrix1=new matrix_Calculator(m1);
		matrix_Calculator matrix2=new matrix_Calculator(m2);
		
		 System.out.println("Matrix 1:");
	      matrix1.print();
	     System.out.println("Matrix 2:");
	      matrix2.print();
	      
	      System.out.println("\n");
	      
	     matrix_Calculator sum=matrix1.addition(matrix2);
	     matrix_Calculator product=matrix1.multiply(matrix2);
	     
	     System.out.println("The sum is: ");
	      sum.print();
	     System.out.println("The product is: ");
	      product.print();
	}
}


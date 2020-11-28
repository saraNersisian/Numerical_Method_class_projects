
import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;

public class GE_SPP_1 {
	
	public static void main(String[] args)
	{
		int n;
	
		Scanner kb = new Scanner(System.in);
		final double EPSILON = 1e-10;
		
		
//		File file = new File(GE_coefficient);
		
		
//		 Scanner sc = new Scanner(new BufferedReader(new FileReader("sample.txt")));
		
		String equations;
			
		do
		{
		   System.out.print("Enter the number of equations (n <= 10): ");
		   n = kb.nextInt();
		   if(n > 10)
			   System.out.println("Invalid input for n! Try again.");
		} while(n > 10);
		kb.nextLine();

		
		System.out.println("Enter your " + n + " equations bellow:");
		for(int i=1; i<=n; i++)
		{
			System.out.print("Equation " + i +": ");
			equations=kb.nextLine();
			
		}
		
		
		System.out.println("\nMake your selection on how you would like to input the coefficients: \n1) Using command line \n2) Using Files ");
		int selection = kb.nextInt();
		
		while (selection!=1 && selection!=2)
		{
			System.out.println("Invalid selection! Try again.");
			System.out.print("Enter youyr selection: ");
				selection = kb.nextInt();
		}
		
		
		double[][] A = new double[n][n];   //coefficient matrix
		double[] x = new double[n];      //solutions
		double[] B = new double[n];      // B vector
		
		
		if(selection == 1)
			{
			  System.out.println("Enter the coefficients incluing b values row by row:");
			  System.out.println("Example for [3x3]: \n\t\t  A B C b1\n\t\t  D E F b2\n\t\t  G H I b3");
			
		
		
		
				//getting the matrix from user
				for(int i=0; i<n; i++)
				{
					for(int j=0; j<n; j++)
					{
						A[i][j]=kb.nextDouble();
					}
					B[i]=kb.nextDouble();
					
					
					
				}
				
		
			}//command selection end 
		
		if(selection==2) {
			System.out.println("Not Available at this time!SORRY!");
			
			
			/*
			 while(sc.hasNextLine()) {
		         for (int i=0; i<A.length; i++) {
		            String[] line = sc.nextLine().trim().split(" ");
		            
		            for (int j=0; j<line.length; j++) {
		                A[i][j] = Integer.parseInt(line[j]);
		             }
		}
			 }
			 
			 */
		}
		
		for(int i=0; i<n; i++)
		{
			if (Math.abs(A[i][i]) <= EPSILON) {
			
				throw new ArithmeticException("Matrix is singular! NOT SOLVABLE");
			}
			
		}
		
		
		
		System.out.println("=============== DISPLAY =============== ");
		
		//printing the matrix
		System.out.print("A[]=\n");
		for(int i=0; i<n; i++)
		{
			for(int j=0; j<n; j++)
				System.out.format("%6.2f   ", A[i][j]);
			System.out.println();
		}
		//printing the B vector
		System.out.print("\nB[]= ");
		for(int i=0; i<n; i++)
		{
			System.out.print(B[i]+"   ");
		}
		System.out.println();
		System.out.println("\n========== SOLVING THE MATRIX ==========");
		
		//initializing index vector(I)
		int[] I = new int[n];
		for(int i=0; i<n; i++)
			I[i]=i;
		
		//System.out.println();
		
		//printing initial index vector
		System.out.print("\nInitial index[]= ");
		for(int i=0; i<n; i++)
		{
			System.out.print(I[i]+"  ");
			
		}
		
		//finding scale vector
		double smax;  //max in each row
		double s[]= new double [n];  //scale vector
	    for(int i=0; i<n; i++)
	     {
		   smax=0.0;
		   
		    for(int j=0; j<n; j++)
		    {
		    	smax=Math.max(smax,Math.abs(A[i][j]));
		    }
		   
		   s[i]=smax;
		 
		  
	      }
	   
	   //printing scale vector
	    System.out.print("\nInitial Scale[]= ");
	    for(int i=0; i<n; i++)
		{
			System.out.print(s[i]+"  ");
			
		}
	    System.out.println();
	    
	    //finding the ratios
	    double rmax;
	    double[] r = new double[n];
	    int maxInd;
		  
		   for( int k=0; k<n - 1; k++)
		   {
			   rmax=0;
			   maxInd=k;
			   for(int i=k; i<n; i++)
			   {
				   r[i]=Math.abs(A[I[i]][k]/s[I[i]]);  
		
				   if(r[i]>rmax)
				   {
					   rmax=r[i];
					   maxInd=i;
					   //System.out.println("Pivot row: "+i);
				   }
			   }
			   
			   System.out.println("\nPivot row: "+(maxInd+1));
			   //System.out.println("\nMoving Forward...");
			   //System.out.println();
			  
				 
				
			   System.out.print("index[]= ");
				for(int i=0; i<n; i++)
				{
					System.out.print(I[i]+"  ");
					
				}  
				
				System.out.print("\nr[]= ");
				for(int i=0; i<n; i++)
				{
					System.out.format("%.2f  ",r[i]);
					
				}
				System.out.println();
				
			 
			  //swapping scale vector's rows 
            int temp = I[maxInd];
	        I[maxInd] = I[k];
	        I[k] = temp;
	        
	       
	       
	       
	        
	        double xmult;
			   for(int i=k+1; i< n;i++)
			   {
				    xmult=(A[I[i]][k]/A[I[k]][k]);
				   A[I[i]][k] =xmult;
				   for(int j=k+1; j< n;j++)
				   {
					   A[I[i]][j]=A[I[i]][j] -( xmult * A[I[k]][j]);
				   }
				   B[I[i]]=B[I[i]] - xmult * B[I[k]];
			   }
		   
			 
	        
		   }
		   
		   System.out.println();
		   System.out.println();
		   System.out.println("********** FINAL ANSWER ********** ");
		   //printing new scale vector
	        System.out.print("\nFinal index[]= ");
			for(int i=0; i<n; i++)
			{
				System.out.print(I[i]+"  ");
				
			}
	        
			//printing ratios
			System.out.print("\nFinal r[]= ");
			for(int i=0; i<n; i++)
			{
				System.out.format("%.2f  ",r[i]);
				
			}
			System.out.println();
			 
		   
		
   
		  //  x[n] = B[I[n]]/A[I[n]][n];
		  
			//back substituting   
		   for (int i = n - 1; i > -1; i--) 
		   {
	            double sum =B[I[i]];
	            for (int j = i+1 ; j <n; j++) {
	                sum = sum - A[I[i]][j] * x[j];
	            }
	            x[i] = sum / A[I[i]][i];
	        }
		   
		   System.out.println();
		  // System.out.print("\nx[]= " );
	        for(int i=0; i<n;i++)
	        	{
	        		System.out.print("X"+ (i+1) +"= ");  
	        		System.out.format("%4.2f ",x[i]);
	        		System.out.println();
	        	
	        	}
	        
	        
	       // else
	        //	System.out.println("Wrong selection!");
	}

}

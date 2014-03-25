import Jama.Matrix; 
import Jama.SingularValueDecomposition; 
import java.util.Scanner;

public class ImageSVD {
	
	private Pic picture;

	
	public ImageSVD(String imageName){
		picture = new Pic(imageName);
	}
	
	public int[][] SVD(double array1[][], int k) {
		
		    Matrix A = new Matrix(array1);         
	        SingularValueDecomposition svd = new SingularValueDecomposition(A); 

	        double[][] U = svd.getU().getArray();
	        double[][] Ut = svd.getU().transpose().getArray();
	        double[][] V = svd.getV().getArray();
	        double[][] Sigma = svd.getS().getArray();
	        double[] Sigma1 = svd.getSingularValues();
	        
	        double UtVector[][] = new double[1][U.length];
	        double VVector[][] = new double[V.length][1];
	       
	        double sum[][] = new double[array1.length][array1[0].length];
	        for(int row = 0; row < array1.length; row ++) {
	        	for(int col = 0; col < array1[0].length; col++)
	        		sum[row][col] = 0;
	        }
	        
	        Matrix Msum = new Matrix(sum);
	        Matrix MVVector;
	        Matrix MUtVector;
	        for(int i = 0; i < k; i++) {
	        	for(int j = 0; j < V.length; j++)
	        		VVector[j][0] = V[j][i];
	        	for(int j = 0; j < U.length; j++)
	        		UtVector[0][j] = U[j][i];
	        	MVVector = new Matrix(VVector);
	        	MUtVector = new Matrix(UtVector);
	        	Msum.plusEquals(MVVector.times(MUtVector).timesEquals(Sigma1[i]));
	        }
	        
	        sum = Msum.getArray();
	        int[][] sum1 = new int[sum.length][sum[0].length];
	        for(int row = 0; row < sum1.length; row ++) {
	        	for(int col = 0; col < sum1[0].length; col++)
	        		sum1[row][col] = (int)(sum[row][col]);
	        }
	        System.out.println("Sigma(1) = " + Sigma1[1]);
	        System.out.println("Sigma(k+1) = " + Sigma1[k+1]);
	        return sum1;

	        
	}
	
	public Pic newImage(int k){
		Pic copy = picture.deepCopy();
		double backingR[][] = new double[copy.getWidth()][copy.getHeight()];
		double backingG[][] = new double[copy.getWidth()][copy.getHeight()];
		double backingB[][] = new double[copy.getWidth()][copy.getHeight()];
		for(int i = 0; i < copy.getWidth(); i++){
			for(int j = 0; j < copy.getHeight(); j++){
				backingR[i][j] = copy.getPixel(i,j).getRed();
				backingG[i][j] = copy.getPixel(i,j).getGreen();
				backingB[i][j] = copy.getPixel(i,j).getBlue();
				
			}
		}
		
		for(int i = 0; i < copy.getWidth(); i++){
			for(int j = 0; j < copy.getHeight(); j++){
				copy.getPixel(i,j).setRed(SVD(backingR,k)[i][j]);
				copy.getPixel(i,j).setGreen(SVD(backingG,k)[i][j]);
				copy.getPixel(i,j).setBlue(SVD(backingB,k)[i][j]);
			}
		}
		
		return copy;
	}
	
	public static void main(String[]args){
		String imageName = args[0];
		Scanner keyboard = new Scanner(System.in);
		ImageSVD imgPr = new ImageSVD(imageName);
		int k;
		System.out.println("How many iterations of k would you like this program to do?");
		k = keyboard.nextInt();
		keyboard.nextLine();
		imgPr.newImage(k).show();
		
	}
}

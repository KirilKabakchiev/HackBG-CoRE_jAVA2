package week1.matrix;

import java.util.Random;

public class Matrix {

	private Pixel[][] matrix;
	private Random rand = new Random();
	
	public Matrix(Pixel[] [] matrix){
		this.matrix = matrix;
	}
	
	public Matrix(int rows, int cols){
		if(rows > 0 && cols > 0){
			matrix = new Pixel[rows][cols];
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					matrix[i][j] = new Pixel(0.0f,0.0f,0.0f);
				}
			}
		}
		else {
			throw new IllegalArgumentException("invalid rows/cols for the matrix");
		}		
	}

	public Pixel[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Pixel[][] matrix) {
		this.matrix = matrix;
	}
	
	public Pixel getPixel(int x, int y){
		return matrix[x][y];
	}
	
	public void setPixel(int x, int y, float r, float g, float b){
		if(x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length){
			matrix[x][y].setRed(r);
			matrix[x][y].setGreen(g);
			matrix[x][y].setBlue(b);
		}
		else {
			throw new IllegalArgumentException("The matrix doesnt have such a Pixel...");
		}
	}
	
	public void operate(MatrixOperation operation){
		int rows = matrix.length;
		int cols = matrix[0].length;
		Pixel[][] opMatrix = new Pixel[rows][cols];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				Pixel pixel = operation.withPixel(i, j, matrix);
				opMatrix[i][j] = pixel;
			}
		}
		matrix = opMatrix;
	}
	
	@Override
	public String toString() {
		int rows = matrix.length;
		int cols = matrix[0].length;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				sb.append(matrix[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public void setRandomValues(){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
			setPixel(i, j, rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
			}
		}
	}
	
	public static void main(String[] args) {
		Matrix matrix2 = new Matrix(3,3);
		matrix2.setRandomValues();
		System.out.println(matrix2);
		
		matrix2.operate(new BrightnessReduce(0.7f));
		System.out.println(matrix2);
		
		matrix2.operate(new GrayScale());
		System.out.println(matrix2);
		
		matrix2.operate(new GaussianBlur());
		System.out.println(matrix2);
		
		ParallelMultiplication multi = new ParallelMultiplication();
		System.out.println(multi.multiply(matrix2, matrix2));
	}
}

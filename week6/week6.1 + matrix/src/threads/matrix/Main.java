package threads.matrix;

import java.util.Arrays;
import java.util.Random;

public class Main {
	long t0 = System.currentTimeMillis();

	private static Random rand = new Random();

	public static double[][] generateMatrix(int rows, int cols) {
		double[][] matrix = new double[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				matrix[i][j] = rand.nextDouble();
			}
		}
		return matrix;
	}

	public static String print(double[][] matrix) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				sb.append(matrix[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		MatrixMultiplier parallel = new ParallelMultiplication();
		MatrixMultiplier serial = new SerialMultiplication();
		double[][] matrix1 = generateMatrix(3000, 3000);
		double[][] matrix2 = generateMatrix(3000, 3000);
		
		long t0 = System.currentTimeMillis();
		double[][] multiply = parallel.multiply(matrix1, matrix2);
		long t1 = System.currentTimeMillis();
		System.out.println("Parallel multiplication for " + (t1-t0) + " milliseconds");
		
		long t2 = System.currentTimeMillis();
		double[][] multiply2 = serial.multiply(matrix1, matrix2);
		long t3 = System.currentTimeMillis();
		System.out.println("Serial multiplication for " + (t3-t2) + " milliseconds");
		
		System.out.println(multiply.length + "  " + multiply[0].length);
		
		
		
	}
}

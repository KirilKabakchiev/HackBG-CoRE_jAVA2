package week1.matrix;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMultiplication {

	public Matrix multiply(Matrix matrix1, Matrix matrix2) {
		Matrix result = new Matrix(matrix1.getMatrix().length, matrix2.getMatrix()[0].length);

		ForkJoinPool pool = new ForkJoinPool();

		try {
			pool.invoke(new MultiplicationTask(matrix1, matrix2, result));
			
		} finally {
			pool.shutdown();
		}
		return result;
	}
}

class MultiplicationTask extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5626285842878587611L;
	
	private int row;
	private Matrix matrix1;
	private Matrix matrix2;
	private Matrix result;
	
	
	

	public MultiplicationTask(int row, Matrix matrix1, Matrix matrix2,
			Matrix result) {
		if(matrix1.getMatrix()[0].length != matrix2.getMatrix().length){
			throw new IllegalArgumentException("row/col missmatch - cannot multiply");
		}
		
		this.row = row;
		this.matrix1 = matrix1;
		this.matrix2 = matrix2;
		this.result = result;
	}

	public MultiplicationTask(Matrix matrix1, Matrix matrix2, Matrix result) {
		this(-1, matrix1, matrix2, result);
	}
	
	@Override
	protected void compute() {
		if(row == -1){
			List<RecursiveAction> actions = new ArrayList<>();
			for (int r = 0; r < matrix1.getMatrix().length; r++) {
				actions.add(new MultiplicationTask(r, matrix1, matrix2, result));
			}
			invokeAll(actions);
		}
		else {
			constructResultRow(); 
			// tazi funkciq  izvurshva (broqt na stulbovete na matrix2) na bbroi ymnojeniq 
			//ot vida "red po stulb" i postroqva edin red (indeks row) ot novata matrica
		}
		
	}
	
	private void constructResultRow(){
		for (int k = 0; k < matrix1.getMatrix()[0].length; k++) {
			for (int j = 0; j < matrix2.getMatrix()[0].length; j++) {
				result.getMatrix()[row][j] = matrix1.getPixel(row, k).multiplyPixels(matrix2.getPixel(k, j));
			}
		}
	}

	public int getRow() {
		return row;
	}

	
}

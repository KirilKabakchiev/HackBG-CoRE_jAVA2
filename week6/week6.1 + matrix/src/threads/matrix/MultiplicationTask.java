package threads.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

public class MultiplicationTask extends RecursiveAction {

    private double[][] a, b, c;
    private int row;

    public MultiplicationTask(double[][] a, double[][] b, double[][] c) {
        this(a, b, c, -1);
    }

    public MultiplicationTask(double[][] a, double[][] b, double[][] c, int row) {
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("rows/columns missmatch!\n");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.row = row;
    }

    public void multiplyRowByColumn() {
         for (int k = 0; k < a[0].length; k++) 
         {
            for (int j = 0; j < b[0].length; j++){
                c[row][j] += a[row][k] * b[k][j];
            }
        }
    }

    @Override
    public void compute() {
        if (row == -1) {
            List<MultiplicationTask> tasks = new ArrayList<>();
            for (int roww = 0; roww < a.length; roww++) {
                tasks.add(new MultiplicationTask(a, b, c, roww));
            }
            invokeAll(tasks);
        } else {
            multiplyRowByColumn();
        }
    }   

    public int getRow() {
        return row;
    }

}
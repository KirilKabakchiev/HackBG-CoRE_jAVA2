package threads.matrix;

import java.util.concurrent.ForkJoinPool;

public class ParallelMultiplication implements MatrixMultiplier{

    @Override
    public double[][] multiply(double[][] a, double[][] b) {
        double[][] c = new double[a.length][b[0].length];

        ForkJoinPool pool = new ForkJoinPool();
        try {
            pool.invoke(new MultiplicationTask(a, b, c));
        } finally {
            pool.shutdown();
        }
        return c;
    }
}


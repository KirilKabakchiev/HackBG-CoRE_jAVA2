package threads.matrix;

public class SerialMultiplication implements MatrixMultiplier {

    @Override
    public double[][] multiply(double[][] matrixA, double[][] matrixB) {
        int aRows = matrixA.length;
        int aColumns = matrixA[0].length;
        int bRows = matrixB.length;
        int bColumns = matrixB[0].length;
        // System.out.println(aRows + " " + aColumns + " " + bRows + " " + bColumns);

        if (aColumns != bRows) {
            throw new IllegalArgumentException("Row/Column missmatch!\n");
        }

        double[][] matrixProduct = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int k = 0; k < bRows; k++) {
                for (int j = 0; j < bColumns; j++) {

                    matrixProduct[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }
        return matrixProduct;
    }
}
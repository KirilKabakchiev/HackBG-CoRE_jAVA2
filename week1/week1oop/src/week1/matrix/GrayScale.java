package week1.matrix;

public class GrayScale implements MatrixOperation{

	@Override
	public Pixel withPixel(int x, int y, Pixel[][] matrix) {
		Pixel original = matrix[x][y];
		float gray = (original.getRed() + original.getGreen() + original.getBlue()) / 3;
		return new Pixel(gray * 0.3f, gray * 0.6f, gray * 0.1f);
	}

}

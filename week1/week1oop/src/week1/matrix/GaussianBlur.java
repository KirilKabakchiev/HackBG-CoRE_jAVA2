package week1.matrix;


public class GaussianBlur implements MatrixOperation{

	@Override
	public Pixel withPixel(int x, int y, Pixel[][] matrix) {
		float sumOfReds = 0.0f, sumOfGreens = 0.f, sumOfBlues = 0.0f;
		int count = 0;
		
		for(int i = y - 1; i <= y + 1; i++){
			for(int j = x - 1; j <= x + 1; j++){
				if(i >= 0 && i < matrix.length
					&& j >= 0 && j < matrix[0].length
					&& (i != y || j != x)){
					
					sumOfReds += matrix[i][j].getRed();
					sumOfGreens += matrix[i][j].getGreen();
					sumOfBlues += matrix[i][j].getBlue();
					count++;
				}
			}
		}
		return new Pixel(sumOfReds / count, sumOfGreens / count, sumOfBlues / count);
	}

}

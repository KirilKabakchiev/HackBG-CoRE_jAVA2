package week1.matrix;

public class BrightnessReduce implements MatrixOperation{
	
	private float value;
	
	public BrightnessReduce(float v){
		setValue(v);
	}

	@Override
	public Pixel withPixel(int x, int y, Pixel[][] matrix) {
		Pixel original = matrix[x][y];
		return new Pixel(original.getRed() * value, original.getGreen() * value , original.getBlue() * value);
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

}

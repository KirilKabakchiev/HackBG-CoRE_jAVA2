package week1.matrix;

public class Pixel {
	private float red;
	private float green;
	private float blue;
	
	public Pixel(float red, float green, float blue) {
		super();
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public float getRed() {
		return red;
	}

	public void setRed(float red) {
		this.red = red;
	}

	public float getGreen() {
		return green;
	}

	public void setGreen(float green) {
		this.green = green;
	}

	public float getBlue() {
		return blue;
	}

	public void setBlue(float blue) {
		this.blue = blue;
	}
	
	@Override
	public String toString() {
		
		return String.format("{" + red + ", " + green + ", " + blue+ "}");
	}
	
	public Pixel multiplyPixels(Pixel other){
		return new Pixel(getRed() * other.getRed(), getGreen() * other.getGreen(), getBlue() * other.getBlue());
	}
}

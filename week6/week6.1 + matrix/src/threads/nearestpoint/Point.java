package threads.nearestpoint;

import java.util.Arrays;

public class Point {

	private final int[] coordinates;

	public Point(int[] coords) {
		if (coords == null || coords.length == 0) {
			throw new IllegalArgumentException(
					"Bad coordinates input for point");
		}
		coordinates = new int[coords.length];
		for (int i = 0; i < coords.length; i++) {
			coordinates[i] = coords[i];
		}
	}

	public int getCoordByIndex(int index) {
		return coordinates[index];
	}

	public int getPointDimension() {
		return coordinates.length;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append('(');
		for (int i = 0; i < coordinates.length - 1; i++) {
			sb.append(coordinates[i]).append(',');
		}
		
		sb.append(coordinates[coordinates.length - 1]).append(')');
		return sb.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coordinates);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (!Arrays.equals(coordinates, other.coordinates))
			return false;
		return true;
	}
	
	

//	public static void main(String[] args) {
//		int[] coords = { 1, 2, 3, 4 };
//		System.out.println(new Point(coords));
//	}

}

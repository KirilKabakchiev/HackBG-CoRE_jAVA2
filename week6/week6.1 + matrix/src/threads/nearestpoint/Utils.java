package threads.nearestpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Utils {

	private static Random rand = new Random();
	private static final int NUMBER_OF_POINTS = 100_000;
	private static final int NUMBER_OF_THREADS = 5;
	private static final int DIMENSIONS = 2;
	private static final int FROM = 0;
	private static final int TO = 10_000;

	public static double distance(Point A, Point B) {
		if (A.getPointDimension() != B.getPointDimension()) {
			throw new IllegalArgumentException(
					"cannot determine distance between points in different dimensions!");
		}

		double dist = 0.0;
		for (int i = 0; i < A.getPointDimension(); i++) {
			dist += (A.getCoordByIndex(i) - B.getCoordByIndex(i))
					* (A.getCoordByIndex(i) - B.getCoordByIndex(i));
		}
		return Math.sqrt(dist);
	}

	public static List<Point> generatePoints(int from, int to, int dim,
			int count) {
		List<Point> points = new ArrayList<>();

		int[] coordsOfPoint = new int[dim];
		while (points.size() < count) {
			for (int j = 0; j < dim; j++) {
				coordsOfPoint[j] = from + rand.nextInt(to + 1);
			}
			Point current = new Point(coordsOfPoint);
			if (points.contains(current)) {
				continue;
			}
			points.add(current);
		}
		return points;
	}

	public static Map<Point, Point> getNearestPoint(List<Point> points) {
		Map<Point, Point> output = new HashMap<>();
		double minDist = Double.MAX_VALUE, dist = 0.0;
		int minIndex = 0;
		for (int i = 0; i < points.size(); i++) {
			minDist = Double.MAX_VALUE;
			for (int j = 0; j < points.size(); j++) {
				if (i == j) {
					continue;
				}

				dist = distance(points.get(i), points.get(j));
				if (dist < minDist) {
					minDist = dist;
					minIndex = j;
				}
			}
			output.put(points.get(i), points.get(minIndex));
		}

		return output;
	}

	public static void doCalculations(final List<Point> inPoints,
			final int indexFrom, final int indexTo,
			final Map<Point, Point> outMap, int from, int to) {
		double minDist = Double.MAX_VALUE, dist = 0.0;
		int minIndex = 0;
		for (int i = indexFrom; i < indexTo; i++) {
			Point currentPoint = inPoints.get(i);
			minDist = Double.MAX_VALUE;
			for (int j = from; j < to; j++) {
				if (i == j) {
					continue;
				}

				dist = distance(inPoints.get(i), inPoints.get(j));
				if (dist < minDist) {
					minDist = dist;
					minIndex = j;
				}
			}
			// synchronized (outMap) {
			if (outMap.containsKey(currentPoint)) {
				if (Utils.distance(currentPoint, outMap.get(currentPoint)) > Utils
						.distance(currentPoint, inPoints.get(minIndex))) {
					outMap.put(currentPoint, inPoints.get(minIndex));
				}
			} else {

				outMap.put(currentPoint, inPoints.get(minIndex));
			}
		}
	}

	public static Map<Point, Point> getNeareastPointsViaThreads(
			List<Point> inPoints, int threadCount) {
		final Map<Point, Point> outMap = new ConcurrentHashMap<>();
		// ExecutorService service = Executors.newFixedThreadPool(threadCount);
		// int onePiece = inPoints.size() / threadCount; // trqbva da e cqlo
		// chislo
		// for (int i = 0; i < threadCount; i++) {
		// Runnable th = new CalcThread(inPoints, i * onePiece, (i + 1)
		// * onePiece, outMap);
		// service.submit(th);
		// }
		// service.shutdown();

		Thread[] threads = new Thread[3*threadCount];
		int onePiece = inPoints.size() / threadCount; // trqbva da e cqlo chislo
		int i = 0;
		while (i < 3*threadCount) {
			int temp = i/3;
			threads[i] = new Thread(new CalcThread(inPoints, temp * onePiece, (temp + 1) * onePiece, outMap,0, temp * onePiece));
			threads[i].start();
			
			i++;
			threads[i] = new Thread(new CalcThread(inPoints, temp * onePiece, (temp + 1) * onePiece, outMap,temp * onePiece,(temp+1)*onePiece));
			threads[i].start();
			i++;
			threads[i] = new Thread(new CalcThread(inPoints, temp * onePiece, (temp + 1) * onePiece, outMap,(temp+1) * onePiece,inPoints.size()));
			threads[i].start();
			i++;
		}

		for (i = 0; i < threadCount; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return outMap;
	}

	public static void main(String[] args) {
		List<Point> points = generatePoints(FROM, TO, DIMENSIONS,
				NUMBER_OF_POINTS+2);
		long start = System.currentTimeMillis();
		// System.out.println(getNearestPoint(generatePoints(0, 10_000, 2,
		// 10000)).size());
		System.out.println(getNeareastPointsViaThreads(points, 6).size());
		long end = System.currentTimeMillis();
		System.out.println("Total time: " + (end - start));
	}
}

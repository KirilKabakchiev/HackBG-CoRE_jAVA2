package threads.nearestpoint;

import java.util.List;
import java.util.Map;

public class CalcThread implements Runnable {
	private final List<Point> inPoints;
	private int indexFrom;
	private int indexTo;
	private int from;
	private int to;
	private final Map<Point, Point> outMap;

	public CalcThread(List<Point> points, int from, int to,
			Map<Point, Point> map, int from2, int to2) {
		this.inPoints = points;
		this.indexFrom = from;
		this.indexTo = to;
		this.outMap = map;
		this.from = from2;
		this.to = to2;
	}

	@Override
	public void run() {
		Utils.doCalculations(inPoints, indexFrom, indexTo, outMap, from, to);
	}

}

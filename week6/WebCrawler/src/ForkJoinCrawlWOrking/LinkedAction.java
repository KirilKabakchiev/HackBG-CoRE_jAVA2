package ForkJoinCrawlWOrking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.locks.Lock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkedAction extends RecursiveAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6003785349253510518L;

	private String url;
	private LinkHandler cr;
	private static final String SCOPE_LINK = "http://ebusiness.free.bg"; //ebusiness.free.bg
	/**
	 * Used for statistics
	 */
	private static final long t0 = System.nanoTime();

	private Lock lock;

	public LinkedAction(String url, LinkHandler cr) {
		this.url = url;
		this.cr = cr;
	}

	@Override
	public void compute() {
		//if (!cr.visited(url)) {
			try {
				List<RecursiveAction> actions = new ArrayList<RecursiveAction>();
				String content = getContent(new URL(url));

				if (content.contains("Револвираща")) {
					System.out.println(Thread.currentThread().getName());
					System.out.println(url);
				}
				// lock.lock();

				List<String> links = getAllLinks(content);

				// synchronized (cr){

				for (String string : links) {
					// if (string.contains(SCOPE_LINK)) {
					if (string.contains("..")) {
						continue;
					}
					String nextUrl = string;
					if (!string.contains("//")) {
						nextUrl = SCOPE_LINK + "/" + string;
					}
					if (!nextUrl.contains(SCOPE_LINK)) {
						continue;
					}
					 synchronized (cr) {

					if (!cr.visited(nextUrl)) {
						System.out.println(Thread.currentThread().getName() + "   " + nextUrl);
						actions.add(new LinkedAction(nextUrl, cr));
						cr.addVisited(nextUrl);
					//	System.out.println("hello");
					}
				}

				 }

				cr.addVisited(url);
				// System.out.println(url);

				if (cr.size() == 200) {
					System.out.println("Time for visit 200 distinct links= "
							+ (System.nanoTime() - t0));
				}

				// invoke recursively
				// System.out.println(actions);
				invokeAll(actions);
				// }
				// lock.unlock();
			} catch (Exception e) {
				// ignore 404, unknown protocol or other server errors
			}
		//}
	}

	private String getContent(URL url) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader input = new BufferedReader(new InputStreamReader(
				url.openStream()))) {
			String b = "";
			while ((b = input.readLine()) != null) {
				sb.append(b).append("\n");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

	private List<String> getAllLinks(String content) {
		ArrayList<String> resultList = new ArrayList<>();
		String regex = "<a.*?href=\"((?!javascript).*?)\".*?>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			resultList.add(matcher.group(1));
		}
		return resultList;
	}
}
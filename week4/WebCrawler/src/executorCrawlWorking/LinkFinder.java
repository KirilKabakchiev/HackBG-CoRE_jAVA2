package executorCrawlWorking;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Madalin Ilie
 */
public class LinkFinder implements Runnable {

	public static final long START = System.currentTimeMillis();
	private String url;
	private LinkHandler linkHandler;
	private static final String SCOPE_LINK = "http://ebusiness.free.bg";//;http://ebusiness.free.bg
	private AtomicInteger i = new AtomicInteger(0);

	/**
	 * Used fot statistics
	 */

	public LinkFinder(String url, LinkHandler handler) {
		this.url = url;
		this.linkHandler = handler;
		//this.i = i;
	}

	@Override
	public void run() {
		try {
			getSimpleLinks(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getSimpleLinks(String url) throws Exception {
		// if not already visited
		// if (!linkHandler.visited(url)) {

		// System.out.println(linkHandler.size());
		String content = getContent(new URL(url));
		// synchronized (linkHandler) {

		// linkHandler.addVisited(url);

		if (content.contains(" meh ")) {
			System.out.println(Thread.currentThread().getName());
			System.out.println(url);
		}
//
//		System.out.println(Thread.currentThread().getName() + "   "
////				+ linkHandler.size());
//		if (linkHandler.size() == 100) {
//			System.out.println("time " + (System.currentTimeMillis() - START));
//
//			System.exit(0);
//
//		}
		// }
		List<String> links = getAllLinks(content);
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
			if (!linkHandler.visited(nextUrl)) {
				linkHandler.queueLink(nextUrl);
			}

		}
	}

	// }

	private static String getContent(URL url) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader input = new BufferedReader(new InputStreamReader(
				url.openStream(), Charset.forName("UTF-8")))) {
			String b = "";
			while ((b = input.readLine()) != null) {
				sb.append(b).append("\n");
			}
		} catch (FileNotFoundException f) {
			System.out.println("file not found ex, its fine");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

	private static List<String> getAllLinks(String content) {
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

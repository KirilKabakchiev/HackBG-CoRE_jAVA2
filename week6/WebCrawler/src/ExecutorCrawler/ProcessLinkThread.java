package ExecutorCrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessLinkThread implements Runnable {

	private static final String SCOPE_LINK = "http://ebusiness.free.bg";
	private final Set<String> visited; // = Collections.synchronizedSet(new
	private String needle; // HashSet<String>());
	private String url;
	private LinkUser linkUser;

	public ProcessLinkThread(String url, Set<String> visited, String needle, LinkUser l) {
		this.url = url;
		this.visited = visited;
		this.needle = needle;
		this.linkUser = l;
	}

	@Override
	public void run() {
		try {
			processUrl(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processUrl(String url) throws MalformedURLException {
		if (!visited.contains(url)) {
			String content = getContent(new URL(url));
			visited.add(url);
		//	System.out.println(visited);
			//System.out.println("Visited");

			if (content.contains(needle)) {
				System.out.println(Thread.currentThread().getName());
				System.out.println(url);
			}

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
				if (!visited.contains(nextUrl)) {
				linkUser.startNewThread(nextUrl);}
			}
		}
	}

	private static String getContent(URL url) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader input = new BufferedReader(new InputStreamReader(
				url.openStream(), Charset.forName("UTF-8")))) {
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

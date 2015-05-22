package com.crawl;

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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {

	private static final long start = System.currentTimeMillis();
	private static final String SCOPE_LINK = "http://9gag.com";
	private final Set<String> visited = Collections.synchronizedSet(new HashSet<String>());
	private final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();

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

	public void crawl(URL url, String needle) {
		System.out.println(visited.size());
		if(visited.size() == 200){
			long end = System.currentTimeMillis();
			System.out.println((end - start) +  " mililseconds");
			System.exit(0);
		}
		if(!visited.contains(url.toString())){
			visited.add(url.toString());
		}
		else {
			return;
		}
		String content = getContent(url);

		if (content.contains(needle)) {
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
			if (!visited.contains(nextUrl))
				try {
					crawl(new URL(nextUrl), needle);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					// }
				}
		}
	}

	public static void main(String[] args) {
		try {
			Set<String> visited = new HashSet<>();
			new Crawler().crawl(new URL("http://9gag.com"), "Револвираща");
			//System.out.println(visited);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
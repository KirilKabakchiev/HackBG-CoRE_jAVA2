package ExecutorCrawler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WebCrawler implements LinkUser{

	private static final Set<String> visited = Collections.synchronizedSet(new HashSet<String>());
	private String startUrl;
	private static ExecutorService service;
	private static String needle;

	public WebCrawler(String startUrl,int nThreads,String needle) {
		service = Executors.newFixedThreadPool(nThreads);
		this.startUrl = startUrl;
		this.needle = needle;
	}
	
	@Override
	public void startNewThread(String url){
		
		service.execute(new ProcessLinkThread(url, visited, needle, this));
		//System.out.println("exe     " + url);
		
	}
	
	private void startCrawling() {
		startNewThread(startUrl);
	}
	
	public static void main(String[] args) throws InterruptedException {
		long start = System.currentTimeMillis();
		new WebCrawler("http://ebusiness.free.bg", 65, "Револвираща").startCrawling();
		
			//long end = System.currentTimeMillis();
			//System.out.println("Total Time: " + (end-start));
		
		
	}

}

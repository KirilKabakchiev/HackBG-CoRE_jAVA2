package rss.web;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

@ApplicationScoped
public class XMLReader {

//	public XMLReader(){
//		
//	}
//	public String say(){
//		return "hello";
//	}

	public String parseNews() throws IllegalArgumentException, FeedException,
			IOException {

		URL url = new URL("http://www.sportal.bg/uploads/rss_category_2.xml");
		HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
		SyndFeedInput input = new SyndFeedInput();
		SyndFeed feed = input.build(new XmlReader(httpcon));

		List entries = feed.getEntries();
		Iterator itEntries = entries.iterator();

		StringBuilder sb = new StringBuilder();

		while (itEntries.hasNext()) {
			SyndEntry entry = (SyndEntry) itEntries.next();
			sb.append("<a href=" + entry.getLink() + ">" + entry.getTitle()
					+ "</a>");
			sb.append("<br/>");
			sb.append(entry.getDescription().getValue());
			sb.append("<br/>");
		}
		return sb.toString();
	}
//	public static void main(String[] args) throws IllegalArgumentException, FeedException, IOException {
//		System.out.println(new XMLReader().parseNews());
//	}
}

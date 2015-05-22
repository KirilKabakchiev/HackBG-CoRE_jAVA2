package rss.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * Servlet implementation class RSSVisualizer
 */
@WebServlet("/RSSVisualizer")
public class RSSVisualizer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private XMLReader getNews;
	
    @Override
    public void init() throws ServletException {
        
    }


	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException  {
		response.setContentType("text/html; charset=utf-8");
		try {
			response.getWriter().print(getNews.parseNews());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FeedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}

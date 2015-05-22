package com.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.openjpa.jdbc.kernel.exps.GetColumn;

@Stateless
public class Utils {

	private static final String URL = "https://hackbulgaria.com/api/checkins/";
	private static final String FORMATER_SERVLET_URL = "http://localhost:8080/JSONFormater/FormatServlet";

	

	public static String getUrlContents(String url)
			throws UnsupportedOperationException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuilder sb = new StringBuilder();
		String line = "";
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}

		return sb.toString();
	}

	public static void generatePostRequest(String content) {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(FORMATER_SERVLET_URL);

		List<NameValuePair> arguments = new ArrayList<>(1);
		arguments.add(new BasicNameValuePair("content", content));
		//arguments.add(new BasicNameValuePair("firstName", "System"));
		//arguments.add(new BasicNameValuePair("lastName", "Administrator"));

		try {
			post.setEntity(new UrlEncodedFormEntity(arguments));
			HttpResponse response = client.execute(post);

			System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void generateRequest() throws UnsupportedOperationException, IOException{
		generatePostRequest(getUrlContents(URL));
	}

	public static void main(String[] args)
			throws UnsupportedOperationException, IOException {
		generatePostRequest(getUrlContents(URL));
	}

}

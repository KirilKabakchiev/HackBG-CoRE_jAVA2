package com.web;

import java.io.IOException;
import java.util.Collection;

import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class FormatServlet
 */
@WebServlet("/FormatServlet")
public class FormatServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private Utils generateRequest;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FormatServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// public void init(ServletConfig config) throws ServletException {
	// super.init(config);
	// try {
	// generateRequest.generateRequest();
	// } catch (UnsupportedOperationException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String param = request.getParameter("content");
		if (null != param) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(param);
			String prettyJsonString = gson.toJson(je);
			response.getWriter().println(prettyJsonString);
			 response.getWriter().println("hi im servlet");
			// System.out.println(param+"aaaaa");

		}
		else {
			response.getWriter().println("Request param is null");
		}

	}
}

package com.congo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToOrder
 */
@WebServlet("/AddToOrder")
public class AddToOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToOrder() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("error", "Sorry, something went wrong");
		req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameterMap().containsKey("recordingId")) {
			int recordingId = Integer.parseInt(request.getParameter("recordingId"));
			String from = getPreviousPageByRequest(request).orElse("/");
			HttpSession session = request.getSession();
			if (session.getAttribute("order") == null) {
			    ArrayList<Integer> order = new ArrayList<Integer>();
			    order.add(recordingId);
			    session.setAttribute("order", order);
			    System.out.println(from);
			    response.sendRedirect(from);
			} else {
			    ArrayList<Integer> order = (ArrayList<Integer>) session.getAttribute("order");
			    order.add(recordingId);
			    session.setAttribute("order", order);
			    System.out.println(from);
			    response.sendRedirect(from);
			}
		} else {
			request.setAttribute("error", "Sorry, something went wrong");
			request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
		}
		
	}
	
	/**
	* Returns the viewName to return for coming back to the sender URL
	*
	* @param request Instance of {@link HttpServletRequest} or use an injected instance
	* @return Optional with the view name. Recommended to use an alternative URL with
	* {@link Optional#orElse(java.lang.Object)}
	*/
	protected Optional<String> getPreviousPageByRequest(HttpServletRequest request)
	{
	   return Optional.ofNullable(request.getHeader("Referer"));
	}

}

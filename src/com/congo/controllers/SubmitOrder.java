package com.congo.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.congo.dao.CustomerDAO;
import com.congo.model.Customer;
import com.congo.model.MusicRecordings;

/**
 * Servlet implementation class SubmitOrder
 */
@WebServlet("/SubmitOrder")
public class SubmitOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitOrder() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("customer") != null && session.getAttribute("order") != null) {

			ArrayList<MusicRecordings> albums = Basket.createAlbumArray(session);
			float grandTotal = Basket.calculateGrandTotal(albums);

			Customer customer = (Customer) session.getAttribute("customer");
			CustomerDAO cdao = CustomerDAO.getInstance();
			int orderId = cdao.insertOrder(customer, grandTotal);
			
			if(orderId != 0) {
				cdao.insertOrderDetails(orderId, albums);
				request.setAttribute("msg", "Order completed successfully.");
				session.setAttribute("order", null);
				session.setAttribute("albums", null);
			} else {
				request.setAttribute("error", "Sorry, something went wrong.");
			}

			request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);	
		} else {
			response.sendRedirect("/WEB-INF/home.jsp");
		}
	}

}

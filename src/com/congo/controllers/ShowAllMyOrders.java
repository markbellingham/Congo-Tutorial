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
import com.congo.model.CustomerOrderDetails;
import com.congo.model.CustomerOrders;

/**
 * Servlet implementation class ShowAllMyOrders
 */
@WebServlet("/ShowAllMyOrders")
public class ShowAllMyOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAllMyOrders() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("customer") != null) {
			
			Customer customer = (Customer) session.getAttribute("customer");
			CustomerDAO cdao = CustomerDAO.getInstance();
			ArrayList<CustomerOrders> orders = cdao.customerOrders(customer.getCustId());
			ArrayList<CustomerOrderDetails> customerOrderDetails = cdao.customerOrderDetails(customer.getCustId());			
			
			request.setAttribute("customer", customer);
			request.setAttribute("orders", orders);
			request.setAttribute("order_details", customerOrderDetails);
			request.getRequestDispatcher("/WEB-INF/show-all-orders.jsp").forward(request, response);
		} else {
			response.sendRedirect("/WEB-INF/home.jsp");
		}
	}

}

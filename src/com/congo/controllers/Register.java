package com.congo.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.congo.dao.CustomerDAO;
import com.congo.model.Customer;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Customer customer = new Customer();
		customer.setfName(request.getParameter("fname"));
		customer.setlName(request.getParameter("lname"));
		customer.setAddress1(request.getParameter("address1"));
		customer.setAddress2(request.getParameter("address2"));
		customer.setCity(request.getParameter("city"));
		customer.setPostcode(request.getParameter("postcode"));
		customer.setPhone(request.getParameter("phone"));
		customer.setEmail(request.getParameter("email"));
		customer.setPassword(request.getParameter("password"));
		customer.setAdmin(0);
		
		CustomerDAO cdao = CustomerDAO.getInstance();

		if (cdao.checkEmail(customer.getEmail()) == true) {
			request.setAttribute("error", "Sorry, that email is already registered.");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		
		String hashedPassword = BCrypt.hashpw(customer.getPassword(), BCrypt.gensalt());		
		customer.setPassword(hashedPassword);
		
		if (cdao.insertCustomerIntoDatabase(customer) == 1) {
			request.setAttribute("msg", "Success! You are now registered in our database. Please log in.");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		} else {
			request.setAttribute("error", "Sorry, something went wrong.");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
	}

}

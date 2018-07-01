package com.congo.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import com.congo.dao.CustomerDAO;
import com.congo.model.Customer;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String email = "";
		email = request.getParameter("email");
		String password = "";
		password = request.getParameter("password");
		boolean matched = false;
		
		if(email != "" && password != "") {
			CustomerDAO cdao = CustomerDAO.getInstance();
			Customer customer = cdao.findOneCustomer(email);
			if(customer.getPassword() != null) {
				matched = BCrypt.checkpw(password, customer.getPassword());	
			}
			if (matched) {
	    		customer.setLoggedIn(true);
	            request.setAttribute("customer", customer);
	            HttpSession session = request.getSession();
	            session.setAttribute("customer", customer);
	            request.getRequestDispatcher("/WEB-INF/loggedin.jsp").forward(request, response);       		
	    	} else {
	            request.setAttribute("error", "Invalid Details.");
	            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	        }

		} else {
            request.setAttribute("error", "Please enter your details.");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
		
	}

}

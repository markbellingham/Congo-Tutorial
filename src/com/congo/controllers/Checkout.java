package com.congo.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.congo.model.MusicRecordings;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
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
			request.setAttribute("grandTotal", grandTotal);
			request.setAttribute("albums", albums);
			request.getRequestDispatcher("/WEB-INF/checkout.jsp").forward(request, response);	
		} else {
			response.sendRedirect("/WEB-INF/home.jsp");
		}
	}
}

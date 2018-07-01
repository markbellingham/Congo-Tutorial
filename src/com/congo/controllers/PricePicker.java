package com.congo.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.congo.dao.MusicDAO;
import com.congo.model.Customer;
import com.congo.model.MusicCategories;
import com.congo.model.MusicRecordings;

/**
 * Servlet implementation class PricePicker
 */
@WebServlet("/PricePicker")
public class PricePicker extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PricePicker() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<MusicRecordings> albums = new ArrayList<MusicRecordings>();
		float lower = 0; float higher = 10;
		
		if(request.getParameterMap().containsKey("price")) {
			String price = request.getParameter("price");
			String[] parts = price.split(",");
			lower = Float.parseFloat(parts[0]);
			higher = Float.parseFloat(parts[1]);
		}
		
		MusicDAO mdao = MusicDAO.getInstance();
		albums = mdao.findAlbumsByPrice(lower, higher);
		
		request.setAttribute("select", null);
		HttpSession session = request.getSession();
		if(session.getAttribute("select") != null) {
			MusicRecordings album = (MusicRecordings) session.getAttribute("select");
			request.setAttribute("select", album);
			session.setAttribute("select", null);
		}

		if(session.getAttribute("customer") != null) {
			Customer cust = (Customer) session.getAttribute("customer");
			if(cust.getAdmin() == 1) {
				ArrayList<MusicCategories> categories = mdao.findAllCategories();
				request.setAttribute("categories", categories);
			}
		}
		
		request.setAttribute("albums", albums);
		request.getRequestDispatcher("/WEB-INF/price-picker.jsp").forward(request, response);
	}
}

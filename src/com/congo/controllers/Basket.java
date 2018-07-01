package com.congo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.congo.dao.MusicDAO;
import com.congo.model.MusicRecordings;

/**
 * Servlet implementation class Basket
 */
@WebServlet("/Basket")
public class Basket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Basket() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("customer") != null) {
			if (session.getAttribute("order") == null) {
				request.setAttribute("error", "Your basket is empty");
			} else {
				ArrayList<MusicRecordings> albums = createAlbumArray(session);
				float grandTotal = calculateGrandTotal(albums);
				request.setAttribute("grandTotal", grandTotal);
				request.setAttribute("albums", albums);
			}		
			request.getRequestDispatcher("/WEB-INF/basket.jsp").forward(request, response);			
		} else {
			response.sendRedirect("/WEB-INF/home.jsp");
		}
	}
	
	protected static ArrayList<MusicRecordings> createAlbumArray(HttpSession session) {
		ArrayList<MusicRecordings> albums = new ArrayList<MusicRecordings>();
		ArrayList<Integer> orderArray = (ArrayList<Integer>) session.getAttribute("order");
		MusicDAO mdao = MusicDAO.getInstance();
		Set<Integer> orderSet = new HashSet<Integer>(orderArray);
		for (int recordingId : orderSet) {
			int quantity = Collections.frequency(orderArray, recordingId);
			MusicRecordings album = mdao.findSingleAlbum(recordingId);
			album.setQuantity(quantity);
			float totalPrice = album.getPrice() * quantity;
			album.setTotalPrice(totalPrice);
			albums.add(album);
		}
		return albums;
	}
	
	
	protected static float calculateGrandTotal(ArrayList<MusicRecordings> albumsInOrder) {
		float grandTotal = 0;
		for(MusicRecordings albums : albumsInOrder) {
			grandTotal += albums.getTotalPrice();
		}
		return grandTotal;
	}

}

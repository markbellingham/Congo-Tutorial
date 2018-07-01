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
import com.congo.model.MusicCategories;
import com.congo.model.MusicRecordings;

/**
 * Servlet implementation class Categories
 */
@WebServlet("/Categories")
public class Categories extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Categories() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MusicDAO mdao = MusicDAO.getInstance();
		ArrayList<MusicCategories> categories = mdao.findAllCategories();
		request.setAttribute("categories", categories);
		
		ArrayList<MusicRecordings> albums = new ArrayList<MusicRecordings>();
		String category = "Classical";
		if(request.getParameterMap().containsKey("category")) {
			category = request.getParameter("category");
		}
		albums = mdao.findAlbumsByCategory(category);
		
		request.setAttribute("select", null);
		HttpSession session = request.getSession();
		if(session.getAttribute("select") != null) {
			MusicRecordings album = (MusicRecordings) session.getAttribute("select");
			request.setAttribute("select", album);
			session.setAttribute("select", null);
		}
		
		request.setAttribute("category", category);
		request.setAttribute("albums", albums);
		request.getRequestDispatcher("/WEB-INF/categories.jsp").forward(request, response);
	}

}

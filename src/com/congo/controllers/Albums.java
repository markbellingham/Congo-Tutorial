package com.congo.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.congo.dao.MusicDAO;
import com.congo.model.MusicRecordings;

/**
 * Servlet implementation class Albums
 */
@WebServlet("/Albums")
public class Albums extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Albums() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MusicDAO mdao = MusicDAO.getInstance();
		ArrayList<MusicRecordings> albums = mdao.findAllAlbums();
		request.setAttribute("albums", albums);
		request.getRequestDispatcher("/WEB-INF/albums.jsp").forward(request, response);
	}

}

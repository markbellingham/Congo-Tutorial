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
import com.congo.model.MusicRecordings;
import com.congo.model.MusicTracks;
import com.mysql.jdbc.StringUtils;

/**
 * Servlet implementation class TrackLister
 */
@WebServlet("/TrackLister")
public class TrackLister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrackLister() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<MusicTracks> tracks = new ArrayList<MusicTracks>();
		MusicRecordings album = new MusicRecordings();
		String error = "";
		if(request.getParameterMap().containsKey("recordingId")) {
			String parameter = request.getParameter("recordingId");
			if(StringUtils.isStrictlyNumeric(parameter)) {
				int recordingId = Integer.parseInt(parameter);
				MusicDAO mdao = MusicDAO.getInstance();
				tracks = mdao.findAlbumTracks(recordingId);
				if (tracks.isEmpty()) {
					error = "Error: Album does not exist.";
				}
				album = mdao.findSingleAlbum(recordingId);
				request.setAttribute("tracks", tracks);
				request.setAttribute("album", album);
				
				// If this is an admin edit request
				if (request.getParameterMap().containsKey("action")) {
					String action = request.getParameter("action");
					HttpSession session = request.getSession();
					Customer customer = (Customer) session.getAttribute("customer");
					if (action.equals("modify") && customer.getAdmin() == 1) {
						request.getRequestDispatcher("/WEB-INF/insert-tracks.jsp").forward(request, response);
						return;
					}
				}
			} else {
				error = "Error: The Recording Id must be an integer.";
			}
		} else {
			error = "Error: You must supply a Recording Id.";
		}
		request.setAttribute("error", error);
		request.getRequestDispatcher("/WEB-INF/album.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}

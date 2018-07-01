package com.congo.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.congo.dao.AdminDAO;
import com.congo.model.Customer;
import com.congo.model.MusicTracks;

/**
 * Servlet implementation class InsertTracks
 */
@WebServlet("/InsertTracks")
public class InsertTracks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertTracks() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<MusicTracks> tracks = new ArrayList<MusicTracks>();
		if (session.getAttribute("customer") != null) {
			Customer cust = (Customer) session.getAttribute("customer");
			if (cust.getAdmin() == 1) {
				AdminDAO adao = AdminDAO.getInstance();
				ArrayList<Integer> trackIds = adao.getTrackIds();
				int trackId = UpdateDB.getMaxId(trackIds);
				if (request.getParameter("recordingId") != "" && request.getParameter("name") != "" && request.getParameter("duration") != "") {
					int recordingId = Integer.parseInt(request.getParameter("recordingId"));
					
					// If the recording ID exists in the Music_Tracks table, this is an update, otherwise it's an insert
					// Because of how the table is structured, it's easier to delete all the tracks and re add them
					ArrayList<Integer> recordingIds = adao.getTrackRecordingIds();
					if (recordingIds.contains(recordingId)) {
						adao.deleteAlbumTracks(recordingId);
					}
					
					String[] names = request.getParameterValues("title");
					
					String[] durations = request.getParameterValues("duration");
					for (int i = 0; i < names.length; i++) {
						trackId++;
						MusicTracks track = new MusicTracks();
						track.setId(trackId);
						track.setRecordingId(recordingId);
						track.setTitle(names[i]);
						track.setDuration(Integer.parseInt(durations[i]));
						tracks.add(track);
					}
					int success = adao.insertAlbumTracks(tracks);
					if (success > 0) {
						request.setAttribute("error", "");
						request.setAttribute("msg", "Album and tracks added successfully.");
					} else {
						request.setAttribute("error", "Something went wrong.");
						request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
						return;
					}
					
				}
				
			}

		}
		request.getRequestDispatcher("/TrackLister").forward(request, response);
	}

}

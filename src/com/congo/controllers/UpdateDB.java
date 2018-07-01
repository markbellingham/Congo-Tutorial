package com.congo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.congo.dao.AdminDAO;
import com.congo.model.Customer;
import com.congo.model.MusicRecordings;

/**
 * Servlet implementation class UpdateDB
 */
@WebServlet("/UpdateDB")
public class UpdateDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	AdminDAO adao = AdminDAO.getInstance();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDB() {
        super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("customer") != null) {
			Customer cust = (Customer) session.getAttribute("customer");
			if (cust.getAdmin() == 1) {
				MusicRecordings album = new MusicRecordings();
				if (request.getParameter("recordingId") != "") {
					album.setRecordingId(Integer.parseInt(request.getParameter("recordingId")));
				} else {
					album.setRecordingId(0);
				}
				album.setArtistName(request.getParameter("artist_name"));
				album.setTitle(request.getParameter("title"));
				album.setCategory(request.getParameter("category"));
				album.setNum_tracks(Integer.parseInt(request.getParameter("num_tracks")));
				album.setPrice(Float.parseFloat(request.getParameter("price")));
				album.setStockCount(Integer.parseInt(request.getParameter("stockCount")));
				
				// If the album is already in the DB, this is an update, then redirect user back to where they came from
				// otherwise it is an insert and direct user to a page where they can insert tracks
				ArrayList<Integer> recordingIds = adao.getRecordingIds();
				if (recordingIds.contains(album.getRecordingId())) {
					adao.updateAlbum(album);
					String from = getPreviousPageByRequest(request).orElse("/");
					response.sendRedirect(from);
					return;
				} else {
					int r_id = getMaxId(recordingIds);
					album.setRecordingId(r_id + 1);
					adao.insertAlbum(album);
					request.setAttribute("album", album);
					request.getRequestDispatcher("/WEB-INF/insert-tracks.jsp").forward(request, response);
					return;
				}
			} else {
				request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
				return;
			}
		} else {
			request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
			return;
		}
	}
	
	protected static int getMaxId(ArrayList<Integer> ids) {
		Integer max = Collections.max(ids);
		return max;
	}
	
	/**
	* Returns the viewName to return for coming back to the sender URL
	*
	* @param request Instance of {@link HttpServletRequest} or use an injected instance
	* @return Optional with the view name. Recommended to use an alternative URL with
	* {@link Optional#orElse(java.lang.Object)}
	*/
	protected Optional<String> getPreviousPageByRequest(HttpServletRequest request)	{
	   return Optional.ofNullable(request.getHeader("Referer"));
	}

}

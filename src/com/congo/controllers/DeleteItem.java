package com.congo.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.congo.dao.AdminDAO;
import com.congo.model.Customer;

/**
 * Servlet implementation class DeleteItem
 */
@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteItem() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("customer") != null) {
			Customer cust = (Customer) session.getAttribute("customer");
			if (cust.getAdmin() == 1 && request.getParameter("recordingId") != null) {
				String r_id = request.getParameter("recordingId");
				try {
					int recordingId = Integer.parseInt(r_id);
					AdminDAO adao = AdminDAO.getInstance();
					int deletedTracks = adao.deleteAlbumTracks(recordingId);
					int deletedAlbum = adao.deleteAlbum(recordingId);
					
					if (deletedTracks == 0) { session.setAttribute("Error", "Error: No tracks to delete."); }
					if (deletedAlbum == 0) { session.setAttribute("Error:", "Error: No album to delete."); }
					
				} catch (Exception e) {
					request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
				}
			} else {
				request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
			}
		} else {
			request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
		}		
		String from = getPreviousPageByRequest(request).orElse("/");
		response.sendRedirect(from);
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

package com.congo.controllers;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.congo.dao.MusicDAO;
import com.congo.model.MusicRecordings;
import com.mysql.jdbc.StringUtils;

/**
 * Servlet implementation class SelectItem
 */
@WebServlet("/SelectItem")
public class SelectItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameterMap().containsKey("recordingId")) {
			String r_id = request.getParameter("recordingId");
			if(StringUtils.isStrictlyNumeric(r_id)) {
				int recordingId = Integer.parseInt(r_id);
				MusicDAO mdao = MusicDAO.getInstance();
				MusicRecordings album = mdao.findSingleAlbum(recordingId);
				HttpSession session = request.getSession();
				session.setAttribute("select", album);
			}
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

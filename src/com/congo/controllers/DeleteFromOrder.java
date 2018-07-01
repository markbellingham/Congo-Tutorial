package com.congo.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.congo.model.MusicRecordings;
import com.mysql.jdbc.StringUtils;

/**
 * Servlet implementation class DeleteFromOrder
 */
@WebServlet("/DeleteFromOrder")
public class DeleteFromOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFromOrder() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("Basket");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<MusicRecordings> albumsInOrder = new ArrayList<MusicRecordings>();
		HttpSession session = request.getSession();
		if (session.getAttribute("order") != null && request.getParameter("recordingId") != null) {
			String strRecordingId = request.getParameter("recordingId");
			if(StringUtils.isStrictlyNumeric(strRecordingId)) {
				int recordingId = Integer.parseInt(strRecordingId);
				
				ArrayList<Integer> orderArray = (ArrayList<Integer>) session.getAttribute("order");
				orderArray.removeAll(Collections.singleton(recordingId));

				response.sendRedirect("Basket");
			} else {
				response.sendRedirect("Basket");
			}
		} else {
			response.sendRedirect("Basket");
		}
	}

}

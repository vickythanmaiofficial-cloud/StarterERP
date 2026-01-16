package com.startererp.controller;

import com.startererp.dao.EmployeeDAO;
import com.startererp.dto.EmployeeDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    // 🔒 ABSOLUTE cache prevention
	    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	    response.setHeader("Pragma", "no-cache");
	    response.setDateHeader("Expires", 0);

	    HttpSession session = request.getSession(false);

	    if (session == null || session.getAttribute("loggedInUser") == null) {
	        response.sendRedirect(request.getContextPath() + "/login.jsp");
	        return;
	    }

	    request.getRequestDispatcher("/WEB-INF/views/dashboard.jsp")
	           .forward(request, response);
	}

}

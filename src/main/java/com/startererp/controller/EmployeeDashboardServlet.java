package com.startererp.controller;

import com.startererp.dao.EmployeeDAO;
import com.startererp.dto.EmployeeDTO;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/employee/dashboard")
public class EmployeeDashboardServlet extends HttpServlet {
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // 🔐 Session check
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("../login.jsp");
            return;
        }

        String role = (String) session.getAttribute("role");

        // 🔐 Role check
        if (!"EMPLOYEE".equalsIgnoreCase(role)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }

        String username = (String) session.getAttribute("loggedInUser");

        // 🔍 Fetch employee details
        EmployeeDAO dao = new EmployeeDAO();
        EmployeeDTO employee = dao.getEmployeeByUsername(username);

        request.setAttribute("employee", employee);

        // ➡️ Forward to JSP
        request.getRequestDispatcher("/WEB-INF/views/employee-dashboard.jsp")
               .forward(request, response);
    }
}

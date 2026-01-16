package com.startererp.controller;

import com.startererp.dao.EmployeeDAO;
import com.startererp.dto.EmployeeDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔒 Prevent browser cache
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        HttpSession session = request.getSession(false);

        if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        EmployeeDAO dao = new EmployeeDAO();
        List<EmployeeDTO> employees = dao.getAllEmployees();

        request.setAttribute("employees", employees);
        request.getRequestDispatcher("/WEB-INF/views/admin-dashboard.jsp")
               .forward(request, response);
    }
}

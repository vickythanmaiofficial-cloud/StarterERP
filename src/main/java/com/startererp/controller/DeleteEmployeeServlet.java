package com.startererp.controller;

import com.startererp.dao.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/delete")
public class DeleteEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // 🔐 Security: only ADMIN
        if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }

        String idParam = request.getParameter("id");

        if (idParam == null) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            return;
        }

        int employeeId;

        try {
            employeeId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            return;
        }

        EmployeeDAO dao = new EmployeeDAO();
        dao.deleteEmployee(employeeId);

        // Redirect back to admin dashboard
        response.sendRedirect(request.getContextPath() + "/admin/dashboard?msg=success");

    }
}

package com.startererp.controller;

import com.startererp.dao.EmployeeDAO;
import com.startererp.dto.EmployeeDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/edit")
public class EditEmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // 🔐 Admin only
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
        EmployeeDTO employee = dao.getEmployeeById(employeeId);

        if (employee == null) {
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
            return;
        }

        request.setAttribute("employee", employee);
        request.getRequestDispatcher("/WEB-INF/views/edit-employee.jsp")
               .forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // 🔐 Admin only
        if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied");
            return;
        }

        // Read form data
        String idParam = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String department = request.getParameter("department");

        // Basic validation
        if (idParam == null ||
            name == null || name.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            department == null || department.trim().isEmpty()) {

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

        // Prepare DTO
        EmployeeDTO employee = new EmployeeDTO();
        employee.setId(employeeId);
        employee.setName(name);
        employee.setEmail(email);
        employee.setDepartment(department);

        // Update DB
        EmployeeDAO dao = new EmployeeDAO();
        boolean updated = dao.updateEmployee(employee);

        // Redirect after POST (PRG pattern)
        response.sendRedirect(request.getContextPath() + "/admin/dashboard?msg=success");

    }

}

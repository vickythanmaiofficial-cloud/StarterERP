package com.startererp.controller;

import com.startererp.dao.EmployeeDAO;
import com.startererp.dto.EmployeeDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/add")
public class AddEmployeeServlet extends HttpServlet {

    // Show Add Employee form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Extra safety: only ADMIN allowed
        if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        request.getRequestDispatcher("/WEB-INF/views/add-employee.jsp")
               .forward(request, response);
    }

    // Handle form submission
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || !"ADMIN".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Read form data
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String department = request.getParameter("department");
        if (username == null || username.trim().isEmpty() ||
        	    name == null || name.trim().isEmpty() ||
        	    email == null || email.trim().isEmpty() ||
        	    department == null || department.trim().isEmpty()) {

        	    request.setAttribute("error", "All fields are required");
        	    request.getRequestDispatcher("/WEB-INF/views/add-employee.jsp")
        	           .forward(request, response);
        	    return;
        	}


        EmployeeDTO employee = new EmployeeDTO();
        employee.setUsername(username);
        employee.setName(name);
        employee.setEmail(email);
        employee.setDepartment(department);
        
        EmployeeDAO dao = new EmployeeDAO();
        if (dao.usernameExists(username)) {
            request.setAttribute("error", "Username already exists");
            request.getRequestDispatcher("/WEB-INF/views/add-employee.jsp")
                   .forward(request, response);
            return;
        }

        
        boolean success = dao.addEmployee(employee);

        if (success) {
            // Back to admin dashboard
        	response.sendRedirect(request.getContextPath() + "/admin/dashboard?msg=success");

        } else {
            request.setAttribute("error", "Failed to add employee");
            request.getRequestDispatcher("/WEB-INF/views/add-employee.jsp")
                   .forward(request, response);
        }
    }
}

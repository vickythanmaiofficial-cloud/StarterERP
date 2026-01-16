<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.startererp.dto.EmployeeDTO" %>
<%
    request.setAttribute("pageTitle", "Edit Employee | StarterERP");
%>
<%@ include file="common/header.jsp" %>


<%
    EmployeeDTO employee = (EmployeeDTO) request.getAttribute("employee");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Employee | StarterERP</title>
</head>
<body>

<h2>Edit Employee</h2>

<% if (employee != null) { %>

<form method="post" action="<%= request.getContextPath() %>/admin/edit-employee">

    <!-- Hidden ID -->
    <input type="hidden" name="id" value="<%= employee.getId() %>">

    <label>Username:</label><br>
    <input type="text" name="username"
           value="<%= employee.getUsername() %>" readonly><br><br>

    <label>Name:</label><br>
    <input type="text" name="name"
           value="<%= employee.getName() %>" required><br><br>

    <label>Email:</label><br>
    <input type="email" name="email"
           value="<%= employee.getEmail() %>" required><br><br>

    <label>Department:</label><br>
    <input type="text" name="department"
           value="<%= employee.getDepartment() %>" required><br><br>

    <button type="submit">Update Employee</button>
</form>

<% } else { %>

<p style="color:red;">Employee data not available.</p>

<% } %>

<br>
<a href="<%= request.getContextPath() %>/admin/dashboard">⬅ Back to Admin Dashboard</a>

</body>
</html>

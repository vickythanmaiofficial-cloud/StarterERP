<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    request.setAttribute("pageTitle", "Employee Dashboard | StarterERP");
%>
<%@ include file="common/header.jsp" %>



<%@ page import="com.startererp.dto.EmployeeDTO" %>

<%
    EmployeeDTO employee = (EmployeeDTO) request.getAttribute("employee");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Employee Dashboard</title>
</head>
<body>

<h2>Employee Dashboard</h2>
<span style="background:#2b7cff;color:#fff;padding:4px 8px;border-radius:4px;">
    ROLE: EMPLOYEE
</span>


<% if (employee != null) { %>

    <p><strong>Username:</strong> <%= employee.getUsername() %></p>
    <p><strong>Name:</strong> <%= employee.getName() %></p>
    <p><strong>Email:</strong> <%= employee.getEmail() %></p>
    <p><strong>Department:</strong> <%= employee.getDepartment() %></p>

<% } else { %>

    <p style="color:red;">Employee details not found.</p>

<% } %>

<br>
<a href="<%= request.getContextPath() %>/logout">Logout</a>

</body>
</html>
<%@ include file="common/footer.jsp" %>


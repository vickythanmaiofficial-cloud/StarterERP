<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    request.setAttribute("pageTitle", "Add Employee | StarterERP");
%>

<%@ include file="common/header.jsp" %>

<h2>Add New Employee</h2>

<c:if test="${not empty error}">
    <p style="color:red;">${error}</p>
</c:if>

<form method="post"
      action="${pageContext.request.contextPath}/admin/add">

    <label>Username:</label><br>
    <input type="text" name="username" required><br><br>

    <label>Name:</label><br>
    <input type="text" name="name" required><br><br>

    <label>Email:</label><br>
    <input type="email" name="email" required><br><br>

    <label>Department:</label><br>
    <input type="text" name="department" required><br><br>

    <button type="submit">Add Employee</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/admin/dashboard">
    ⬅ Back to Dashboard
</a>

<%@ include file="common/footer.jsp" %>

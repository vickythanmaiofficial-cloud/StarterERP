<%@ include file="common/header.jsp" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard | StarterERP</title>
</head>
<body>

<h1>Welcome to StarterERP 🚀</h1>

<p>
    Logged in as: <strong>${username}</strong><br>
    Role: <strong>${role}</strong>
</p>

<hr>

<c:choose>

    <c:when test="${role eq 'ADMIN'}">
        <h3>Admin Panel</h3>
        <p>You have admin privileges.</p>
    </c:when>

    <c:when test="${role eq 'EMPLOYEE'}">
        <h3>Employee Dashboard</h3>
        <p>Welcome employee. Limited access enabled.</p>
    </c:when>

    <c:otherwise>
        <p style="color:red;">Unknown role. Access denied.</p>
    </c:otherwise>

</c:choose>

<hr>

<a href="logout">Logout</a>

</body>
</html>
<%@ include file="common/footer.jsp" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    request.setAttribute("pageTitle", "Admin Dashboard | StarterERP");
%>

<%@ include file="common/header.jsp" %>

<div class="card shadow-sm mb-4">
    <div class="card-body">
        <h2 class="card-title">Admin Dashboard 🚀</h2>

        <span class="badge bg-dark mb-2">ROLE: ADMIN</span>

        <c:if test="${param.msg == 'success'}">
            <div class="alert alert-success mt-2">
                Operation completed successfully ✔
            </div>
        </c:if>

        <p class="mt-2">
            You are logged in as <strong>ADMIN</strong>
        </p>
    </div>
</div>

<div class="card shadow-sm">
    <div class="card-body">
        <h4>Employee Management</h4>
        <p class="text-muted">View, add, edit, or remove employees</p>

        <c:choose>
            <c:when test="${empty employees}">
                <div class="alert alert-danger">
                    No employees found.
                </div>
            </c:when>

            <c:otherwise>
                <table class="table table-bordered table-hover align-middle">
                    <thead class="table-light">
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Department</th>
                            <th style="width: 150px;">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="emp" items="${employees}">
                            <tr>
                                <td>${emp.id}</td>
                                <td>${emp.username}</td>
                                <td>${emp.name}</td>
                                <td>${emp.email}</td>
                                <td>${emp.department}</td>
                                <td>
                                    <a class="btn btn-sm btn-primary"
                                       href="${pageContext.request.contextPath}/admin/edit?id=${emp.id}">
                                        Edit
                                    </a>
                                    <a class="btn btn-sm btn-danger"
                                       href="${pageContext.request.contextPath}/admin/delete?id=${emp.id}"
                                       onclick="return confirm('Are you sure you want to delete this employee?');">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <a class="btn btn-success mt-3"
           href="${pageContext.request.contextPath}/admin/add">
            ➕ Add New Employee
        </a>
    </div>
</div>

<%@ include file="common/footer.jsp" %>

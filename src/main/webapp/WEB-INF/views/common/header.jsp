<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">

    <title>
        <%= request.getAttribute("pageTitle") != null
                ? request.getAttribute("pageTitle")
                : "StarterERP" %>
    </title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }
        .navbar-brand {
            font-weight: bold;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">

        <!-- Brand -->
        <a class="navbar-brand"
           href="<%=request.getContextPath()%>/admin/dashboard">
            🚀 StarterERP
        </a>

        <!-- Right side buttons -->
        <div class="d-flex">
            <a class="btn btn-outline-light me-2"
               href="<%=request.getContextPath()%>/admin/dashboard">
                Dashboard
            </a>

            <a class="btn btn-outline-warning"
               href="<%=request.getContextPath()%>/logout">
                Logout
            </a>
        </div>

    </div>
</nav>

<div class="container mt-4">

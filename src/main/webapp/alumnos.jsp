<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Gestión de Alumnos</title>
</head>
<body>
<div class="container mt-4">
    <h2>Lista de Alumnos</h2>

    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombres</th>
            <th>Apellidos</th>
            <th>DNI</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="a" items="${listaAlumnos}">
            <tr>
                <td>${a.idAlumno}</td>
                <td>${a.nombres}</td>
                <td>${a.apellidos}</td>
                <td>${a.dni}</td>
                <td>${a.email}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h2>Registrar Alumno</h2>
    <form action="alumnos" method="post" class="row g-3">
        <div class="col-md-6">
            <label class="form-label">Nombres</label>
            <input type="text" name="nombres" class="form-control" required>
        </div>
        <div class="col-md-6">
            <label class="form-label">Apellidos</label>
            <input type="text" name="apellidos" class="form-control" required>
        </div>
        <div class="col-md-6">
            <label class="form-label">DNI</label>
            <input type="text" name="dni" class="form-control" required>
        </div>
        <div class="col-md-6">
            <label class="form-label">Email</label>
            <input type="email" name="email" class="form-control">
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary">Registrar</button>
        </div>
    </form>
</div>
</body>
</html>
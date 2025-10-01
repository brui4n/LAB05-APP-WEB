<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Gestión de Alumnos</title>
</head>
<body>

<%@ include file="navbar.jsp" %>
<div class="container mt-4">

    <!-- Encabezado -->
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-primary">Gestión de Alumnos</h2>
        <span class="badge bg-secondary">Total: ${listaAlumnos.size()}</span>
    </div>

    <!-- Tabla de alumnos -->
    <div class="card shadow-sm mb-5">
        <div class="card-header bg-dark text-white">
            Lista de Alumnos Registrados
        </div>
        <div class="card-body p-0">
            <table class="table table-hover table-striped mb-0">
                <thead class="table-light">
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
        </div>
    </div>

    <!-- Formulario -->
    <div class="card shadow-sm">
        <div class="card-header bg-primary text-white">
            Registrar Nuevo Alumno
        </div>
        <div class="card-body">
            <form action="alumnos" method="post" class="row g-3">
                <div class="col-md-6">
                    <label class="form-label fw-bold">Nombres</label>
                    <input type="text" name="nombres" class="form-control" placeholder="Ingrese nombres" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label fw-bold">Apellidos</label>
                    <input type="text" name="apellidos" class="form-control" placeholder="Ingrese apellidos" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label fw-bold">DNI</label>
                    <input type="text" name="dni" class="form-control" placeholder="Ingrese DNI" required>
                </div>
                <div class="col-md-6">
                    <label class="form-label fw-bold">Email</label>
                    <input type="email" name="email" class="form-control" placeholder="ejemplo@correo.com">
                </div>
                <div class="col-12 text-end">
                    <button type="submit" class="btn btn-success px-4">Registrar Alumno</button>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>
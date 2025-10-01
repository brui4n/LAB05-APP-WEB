<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Cursos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="container mt-4">

    <!-- Encabezado -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="text-primary mb-0">📘 Gestión de Cursos</h2>
        <span class="badge bg-secondary">Total: <c:out value="${fn:length(listaCursos)}" /></span>
    </div>

    <!-- Mensajes -->
    <c:if test="${not empty sessionScope.mensajeExito}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${sessionScope.mensajeExito}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <c:remove var="mensajeExito" scope="session"/>
    </c:if>
    <c:if test="${not empty sessionScope.mensajeError}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${sessionScope.mensajeError}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <c:remove var="mensajeError" scope="session"/>
    </c:if>

    <!-- Tabla de cursos -->
    <div class="card shadow-sm mb-5">
        <div class="card-header bg-dark text-white">
            Lista de Cursos Registrados
        </div>
        <div class="card-body p-3">
            <div class="table-responsive">
                <table class="table table-hover table-bordered mb-0 align-middle">
                    <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Nombre del Curso</th>
                        <th>Créditos</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="c" items="${listaCursos}">
                        <tr>
                            <td>${c.idCurso}</td>
                            <td>${c.nombreCurso}</td>
                            <td>${c.creditos}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Formulario para registrar curso -->
    <div class="card shadow-sm mb-5">
        <div class="card-header bg-primary text-white">
            Registrar Nuevo Curso
        </div>
        <div class="card-body">
            <form action="cursos" method="post" class="row g-3">

                <div class="col-md-6">
                    <label class="form-label fw-bold">Nombre del Curso</label>
                    <input type="text" name="nombreCurso" class="form-control" required>
                </div>

                <div class="col-md-3">
                    <label class="form-label fw-bold">Créditos</label>
                    <input type="number" name="creditos" class="form-control" min="1" max="10" required>
                </div>

                <div class="col-md-3 d-flex align-items-end">
                    <button type="submit" class="btn btn-success w-100">
                        ➕ Registrar Curso
                    </button>
                </div>

            </form>
        </div>
    </div>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
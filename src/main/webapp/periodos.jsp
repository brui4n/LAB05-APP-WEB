<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestión de Periodos Académicos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="navbar.jsp" %>

<div class="container mt-4">
    <h2 class="mb-4">Gestión de Periodos Académicos</h2>

    <!-- Mensajes -->
    <c:if test="${not empty sessionScope.mensajeExito}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${sessionScope.mensajeExito}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <c:remove var="mensajeExito" scope="session"/>
    </c:if>

    <c:if test="${not empty sessionScope.mensajeError}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${sessionScope.mensajeError}
            <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
        </div>
        <c:remove var="mensajeError" scope="session"/>
    </c:if>

    <!-- Lista de periodos -->
    <div class="card shadow mb-4">
        <div class="card-header bg-dark text-white">
            <h5 class="mb-0">Lista de Periodos Académicos</h5>
        </div>
        <div class="card-body p-0">
            <table class="table table-hover mb-0">
                <thead class="table-light">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Fecha Inicio</th>
                    <th>Fecha Fin</th>
                    <th>Estado</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="p" items="${listaPeriodos}">
                    <tr>
                        <td>${p.idPeriodo}</td>
                        <td>${p.nombrePeriodo}</td>
                        <td>${p.fechaInicio}</td>
                        <td>${p.fechaFin}</td>
                        <td>
                            <c:choose>
                                <c:when test="${p.estado == 'activo'}">
                                    <span class="badge bg-success">Activo</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge bg-secondary">Cerrado</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Formulario de registro -->
    <div class="card shadow">
        <div class="card-header bg-primary text-white">
            <h5 class="mb-0">Registrar Nuevo Periodo</h5>
        </div>
        <div class="card-body">
            <form action="periodos" method="post" class="row g-3">
                <div class="col-md-4">
                    <label class="form-label">Nombre del Periodo</label>
                    <input type="text" name="nombrePeriodo" class="form-control" required>
                </div>
                <div class="col-md-3">
                    <label class="form-label">Fecha Inicio</label>
                    <input type="date" name="fechaInicio" class="form-control" required>
                </div>
                <div class="col-md-3">
                    <label class="form-label">Fecha Fin</label>
                    <input type="date" name="fechaFin" class="form-control" required>
                </div>
                <div class="col-md-2">
                    <label class="form-label">Estado</label>
                    <select name="estado" class="form-select" required>
                        <option value="activo">Activo</option>
                        <option value="cerrado">Cerrado</option>
                    </select>
                </div>
                <div class="col-12 text-end">
                    <button type="submit" class="btn btn-success">Registrar</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Gestión de Matrículas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="container mt-4">

    <!-- Encabezado -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="text-primary mb-0">📚 Gestión de Matrículas</h2>
        <span class="badge bg-secondary">Total: <c:out value="${fn:length(listaMatriculas)}" /></span>
    </div>

    <!-- Filtros -->
    <form method="get" action="matriculas" class="row g-3 mb-3">
        <!-- Filtro por Alumno -->
        <div class="col-md-6">
            <label class="form-label fw-bold">Filtrar por Alumno</label>
            <select name="idAlumnoFiltro" class="form-select" onchange="this.form.submit()">
                <option value="">-- Todos --</option>
                <c:forEach var="a" items="${listaAlumnos}">
                    <option value="${a.idAlumno}"
                            <c:if test="${param.idAlumnoFiltro == a.idAlumno}">selected</c:if>>
                            ${a.nombres} ${a.apellidos}
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- Filtro por Periodo -->
        <div class="col-md-6">
            <label class="form-label fw-bold">Filtrar por Periodo</label>
            <select name="idPeriodoFiltro" class="form-select" onchange="this.form.submit()">
                <option value="">-- Todos --</option>
                <c:forEach var="p" items="${listaPeriodos}">
                    <option value="${p.idPeriodo}"
                            <c:if test="${param.idPeriodoFiltro == p.idPeriodo}">selected</c:if>>
                            ${p.nombrePeriodo}
                    </option>
                </c:forEach>
            </select>
        </div>
    </form>

    <!-- Tabla de matrículas -->
    <div class="card shadow-sm mb-5">
        <div class="card-header bg-dark text-white">
            Lista de Matrículas Registradas
        </div>
        <div class="card-body p-3">
            <div class="table-responsive">
                <table class="table table-hover table-bordered mb-0 align-middle">
                    <thead class="table-light">
                    <tr>
                        <th>ID</th>
                        <th>Alumno</th>
                        <th>Periodo</th>
                        <th>Fecha</th>
                        <th>Estado</th>
                        <th>Acción</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="m" items="${listaMatriculas}">
                        <tr>
                            <td>${m.idMatricula}</td>
                            <td>
                                <c:forEach var="a" items="${listaAlumnos}">
                                    <c:if test="${a.idAlumno == m.idAlumno}">
                                        ${a.nombres} ${a.apellidos}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <c:forEach var="p" items="${listaPeriodos}">
                                    <c:if test="${p.idPeriodo == m.idPeriodo}">
                                        ${p.nombrePeriodo}
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>${m.fechaMatricula}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${m.estado == 'activo'}">
                                        <span class="badge bg-success">${m.estado}</span>
                                    </c:when>
                                    <c:when test="${m.estado == 'retirado'}">
                                        <span class="badge bg-warning text-dark">${m.estado}</span>
                                    </c:when>
                                    <c:when test="${m.estado == 'anulado'}">
                                        <span class="badge bg-danger">${m.estado}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary">${m.estado}</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <form action="matriculas" method="post" class="d-flex">
                                    <input type="hidden" name="idMatricula" value="${m.idMatricula}" />
                                    <select name="nuevoEstado" class="form-select form-select-sm w-auto me-2">
                                        <option value="activo" <c:if test="${m.estado == 'activo'}">selected</c:if>>Activo</option>
                                        <option value="retirado" <c:if test="${m.estado == 'retirado'}">selected</c:if>>Retirado</option>
                                        <option value="anulado" <c:if test="${m.estado == 'anulado'}">selected</c:if>>Anulado</option>
                                    </select>
                                    <button type="submit" class="btn btn-sm btn-primary">Actualizar</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Formulario -->
    <div class="card shadow-sm mb-5">
        <div class="card-header bg-primary text-white">
            Registrar Nueva Matrícula
        </div>
        <div class="card-body">
            <form action="matriculas" method="post" class="row g-3">

                <div class="col-md-6">
                    <label class="form-label fw-bold">Alumno</label>
                    <select name="idAlumno" class="form-select" required>
                        <option value="">-- Seleccione Alumno --</option>
                        <c:forEach var="a" items="${listaAlumnos}">
                            <option value="${a.idAlumno}">${a.nombres} ${a.apellidos}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Periodo (solo muestra el activo) -->
                <div class="col-md-6">
                    <label class="form-label fw-bold">Periodo Actual</label>
                    <c:forEach var="p" items="${listaPeriodos}">
                        <c:if test="${p.estado == 'activo'}">
                            <input type="hidden" name="idPeriodo" value="${p.idPeriodo}" />
                            <input type="text" class="form-control" value="${p.nombrePeriodo}" readonly />
                        </c:if>
                    </c:forEach>
                </div>

                <div class="col-md-6">
                    <label class="form-label fw-bold">Fecha Matrícula</label>
                    <input type="date" name="fechaMatricula" class="form-control" required>
                </div>

                <!-- Estado siempre activo -->
                <input type="hidden" name="estado" value="activo"/>

                <div class="col-md-6 d-flex align-items-end">
                    <button type="submit" class="btn btn-success w-100">
                        ➕ Registrar Matrícula
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Detalle de Matrículas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%@ include file="navbar.jsp" %>

<div class="container mt-4">

    <!-- Encabezado -->
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2 class="text-primary mb-0">📑 Gestión de Detalle de Matrículas</h2>
    </div>

    <!-- Filtros -->
    <div class="card shadow-sm mb-4">
        <div class="card-header bg-dark text-white">
            🔎 Filtros de Búsqueda
        </div>
        <div class="card-body">
            <form method="get" action="detalleMatricula" class="row g-3">
                <div class="col-md-6">
                    <label class="form-label fw-bold">Alumno</label>
                    <select name="idAlumnoFiltro" class="form-select" onchange="this.form.submit()">
                        <option value="">-- Seleccione Alumno --</option>
                        <c:forEach var="a" items="${listaAlumnos}">
                            <option value="${a.idAlumno}"
                                    <c:if test="${a.idAlumno == idAlumnoFiltro}">selected</c:if>>
                                    ${a.nombres} ${a.apellidos}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-6">
                    <label class="form-label fw-bold">Periodo Académico</label>
                    <select name="idPeriodoFiltro" class="form-select" onchange="this.form.submit()">
                        <option value="">-- Seleccione Periodo --</option>
                        <c:forEach var="p" items="${listaPeriodos}">
                            <option value="${p.idPeriodo}"
                                    <c:if test="${p.idPeriodo == idPeriodoFiltro}">selected</c:if>>
                                    ${p.nombrePeriodo}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </form>
        </div>
    </div>

    <!-- Tabla de cursos filtrados -->
    <div class="card shadow-sm mb-4">
        <div class="card-header bg-primary text-white">
            📚 Cursos de la Matrícula
        </div>
        <div class="card-body">
            <c:choose>
                <c:when test="${empty listaDetallesFiltrados}">
                    <div class="alert alert-info mb-0">
                        No hay cursos registrados para el filtro seleccionado.
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="table-responsive">
                        <table class="table table-hover table-bordered mb-0 align-middle">
                            <thead class="table-dark">
                            <tr>
                                <th>ID Detalle</th>
                                <th>Alumno</th>
                                <th>Periodo</th>
                                <th>Curso</th>
                                <th>Estado</th>
                                <th>Acción</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="d" items="${listaDetallesFiltrados}">
                                <tr>
                                    <td>${d.idDetalle}</td>
                                    <td>
                                        <c:forEach var="m" items="${listaMatriculas}">
                                            <c:if test="${m.idMatricula == d.idMatricula}">
                                                <c:forEach var="a" items="${listaAlumnos}">
                                                    <c:if test="${a.idAlumno == m.idAlumno}">
                                                        ${a.nombres} ${a.apellidos}
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:forEach var="m" items="${listaMatriculas}">
                                            <c:if test="${m.idMatricula == d.idMatricula}">
                                                <c:forEach var="p" items="${listaPeriodos}">
                                                    <c:if test="${p.idPeriodo == m.idPeriodo}">
                                                        ${p.nombrePeriodo}
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:forEach var="c" items="${listaCursos}">
                                            <c:if test="${c.idCurso == d.idCurso}">
                                                ${c.nombreCurso}
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${d.estado == 'matriculado'}">
                                                <span class="badge bg-success">Matriculado</span>
                                            </c:when>
                                            <c:when test="${d.estado == 'retirado'}">
                                                <span class="badge bg-danger">Retirado</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-secondary">${d.estado}</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:forEach var="m" items="${listaMatriculas}">
                                            <c:if test="${m.idMatricula == d.idMatricula}">
                                                <c:choose>
                                                    <c:when test="${m.estado == 'activo'}">
                                                        <!-- Botón para editar estado -->
                                                        <form action="detalleMatricula" method="post" class="d-inline">
                                                            <input type="hidden" name="idDetalle" value="${d.idDetalle}">
                                                            <select name="estado" class="form-select form-select-sm d-inline w-auto me-2">
                                                                <option value="matriculado" <c:if test="${d.estado == 'matriculado'}">selected</c:if>>Matriculado</option>
                                                                <option value="retirado" <c:if test="${d.estado == 'retirado'}">selected</c:if>>Retirado</option>
                                                            </select>
                                                            <button type="submit" class="btn btn-sm btn-primary">Actualizar</button>
                                                        </form>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge bg-secondary">No editable</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- Mensajes de éxito o error -->
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

    <!-- Formulario para asignar curso -->
    <div class="card shadow-sm">
        <div class="card-header bg-success text-white">
            ➕ Asignar Curso a Matrícula
        </div>
        <div class="card-body">
            <form action="asignarCurso" method="post" class="row g-3">
                <div class="col-md-4">
                    <label class="form-label fw-bold">Alumno</label>
                    <select name="idAlumno" class="form-select" required>
                        <option value="">-- Seleccione Alumno --</option>
                        <c:forEach var="a" items="${listaAlumnos}">
                            <option value="${a.idAlumno}"
                                    <c:if test="${a.idAlumno == idAlumnoFiltro}">selected</c:if>>
                                    ${a.nombres} ${a.apellidos}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-4">
                    <label class="form-label fw-bold">Periodo</label>
                    <select name="idPeriodo" class="form-select" required>
                        <option value="">-- Seleccione Periodo --</option>
                        <c:forEach var="p" items="${listaPeriodos}">
                            <option value="${p.idPeriodo}"
                                    <c:if test="${p.idPeriodo == idPeriodoFiltro}">selected</c:if>>
                                    ${p.nombrePeriodo}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-4">
                    <label class="form-label fw-bold">Curso</label>
                    <select name="idCurso" class="form-select" required>
                        <c:forEach var="c" items="${listaCursos}">
                            <option value="${c.idCurso}">${c.nombreCurso}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Estado fijo -->
                <input type="hidden" name="estado" value="matriculado"/>

                <div class="col-12 d-flex justify-content-end">
                    <button type="submit" class="btn btn-success">
                        Asignar Curso
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
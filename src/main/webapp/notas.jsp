<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestión de Notas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2 class="mb-4">Notas de Alumnos</h2>

    <!-- Tabla de notas existentes -->
    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID Nota</th>
            <th>Alumno</th>
            <th>Curso</th>
            <th>Evaluación</th>
            <th>Nota</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="n" items="${listaNotas}">
            <tr>
                <td>${n.idNota}</td>
                <td>
                    <c:forEach var="d" items="${listaDetalles}">
                        <c:if test="${d.idDetalle == n.idDetalle}">
                            <c:forEach var="m" items="${listaMatriculas}">
                                <c:if test="${m.idMatricula == d.idMatricula}">
                                    <c:forEach var="a" items="${listaAlumnos}">
                                        <c:if test="${a.idAlumno == m.idAlumno}">
                                            ${a.nombres} ${a.apellidos}
                                        </c:if>
                                    </c:forEach>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="d" items="${listaDetalles}">
                        <c:if test="${d.idDetalle == n.idDetalle}">
                            <c:forEach var="c" items="${listaCursos}">
                                <c:if test="${c.idCurso == d.idCurso}">
                                    ${c.nombreCurso}
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="e" items="${listaEvaluaciones}">
                        <c:if test="${e.idEvaluacion == n.idEvaluacion}">
                            ${e.nombre} (${e.peso}%)
                            <c:forEach var="c" items="${listaCursos}">
                                <c:if test="${c.idCurso == e.idCurso}">
                                    - ${c.nombreCurso}
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </c:forEach>
                </td>
                <td>${n.nota}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Formulario para registrar nueva nota -->
    <h2 class="mt-5 mb-3">Registrar Nueva Nota</h2>
    <form action="notas" method="post" class="row g-3">

        <!-- Select de alumnos -->
        <div class="col-md-6">
            <label class="form-label">Alumno</label>
            <select name="idAlumno" class="form-select" required>
                <c:forEach var="a" items="${listaAlumnos}">
                    <option value="${a.idAlumno}">${a.nombres} ${a.apellidos}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Select de cursos -->
        <div class="col-md-4">
            <label class="form-label">Curso</label>
            <select name="idCurso" class="form-select" required>
                <c:forEach var="c" items="${listaCursos}">
                    <option value="${c.idCurso}">${c.nombreCurso}</option>
                </c:forEach>
            </select>
        </div>

        <!-- Select de evaluaciones -->
        <div class="col-md-4">
            <label class="form-label">Evaluación</label>
            <select name="idEvaluacion" class="form-select" required>
                <c:forEach var="e" items="${listaEvaluaciones}">
                    <option value="${e.idEvaluacion}">
                            ${e.nombre} (${e.peso}%)
                        <c:forEach var="c" items="${listaCursos}">
                            <c:if test="${c.idCurso == e.idCurso}">
                                - ${c.nombreCurso}
                            </c:if>
                        </c:forEach>
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- Input de nota -->
        <div class="col-md-2">
            <label class="form-label">Nota</label>
            <input type="number" step="0.01" name="nota" class="form-control" min="0" max="20" required>
        </div>

        <div class="col-md-2 d-flex align-items-end">
            <button type="submit" class="btn btn-primary w-100">Registrar Nota</button>
        </div>
    </form>
</div>
</body>
</html>
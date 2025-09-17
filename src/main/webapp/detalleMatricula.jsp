<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Detalle de Matrículas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Detalle de Matrículas</h2>

    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Matrícula</th>
            <th>Curso</th>
            <th>Estado</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="d" items="${listaDetalles}">
            <tr>
                <td>${d.idDetalle}</td>
                <td>${d.idMatricula}</td>
                <td>
                    <c:forEach var="c" items="${listaCursos}">
                        <c:if test="${c.idCurso == d.idCurso}">
                            ${c.nombreCurso}
                        </c:if>
                    </c:forEach>
                </td>
                <td>${d.estado}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <h2>Asignar Curso a Matrícula</h2>
    <form action="detalleMatricula" method="post" class="row g-3">
        <div class="col-md-6">
            <label class="form-label">Matrícula</label>
            <select name="idMatricula" class="form-select" required>
                <c:forEach var="m" items="${listaMatriculas}">
                    <option value="${m.idMatricula}">
                            ${m.idMatricula} -
                        <c:forEach var="a" items="${listaAlumnos}">
                            <c:if test="${a.idAlumno == m.idAlumno}">
                                ${a.nombres} ${a.apellidos}
                            </c:if>
                        </c:forEach>
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md-4">
            <label class="form-label">Curso</label>
            <select name="idCurso" class="form-select" required>
                <c:forEach var="c" items="${listaCursos}">
                    <option value="${c.idCurso}">${c.nombreCurso}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md-2">
            <label class="form-label">Estado</label>
            <select name="estado" class="form-select" required>
                <option value="matriculado">Matriculado</option>
                <option value="retirado">Retirado</option>
            </select>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary mt-2">Asignar Curso</button>
        </div>
    </form>
</div>
</body>
</html>
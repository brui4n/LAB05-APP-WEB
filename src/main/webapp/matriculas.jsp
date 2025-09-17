<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestión de Matrículas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Lista de Matrículas</h2>

    <table class="table table-striped table-bordered">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Alumno</th>
            <th>Periodo</th>
            <th>Fecha</th>
            <th>Estado</th>
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
                <td>${m.estado}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h2>Registrar Matrícula</h2>
    <form action="matriculas" method="post" class="row g-3">
        <div class="col-md-4">
            <label class="form-label">Alumno</label>
            <select name="idAlumno" class="form-select" required>
                <c:forEach var="a" items="${listaAlumnos}">
                    <option value="${a.idAlumno}">${a.nombres} ${a.apellidos}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md-4">
            <label class="form-label">Periodo</label>
            <select name="idPeriodo" class="form-select" required>
                <c:forEach var="p" items="${listaPeriodos}">
                    <option value="${p.idPeriodo}">${p.nombrePeriodo}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md-2">
            <label class="form-label">Fecha</label>
            <input type="date" name="fechaMatricula" class="form-control" required>
        </div>
        <div class="col-md-2">
            <label class="form-label">Estado</label>
            <select name="estado" class="form-select" required>
                <option value="activo">Activo</option>
                <option value="retirado">Retirado</option>
                <option value="anulado">Anulado</option>
            </select>
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-primary mt-2">Registrar Matrícula</button>
        </div>
    </form>
</div>
</body>
</html>
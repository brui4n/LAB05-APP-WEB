<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Gestión de Periodos Académicos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Lista de Periodos Académicos</h2>

    <table class="table table-striped table-bordered">
        <thead class="table-dark">
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
                <td>${p.estado}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <h2>Registrar Periodo Académico</h2>
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
        <div class="col-12">
            <button type="submit" class="btn btn-primary mt-2">Registrar Periodo</button>
        </div>
    </form>
</div>
</body>
</html>
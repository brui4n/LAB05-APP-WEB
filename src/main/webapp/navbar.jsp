<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Bootstrap Navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="index_matricula.jsp">Sistema Academico</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="alumnos">Alumnos</a></li>
                <li class="nav-item"><a class="nav-link" href="matriculas">Matriculas</a></li>
                <li class="nav-item"><a class="nav-link" href="detalleMatricula">Detalle de Matriculas</a></li>
                <li class="nav-item"><a class="nav-link" href="periodos">Periodos Academicos</a></li>
                <li class="nav-item"><a class="nav-link" href="cursos">Cursos</a></li>
                <li class="nav-item"><a class="nav-link" href="notas">Notas</a></li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="logout">Salir</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
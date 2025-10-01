package pe.edu.tecsup.lab05.app05web.controller;

import pe.edu.tecsup.lab05.app05web.dao.AlumnoDAO;
import pe.edu.tecsup.lab05.app05web.dao.DetalleMatriculaDAO;
import pe.edu.tecsup.lab05.app05web.dao.MatriculaDAO;
import pe.edu.tecsup.lab05.app05web.dao.PeriodoAcademicoDAO;
import pe.edu.tecsup.lab05.app05web.model.Alumno;
import pe.edu.tecsup.lab05.app05web.model.Matricula;
import pe.edu.tecsup.lab05.app05web.model.PeriodoAcademico;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/matriculas")
public class MatriculaServlet extends HttpServlet {

    private MatriculaDAO matriculaDAO = new MatriculaDAO();
    private AlumnoDAO alumnoDAO = new AlumnoDAO();
    private PeriodoAcademicoDAO periodoDAO = new PeriodoAcademicoDAO();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private DetalleMatriculaDAO detalleDAO = new DetalleMatriculaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Matricula> matriculas = matriculaDAO.listar();
            List<Alumno> alumnos = alumnoDAO.listar();
            List<PeriodoAcademico> periodos = periodoDAO.listar();

            // ================================
            // Filtros
            // ================================
            String idAlumnoParam = request.getParameter("idAlumnoFiltro");
            String idPeriodoParam = request.getParameter("idPeriodoFiltro");

            Integer idAlumnoFiltro = (idAlumnoParam != null && !idAlumnoParam.isEmpty())
                    ? Integer.parseInt(idAlumnoParam)
                    : null;
            Integer idPeriodoFiltro = (idPeriodoParam != null && !idPeriodoParam.isEmpty())
                    ? Integer.parseInt(idPeriodoParam)
                    : null;

            // ================================
            // Filtrar lista de matrículas
            // ================================
            List<Matricula> matriculasFiltradas = new java.util.ArrayList<>();
            for (Matricula m : matriculas) {
                boolean okAlumno = (idAlumnoFiltro == null) || (m.getIdAlumno() == idAlumnoFiltro);
                boolean okPeriodo = (idPeriodoFiltro == null) || (m.getIdPeriodo() == idPeriodoFiltro);

                if (okAlumno && okPeriodo) {
                    matriculasFiltradas.add(m);
                }
            }

            // ================================
            // Pasar atributos al JSP
            // ================================
            request.setAttribute("listaMatriculas", matriculasFiltradas);
            request.setAttribute("listaAlumnos", alumnos);
            request.setAttribute("listaPeriodos", periodos);

            // Mantener seleccionados los filtros
            request.setAttribute("idAlumnoFiltro", idAlumnoFiltro);
            request.setAttribute("idPeriodoFiltro", idPeriodoFiltro);

            request.getRequestDispatcher("/matriculas.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String idMatriculaParam = request.getParameter("idMatricula");
            String nuevoEstado = request.getParameter("nuevoEstado");

            // ================================
            // 1) Cambio de estado de matrícula
            // ================================
            if (idMatriculaParam != null && nuevoEstado != null) {
                int idMatricula = Integer.parseInt(idMatriculaParam);
                matriculaDAO.actualizarEstado(idMatricula, nuevoEstado);

                // 👇 Nueva lógica: si el estado es retirado/anulado, actualizar detalles también
                if ("retirado".equalsIgnoreCase(nuevoEstado) || "anulado".equalsIgnoreCase(nuevoEstado)) {
                    detalleDAO.actualizarEstadoPorMatricula(idMatricula, "retirado");
                }

                request.getSession().setAttribute("mensajeExito",
                        "Estado de la matrícula actualizado correctamente.");
                response.sendRedirect("matriculas");
                return;
            }

            // ================================
            // 2) Registrar nueva matrícula
            // ================================
            int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));
            Date fechaMatricula = sdf.parse(request.getParameter("fechaMatricula"));

            // Buscar periodo activo
            PeriodoAcademico periodoActivo = null;
            for (PeriodoAcademico p : periodoDAO.listar()) {
                if ("activo".equalsIgnoreCase(p.getEstado())) {
                    periodoActivo = p;
                    break;
                }
            }

            if (periodoActivo == null) {
                request.getSession().setAttribute("mensajeError",
                        "No existe un periodo activo para matricular.");
                response.sendRedirect("matriculas");
                return;
            }

            // Validar duplicado: alumno ya matriculado en periodo activo
            for (Matricula m : matriculaDAO.listar()) {
                if (m.getIdAlumno() == idAlumno && m.getIdPeriodo() == periodoActivo.getIdPeriodo()) {
                    request.getSession().setAttribute("mensajeError",
                            "El alumno ya está matriculado en el periodo activo.");
                    response.sendRedirect("matriculas");
                    return;
                }
            }

            // Insertar matrícula (estado siempre "activo")
            Matricula nueva = new Matricula(0, idAlumno, periodoActivo.getIdPeriodo(), fechaMatricula, "activo");
            matriculaDAO.insertar(nueva);

            request.getSession().setAttribute("mensajeExito", "Matrícula registrada correctamente.");
            response.sendRedirect("matriculas");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
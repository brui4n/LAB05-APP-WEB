package pe.edu.tecsup.lab05.app05web.controller;

import pe.edu.tecsup.lab05.app05web.dao.*;
import pe.edu.tecsup.lab05.app05web.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/detalleMatricula")
public class DetalleMatriculaServlet extends HttpServlet {

    private DetalleMatriculaDAO detalleDAO = new DetalleMatriculaDAO();
    private MatriculaDAO matriculaDAO = new MatriculaDAO();
    private CursoDAO cursoDAO = new CursoDAO();
    private AlumnoDAO alumnoDAO = new AlumnoDAO();
    private PeriodoAcademicoDAO periodoDAO = new PeriodoAcademicoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // cargar datos base
            List<DetalleMatricula> detalles = detalleDAO.listar();
            List<Curso> cursos = cursoDAO.listar();
            List<Alumno> alumnos = alumnoDAO.listar();
            List<Matricula> matriculas = matriculaDAO.listar();
            List<PeriodoAcademico> periodos = periodoDAO.listar();

            // leer filtros
            String idAlumnoParam = request.getParameter("idAlumnoFiltro");
            String idPeriodoParam = request.getParameter("idPeriodoFiltro");

            Integer idAlumnoFiltro = (idAlumnoParam != null && !idAlumnoParam.isEmpty())
                    ? Integer.parseInt(idAlumnoParam) : null;
            Integer idPeriodoFiltro = (idPeriodoParam != null && !idPeriodoParam.isEmpty())
                    ? Integer.parseInt(idPeriodoParam) : null;

            // ===============================
            // 1) Filtrar detalles (tabla cursos)
            // ===============================
            List<DetalleMatricula> detallesFiltrados = new ArrayList<>();
            for (DetalleMatricula d : detalles) {
                Matricula mat = null;
                for (Matricula m : matriculas) {
                    if (m.getIdMatricula() == d.getIdMatricula()) {
                        mat = m;
                        break;
                    }
                }
                if (mat == null) continue;

                boolean okAlumno = (idAlumnoFiltro == null) || (mat.getIdAlumno() == idAlumnoFiltro);
                boolean okPeriodo = (idPeriodoFiltro == null) || (mat.getIdPeriodo() == idPeriodoFiltro);

                if (okAlumno && okPeriodo) {
                    detallesFiltrados.add(d);
                }
            }

            // ===============================
            // 2) Filtrar lista de alumnos (para el select)
            // ===============================
            List<Alumno> alumnosFiltrados = new ArrayList<>(alumnos);
            if (idPeriodoFiltro != null) {
                alumnosFiltrados.clear();
                for (Matricula m : matriculas) {
                    if (m.getIdPeriodo() == idPeriodoFiltro) {
                        for (Alumno a : alumnos) {
                            if (a.getIdAlumno() == m.getIdAlumno() && !alumnosFiltrados.contains(a)) {
                                alumnosFiltrados.add(a);
                            }
                        }
                    }
                }
            }

            // ===============================
            // 3) Filtrar lista de periodos (para el select)
            // ===============================
            List<PeriodoAcademico> periodosFiltrados = new ArrayList<>(periodos);
            if (idAlumnoFiltro != null) {
                periodosFiltrados.clear();
                for (Matricula m : matriculas) {
                    if (m.getIdAlumno() == idAlumnoFiltro) {
                        PeriodoAcademico p = periodoDAO.obtenerPorId(m.getIdPeriodo());
                        if (p != null && !periodosFiltrados.contains(p)) {
                            periodosFiltrados.add(p);
                        }
                    }
                }
            }

            // ===============================
            // 4) Pasar atributos al JSP
            // ===============================
            request.setAttribute("listaDetallesFiltrados", detallesFiltrados);
            request.setAttribute("listaMatriculas", matriculas); // para mostrar info en la tabla
            request.setAttribute("listaCursos", cursos);
            request.setAttribute("listaAlumnos", alumnosFiltrados);
            request.setAttribute("listaPeriodos", periodosFiltrados);

            request.setAttribute("idAlumnoFiltro", idAlumnoFiltro);
            request.setAttribute("idPeriodoFiltro", idPeriodoFiltro);

            request.getRequestDispatcher("/detalleMatricula.jsp").forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // 1) actualización de estado de detalle (editar)
            String idDetalleParam = request.getParameter("idDetalle");
            if (idDetalleParam != null && !idDetalleParam.isEmpty()) {
                int idDetalle = Integer.parseInt(idDetalleParam);
                String nuevoEstado = request.getParameter("estado");
                detalleDAO.actualizarEstado(idDetalle, nuevoEstado);
                response.sendRedirect("detalleMatricula");
                return;
            }

            // 2) retiro completo de matrícula
            if ("true".equals(request.getParameter("retirarMatricula"))) {
                int idMatricula = Integer.parseInt(request.getParameter("idMatricula"));

                // cambiar estado de la matrícula
                matriculaDAO.actualizarEstado(idMatricula, "retirado");

                // cambiar estado de todos los detalles vinculados
                for (DetalleMatricula d : detalleDAO.listar()) {
                    if (d.getIdMatricula() == idMatricula) {
                        detalleDAO.actualizarEstado(d.getIdDetalle(), "retirado");
                    }
                }

                request.getSession().setAttribute("mensajeExito", "La matrícula y todos sus cursos fueron retirados.");
                response.sendRedirect("detalleMatricula");
                return;
            }

            // 3) intento de inserción de detalle (asignar curso)
            String idAlumnoParam = request.getParameter("idAlumno");
            if (idAlumnoParam != null && !idAlumnoParam.isEmpty()) {
                int idAlumno = Integer.parseInt(idAlumnoParam);
                int idCurso = Integer.parseInt(request.getParameter("idCurso"));
                String estado = request.getParameter("estado");

                // Buscar matrícula activa de ese alumno
                Matricula matriculaActiva = null;
                for (Matricula m : matriculaDAO.listar()) {
                    if (m.getIdAlumno() == idAlumno && "activo".equalsIgnoreCase(m.getEstado())) {
                        matriculaActiva = m;
                        break;
                    }
                }

                if (matriculaActiva == null) {
                    request.getSession().setAttribute("mensajeError", "El alumno no tiene matrícula activa.");
                    response.sendRedirect("detalleMatricula?idAlumnoFiltro=" + idAlumno);
                    return;
                }

                // Insertar detalle
                DetalleMatricula nuevo = new DetalleMatricula(0, matriculaActiva.getIdMatricula(), idCurso, estado);
                detalleDAO.insertar(nuevo);

                // Redirigir manteniendo el filtro por alumno
                response.sendRedirect("detalleMatricula?idAlumnoFiltro=" + idAlumno);
                return;
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

}
package pe.edu.tecsup.lab05.app05web.controller;

import pe.edu.tecsup.lab05.app05web.dao.*;
import pe.edu.tecsup.lab05.app05web.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/asignarCurso")
public class AsignarCursoServlet extends HttpServlet {

    private MatriculaDAO matriculaDAO = new MatriculaDAO();
    private DetalleMatriculaDAO detalleDAO = new DetalleMatriculaDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));
            int idPeriodo = Integer.parseInt(request.getParameter("idPeriodo"));
            int idCurso = Integer.parseInt(request.getParameter("idCurso"));
            String estado = request.getParameter("estado");

            // 1) Buscar matrícula activa
            Matricula matriculaActiva = null;
            for (Matricula m : matriculaDAO.listar()) {
                if (m.getIdAlumno() == idAlumno
                        && m.getIdPeriodo() == idPeriodo
                        && "activo".equalsIgnoreCase(m.getEstado())) {
                    matriculaActiva = m;
                    break;
                }
            }

            if (matriculaActiva == null) {
                request.getSession().setAttribute("mensajeError", "El alumno no tiene matrícula activa en este periodo.");
                response.sendRedirect("detalleMatricula?idAlumnoFiltro=" + idAlumno + "&idPeriodoFiltro=" + idPeriodo);
                return;
            }

            // 2) Validar que no esté repetido
            for (DetalleMatricula d : detalleDAO.listar()) {
                if (d.getIdMatricula() == matriculaActiva.getIdMatricula()
                        && d.getIdCurso() == idCurso) {
                    request.getSession().setAttribute("mensajeError", "El curso ya está asignado a esta matrícula.");
                    response.sendRedirect("detalleMatricula?idAlumnoFiltro=" + idAlumno + "&idPeriodoFiltro=" + idPeriodo);
                    return;
                }
            }

            // 3) Insertar
            DetalleMatricula nuevo = new DetalleMatricula(0, matriculaActiva.getIdMatricula(), idCurso, estado);
            detalleDAO.insertar(nuevo);

            request.getSession().setAttribute("mensajeExito", "Curso asignado correctamente ✅");
            response.sendRedirect("detalleMatricula?idAlumnoFiltro=" + idAlumno + "&idPeriodoFiltro=" + idPeriodo);

        } catch (Exception e) {
            request.getSession().setAttribute("mensajeError", "Error inesperado: " + e.getMessage());
            response.sendRedirect("detalleMatricula");
        }
    }
}
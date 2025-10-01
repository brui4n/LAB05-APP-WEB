package pe.edu.tecsup.lab05.app05web.controller;

import pe.edu.tecsup.lab05.app05web.dao.PeriodoAcademicoDAO;
import pe.edu.tecsup.lab05.app05web.model.PeriodoAcademico;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/periodos")
public class PeriodoAcademicoServlet extends HttpServlet {

    private PeriodoAcademicoDAO dao = new PeriodoAcademicoDAO();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<PeriodoAcademico> periodos = dao.listar();

            // 1) Actualizar automáticamente estado si ya pasó la fecha fin
            Date hoy = new Date();
            for (PeriodoAcademico p : periodos) {
                if ("activo".equalsIgnoreCase(p.getEstado()) && p.getFechaFin().before(hoy)) {
                    dao.actualizarEstado(p.getIdPeriodo(), "cerrado");
                    p.setEstado("cerrado"); // actualizar en memoria también
                }
            }

            request.setAttribute("listaPeriodos", periodos);
            request.getRequestDispatcher("/periodos.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombrePeriodo");
            Date fechaInicio = sdf.parse(request.getParameter("fechaInicio"));
            Date fechaFin = sdf.parse(request.getParameter("fechaFin"));
            String estado = request.getParameter("estado");

            List<PeriodoAcademico> existentes = dao.listar();

            // Validar nombre repetido
            for (PeriodoAcademico per : existentes) {
                if (per.getNombrePeriodo().equalsIgnoreCase(nombre)) {
                    request.getSession().setAttribute("mensajeError", "El periodo '" + nombre + "' ya existe.");
                    response.sendRedirect("periodos");
                    return;
                }
            }

            // Validar que no haya otro activo
            if ("activo".equalsIgnoreCase(estado)) {
                for (PeriodoAcademico per : existentes) {
                    if ("activo".equalsIgnoreCase(per.getEstado())) {
                        request.getSession().setAttribute("mensajeError", "Ya existe un periodo activo (" + per.getNombrePeriodo() + ").");
                        response.sendRedirect("periodos");
                        return;
                    }
                }
            }

            PeriodoAcademico p = new PeriodoAcademico(0, nombre, fechaInicio, fechaFin, estado);
            dao.insertar(p);

            request.getSession().setAttribute("mensajeExito", "Periodo registrado correctamente.");
            response.sendRedirect("periodos");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
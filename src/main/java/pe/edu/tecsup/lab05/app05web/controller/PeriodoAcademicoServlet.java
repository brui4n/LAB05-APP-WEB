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

            PeriodoAcademico p = new PeriodoAcademico(0, nombre, fechaInicio, fechaFin, estado);
            dao.insertar(p);

            response.sendRedirect("periodos");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
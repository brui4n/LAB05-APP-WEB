package pe.edu.tecsup.lab05.app05web.controller;

import pe.edu.tecsup.lab05.app05web.dao.AlumnoDAO;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Matricula> matriculas = matriculaDAO.listar();
            List<Alumno> alumnos = alumnoDAO.listar();
            List<PeriodoAcademico> periodos = periodoDAO.listar();

            request.setAttribute("listaMatriculas", matriculas);
            request.setAttribute("listaAlumnos", alumnos);
            request.setAttribute("listaPeriodos", periodos);

            request.getRequestDispatcher("/matriculas.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));
            int idPeriodo = Integer.parseInt(request.getParameter("idPeriodo"));
            Date fechaMatricula = sdf.parse(request.getParameter("fechaMatricula"));
            String estado = request.getParameter("estado");

            Matricula m = new Matricula(0, idAlumno, idPeriodo, fechaMatricula, estado);
            matriculaDAO.insertar(m);

            response.sendRedirect("matriculas");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
package pe.edu.tecsup.lab05.app05web.controller;

import pe.edu.tecsup.lab05.app05web.dao.*;
import pe.edu.tecsup.lab05.app05web.model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/notas")
public class NotasServlet extends HttpServlet {

    private DetalleMatriculaDAO detalleDAO = new DetalleMatriculaDAO();
    private EvaluacionDAO evalDAO = new EvaluacionDAO();
    private NotaDAO notaDAO = new NotaDAO();
    private AlumnoDAO alumnoDAO = new AlumnoDAO();
    private CursoDAO cursoDAO = new CursoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<DetalleMatricula> detalles = detalleDAO.listar();
            List<Evaluacion> evaluaciones = evalDAO.listar();
            List<Nota> notas = notaDAO.listar();
            List<Alumno> alumnos = alumnoDAO.listar();
            List<Curso> cursos = cursoDAO.listar();
            List<Matricula> matriculas = new MatriculaDAO().listar(); // nueva lista

            request.setAttribute("listaDetalles", detalles);
            request.setAttribute("listaEvaluaciones", evaluaciones);
            request.setAttribute("listaNotas", notas);
            request.setAttribute("listaAlumnos", alumnos);
            request.setAttribute("listaCursos", cursos);
            request.setAttribute("listaMatriculas", matriculas); // pasa al JSP

            request.getRequestDispatcher("/notas.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idDetalle = Integer.parseInt(request.getParameter("idDetalle"));
            int idEvaluacion = Integer.parseInt(request.getParameter("idEvaluacion"));
            double nota = Double.parseDouble(request.getParameter("nota"));

            Nota n = new Nota(0, idDetalle, idEvaluacion, nota);
            notaDAO.insertar(n);

            response.sendRedirect("notas");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
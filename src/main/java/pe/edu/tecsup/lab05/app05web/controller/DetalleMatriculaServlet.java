package pe.edu.tecsup.lab05.app05web.controller;

import pe.edu.tecsup.lab05.app05web.dao.AlumnoDAO;
import pe.edu.tecsup.lab05.app05web.dao.DetalleMatriculaDAO;
import pe.edu.tecsup.lab05.app05web.dao.MatriculaDAO;
import pe.edu.tecsup.lab05.app05web.dao.CursoDAO; // vamos a necesitar un DAO de cursos
import pe.edu.tecsup.lab05.app05web.model.Alumno;
import pe.edu.tecsup.lab05.app05web.model.DetalleMatricula;
import pe.edu.tecsup.lab05.app05web.model.Matricula;
import pe.edu.tecsup.lab05.app05web.model.Curso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/detalleMatricula")
public class DetalleMatriculaServlet extends HttpServlet {

    private DetalleMatriculaDAO detalleDAO = new DetalleMatriculaDAO();
    private MatriculaDAO matriculaDAO = new MatriculaDAO();
    private CursoDAO cursoDAO = new CursoDAO(); // asumimos que ya tienes la tabla Curso
    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<DetalleMatricula> detalles = detalleDAO.listar();
            List<Matricula> matriculas = matriculaDAO.listar();
            List<Curso> cursos = cursoDAO.listar();
            List<Alumno> alumnos = alumnoDAO.listar(); // obtiene todos los alumnos

            request.setAttribute("listaDetalles", detalles);
            request.setAttribute("listaMatriculas", matriculas);
            request.setAttribute("listaCursos", cursos);
            request.setAttribute("listaAlumnos", alumnos); // pasa la lista de alumnos al jsp

            request.getRequestDispatcher("/detalleMatricula.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idMatricula = Integer.parseInt(request.getParameter("idMatricula"));
            int idCurso = Integer.parseInt(request.getParameter("idCurso"));
            String estado = request.getParameter("estado");

            DetalleMatricula detalle = new DetalleMatricula(0, idMatricula, idCurso, estado);
            detalleDAO.insertar(detalle);

            response.sendRedirect("detalleMatricula");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
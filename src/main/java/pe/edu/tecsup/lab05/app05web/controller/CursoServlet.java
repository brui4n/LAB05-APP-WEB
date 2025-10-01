package pe.edu.tecsup.lab05.app05web.controller;

import pe.edu.tecsup.lab05.app05web.dao.CursoDAO;
import pe.edu.tecsup.lab05.app05web.model.Curso;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/cursos")
public class CursoServlet extends HttpServlet {

    private CursoDAO cursoDAO = new CursoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Curso> cursos = cursoDAO.listar();
            request.setAttribute("listaCursos", cursos);
            request.getRequestDispatcher("/curso.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombreCurso = request.getParameter("nombreCurso");
            int creditos = Integer.parseInt(request.getParameter("creditos"));

            Curso nuevo = new Curso(0, nombreCurso, creditos);
            cursoDAO.insertar(nuevo);

            request.getSession().setAttribute("mensajeExito", "Curso registrado correctamente.");
            response.sendRedirect("cursos");
        } catch (Exception e) {
            request.getSession().setAttribute("mensajeError", "Error al registrar curso: " + e.getMessage());
            response.sendRedirect("cursos");
        }
    }
}
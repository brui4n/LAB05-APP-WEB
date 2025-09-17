package pe.edu.tecsup.lab05.app05web.controller;

import pe.edu.tecsup.lab05.app05web.dao.AlumnoDAO;
import pe.edu.tecsup.lab05.app05web.model.Alumno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/alumnos")
public class AlumnoServlet extends HttpServlet {

    private AlumnoDAO alumnoDAO = new AlumnoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Alumno> alumnos = alumnoDAO.listar();
            request.setAttribute("listaAlumnos", alumnos);
            request.getRequestDispatcher("alumnos.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombres = request.getParameter("nombres");
            String apellidos = request.getParameter("apellidos");
            String dni = request.getParameter("dni");
            String email = request.getParameter("email");

            Alumno nuevo = new Alumno(0, nombres, apellidos, dni, email);
            alumnoDAO.insertar(nuevo);

            response.sendRedirect("alumnos"); // redirige al listado
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
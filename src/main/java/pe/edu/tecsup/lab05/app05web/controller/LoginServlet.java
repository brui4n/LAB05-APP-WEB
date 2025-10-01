package pe.edu.tecsup.lab05.app05web.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final String ADMIN_USER = "admin";
    private final String ADMIN_PASS = "12345";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");

        if (ADMIN_USER.equals(usuario) && ADMIN_PASS.equals(clave)) {
            HttpSession session = request.getSession();
            session.setAttribute("adminLogueado", true);
            response.sendRedirect("matriculas"); // inicio después de login
        } else {
            request.setAttribute("error", "Usuario o clave incorrectos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}

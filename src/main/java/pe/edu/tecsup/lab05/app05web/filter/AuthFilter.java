package pe.edu.tecsup.lab05.app05web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter({"/alumnos", "/matriculas", "/detalleMatricula", "/periodos", "/cursos", "/notas"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession(false);

        boolean logueado = (session != null && Boolean.TRUE.equals(session.getAttribute("adminLogueado")));

        if (!logueado) {
            response.sendRedirect("index.jsp");
            return;
        }

        chain.doFilter(req, res);
    }
}
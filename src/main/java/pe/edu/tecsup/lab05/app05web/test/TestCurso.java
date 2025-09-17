package pe.edu.tecsup.lab05.app05web.test;

import pe.edu.tecsup.lab05.app05web.dao.CursoDAO;
import pe.edu.tecsup.lab05.app05web.model.Curso;

public class TestCurso {
    public static void main(String[] args) {
        CursoDAO dao = new CursoDAO();
        try {
            dao.insertar(new Curso(0, "Matemáticas", 4));
            dao.insertar(new Curso(0, "Programación Java", 3));
            dao.insertar(new Curso(0, "Física", 3));
            System.out.println("Cursos insertados correctamente!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
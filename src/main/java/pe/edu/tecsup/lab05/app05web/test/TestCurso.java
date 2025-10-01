package pe.edu.tecsup.lab05.app05web.test;

import pe.edu.tecsup.lab05.app05web.dao.CursoDAO;
import pe.edu.tecsup.lab05.app05web.model.Curso;

public class TestCurso {
    public static void main(String[] args) {
        CursoDAO dao = new CursoDAO();
        try {
            dao.insertar(new Curso(0, "Expresion Oral", 3));
            dao.insertar(new Curso(0, "Aplicaciones web", 5));
            dao.insertar(new Curso(0, "Programaciones Moviles", 5));
            System.out.println("Cursos insertados correctamente!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
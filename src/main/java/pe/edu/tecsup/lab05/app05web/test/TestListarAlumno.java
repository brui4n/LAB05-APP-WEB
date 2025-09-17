package pe.edu.tecsup.lab05.app05web.test;

import pe.edu.tecsup.lab05.app05web.dao.AlumnoDAO;
import pe.edu.tecsup.lab05.app05web.model.Alumno;

public class TestListarAlumno {
    public static void main(String[] args) throws Exception {
        AlumnoDAO dao = new AlumnoDAO();
        for (Alumno a : dao.listar()) {
            System.out.println(a.getIdAlumno() + " - " + a.getNombres());
        }
    }
}

package pe.edu.tecsup.lab05.app05web.test;

import pe.edu.tecsup.lab05.app05web.dao.AlumnoDAO;
import pe.edu.tecsup.lab05.app05web.model.Alumno;

import java.util.List;

public class TestAlumno {
    public static void main(String[] args) {
        AlumnoDAO dao = new AlumnoDAO();

        try {
            // Insertar alumno
            Alumno nuevo = new Alumno(0, "Bryan", "Coronel", "12345678", "bryan@example.com");
            dao.insertar(nuevo);
            System.out.println("✅ Alumno insertado!");

            // Listar alumnos
            List<Alumno> lista = dao.listar();
            for (Alumno a : lista) {
                System.out.println(a.getIdAlumno() + " - " + a.getNombres() + " " + a.getApellidos());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
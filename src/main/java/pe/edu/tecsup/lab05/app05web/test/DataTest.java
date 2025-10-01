package pe.edu.tecsup.lab05.app05web.test;

import pe.edu.tecsup.lab05.app05web.dao.*;
import pe.edu.tecsup.lab05.app05web.model.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTest {

    public static void main(String[] args) {
        try {
            AlumnoDAO alumnoDAO = new AlumnoDAO();
            CursoDAO cursoDAO = new CursoDAO();
            PeriodoAcademicoDAO periodoDAO = new PeriodoAcademicoDAO();
            MatriculaDAO matriculaDAO = new MatriculaDAO();
            DetalleMatriculaDAO detalleDAO = new DetalleMatriculaDAO();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            // ================================
            // 1) Insertar alumnos de ejemplo
            // ================================
            alumnoDAO.insertar(new Alumno(0, "Bryan", "Coronel", "12345678", "bc@ejemplo.com"));
            alumnoDAO.insertar(new Alumno(0, "Aldo", "Montoya", "87654321", "aldy@ejemplo.com"));
            alumnoDAO.insertar(new Alumno(0, "Roberto", "Mas", "45678912", "rob@ejemplo.com"));

            System.out.println("✅ Alumnos insertados correctamente.");

            // ================================
            // 2) Insertar cursos de ejemplo
            // ================================
            cursoDAO.insertar(new Curso(0, "Programación I", 4));
            cursoDAO.insertar(new Curso(0, "Base de Datos", 3));
            cursoDAO.insertar(new Curso(0, "Estructuras de Datos", 4));

            System.out.println("✅ Cursos insertados correctamente.");

            // ================================
            // 3) Insertar periodo académico
            // ================================
            Date inicio = sdf.parse("2025-03-01");
            Date fin = sdf.parse("2025-07-30");
            PeriodoAcademico periodo = new PeriodoAcademico(0, "2025-I", inicio, fin, "activo");
            periodoDAO.insertar(periodo);

            System.out.println("✅ Periodo académico insertado correctamente.");

            // ================================
            // 4) Insertar matrícula
            // ================================
            Date fechaMatricula = sdf.parse("2025-03-05");
            Matricula matricula = new Matricula(0, 1, 1, fechaMatricula, "activo"); // alumno 1, periodo 1
            matriculaDAO.insertar(matricula);

            System.out.println("✅ Matrícula insertada correctamente.");

            // ================================
            // 5) Insertar detalles de matrícula
            // ================================
            detalleDAO.insertar(new DetalleMatricula(0, 1, 1, "matriculado")); // Programación I
            detalleDAO.insertar(new DetalleMatricula(0, 1, 2, "matriculado")); // Base de Datos

            System.out.println("✅ Detalles de matrícula insertados correctamente.");

            System.out.println("🎉 Datos de prueba cargados con éxito.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package pe.edu.tecsup.lab05.app05web.test;

import pe.edu.tecsup.lab05.app05web.dao.PeriodoAcademicoDAO;
import pe.edu.tecsup.lab05.app05web.model.PeriodoAcademico;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestPeriodoAcademico {
    public static void main(String[] args) {
        PeriodoAcademicoDAO dao = new PeriodoAcademicoDAO();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Crear algunos periodos de prueba
            PeriodoAcademico p1 = new PeriodoAcademico(0, "2025-I", sdf.parse("2025-03-01"), sdf.parse("2025-07-31"), "activo");
            PeriodoAcademico p2 = new PeriodoAcademico(0, "2025-II", sdf.parse("2025-08-01"), sdf.parse("2025-12-31"), "cerrado");

            dao.insertar(p1);
            dao.insertar(p2);

            System.out.println("✅ Periodos insertados correctamente!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
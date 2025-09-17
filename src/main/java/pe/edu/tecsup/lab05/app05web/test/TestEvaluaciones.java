package pe.edu.tecsup.lab05.app05web.test;

import pe.edu.tecsup.lab05.app05web.dao.EvaluacionDAO;
import pe.edu.tecsup.lab05.app05web.model.Evaluacion;

public class TestEvaluaciones {
    public static void main(String[] args) throws Exception {
        EvaluacionDAO dao = new EvaluacionDAO();

        // Suponiendo que tus cursos tienen IDs 1, 2, 3...
        int[] cursos = {1, 2, 3};
        String[] tipos = {"Examen", "Práctica", "Proyecto"};
        double[] pesos = {40, 30, 30}; // ejemplo de ponderación

        for (int idCurso : cursos) {
            for (int i = 0; i < tipos.length; i++) {
                dao.insertar(new Evaluacion(0, idCurso, tipos[i], pesos[i]));
            }
        }
        System.out.println("✅ Evaluaciones insertadas correctamente!");
    }
}
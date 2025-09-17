package pe.edu.tecsup.lab05.app05web.model;

public class Evaluacion {
    private int idEvaluacion;
    private int idCurso; // FK a Curso
    private String nombre; // examen, práctica, proyecto
    private double peso;   // porcentaje de la nota final

    public Evaluacion() {}

    public Evaluacion(int idEvaluacion, int idCurso, String nombre, double peso) {
        this.idEvaluacion = idEvaluacion;
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.peso = peso;
    }

    public int getIdEvaluacion() { return idEvaluacion; }
    public void setIdEvaluacion(int idEvaluacion) { this.idEvaluacion = idEvaluacion; }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
}
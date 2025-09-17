package pe.edu.tecsup.lab05.app05web.model;

public class Curso {
    private int idCurso;
    private String nombreCurso;
    private int creditos;

    public Curso() {}

    public Curso(int idCurso, String nombreCurso, int creditos) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.creditos = creditos;
    }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getNombreCurso() { return nombreCurso; }
    public void setNombreCurso(String nombreCurso) { this.nombreCurso = nombreCurso; }

    public int getCreditos() { return creditos; }
    public void setCreditos(int creditos) { this.creditos = creditos; }
}
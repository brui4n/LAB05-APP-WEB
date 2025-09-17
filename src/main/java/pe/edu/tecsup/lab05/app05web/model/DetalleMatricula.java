package pe.edu.tecsup.lab05.app05web.model;

public class DetalleMatricula {
    private int idDetalle;
    private int idMatricula; // FK a Matricula
    private int idCurso;     // FK a Curso
    private String estado;   // matriculado, retirado

    public DetalleMatricula() {}

    public DetalleMatricula(int idDetalle, int idMatricula, int idCurso, String estado) {
        this.idDetalle = idDetalle;
        this.idMatricula = idMatricula;
        this.idCurso = idCurso;
        this.estado = estado;
    }

    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }

    public int getIdMatricula() { return idMatricula; }
    public void setIdMatricula(int idMatricula) { this.idMatricula = idMatricula; }

    public int getIdCurso() { return idCurso; }
    public void setIdCurso(int idCurso) { this.idCurso = idCurso; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
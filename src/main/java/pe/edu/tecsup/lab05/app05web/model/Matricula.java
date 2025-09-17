package pe.edu.tecsup.lab05.app05web.model;

import java.util.Date;

public class Matricula {
    private int idMatricula;
    private int idAlumno; // FK a Alumno
    private int idPeriodo; // FK a PeriodoAcademico
    private Date fechaMatricula;
    private String estado; // activo, retirado, anulado

    public Matricula() {}

    public Matricula(int idMatricula, int idAlumno, int idPeriodo, Date fechaMatricula, String estado) {
        this.idMatricula = idMatricula;
        this.idAlumno = idAlumno;
        this.idPeriodo = idPeriodo;
        this.fechaMatricula = fechaMatricula;
        this.estado = estado;
    }

    public int getIdMatricula() { return idMatricula; }
    public void setIdMatricula(int idMatricula) { this.idMatricula = idMatricula; }

    public int getIdAlumno() { return idAlumno; }
    public void setIdAlumno(int idAlumno) { this.idAlumno = idAlumno; }

    public int getIdPeriodo() { return idPeriodo; }
    public void setIdPeriodo(int idPeriodo) { this.idPeriodo = idPeriodo; }

    public Date getFechaMatricula() { return fechaMatricula; }
    public void setFechaMatricula(Date fechaMatricula) { this.fechaMatricula = fechaMatricula; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
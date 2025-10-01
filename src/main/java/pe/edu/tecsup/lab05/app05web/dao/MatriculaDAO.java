package pe.edu.tecsup.lab05.app05web.dao;

import pe.edu.tecsup.lab05.app05web.config.DBConnection;
import pe.edu.tecsup.lab05.app05web.model.Matricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    // Insertar matrícula
    public void insertar(Matricula matricula) throws Exception {
        String sql = "INSERT INTO Matricula (idAlumno, idPeriodo, fechaMatricula, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, matricula.getIdAlumno());
            ps.setInt(2, matricula.getIdPeriodo());
            ps.setDate(3, new java.sql.Date(matricula.getFechaMatricula().getTime()));
            ps.setString(4, matricula.getEstado());
            ps.executeUpdate();
        }
    }

    // Listar matrículas con info básica de alumno y periodo
    public List<Matricula> listar() throws Exception {
        List<Matricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM Matricula";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Matricula m = new Matricula(
                        rs.getInt("idMatricula"),
                        rs.getInt("idAlumno"),
                        rs.getInt("idPeriodo"),
                        rs.getDate("fechaMatricula"),
                        rs.getString("estado")
                );
                lista.add(m);
            }
        }
        return lista;
    }

    public List<Matricula> listarPorAlumnoYEstado(int idAlumno, String estado) throws Exception {
        List<Matricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM matricula WHERE idAlumno = ? AND estado = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idAlumno);
            ps.setString(2, estado);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(new Matricula(
                            rs.getInt("idMatricula"),
                            rs.getInt("idAlumno"),
                            rs.getInt("idPeriodo"),
                            rs.getDate("fechaMatricula"),
                            rs.getString("estado")
                    ));
                }
            }
        }
        return lista;
    }

    public void actualizarEstado(int idMatricula, String nuevoEstado) throws Exception {
        String sql = "UPDATE Matricula SET estado = ? WHERE idMatricula = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, idMatricula);
            ps.executeUpdate();
        }
    }
}
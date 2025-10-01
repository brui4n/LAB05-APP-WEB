package pe.edu.tecsup.lab05.app05web.dao;

import pe.edu.tecsup.lab05.app05web.config.DBConnection;
import pe.edu.tecsup.lab05.app05web.model.PeriodoAcademico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PeriodoAcademicoDAO {

    // Insertar periodo
    public void insertar(PeriodoAcademico periodo) throws Exception {
        String sql = "INSERT INTO PeriodoAcademico (nombrePeriodo, fechaInicio, fechaFin, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, periodo.getNombrePeriodo());
            ps.setDate(2, new java.sql.Date(periodo.getFechaInicio().getTime()));
            ps.setDate(3, new java.sql.Date(periodo.getFechaFin().getTime()));
            ps.setString(4, periodo.getEstado());
            ps.executeUpdate();
        }
    }

    // Listar periodos
    public List<PeriodoAcademico> listar() throws Exception {
        List<PeriodoAcademico> lista = new ArrayList<>();
        String sql = "SELECT * FROM PeriodoAcademico";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PeriodoAcademico p = new PeriodoAcademico(
                        rs.getInt("idPeriodo"),
                        rs.getString("nombrePeriodo"),
                        rs.getDate("fechaInicio"),
                        rs.getDate("fechaFin"),
                        rs.getString("estado")
                );
                lista.add(p);
            }
        }
        return lista;
    }

    // Obtener periodo por ID
    public PeriodoAcademico obtenerPorId(int idPeriodo) throws Exception {
        String sql = "SELECT * FROM PeriodoAcademico WHERE idPeriodo = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPeriodo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PeriodoAcademico(
                            rs.getInt("idPeriodo"),
                            rs.getString("nombrePeriodo"),
                            rs.getDate("fechaInicio"),
                            rs.getDate("fechaFin"),
                            rs.getString("estado")
                    );
                }
            }
        }
        return null;
    }

    public void actualizarEstado(int idPeriodo, String nuevoEstado) throws Exception {
        String sql = "UPDATE PeriodoAcademico SET estado = ? WHERE idPeriodo = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idPeriodo);
            ps.executeUpdate();
        }
    }
}
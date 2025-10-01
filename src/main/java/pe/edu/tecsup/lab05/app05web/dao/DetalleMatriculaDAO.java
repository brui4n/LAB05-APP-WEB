package pe.edu.tecsup.lab05.app05web.dao;

import pe.edu.tecsup.lab05.app05web.config.DBConnection;
import pe.edu.tecsup.lab05.app05web.model.DetalleMatricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DetalleMatriculaDAO {

    // Insertar detalle
    public void insertar(DetalleMatricula detalle) throws Exception {
        String sql = "INSERT INTO DetalleMatricula (idMatricula, idCurso, estado) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, detalle.getIdMatricula());
            ps.setInt(2, detalle.getIdCurso());
            ps.setString(3, detalle.getEstado());
            ps.executeUpdate();
        }
    }

    // Listar detalles
    public List<DetalleMatricula> listar() throws Exception {
        List<DetalleMatricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM DetalleMatricula";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DetalleMatricula d = new DetalleMatricula(
                        rs.getInt("idDetalle"),
                        rs.getInt("idMatricula"),
                        rs.getInt("idCurso"),
                        rs.getString("estado")
                );
                lista.add(d);
            }
        }
        return lista;
    }

    // Actualizar estado de un detalle existente
    public void actualizarEstado(int idDetalle, String nuevoEstado) throws Exception {
        String sql = "UPDATE DetalleMatricula SET estado = ? WHERE idDetalle = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, idDetalle);
            ps.executeUpdate();
        }
    }

    public void actualizarEstadoPorMatricula(int idMatricula, String nuevoEstado) throws Exception {
        String sql = "UPDATE DetalleMatricula SET estado = ? WHERE idMatricula = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, idMatricula);
            ps.executeUpdate();
        }
    }

}
package pe.edu.tecsup.lab05.app05web.dao;

import pe.edu.tecsup.lab05.app05web.config.DBConnection;
import pe.edu.tecsup.lab05.app05web.model.Evaluacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EvaluacionDAO {

    public void insertar(Evaluacion e) throws Exception {
        String sql = "INSERT INTO Evaluacion (idCurso, nombre, peso) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, e.getIdCurso());
            ps.setString(2, e.getNombre());
            ps.setDouble(3, e.getPeso());
            ps.executeUpdate();
        }
    }

    public List<Evaluacion> listar() throws Exception {
        List<Evaluacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM Evaluacion";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Evaluacion e = new Evaluacion(
                        rs.getInt("idEvaluacion"),
                        rs.getInt("idCurso"),
                        rs.getString("nombre"),
                        rs.getDouble("peso")
                );
                lista.add(e);
            }
        }
        return lista;
    }
}
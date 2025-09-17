package pe.edu.tecsup.lab05.app05web.dao;

import pe.edu.tecsup.lab05.app05web.config.DBConnection;
import pe.edu.tecsup.lab05.app05web.model.Nota;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NotaDAO {

    public void insertar(Nota n) throws Exception {
        String sql = "INSERT INTO Nota (idDetalle, idEvaluacion, nota) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, n.getIdDetalle());
            ps.setInt(2, n.getIdEvaluacion());
            ps.setDouble(3, n.getNota());
            ps.executeUpdate();
        }
    }

    public List<Nota> listar() throws Exception {
        List<Nota> lista = new ArrayList<>();
        String sql = "SELECT * FROM Nota";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Nota n = new Nota(
                        rs.getInt("idNota"),
                        rs.getInt("idDetalle"),
                        rs.getInt("idEvaluacion"),
                        rs.getDouble("nota")
                );
                lista.add(n);
            }
        }
        return lista;
    }
}
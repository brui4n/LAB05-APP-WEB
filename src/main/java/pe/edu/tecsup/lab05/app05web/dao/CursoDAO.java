package pe.edu.tecsup.lab05.app05web.dao;

import pe.edu.tecsup.lab05.app05web.config.DBConnection;
import pe.edu.tecsup.lab05.app05web.model.Curso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    // Insertar curso
    public void insertar(Curso curso) throws Exception {
        String sql = "INSERT INTO Curso (nombreCurso, creditos) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, curso.getNombreCurso());
            ps.setInt(2, curso.getCreditos());
            ps.executeUpdate();
        }
    }

    // Listar cursos
    public List<Curso> listar() throws Exception {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM Curso";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso c = new Curso(
                        rs.getInt("idCurso"),
                        rs.getString("nombreCurso"),
                        rs.getInt("creditos")
                );
                lista.add(c);
            }
        }
        return lista;
    }
}
package pe.edu.tecsup.lab05.app05web.model;

public class Administrador {
    private String usuario;
    private String clave;

    public Administrador(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    public String getUsuario() { return usuario; }
    public String getClave() { return clave; }
}

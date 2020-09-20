package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexion {
    // URL de la base de datos
    private String url = "jdbc:mysql://localhost/pezkbonis";;
    // Usuario para conectar
    private String usuario = "root";
    // Contraseña de usuario
    private String contrasena = "n0m3l0";

    //Regresa la conexión a la base de datos
    public Connection ObtenerConexionDB() {
        Connection conexion = null;
        
        // Conectar a base de datos
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(this.url, this.usuario, this.contrasena);
            System.out.println("Base de datos conectada");
        } catch(ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar a la base de datos");
            System.out.println(e.getMessage());
        }
        
        return conexion;
    }
}

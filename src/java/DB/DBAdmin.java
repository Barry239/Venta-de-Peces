package DB;

import Classes.Carrito;
import Classes.Compra;
import Classes.Producto;
import Classes.Sesion;
import Classes.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBAdmin {
    
    public static int RegistrarUsuario(Usuario usuario) {
        int estado = 0;
        
        // Establecer conexion a base de datos
        DBConexion dbconexion = new DBConexion();
        Connection conexion = dbconexion.ObtenerConexionDB();
        
        try {
            // Establecer Query
            String q = "INSERT INTO usuarios(nombre_usuario, email_usuario, contrasena_usuario) VALUE (?, ?, ?)";
            
            // Hacer registro
            PreparedStatement ps = conexion.prepareStatement(q);
            ps.setString(1, usuario.getNombre_usuario());
            ps.setString(2, usuario.getEmail_usuario());
            ps.setString(3, usuario.getContrasena_usuario());
            estado = ps.executeUpdate();
            
            System.out.println("Usuario registrado exitosamente");
            
            // Cerrar conexion
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al registrar usuario");
            System.out.println(e.getMessage());
        }
        
        return estado;
    }
    
    public static Sesion ObtenerSesionUsuario(String email_usuario) {
        Sesion sesion = new Sesion();
        
        // Establecer conexion a base de datos
        DBConexion dbconexion = new DBConexion();
        Connection conexion = dbconexion.ObtenerConexionDB();
        
        try {
            // Establecer Query
            String q = "SELECT id_usuario, nombre_usuario FROM usuarios WHERE email_usuario = ?";
            
            // Hacer consulta
            PreparedStatement ps = conexion.prepareStatement(q);
            ps.setString(1, email_usuario);
            ResultSet rs = ps.executeQuery();
            
            // Obtener datos de consulta
            if (rs.next()) {
                sesion.setId_usuario(rs.getInt("id_usuario"));
                sesion.setNombre_usuario(rs.getString("nombre_usuario"));
            }
            
            System.out.println("Sesion de usuario obtenida exitosamente");
            
            // Cerrar conexion
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener sesion de usuario");
            System.out.println(e.getMessage());
        }
        
        return sesion;
    }
    
    public static boolean AutenticarUsuario(String email_usuario, String contrasena) {
        boolean autenticado = false;
        
        // Establecer conexion a base de datos
        DBConexion dbconexion = new DBConexion();
        Connection conexion = dbconexion.ObtenerConexionDB();
        
        try {
            // Establecer Query
            String q = "SELECT contrasena_usuario FROM usuarios WHERE email_usuario = ?";
            
            // Hacer consulta
            PreparedStatement ps = conexion.prepareStatement(q);
            ps.setString(1, email_usuario);
            ResultSet rs = ps.executeQuery();
            
            // Obtener datos de consulta
            if (rs.next()) {
                String contrasena_usuario = rs.getString("contrasena_usuario");
                autenticado = contrasena.equals(contrasena_usuario);
            }
            
            System.out.println("Usuario autenticado exitosamente");
            
            // Cerrar conexion
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al autenticar usuario");
            System.out.println(e.getMessage());
        }
        
        return autenticado;
    }
    
    public static List<Carrito> ObtenerCarrito(int id_usuario) {
        List<Carrito> carrito = new ArrayList<>();
        
        // Establecer conexion a base de datos
        DBConexion dbconexion = new DBConexion();
        Connection conexion = dbconexion.ObtenerConexionDB();
        
        try {
            // Establecer Query
            String q = "SELECT id_carrito, cantidad_carrito, id_producto, nombre_producto, precio_producto"
                    + " FROM carrito NATURAL JOIN productos WHERE id_usuario = ?";
            
            // Hacer consulta
            PreparedStatement ps = conexion.prepareStatement(q);
            ps.setInt(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            
            // Obtener datos de consulta
            while (rs.next()) {
                Carrito item = new Carrito();
                item.setId_carrito(rs.getInt("id_carrito"));
                item.setCantidad_carrito(rs.getInt("cantidad_carrito"));
                item.setId_producto(rs.getInt("id_producto"));
                item.setNombre_producto(rs.getString("nombre_producto"));
                item.setPrecio_producto(rs.getFloat("precio_producto"));
                carrito.add(item);
            }
            
            System.out.println("Carrito obtenido exitosamente");
            
            // Cerrar conexion
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener carrito");
            System.out.println(e.getMessage());
        }
        
        return carrito;
    }
    
    public static int ComprarItemCarritoUsuario(String domicilio_compra, int id_u, int id_producto, int cantidad_compra) {
        int estado = 0;
        
        // Establecer conexion a base de datos
        DBConexion dbconexion = new DBConexion();
        Connection conexion = dbconexion.ObtenerConexionDB();
        
        try {
            // Establecer Query
            String q = "INSERT INTO compras(domicilio_compra, cantidad_compra, id_u, id_producto) VALUE (?, ?, ?, ?)";
            
            // Eliminar
            PreparedStatement ps = conexion.prepareStatement(q);
            ps.setString(1, domicilio_compra);
            ps.setInt(2, cantidad_compra);
            ps.setInt(3, id_u);
            ps.setInt(4, id_producto);
            estado = ps.executeUpdate();
            
            System.out.println("Item comprado exitosamente");
            
            // Cerrar conexion
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al comprar item del carrito");
            System.out.println(e.getMessage());
        }
        
        return estado;
    }
    
    public static int AgregarAlCarrito(int id_usuario, int id_producto, int cantidad_carrito) {
        int estado = 0;
        
        // Establecer conexion a base de datos
        DBConexion dbconexion = new DBConexion();
        Connection conexion = dbconexion.ObtenerConexionDB();
        
        try {
            // Establecer Query
            String q = "INSERT INTO carrito(id_usuario, id_producto, cantidad_carrito)"
                    + " VALUE (?, ?, ?)";
            
            // Hacer registro
            PreparedStatement ps = conexion.prepareStatement(q);
            ps.setInt(1, id_usuario);
            ps.setInt(2, id_producto);
            ps.setInt(3, cantidad_carrito);
            estado = ps.executeUpdate();
            
            System.out.println("Item agregado al carrito exitosamente");
            
            // Cerrar conexion
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al agregar item al carrito");
            System.out.println(e.getMessage());
        }
        
        return estado;
    }
    
    public static int EliminarDelCarrito(int id_carrito) {
        int estado = 0;
        
        // Establecer conexion a base de datos
        DBConexion dbconexion = new DBConexion();
        Connection conexion = dbconexion.ObtenerConexionDB();
        
        try {
            // Establecer Query
            String q = "DELETE FROM carrito WHERE id_carrito = ?";
            
            // Eliminar
            PreparedStatement ps = conexion.prepareStatement(q);
            ps.setInt(1, id_carrito);
            estado = ps.executeUpdate();
            
            System.out.println("Item eliminado del carrito exitosamente");
            
            // Cerrar conexion
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al eliminar item del carrito");
            System.out.println(e.getMessage());
        }
        
        return estado;
    }
    
    public static List<Compra> ObtenerComprasDeUsuario(int id_u) {
        List<Compra> compras = new ArrayList<>();
        
        // Establecer conexion a base de datos
        DBConexion dbconexion = new DBConexion();
        Connection conexion = dbconexion.ObtenerConexionDB();
        
        try {
            // Establecer Query
            String q = "SELECT creada_compra, terminada_compra, domicilio_compra, cantidad_compra,"
                    + " nombre_producto, precio_producto FROM compras NATURAL JOIN productos"
                    + " WHERE id_u = ?";
            
            // Hacer consulta
            PreparedStatement ps = conexion.prepareStatement(q);
            ps.setInt(1, id_u);
            ResultSet rs = ps.executeQuery();
            
            // Obtener datos de consulta
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setCreada_compra(rs.getString("creada_compra"));
                compra.setTerminada_compra(rs.getBoolean("terminada_compra"));
                compra.setDomicilio_compra(rs.getString("domicilio_compra"));
                compra.setCantidad_compra(rs.getInt("cantidad_compra"));
                compra.setNombre_producto(rs.getString("nombre_producto"));
                compra.setPrecio_producto(rs.getFloat("precio_producto"));
                compras.add(compra);
            }
            
            System.out.println("Compras del usuario obtenidas exitosamente");
            
            // Cerrar conexion
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener compras del usuario");
            System.out.println(e.getMessage());
        }
        
        return compras;
    }
    
    public static List<Producto> ObtenerProductos() {
        List<Producto> productos = new ArrayList<>();
        
        // Establecer conexion a base de datos
        DBConexion dbconexion = new DBConexion();
        Connection conexion = dbconexion.ObtenerConexionDB();
        
        try {
            // Establecer Query
            String q = "SELECT * FROM productos";
            
            // Hacer consulta
            PreparedStatement ps = conexion.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            
            // Obtener datos de consulta
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre_producto(rs.getString("nombre_producto"));
                producto.setPrecio_producto(rs.getFloat("precio_producto"));
                producto.setStock_producto(rs.getInt("stock_producto"));
                productos.add(producto);
            }
            
            System.out.println("Productos obtenidos exitosamente");
            
            // Cerrar conexion
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener productos");
            System.out.println(e.getMessage());
        }
        
        return productos;
    }
    
    public static List<Compra> ObtenerTodasLasCompras() {
        List<Compra> compras = new ArrayList<>();
        
        // Establecer conexion a base de datos
        DBConexion dbconexion = new DBConexion();
        Connection conexion = dbconexion.ObtenerConexionDB();
        
        try {
            // Establecer Query
            String q = "SELECT id_compra, creada_compra, terminada_compra, domicilio_compra, cantidad_compra,"
                    + " nombre_producto, precio_producto FROM compras NATURAL JOIN productos";
            
            // Hacer consulta
            PreparedStatement ps = conexion.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            
            // Obtener datos de consulta
            while (rs.next()) {
                Compra compra =  new Compra();
                compra.setId_compra(rs.getInt("id_compra"));
                compra.setCreada_compra(rs.getString("creada_compra"));
                compra.setTerminada_compra(rs.getBoolean("terminada_compra"));
                compra.setDomicilio_compra(rs.getString("domicilio_compra"));
                compra.setCantidad_compra(rs.getInt("cantidad_compra"));
                compra.setNombre_producto(rs.getString("nombre_producto"));
                compra.setPrecio_producto(rs.getFloat("precio_producto"));
                compras.add(compra);
            }
            
            System.out.println("Compras obtenidas exitosamente");
            
            // Cerrar conexion
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al obtener compras");
            System.out.println(e.getMessage());
        }
        
        return compras;
    }
    
    public static int MarcarDeRecibido(int id_compra) {
        int estado = 0;
        
        // Establecer conexion a base de datos
        DBConexion dbconexion = new DBConexion();
        Connection conexion = dbconexion.ObtenerConexionDB();
        
        try {
            // Establecer Query
            String q = "UPDATE compras SET terminada_compra = TRUE WHERE id_compra = ?";
            
            // Modificar registro
            PreparedStatement ps = conexion.prepareStatement(q);
            ps.setInt(1, id_compra);
            estado = ps.executeUpdate();
            
            System.out.println("Marcado de recibido exitosamente");
            
            // Cerrar conexion
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al marcar de recibido");
            System.out.println(e.getMessage());
        }
        
        return estado;
    }
    
    public static int EditarProducto(float precio_producto, int stock_producto, int id_producto) {
        int estado = 0;
        
        // Establecer conexion a base de datos
        DBConexion dbconexion = new DBConexion();
        Connection conexion = dbconexion.ObtenerConexionDB();
        
        try {
            // Establecer Query
            String q = "UPDATE productos SET precio_producto = ?, stock_producto = ? WHERE id_producto = ?";
            
            // Modificar registro
            PreparedStatement ps = conexion.prepareStatement(q);
            ps.setFloat(1, precio_producto);
            ps.setInt(2, stock_producto);
            ps.setInt(3, id_producto);
            estado = ps.executeUpdate();
            
            System.out.println("Producto editado exitosamente");
            
            // Cerrar conexion
            conexion.close();
        } catch (SQLException e) {
            System.out.println("Error al editar producto");
            System.out.println(e.getMessage());
        }
        
        return estado;
    }
    
}

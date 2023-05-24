/*
 * sigue el patrón Singleton para la conexión a la base de datos
 */
package Implementacion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author cic
 */
public class AccesoBaseDatos {

    private Connection conn = null; //atributo conexión
    // constantes a definir
    private static final String BD = "banco";
    private static final String USUARIO = "root";
    private static final String CLAVE = "root";
    private static final String URL = "jdbc:mysql://10.0.16.36:3306/" + BD;

    private AccesoBaseDatos() {

        try {
            Properties properties = new Properties();
            properties.setProperty("user", USUARIO);
            properties.setProperty("password", CLAVE);
            properties.setProperty("useSSL", "false");
            properties.setProperty("autoReconnect", "true");
            conn = (Connection) DriverManager.getConnection(URL, properties);
            if (conn == null) {
                System.out.println("Error en conexion");
            } else {
                System.out.println("Conexion correcta a: " + URL);
            }
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    public static AccesoBaseDatos getInstance() {
        return AccesoBaseDatosHolder.INSTANCE;
    }

    private static class AccesoBaseDatosHolder {

        private static final AccesoBaseDatos INSTANCE = new AccesoBaseDatos();
    }


//obtener la conexion
    public Connection getConn() {
        return conn;
    }
// cerrar la conexión

    public boolean cerrar() {
        boolean siCerrada = false;
        try {
            conn.close();
            if (conn.isClosed()) {
                siCerrada = true;
            }
        } catch (SQLException sqe) {
            System.out.println("Se produjo un error en el cierre");
        }
        return siCerrada;
    }
}

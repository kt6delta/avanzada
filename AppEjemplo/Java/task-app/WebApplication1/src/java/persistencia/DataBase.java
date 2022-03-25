
package persistencia;

/**
 *
 * @author Oswaldo
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBase {

    private String strRutaDB = "E:\\data\\tareas.db"; // Ubicación del archivo de Base de Datos de SQLite
    
    public Connection getConexion() throws ClassNotFoundException, SQLException {                        
        Connection conn = null;                  
        String url = "jdbc:sqlite:" + strRutaDB;            
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection(url);                       
        return conn;

    }
    
}
package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPoolSQL {
    private static Connection connection;
    
    private ConnectionPoolSQL() {} // Evita la creación de instancias de la clase
    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:sqlserver://IRINITA:1433;databaseName=uwu;";
            String username = "nombreirina"; // Reemplaza "tu_usuario" con el nombre de usuario correcto
            String password = "123"; // Reemplaza "tu_contraseña" con la contraseña correcta
            connection = DriverManager.getConnection(url, username, password);
        }
        
        return connection;
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

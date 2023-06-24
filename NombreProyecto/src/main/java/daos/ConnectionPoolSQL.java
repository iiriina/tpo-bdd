package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPoolSQL {
    private static Connection connection;

    static {
        try {
            String url = "jdbc:sqlserver://IRINITA:1433;databaseName=uwu;";
            String username = "nombreirina"; // Reemplaza "tu_usuario" con el nombre de usuario correcto
            String password = "123"; // Reemplaza "tu_contraseña" con la contraseña correcta
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void closeConnection(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}

package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import daos.ConnectionPoolSQL;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import negocio.Alumno;

@Entity
public class AlumnoDAO_SQL {

	private static AlumnoDAO_SQL instancia;
	
	private AlumnoDAO_SQL () {}
	
	public static AlumnoDAO_SQL getInstancia() {
		if (instancia == null)
			instancia = new AlumnoDAO_SQL();
		return instancia;
	}    

	public void guardar(Alumno alumno) {
        Connection connection = null;
        try {
            connection = ConnectionPoolSQL.getConnection();
            // Realiza las operaciones con la base de datos utilizando la conexión

            // Ejemplo: ejecutar una consulta de inserción
            String sql = "INSERT INTO Alumno (legajo, nombre) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, alumno.getLegajo());
                statement.setString(2, alumno.getNombre());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            // Manejo de excepciones
        } finally {
            try {
                if (connection != null) {
                    ConnectionPoolSQL.closeConnection(connection);
                }
            } catch (SQLException e) {
                // Manejo de excepciones
            }
        }
    }
	
	public List<Alumno> obtenerAlumnosSQL() {
	    List<Alumno> alumnos = new ArrayList<>();
	    Connection connection = null;
	    try {
	        connection = ConnectionPoolSQL.getConnection();
	        // Realiza las operaciones con la base de datos utilizando la conexión

	        // Ejemplo: ejecutar una consulta de selección
	        String sql = "SELECT legajo, nombre FROM Alumno";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            ResultSet resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	                int legajo = resultSet.getInt("legajo");
	                String nombre = resultSet.getString("nombre");
	                Alumno alumno = new Alumno(legajo, nombre);
	                alumnos.add(alumno);
	            }
	        }
	    } catch (SQLException e) {
	        // Manejo de excepciones
	    } finally {
	        try {
	            if (connection != null) {
	                ConnectionPoolSQL.closeConnection(connection);
	            }
	        } catch (SQLException e) {
	            // Manejo de excepciones
	        }
	    }
	    return alumnos;
	}
	
}
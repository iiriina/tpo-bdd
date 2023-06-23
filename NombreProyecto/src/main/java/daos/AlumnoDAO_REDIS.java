package daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import redis.clients.jedis.Jedis;
import daos.ConnectionPoolREDIS;
import negocio.Alumno;

@Entity
public class AlumnoDAO_REDIS {

	private static AlumnoDAO_REDIS instancia;
	
	private AlumnoDAO_REDIS () {}
	
	public static AlumnoDAO_REDIS getInstancia() {
		if (instancia == null)
			instancia = new AlumnoDAO_REDIS();
		return instancia;
	}
	
	public void guardar(Alumno alumno) {
		Jedis jedis = null;
		try {
			jedis = ConnectionPoolREDIS.getConnection();
			// Realiza las operaciones con Redis utilizando el objeto jedis

			// Ejemplo: guardar el alumno en Redis
			jedis.hset("alumnos", Integer.toString(alumno.getLegajo()), alumno.getNombre());

		} finally {
			if (jedis != null) {
				ConnectionPoolREDIS.closeConnection(jedis);
		}
		}
	}
		
	public List<Alumno> obtenerAlumnos() {
		    List<Alumno> listaAlumnos = new ArrayList<>();

		    Jedis jedis = null;
		    try {
		        jedis = ConnectionPoolREDIS.getConnection();
		        // Obtener todos los campos y valores del hash "alumnos"
		        Map<String, String> alumnos = jedis.hgetAll("alumnos");

		        // Iterar sobre las claves y valores del hash
		        for (Map.Entry<String, String> entry : alumnos.entrySet()) {
		            String legajo = entry.getKey();
		            String nombre = entry.getValue();
		            
		            // Crear objeto Alumno y agregarlo a la lista
		            Alumno alumno = new Alumno(Integer.parseInt(legajo), nombre);
		            listaAlumnos.add(alumno);
		        }
		    } finally {
		        if (jedis != null) {
		            ConnectionPoolREDIS.closeConnection(jedis);
		        }
		    }

		    return listaAlumnos;
		}
}

    
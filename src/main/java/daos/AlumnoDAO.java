package daos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import negocio.Alumno;

@Entity
public class AlumnoDAO {

	private static AlumnoDAO instancia;
	
	private AlumnoDAO () {}
	
	public static AlumnoDAO getInstancia() {
		if (instancia == null)
			instancia = new AlumnoDAO();
		return instancia;
	}
	
	public void guardar(Alumno alumno) {
		EntityManager em = ConnectionPool.getInstance().getEntityManager();
		em.getTransaction().begin();
		em.persist(alumno);
		em.getTransaction().commit();
		em.close();
	}
	
	public Alumno obtenerPorId(int legajo) {
		EntityManager em = ConnectionPool.getInstance().getEntityManager();
		Query consulta = em.createQuery("SELECT a FROM Alumno a WHERE a.legajo = :legajo", Alumno.class);
		consulta.setParameter("legajo", legajo);
		Alumno resultado = (Alumno) consulta.getSingleResult();
		em.close();
		return resultado;
	}
	
	// aca la idea es devolver una lista de alumnos, y tengo que castear a lista de alumnos
	// Alumno.class esta bien porque lo que voy a devolver de la consulta es clase alumnos.
	public List<Alumno> obtenerAlumnos() {
		EntityManager em = ConnectionPool.getInstance().getEntityManager();
		Query consulta = em.createQuery("SELECT a FROM Alumno a", Alumno.class);
		List<Alumno> alumnos = (List<Alumno>) consulta.getResultList();
		em.close();
		return alumnos;
	}

	public Boolean esAlumnoPorNombre(String nombre) {
		
		/* preguntar si esta bien hacer esto o no */
		
		EntityManager em = ConnectionPool.getInstance().getEntityManager();
		Query consulta = em.createQuery("SELECT a FROM Alumno a", Alumno.class);
		List<Alumno> alumnos = (List<Alumno>) consulta.getResultList();
		
		for(Alumno a : alumnos) {
			if(a.soyElAlumnoNombre(nombre)) {
				return true;
			}
		}
		em.close();
		/* en el caso en el que haya recorrido y no haya un alumno con ese nombre
		 * devuelve false
		 */
		return false;
	}
}

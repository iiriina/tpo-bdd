package daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionPool {
	
	private static ConnectionPool instancia;
	private EntityManagerFactory emf;
	
	private ConnectionPool() {
		emf = Persistence.createEntityManagerFactory("objectdb:Inscripciones.odb");
		
	}
	
	public static ConnectionPool getInstance() {
		if (instancia == null) {
			instancia = new ConnectionPool();
		}
		return instancia;
	}
	
	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
}
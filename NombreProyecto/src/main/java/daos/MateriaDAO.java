package daos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import negocio.Materia;

@Entity
public class MateriaDAO {

	private static MateriaDAO instancia;
	
	private MateriaDAO () {}
	
	public static MateriaDAO getInstancia() {
		if (instancia == null)
			instancia = new MateriaDAO();
		return instancia;
	}
	
	public Materia buscarMateria(String codigo) {
		/* abro una conexion */
		EntityManager em = ConnectionPool.getInstance().getEntityManager();
		/* hago una query que me traiga toda la lista de Materia's */
		Query consulta = em.createQuery("SELECT m FROM Materia m", Materia.class);
		/* casteo el "Object" a List<Materia> para poder usar los metodos de Materia */
		List<Materia> listaMaterias = (List<Materia>)consulta.getResultList();
		/* cierro la conexion */
		em.close();
		
		/* de esa lista de materias, me fijo si alguna tiene el codigo y la devuelvo */
		for(Materia m : listaMaterias)
			if(m.soyLaMateria(codigo))
				return m;
		/* si no existe materia con ese codigo devuelvo null */
		return null;
		
	}
	
	public void agregarMateria(Materia materia) {
		
		/* aca hay que usar algo de persistir ese objeto Materia en cuestion */
		EntityManager em = ConnectionPool.getInstance().getEntityManager();
		em.getTransaction().begin();
		em.persist(materia);
		em.getTransaction().commit();
		em.close();
		
	}
}


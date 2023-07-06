package daos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import negocio.Alumno;
import negocio.Curso;
import negocio.Materia;

@Entity
public class CursoDAO {

	private static CursoDAO instancia;
	
	private CursoDAO () {}
	
	public static CursoDAO getInstancia() {
		if (instancia == null)
			instancia = new CursoDAO();
		return instancia;
	}
	
	public void crearCurso(Curso curso){
		
		
		/* aca hay que usar algo de persistir ese objeto Curso en cuestion */
		
		EntityManager em = ConnectionPool.getInstance().getEntityManager();
		em.getTransaction().begin();
		em.persist(curso);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public Curso buscarCurso(int numero) {
		/* abro una conexion */
		EntityManager em = ConnectionPool.getInstance().getEntityManager();
		/* hago una query que me traiga el curso con el numero que le pase */
		Query consulta = em.createQuery("SELECT c FROM Curso c WHERE c.numero = :numero", Curso.class);
		consulta.setParameter("numero", numero);
		/* casteo el "Object" a Curso para poder usar los metodos de Curso */
		Curso curso = (Curso)consulta.getSingleResult();
		/* cierro la conexion */
		em.close();
		
		return curso;
		
	}

	
}
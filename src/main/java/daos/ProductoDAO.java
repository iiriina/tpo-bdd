package daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.NoResultException;

import negocio.Alumno;
import negocio.Producto;

public class ProductoDAO {
	
	private static ProductoDAO instancia;
	
	private ProductoDAO () {}
	 
	public static ProductoDAO getInstancia() {
		if (instancia == null)
			instancia = new ProductoDAO();
		return instancia;
	}
	
	public void guardar(Producto producto) {
		EntityManager em = ConnectionPool.getInstance().getEntityManager();
		em.getTransaction().begin();
		em.persist(producto);
		em.getTransaction().commit();
		em.close();
	}
	
	public Producto obtenerPorId(String idProducto) {
		EntityManager em = ConnectionPool.getInstance().getEntityManager();
		Query consulta = em.createQuery("SELECT p FROM Producto p WHERE p.idProducto = :idProducto", Producto.class);
		consulta.setParameter("idProducto", idProducto);
		Producto resultado = (Producto) consulta.getSingleResult();
		return resultado;
		
	}
	
	public Producto obtenerPorNombre(String nombreProducto) {
	    EntityManager em = ConnectionPool.getInstance().getEntityManager();
	    TypedQuery<Producto> consulta = em.createQuery("SELECT p FROM Producto p WHERE p.nombreProducto = :nombreProducto", Producto.class);
	    consulta.setParameter("nombreProducto", nombreProducto);
	    try {
	        Producto resultado = (Producto) consulta.getSingleResult();
	        return resultado;
	    } catch (NoResultException e) {
	        return null;
	    } finally {
	        em.close();
    	}
}

	
	public List<Producto> obtenerProductos() {
		EntityManager em = ConnectionPool.getInstance().getEntityManager();
		Query consulta = em.createQuery("SELECT p FROM Producto p", Producto.class);
		List<Producto> productos = (List<Producto>) consulta.getResultList();
		em.close();
		return productos;
	}
	
	public void eliminar(Producto producto) {
	    EntityManager em = ConnectionPool.getInstance().getEntityManager();
	    em.getTransaction().begin();
	    em.remove(producto);
	    em.getTransaction().commit();
	    em.close();
	}
	
	// guarda el nuevo valor de algun cambio del producto, acá se hace un merge
	// en ese producto de la base de datos, entonces se actualiza
	// se cambian los valores de los campos anteriores por los nuevos valores.
	public void guardarCambioEnXCampo(Producto producto){
	    EntityManager em = ConnectionPool.getInstance().getEntityManager();
	    em.getTransaction().begin();
	    em.merge(producto);
	    em.getTransaction().commit();
	    em.close();
	}
}


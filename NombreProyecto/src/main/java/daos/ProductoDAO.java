package daos;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
}


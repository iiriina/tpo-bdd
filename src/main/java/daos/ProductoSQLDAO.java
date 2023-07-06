package daos;

import java.beans.Statement;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import negocio.Carrito;
import negocio.ProductoCarrito;

public class ProductoSQLDAO {
    private static ProductoSQLDAO instancia;

    private ProductoSQLDAO() {
    }

    public static ProductoSQLDAO getInstancia() {
        if (instancia == null)
            instancia = new ProductoSQLDAO();
        return instancia;
    }

    public void guardarProductosDelCarrito(Carrito carrito, int pedidoId, Connection connection) {
        List<ProductoCarrito> productos = carrito.getProductosCarrito();

        //Connection connection = null;
        try {
            //connection = ConnectionPoolSQL.getConnection();
            // Realiza las operaciones con la base de datos utilizando la conexión

            // Ejecutar una consulta de inserción para cada producto en la lista
        	String sql = "INSERT INTO Productos (idProducto, nombre, precio, cantidadSeleccionada, descuento, precioFinal, pedido_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        	try (PreparedStatement statement = connection.prepareStatement(sql)) {
                for (ProductoCarrito producto : productos) {
                    statement.setString(1, producto.getIdProducto());
                    statement.setString(2, producto.getNombre());
                    BigDecimal precio = new BigDecimal(Double.toString(producto.getPrecio()));
                    statement.setBigDecimal(3, precio);
                    statement.setInt(4, producto.getCantidadSeleccionada());
                    BigDecimal descuento = new BigDecimal(Double.toString(producto.getDescuento()));
                    statement.setBigDecimal(5, descuento);
                    BigDecimal precioFinal = new BigDecimal(Double.toString(producto.getPrecioFinal()));
                    statement.setBigDecimal(6, precioFinal);
                    statement.setInt(7, pedidoId); // Asigna el ID del pedido al campo pedido_id

                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            // Manejo de excepciones SQL
            e.printStackTrace();
        } //finally {
         //   try {
         //       if (connection != null) {
         //           ConnectionPoolSQL.closeConnection(connection);
         //       }
         //   } catch (SQLException e) {
                // Manejo de excepciones SQL al cerrar la conexión
         //       e.printStackTrace();
         //   }
        //}
    }
     
    //elimina los productos con el id que esta guardado como pedido de una persona usuarioo
    public void eliminarProductosDelPedido(int pedidoId, Connection connection) {
        try {
            // Eliminar los productos asociados al pedido en la tabla "Productos"
            String sql = "DELETE FROM Productos WHERE pedido_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, pedidoId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            // Manejo de excepciones SQL
            e.printStackTrace();
        }
    }

}

package daos;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import negocio.Carrito;
import negocio.Pedido;

public class PedidoDAO {
    private static PedidoDAO instancia;

    private PedidoDAO() {
    }

    public static PedidoDAO getInstancia() {
        if (instancia == null) {
            instancia = new PedidoDAO();
        }
        return instancia;
    }

    public void guardarPedido(Pedido pedido, Carrito carrito) {
        Connection connection = null;
        try {
            connection = ConnectionPoolSQL.getConnection();
            connection.setAutoCommit(false); // Desactivar el autocommit

            // Ejecutar una consulta de inserción del pedido
            String sql = "INSERT INTO Pedidos (id, importe, impuestos) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, pedido.getId());
                statement.setBigDecimal(2, pedido.getImporte());
                statement.setBigDecimal(3, pedido.getImpuestos());
                statement.executeUpdate();
            }
 
            connection.commit(); // Confirmar la transacción
            
            // Guardar los productos del carrito
            ProductoSQLDAO.getInstancia().guardarProductosDelCarrito(carrito, pedido.getId(), connection);
            System.out.println("PedidoDAO guardarPedido: Se guardaron los productos del carrito");

        } catch (SQLException e) {
            // Manejo de excepciones
            if (connection != null) {
                try {
                    connection.rollback(); // Deshacer la transacción en caso de error
                } catch (SQLException ex) {
                    // Manejo de excepciones al deshacer la transacción
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true); // Volver a activar el autocommit
                    ConnectionPoolSQL.closeConnection(connection);
                }
            } catch (SQLException e) {
                // Manejo de excepciones
                e.printStackTrace();
            }
        }
    }


    public void mostrarPedido(int pedidoId) {
        Connection connection = null;
        try {
            connection = ConnectionPoolSQL.getConnection();

            // Ejecutar una consulta para obtener los datos del pedido y sus productos
            String sql = "SELECT P.*, PR.* FROM Pedidos P " +
                         "JOIN Productos PR ON P.id = PR.pedido_id " +
                         "WHERE P.id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, pedidoId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Verificar si hay registros para el pedido especificado
                    if (resultSet.next()) {
                        // Obtener los datos del pedido
                        BigDecimal importe = resultSet.getBigDecimal("importe");
                        BigDecimal impuestos = resultSet.getBigDecimal("impuestos");
                        
                        // Imprimir los datos del pedido por pantalla
                        System.out.println("Pedido ID: " + pedidoId);
                        System.out.println("Importe: " + importe);
                        System.out.println("Impuestos: " + impuestos);
                        System.out.println("----------------------");
                        
                        // Imprimir los datos de los productos asociados al pedido
                        do {
                            String idProducto = resultSet.getString("idProducto");
                            String nombreProducto = resultSet.getString("nombre");
                            BigDecimal precio = resultSet.getBigDecimal("precio");
                            int cantidadSeleccionada = resultSet.getInt("cantidadSeleccionada");
                            BigDecimal descuento = resultSet.getBigDecimal("descuento");
                            BigDecimal precioFinal = resultSet.getBigDecimal("precioFinal");
                            
                            // Imprimir los datos del producto por pantalla
                            System.out.println("ID Producto: " + idProducto);
                            System.out.println("Nombre Producto: " + nombreProducto);
                            System.out.println("Precio: " + precio);
                            System.out.println("Cantidad Seleccionada: " + cantidadSeleccionada);
                            System.out.println("Descuento: " + descuento);
                            System.out.println("Precio Final: " + precioFinal);
                            System.out.println("----------------------");
                        } while (resultSet.next());
                    } else {
                        System.out.println("No se encontró un pedido con ID: " + pedidoId);
                    }
                }
            }
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    ConnectionPoolSQL.closeConnection(connection);
                }
            } catch (SQLException e) {
                // Manejo de excepciones al cerrar la conexión
                e.printStackTrace();
            }
        }
    }

    public void eliminarPedido(int pedidoId) {
        Connection connection = null;
        try {
            connection = ConnectionPoolSQL.getConnection();
            connection.setAutoCommit(false); // Desactivar el autocommit

            // Eliminar los productos asociados al pedido en la tabla "Productos"
            ProductoSQLDAO.getInstancia().eliminarProductosDelPedido(pedidoId, connection);

            // Eliminar el pedido en la tabla "Pedidos"
            String sql = "DELETE FROM Pedidos WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, pedidoId);
                statement.executeUpdate();
            }

            connection.commit(); // Confirmar la transacción
        } catch (SQLException e) {
            // Manejo de excepciones
            if (connection != null) {
                try {
                    connection.rollback(); // Deshacer la transacción en caso de error
                } catch (SQLException ex) {
                    // Manejo de excepciones al deshacer la transacción
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true); // Volver a activar el autocommit
                    ConnectionPoolSQL.closeConnection(connection);
                }
            } catch (SQLException e) {
                // Manejo de excepciones
                e.printStackTrace();
            }
        }
    }

    public Pedido getPedido(int pedidoId) {
        Connection connection = null;
        Pedido pedido = null;
        try {
            connection = ConnectionPoolSQL.getConnection();

            // Ejecutar una consulta para obtener los datos del pedido
            String sql = "SELECT * FROM Pedidos WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, pedidoId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Verificar si hay un registro para el pedido especificado
                    if (resultSet.next()) {
                        // Obtener los datos del pedido
                        BigDecimal importe = resultSet.getBigDecimal("importe");
                        BigDecimal impuestos = resultSet.getBigDecimal("impuestos");
                        
                        // Crear el objeto Pedido
                        pedido = new Pedido(pedidoId, importe, impuestos);
                    }
                }
            }
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    ConnectionPoolSQL.closeConnection(connection);
                }
            } catch (SQLException e) {
                // Manejo de excepciones al cerrar la conexión
                e.printStackTrace();
            }
        }
        
        return pedido;
    }

}

package daos;
 
import java.math.BigDecimal;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import negocio.Factura;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {
    private static FacturaDAO instancia;

    private FacturaDAO() {
    }

    public static FacturaDAO getInstancia() {
        if (instancia == null) {
            instancia = new FacturaDAO();
        }
        return instancia;
    }

    public void guardarFactura(Factura factura) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPoolSQL.getConnection();
            connection.setAutoCommit(false); // Desactivar el autocommit
            System.out.println("Que hay adentro de factura?: ");
            System.out.println("ID: " + factura.getId());
            System.out.println("Usuario ID: " + factura.getUsuarioId());
            System.out.println("Pedido ID: " + factura.getPedidoId());
            System.out.println("Pago ID: " + factura.getPagoId());
            System.out.println("Forma de Pago: " + factura.getFormaDePago());
            System.out.println("Fecha y Hora: " + factura.getFechaHora());
            System.out.println("Medio de Pago: " + factura.getMedioPago());
            System.out.println("Operador de Pago: " + factura.getOperadorPago());
            System.out.println("Monto de Pago: " + factura.getMontoPago());

            // Ejecutar una consulta de inserción de la factura
            String sql = "INSERT INTO Facturas (id, usuario_id, pedido_id, pago_id, formaDePago, fechaHora, medioPago, operadorPago, montoPago) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, factura.getId());
            statement.setInt(2, factura.getUsuarioId());
            statement.setInt(3, factura.getPedidoId());
            statement.setInt(4, factura.getPagoId());
            statement.setString(5, factura.getFormaDePago());
            statement.setTimestamp(6, factura.getFechaHora());
            statement.setString(7, factura.getMedioPago());
            statement.setString(8, factura.getOperadorPago());
            statement.setBigDecimal(9, factura.getMontoPago());

            statement.executeUpdate();

            connection.commit(); // Confirmar la transacción

        } catch (SQLException e) {
            // Manejo de excepciones
        	e.printStackTrace();

        } finally {
            // Cerrar los recursos y la conexión
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // Manejo de excepciones al cerrar el PreparedStatement
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
					ConnectionPoolSQL.closeConnection(connection);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
    }


    public void eliminarFactura(int facturaId) {
        Connection connection = null;
        try {
            connection = ConnectionPoolSQL.getConnection();
            connection.setAutoCommit(false); // Desactivar el autocommit

            // Eliminar la factura en la tabla "Facturas"
            String sql = "DELETE FROM Facturas WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, facturaId);
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
    
    public List<Factura> mostrarFacturasPorUsuario(int usuarioId) {
        List<Factura> facturas = new ArrayList<>();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolSQL.getConnection();

            // Consultar las facturas por usuario
            String sql = "SELECT * FROM Facturas WHERE usuario_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, usuarioId);
            resultSet = statement.executeQuery();

            // Recorrer los resultados y crear objetos Factura
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int pedidoId = resultSet.getInt("pedido_id");
                int pagoId = resultSet.getInt("pago_id");
                String formaDePago = resultSet.getString("formaDePago");
                java.sql.Timestamp fechaHora = resultSet.getTimestamp("fechaHora");
                String medioPago = resultSet.getString("medioPago");
                String operadorPago = resultSet.getString("operadorPago");
                BigDecimal montoPago = resultSet.getBigDecimal("montoPago");

                Factura factura = new Factura(id, usuarioId, pedidoId, pagoId, formaDePago, fechaHora,
                        medioPago, operadorPago, montoPago);
                facturas.add(factura);
            }
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
        } finally {
            // Cerrar los recursos y la conexión
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // Manejo de excepciones al cerrar el ResultSet
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    // Manejo de excepciones al cerrar el PreparedStatement
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    ConnectionPoolSQL.closeConnection(connection);
                } catch (SQLException e) {
                    // Manejo de excepciones al cerrar la conexión
                    e.printStackTrace();
                }
            }
        }

        return facturas;
    }

}

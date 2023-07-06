package daos;
import negocio.Usuario;
import java.util.ArrayList; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;

import java.math.BigDecimal;

import negocio.Factura;
import negocio.Pago;


public class UsuarioDAO {
    private static UsuarioDAO instancia;

    private UsuarioDAO() {
        // Constructor privado para evitar instanciación externa
    }

    public static UsuarioDAO getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioDAO();
        }
        return instancia;
    }


public void guardarUsuario(Usuario usuario) {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
        connection = ConnectionPoolSQL.getConnection();
        connection.setAutoCommit(false); // Desactivar el autocommit
        // Ejecutar una consulta de inserción de usuario
        String sql = "INSERT INTO Usuarios (id, nombre, direccion, dni, categoria, condicionIVA, contraseña) VALUES (?, ?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, usuario.getId());
        statement.setString(2, usuario.getNombre());
        statement.setString(3, usuario.getDireccion());
        statement.setInt(4, usuario.getDni());
        statement.setString(5, usuario.getCategoria());
        statement.setString(6, usuario.getCondicionIVA());
        statement.setString(7, usuario.getContraseña());

        int filasInsertadas = statement.executeUpdate();
        if (filasInsertadas > 0) {
            System.out.println("Usuario guardado exitosamente");
        } else {
            System.out.println("No se pudo guardar el usuario");
        }

        connection.commit(); // Confirmar la transacción

    } catch (SQLException e) {
        // Manejo de excepciones
        e.printStackTrace();
        try {
            connection.rollback(); // Revertir la transacción en caso de error
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } finally {
        // Cerrar los recursos y la conexión
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                ConnectionPoolSQL.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


    public Usuario obtenerUsuarioPorId(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Usuario usuario = null;
        try {
            connection = ConnectionPoolSQL.getConnection();
            String sql = "SELECT * FROM Usuarios WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                usuario = mapearUsuario(resultSet);
            }

        } catch (SQLException e) {
            // Manejo de excepciones
        } finally {
            // Cerrar los recursos y la conexión
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    ConnectionPoolSQL.closeConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return usuario;
    }
    
    public Usuario obtenerUsuarioPorNombre(String nombre){
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    Usuario usuario = null;
	    try {
	        connection = ConnectionPoolSQL.getConnection();
	        String sql = "SELECT * FROM Usuarios WHERE nombre = ?";
	        statement = connection.prepareStatement(sql);
	        statement.setString(1, nombre);
	        resultSet = statement.executeQuery();
	

	        if (resultSet.next()) {
	            usuario = mapearUsuario(resultSet);
	        }
	
	    } catch (SQLException e) {
	        // Manejo de excepciones
	    } finally {
	        // Cerrar los recursos y la conexión
	        if (resultSet != null) {
	            try {
	                resultSet.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (statement != null) {
	            try {
	                statement.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        if (connection != null) {
	            try {
	                ConnectionPoolSQL.closeConnection(connection);
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
        
	    return usuario;
    }

    private Usuario mapearUsuario(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("nombre");
        String direccion = resultSet.getString("direccion");
        int dni = resultSet.getInt("dni");
        String categoria = resultSet.getString("categoria");
        String condicionIVA = resultSet.getString("condicionIVA");
        String contraseña = resultSet.getString("contraseña");

        return new Usuario(id, nombre, direccion, dni, categoria, condicionIVA, contraseña);
    }
//     public Usuario mapearUsuario(ResultSet rs) throws SQLException {
//     Usuario usuario = null;
//     if (rs.next()) {
//         int id = rs.getInt("id");
//         String nombre = rs.getString("nombre");
//         String direccion = rs.getString("direccion");
//         int dni = rs.getInt("dni");
//         String categoria = rs.getString("categoria");
//         String condicionIVA = rs.getString("condicionIVA");
//         String contraseña = rs.getString("contraseña");

//         usuario = new Usuario(id, nombre, direccion, dni, categoria, condicionIVA, contraseña);

//         // SI NO FUNCIONAN ESTOS CAMBIOS, VOLVER A LO ANTERIOR        
//         // Obtener las facturas del usuario
//         ArrayList<Factura> facturas = obtenerListaFacturas(usuario.getId());
//         usuario.setListaFacturas(facturas);

//         // Obtener los pagos del usuario
//         ArrayList<Pago> pagos = obtenerListaPagos(usuario);
//         usuario.setListaPagos(pagos);

//         // Obtener los tiempos de conexión del usuario
//         ArrayList<Integer> tiemposConexion = obtenerTiemposConexion(usuario.getId());
//         usuario.setTiemposConexion(tiemposConexion);
//     }

//     return usuario;
// }

// PROXIMOS TRES METODOS A TESTEAR
    public ArrayList<Factura> obtenerListaFacturas(int usuarioId) throws SQLException {
        ArrayList<Factura> listaFacturas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPoolSQL.getConnection();
            String sql = "SELECT * FROM Facturas WHERE usuario_id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, usuarioId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Obtener los datos de la factura desde el ResultSet
                int facturaId = resultSet.getInt("id");
                int pedidoId = resultSet.getInt("pedido_id");
                int pagoId = resultSet.getInt("pagoId");
                String formaDePago = resultSet.getString("formaDePago");
                String medioPago = resultSet.getString("medioPago");
                String operadorPago = resultSet.getString("operadorPago");
                BigDecimal montoPago = resultSet.getBigDecimal("montoPago");
                Timestamp fechaHora = resultSet.getTimestamp("fechaHora");
                
                // Crear un objeto Factura y agregarlo a la lista
                Factura factura = new Factura(facturaId, usuarioId, pedidoId, pagoId, formaDePago, fechaHora, medioPago, operadorPago, montoPago);
                listaFacturas.add(factura);
            }
        } finally {
            // Cerrar los recursos (ResultSet, PreparedStatement y Connection)
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    ConnectionPoolSQL.closeConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return listaFacturas;
    }


public ArrayList<Pago> obtenerListaPagos(Usuario usuario) throws SQLException {
    int usuarioId = usuario.getId();
    ArrayList<Pago> listaPagos = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
        connection = ConnectionPoolSQL.getConnection();
        String sql = "SELECT * FROM Pagos WHERE usuario_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, usuarioId);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            // Obtener los datos del pago desde el ResultSet
            int pagoId = resultSet.getInt("id");
            String formaPago = resultSet.getString("formaPago");
            BigDecimal monto = resultSet.getBigDecimal("monto");

            // Crear un objeto Pago y agregarlo a la lista
            Pago pago = new Pago(pagoId, usuario, formaPago, monto);
            listaPagos.add(pago);
        }
    } finally {
        // Cerrar los recursos (ResultSet, PreparedStatement y Connection)
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                ConnectionPoolSQL.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return listaPagos;
}



private ArrayList<Integer> obtenerTiemposConexion(int usuarioId) throws SQLException {
    ArrayList<Integer> tiemposConexion = new ArrayList<>();
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    try {
        connection = ConnectionPoolSQL.getConnection();
        String sql = "SELECT tiempo FROM TiemposConexion WHERE usuario_id = ?";
        statement = connection.prepareStatement(sql);
        statement.setInt(1, usuarioId);
        resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int tiempo = resultSet.getInt("tiempo");
            tiemposConexion.add(tiempo);
        }
    } finally {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                ConnectionPoolSQL.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    return tiemposConexion;
}

    
    // ACA YA TODO OK
    public void actualizarHoraInicioSesion(Usuario usuario, LocalDateTime horaInicio){
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionPoolSQL.getConnection();
            String sql = "UPDATE Usuarios SET hora_inicio = ? WHERE nombre = ?";
            statement = connection.prepareStatement(sql);
            statement.setTimestamp(1, Timestamp.valueOf(horaInicio));
            statement.setString(2, usuario.getNombre());
            statement.executeUpdate();
            
            int tiempoConexion = calcularTiempoConexion(usuario.getHoraInicio(), horaInicio);
            usuario.getTiemposConexion().add(tiempoConexion);
        } catch (SQLException e) {
            // Manejo de excepciones
        } finally {
            // Cerrar los recursos y la conexión
            // Cerrar los recursos y la conexión
	        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                ConnectionPoolSQL.closeConnection(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        }
    }
    
        private int calcularTiempoConexion(LocalDateTime horaInicio, LocalDateTime horaInicio2) {
			Duration duration = Duration.between(horaInicio, horaInicio2);
			return (int) duration.toMinutes();
	}

		public void actualizarHoraCierreSesion(Usuario usuario, LocalDateTime horaCierre) {
            Connection connection = null;
            PreparedStatement statement = null;
            
            try {
                connection = ConnectionPoolSQL.getConnection();
                String sql = "UPDATE Usuarios SET hora_cierre = ? WHERE nombre = ?";
                statement = connection.prepareStatement(sql);
                statement.setTimestamp(1, Timestamp.valueOf(horaCierre));
                statement.setString(2, usuario.getNombre());
                statement.executeUpdate();
            } catch (SQLException e) {
                // Manejo de excepciones
            } finally {
                // Cerrar los recursos y la conexión
                if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    ConnectionPoolSQL.closeConnection(connection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

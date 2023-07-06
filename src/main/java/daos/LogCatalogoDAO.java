package daos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import negocio.Producto;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

public class LogCatalogoDAO {
    private static LogCatalogoDAO instancia;

    private LogCatalogoDAO() {
    }

    public static LogCatalogoDAO getInstancia() {
        if (instancia == null)
            instancia = new LogCatalogoDAO();
        return instancia;
    }
    
    public void guardarCambio(Producto producto, String valorAntiguo, String valorNuevo, String operador) {
        Jedis jedis = null;
        try {
            jedis = ConnectionPoolREDIS.getConnection();

            // Crear la clave única para el cambio
            String claveCambio = "cambio:" + producto.getIdProducto() + ":" + System.currentTimeMillis();

            // Guardar los campos del producto en el hash
            jedis.hset(claveCambio, "idProducto", producto.getIdProducto());
            jedis.hset(claveCambio, "nombreProducto", producto.getNombreProducto());
            jedis.hset(claveCambio, "descripcion", producto.getDescripcion());
            jedis.hset(claveCambio, "precio", Double.toString(producto.getPrecio()));
            jedis.hset(claveCambio, "unidadesDisponibles", Integer.toString(producto.getUnidadesDisponibles()));
            jedis.hset(claveCambio, "descuento", Double.toString(producto.getDescuento()));

            // Guardar las fotos en el hash
            List<String> fotos = producto.getFotos();
            for (int i = 0; i < fotos.size(); i++) {
                jedis.hset(claveCambio, "foto_" + i, fotos.get(i));
            }

            // Guardar los videos en el hash
            List<String> videos = producto.getVideos();
            for (int i = 0; i < videos.size(); i++) {
                jedis.hset(claveCambio, "video_" + i, videos.get(i));
            }

            // Guardar los comentarios en el hash
            List<String> comentarios = producto.getComentarios();
            for (int i = 0; i < comentarios.size(); i++) {
                jedis.hset(claveCambio, "comentario_" + i, comentarios.get(i));
            }

            // Guardar los valores nuevo, antiguo y operador en el hash
            jedis.hset(claveCambio, "valorAntiguo", valorAntiguo);
            jedis.hset(claveCambio, "valorNuevo", valorNuevo);
            jedis.hset(claveCambio, "operador", operador);

        } finally {
            if (jedis != null) {
                ConnectionPoolREDIS.closeConnection(jedis);
            }
        }
    }
    
    public LinkedHashMap<String, Map<String, String>> obtenerCambiosOrdenados() {
        Jedis jedis = null;
        try {
            jedis = ConnectionPoolREDIS.getConnection();

            // Obtener todas las claves de los cambios
            Set<String> keys = jedis.keys("cambio:*");

            // Crear una lista de cambios para ordenar
            List<String> listaClaves = new ArrayList<>(keys);

            // Ordenar la lista de claves utilizando un Comparator personalizado
            Collections.sort(listaClaves, new Comparator<String>() {
                public int compare(String clave1, String clave2) {
                    // Obtener los últimos números después de los dos puntos en las claves de los cambios
                    String[] partesCambio1 = clave1.split(":");
                    String[] partesCambio2 = clave2.split(":");
                    long numeroCambio1 = Long.parseLong(partesCambio1[partesCambio1.length - 1]);
                    long numeroCambio2 = Long.parseLong(partesCambio2[partesCambio2.length - 1]);

                    // Comparar los números de cambio para determinar el orden
                    return Long.compare(numeroCambio1, numeroCambio2);
                }
            });

            // Crear el mapa ordenado para almacenar los cambios
            LinkedHashMap<String, Map<String, String>> cambiosOrdenados = new LinkedHashMap<>();

            // Iterar sobre las claves ordenadas y obtener los cambios correspondientes
            for (String clave : listaClaves) {
                Map<String, String> cambio = jedis.hgetAll(clave);
                cambiosOrdenados.put(clave, cambio);
            }

            return cambiosOrdenados;

        } finally {
            if (jedis != null) {
                ConnectionPoolREDIS.closeConnection(jedis);
            }
        }
    }


    public void borrarContenidoLog() {

        Jedis jedis = null;
        try {
            jedis = ConnectionPoolREDIS.getConnection();

            // Obtener todas las claves de los cambios
            Set<String> keys = jedis.keys("cambio:*");

            // Iterar sobre las claves y eliminarlas una por una
            for (String key : keys) {
                jedis.del(key);
            }
        } finally {
            if (jedis != null) {
                ConnectionPoolREDIS.closeConnection(jedis);
            }
        }
    }
}

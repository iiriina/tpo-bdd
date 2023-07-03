package daos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import negocio.Carrito;
import negocio.Producto;
import negocio.ProductoCarrito;
import redis.clients.jedis.Jedis;

public class CarritoDAO {
    private static CarritoDAO instancia;

    private CarritoDAO() {
    }

    public static CarritoDAO getInstancia() {
        if (instancia == null)
            instancia = new CarritoDAO();
        return instancia;
    }
    
    public List<ProductoCarrito> obtenerProductosCarrito(String nombreUsuario) {
        Jedis jedis = null;
        try {
            jedis = ConnectionPoolREDIS.getConnection(); // Obtener una conexión de la pool de conexiones

            // Obtener los productos del carrito del usuario
            String claveProductos = "productosCarrito:" + nombreUsuario;
            Set<String> idsProductos = jedis.zrange(claveProductos, 0, -1);

            // Crear una lista para almacenar los productos del carrito
            List<ProductoCarrito> productosCarrito = new ArrayList<>();

            // Iterar sobre los ids de productos y obtener la información de cada producto
            for (String idProducto : idsProductos) {
                String claveProducto = "producto:" + idProducto;
                String nombre = jedis.hget(claveProducto, "nombre");
                double precio = Double.parseDouble(jedis.hget(claveProducto, "precio"));
                int cantidadSeleccionada = Integer.parseInt(jedis.hget("cantidad:" + nombreUsuario + ":" + idProducto, "cantidadSeleccionada"));
                double descuento = Double.parseDouble(jedis.hget(claveProducto, "descuento"));

                ProductoCarrito productoCarrito = new ProductoCarrito(idProducto, nombre, precio, cantidadSeleccionada, descuento);
                productosCarrito.add(productoCarrito);
            }

            return productosCarrito;
        } finally {
            if (jedis != null) {
                ConnectionPoolREDIS.closeConnection(jedis); // Cerrar la conexión cuando hayas terminado
            }
        }
    }

    public void agregarProductoAlCarrito(String nombreUsuario, Producto producto, int cantidadSeleccionada) {
        Jedis jedis = null;
        try {
            jedis = ConnectionPoolREDIS.getConnection(); // Obtener una conexión de la pool de conexiones

            // Crear una instancia de ProductoCarrito con los datos del producto
            ProductoCarrito productoCarrito = new ProductoCarrito(
                producto.getIdProducto(),
                producto.getNombreProducto(),
                producto.getPrecio(),
                cantidadSeleccionada,
                producto.getDescuento()
            );

            // Agregar el productoCarrito al carrito del usuario
            String claveProductos = "productosCarrito:" + nombreUsuario;
            double puntaje = System.currentTimeMillis();
            jedis.zadd(claveProductos, puntaje, producto.getIdProducto());

            // Guardar los campos adicionales del producto en Hash
            String claveProducto = "producto:" + producto.getIdProducto();
            jedis.hset(claveProducto, "idProducto", producto.getIdProducto());
            jedis.hset(claveProducto, "nombre", producto.getNombreProducto());
            jedis.hset(claveProducto, "precio", Double.toString(producto.getPrecio()));
            jedis.hset(claveProducto, "descuento", Double.toString(producto.getDescuento()));

            // Guardar la cantidad seleccionada del producto en el carrito del usuario
            String claveCantidad = "cantidad:" + nombreUsuario + ":" + producto.getIdProducto();
            jedis.hset(claveCantidad, "cantidadSeleccionada", Integer.toString(cantidadSeleccionada));
        } finally {
            if (jedis != null) {
                ConnectionPoolREDIS.closeConnection(jedis); // Cerrar la conexión cuando hayas terminado
            }
        }
    }

    public Carrito obtenerCarritoPorUsuario(String nombreUsuario) {
        Jedis jedis = null;
        try {
            jedis = ConnectionPoolREDIS.getConnection(); // Obtener una conexión de la pool de conexiones

            // Obtener los productos del carrito del usuario
            String claveProductos = "productosCarrito:" + nombreUsuario;
            Set<String> idsProductos = jedis.zrange(claveProductos, 0, -1);

            // Crear una lista para almacenar los productos del carrito
            List<ProductoCarrito> productosCarrito = new ArrayList<>();

            // Iterar sobre los ids de productos y obtener la información de cada producto
            for (String idProducto : idsProductos) {
                String claveProducto = "producto:" + idProducto;
                String nombre = jedis.hget(claveProducto, "nombre");
                double precio = Double.parseDouble(jedis.hget(claveProducto, "precio"));
                int cantidadSeleccionada = Integer.parseInt(jedis.hget("cantidad:" + nombreUsuario + ":" + idProducto, "cantidadSeleccionada"));
                double descuento = Double.parseDouble(jedis.hget(claveProducto, "descuento"));

                ProductoCarrito productoCarrito = new ProductoCarrito(idProducto, nombre, precio, cantidadSeleccionada, descuento);
                productosCarrito.add(productoCarrito);
            }

            // Crear y retornar el objeto Carrito
            Carrito carrito = new Carrito(nombreUsuario, productosCarrito);
            return carrito;
        } finally {
            if (jedis != null) {
                ConnectionPoolREDIS.closeConnection(jedis); // Cerrar la conexión cuando hayas terminado
            }
        }
    }


    public List<Carrito> obtenerTodosLosCarritos() {
        Jedis jedis = null;
        try {
            jedis = ConnectionPoolREDIS.getConnection(); // Obtener una conexión de la pool de conexiones

            // Obtener todos los usuarios con carrito
            Set<String> usuariosConCarrito = jedis.keys("productosCarrito:*");

            // Crear una lista para almacenar los carritos
            List<Carrito> carritos = new ArrayList<>();

            // Iterar sobre los usuarios y obtener los carritos
            for (String usuarioKey : usuariosConCarrito) {
                String nombreUsuario = usuarioKey.split(":")[1]; // Obtener el nombre de usuario desde la clave
                Carrito carrito = obtenerCarritoPorUsuario(nombreUsuario);
                carritos.add(carrito);
            }

            return carritos;
        } finally {
            if (jedis != null) {
                ConnectionPoolREDIS.closeConnection(jedis); // Cerrar la conexión cuando hayas terminado
            }
        }
    }
    
    public void eliminarProductoDelCarrito(String nombreUsuario, String idProducto) {
        Jedis jedis = null;
        try {
            jedis = ConnectionPoolREDIS.getConnection(); // Obtener una conexión de la pool de conexiones

            // Eliminar el producto del carrito del usuario
            String claveProductos = "productosCarrito:" + nombreUsuario;
            jedis.zrem(claveProductos, idProducto);

            // Eliminar la cantidad seleccionada del producto en el carrito del usuario
            String claveCantidad = "cantidad:" + nombreUsuario + ":" + idProducto;
            jedis.del(claveCantidad);
        } finally {
            if (jedis != null) {
                ConnectionPoolREDIS.closeConnection(jedis); // Cerrar la conexión cuando hayas terminado
            }
        }
    }


    public void modificarCantidadProductoEnCarrito(String nombreUsuario, String idProducto, int nuevaCantidad) {
        Jedis jedis = null;
        try {
            jedis = ConnectionPoolREDIS.getConnection(); // Obtener una conexión de la pool de conexiones

            // Actualizar la cantidad seleccionada del producto en el carrito del usuario
            String claveCantidad = "cantidad:" + nombreUsuario + ":" + idProducto;
            jedis.hset(claveCantidad, "cantidadSeleccionada", Integer.toString(nuevaCantidad));
        } finally {
            if (jedis != null) {
                ConnectionPoolREDIS.closeConnection(jedis); // Cerrar la conexión cuando hayas terminado
            }
        }
    }

    public void actualizarCarritoEnBaseDeDatos(String nombreUsuario, Carrito carrito) {
        Jedis jedis = null;
        try {
            jedis = ConnectionPoolREDIS.getConnection(); // Obtener una conexión de la pool de conexiones

            // Eliminar los productos actuales del carrito del usuario
            String claveProductos = "productosCarrito:" + nombreUsuario;
            jedis.del(claveProductos);

            // Guardar los nuevos productos en el carrito del usuario
            for (ProductoCarrito producto : carrito.getProductosCarrito()) {
                // Agregar el producto al carrito del usuario
                jedis.zadd(claveProductos, System.currentTimeMillis(), producto.getIdProducto());

                // Guardar los campos adicionales del producto en Hash
                String claveProducto = "producto:" + producto.getIdProducto();
                jedis.hset(claveProducto, "idProducto", producto.getIdProducto());
                jedis.hset(claveProducto, "nombre", producto.getNombre());
                jedis.hset(claveProducto, "precio", Double.toString(producto.getPrecio()));
                jedis.hset(claveProducto, "descuento", Double.toString(producto.getDescuento()));

                // Guardar la cantidad seleccionada del producto en el carrito del usuario
                String claveCantidad = "cantidad:" + nombreUsuario + ":" + producto.getIdProducto();
                jedis.hset(claveCantidad, "cantidadSeleccionada", Integer.toString(producto.getCantidadSeleccionada()));
            }
        } finally {
            if (jedis != null) {
                ConnectionPoolREDIS.closeConnection(jedis); // Cerrar la conexión cuando hayas terminado
            }
        }
    }

    
}

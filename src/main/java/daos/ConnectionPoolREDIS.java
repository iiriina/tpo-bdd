package daos;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class ConnectionPoolREDIS {
    private static final JedisPool pool;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10); // Número máximo de conexiones en el pool
        config.setMaxIdle(5); // Número máximo de conexiones inactivas en el pool
        config.setMinIdle(1); // Número mínimo de conexiones inactivas en el pool

        pool = new JedisPool(config, "localhost");
    }

    public static Jedis getConnection() {
        return pool.getResource();
    }

    public static void closeConnection(Jedis jedis) {
        jedis.close();
    }
}
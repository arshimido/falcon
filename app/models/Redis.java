package models;


import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Redis {
	
	// redis list key, where we persist data
	private static final String LIST_KEY = "playapilist";
	
	//redis pub/sub channel key
	public static final String PUB_SUB_CHANNEL = "playapichannel";
	private static JedisPool pool = new JedisPool(RedisConfiguration.getInstance().getHost());
	
	public Redis() {
		
	}
	
	/**
	 * Get Jedis instance from pool, authenticate, return the authenticated instance.
	 * @return Jedis
	 */
	private Jedis connect() {
		Jedis jedis = pool.getResource();
		jedis.auth(RedisConfiguration.getInstance().getPassword());
		return jedis; 
	}

	
	/**
	 * Saves the dummy input to Redis list.
	 * 
	 * @param message : message to be saved
	 */
	public void add(String message) {
		Jedis jedis = connect();
		jedis.lpush(LIST_KEY, message);
		returnResource(jedis);
	}
	
	/**
	 * retrieve all persisted messages in Redis list.
	 * 
	 * @return list of Strings representes list of mesages in DB.
	 */
	public List<String> getAll() {
		Jedis jedis = connect();
		List<String> list = jedis.lrange(LIST_KEY, 0, -1);
		returnResource(jedis);
		return list;
	}
	
	/**
	 * Publishes message to Redis pub/sub channel
	 *  
	 * @param message : String
	 */
	public void publish(String message) {
		Jedis jedis = connect();
		jedis.publish(PUB_SUB_CHANNEL, message);
		returnResource(jedis);
	}
	
	/**
	 * Returns an authenticated Jedis instance
	 * 
	 * @return Jedis
	 */
	public static synchronized Jedis getJedisInstance() { 
		Jedis jedis = pool.getResource();
		jedis.auth(RedisConfiguration.getInstance().getPassword());
		return jedis; 
	}
	
	/**
	 * Returns a Jedis instance to pool
	 * 
	 * @param jedis : Jedis
	 */
	public void returnResource(Jedis jedis) {
		pool.returnResource(jedis);
	}

}

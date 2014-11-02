package models;


import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Redis {
	
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
		jedis.lpush(RedisConfiguration.getInstance().getList_key(), message);
		returnResource(jedis);
	}
	
	/**
	 * retrieve all persisted messages in Redis list.
	 * 
	 * @return list of Strings representes list of mesages in DB.
	 */
	public List<String> getAll() {
		Jedis jedis = connect();
		List<String> list = jedis.lrange(RedisConfiguration.getInstance().getList_key(), 0, -1);
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
		jedis.publish(RedisConfiguration.getInstance().getPubSubChannel_key(), message);
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

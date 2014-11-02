package models;


import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class Redis {
	
	private static final String LIST_KEY = "playapilist";
	public static final String PUB_SUB_CHANNEL = "playapichannel";
	private static JedisPool pool = new JedisPool(RedisConfiguration.getInstance().getHost());
	
	public Redis() {
		
	}
	
	private Jedis connect() {
		Jedis jedis = pool.getResource();
		jedis.auth(RedisConfiguration.getInstance().getPassword());
		return jedis; 
	}

	
	public void add(String message) {
		Jedis jedis = connect();
		jedis.set("foo", "kamal");
		jedis.lpush(LIST_KEY, message);
		returnResource(jedis);
	}
	
	public String get(String key) {
		Jedis jedis = connect();
		String result = jedis.get(key);
		returnResource(jedis);
		return result;
	}
	
	public List<String> getAll() {
		Jedis jedis = connect();
		List<String> list = jedis.lrange(LIST_KEY, 0, -1);
		returnResource(jedis);
		return list;
	}
	
	public void publish(String message) {
		Jedis jedis = connect();
		jedis.publish(PUB_SUB_CHANNEL, message);
		returnResource(jedis);
	}
	
	public static synchronized Jedis getJedisInstance() {
		return pool.getResource();
	}
	
	public void returnResource(Jedis jedis) {
		pool.returnResource(jedis);
	}

}

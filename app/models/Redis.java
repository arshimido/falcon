package models;


import java.util.List;

import redis.clients.jedis.Jedis;

public class Redis {
	
	private static final String LIST_KEY = "playapilist";
	private static final String PUB_SUB_CHANNEL = "playapichannel";
	
	public Redis() {
		
	}
	
	private Jedis connect() {
		Jedis jedis = new Jedis(RedisConfiguration.getInstance().getHost(), RedisConfiguration.getInstance().getPort());
		jedis.auth(RedisConfiguration.getInstance().getPassword());
		return jedis; 
	}
	
	public void initializeDatabase() {
		
	}
	
	public void add(String message) {
		Jedis jedis = connect();
		jedis.set("foo", "kamal");
		jedis.lpush(LIST_KEY, message);
		jedis.close();
	}
	
	public String get(String key) {
		Jedis jedis = connect();
		String result = jedis.get(key);
		jedis.close();
		return result;
	}
	
	public List<String> getAll() {
		Jedis jedis = connect();
		List<String> list = jedis.lrange(LIST_KEY, 0, -1);
		jedis.close();
		return list;
	}
	
	public void publish(String message) {
		Jedis jedis = connect();
		jedis.publish(PUB_SUB_CHANNEL, message);
		jedis.close();
	}

}

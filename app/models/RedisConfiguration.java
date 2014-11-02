package models;

import play.Play;

public class RedisConfiguration {

	// redis password for authentication
	private static final String REDIS_PASSWORD = "redis.password";
	
	// Redis host
	private static final String REDIS_HOST = "redis.host";
	
	// Redis port
	private static final String REDIS_PORT = "redis.port";

	// Redis list
	private static final String REDIS_List = "redis.list";
	
	// Redis pub/sub channel
	private static final String REDIS_PUB_SUB = "redis.pubsub";
	
	// singleton config object
	private static RedisConfiguration config = null;
	
	private String password = null;
	private String host = null;
	private Integer port = null;
	private String pubSubChannel_key = null;
	private String list_key = null;

	// private constructor for singleton object
	private RedisConfiguration () {
		password = Play.application().configuration().getString(REDIS_PASSWORD);
		host = Play.application().configuration().getString(REDIS_HOST);
		port = Play.application().configuration().getInt(REDIS_PORT);
		pubSubChannel_key = Play.application().configuration().getString(REDIS_PUB_SUB);
		list_key = Play.application().configuration().getString(REDIS_List);
	}
	
	public static RedisConfiguration getInstance() {
		if (config == null) {
			config = new RedisConfiguration();
		}
		return config;
	}

	public String getPassword() {
		return password;
	}

	public String getHost() {
		return host;
	}

	public Integer getPort() {
		return port;
	}

	public String getPubSubChannel_key() {
		return pubSubChannel_key;
	}

	public String getList_key() {
		return list_key;
	}

}

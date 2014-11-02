package models;

import play.Play;

public class RedisConfiguration {

	// redis password for authentication
	private static final String REDIS_PASSWORD = "redis.password";
	
	// Redis host
	private static final String REDIS_HOST = "redis.host";
	
	// Redis port
	private static final String REDIS_PORT = "redis.port";
	
	// singleton config object
	private static RedisConfiguration config = null;
	
	private String password = null;
	private String host = null;
	private Integer port = null;

	// private constructor for singleton object
	private RedisConfiguration () {
		password = Play.application().configuration().getString(REDIS_PASSWORD);
		host = Play.application().configuration().getString(REDIS_HOST);
		port = Play.application().configuration().getInt(REDIS_PORT);
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
}

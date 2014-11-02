package models;

import play.Play;

public class RedisConfiguration {

	private static final String JNDI_REDIS_PASSWORD = "redis.password";
	private static final String JNDI_REDIS_HOST = "redis.host";
	private static final String JNDI_REDIS_PORT = "redis.port";
	private static RedisConfiguration config = null;
	
	private String password = null;
	private String host = null;
	private Integer port = null;

	private RedisConfiguration () {
		password = Play.application().configuration().getString(JNDI_REDIS_PASSWORD);
		host = Play.application().configuration().getString(JNDI_REDIS_HOST);
		port = Play.application().configuration().getInt(JNDI_REDIS_PORT);
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

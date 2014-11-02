package models;

import redis.clients.jedis.JedisPubSub;

public class Consumer extends JedisPubSub {

	/**
	 * Default constructor
	 */
	public Consumer() {
		
	}
	
	
	@Override
	/**
	 * Listen to Redis pub/sub channel and waits for message.
	 * Save message to Redis list.
	 * Notify {@link RealTimebroadcaster} with message
	 * 
	 * @param channel : channel message published on
	 * @param message : message published on channel
	 */
	public void onMessage(String channel, String message) {
		//Persist message
		Redis redis = new Redis();
		redis.add(message);

		// send message sockets handler
		RealTimebroadcaster.broadcast(message);
	}

	@Override
	public void onPMessage(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPSubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onPUnsubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onUnsubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub

	}

}

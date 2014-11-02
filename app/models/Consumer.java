package models;

import play.Logger;
import redis.clients.jedis.JedisPubSub;

public class Consumer extends JedisPubSub {

	public Consumer() {
		Logger.info("------------------------ CONSUMER STARTED TO LISTEN --------------------");
	}
	@Override
	public void onMessage(String arg0, String arg1) {
		//save to redis
		Logger.info(" >>>>>>>>>>> MESSAGE RECEIVED");
		Logger.info(arg0 + ":" + arg1);
		Redis redis = new Redis();
		redis.add(arg1);

		// send message sockets handler
		RealTimebroadcaster.broadcast(arg1);
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

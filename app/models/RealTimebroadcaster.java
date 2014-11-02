package models;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import play.Logger;
import play.libs.Akka;
import play.libs.Json;
import play.mvc.WebSocket;
import redis.clients.jedis.Jedis;
import scala.concurrent.Await;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class RealTimebroadcaster extends UntypedActor{
	
	static ActorRef context = Akka.system().actorOf(Props.create(RealTimebroadcaster.class));
	
	// browsers listening
    Set<WebSocket.Out<JsonNode>> members = new HashSet<WebSocket.Out<JsonNode>>();
    
	static {	
		Logger.info("0000000000   Broadcast starts ");
    	//subscribe to the message channel
    	Akka.system().scheduler().scheduleOnce(
    	        Duration.create(10, TimeUnit.MILLISECONDS),
    	        new Runnable() {
    	            public void run() {
    	            	Jedis j = Redis.getJedisInstance();
    	            	j.subscribe(new Consumer(), Redis.PUB_SUB_CHANNEL);
    	            }
    	        },
    	        Akka.system().dispatcher()
    	);
    }
	
	public static void broadcast(String message) {
		context.tell(message, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onReceive(Object msg) throws Exception {
		if(msg instanceof WebSocket.Out<?>) {
			members.add((WebSocket.Out<JsonNode>)msg);
		} else if (msg instanceof String) {
			notifyAll((String)msg);
		} else {
			unhandled(msg);
		}
	}
	
	public void notifyAll(String message) {
        for(WebSocket.Out<JsonNode> channel: members) {
            
            ObjectNode msgNode = Json.newObject();
            msgNode.put("message", message);

            channel.write(msgNode);
        }
    }
	
	public static void listen(WebSocket.In<JsonNode> in, WebSocket.Out<JsonNode> out) throws Exception{
		Await.result(akka.pattern.Patterns.ask(context, out, 5000), Duration.create(5, TimeUnit.SECONDS));
	}

}

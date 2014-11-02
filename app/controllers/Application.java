package controllers;

import models.RealTimebroadcaster;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.WebSocket;
import views.html.index;
import views.html.realtime;

import com.fasterxml.jackson.databind.JsonNode;

public class Application extends Controller {

	public static Result index() {
		return ok(index.render("OK"));
	}

	public static WebSocket<JsonNode> realTime() {
		return new WebSocket<JsonNode>() {

			// Called when the Websocket Handshake is done.
			public void onReady(WebSocket.In<JsonNode> in,
					WebSocket.Out<JsonNode> out) {

				// Join the chat room.
				try {
					RealTimebroadcaster.listen(in, out);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
	}
	
	public static Result listenerJS() {
		return ok(views.js.listener.render(""));
	}
	
	public static Result listen() {
        return ok(realtime.render(""));
    }

}

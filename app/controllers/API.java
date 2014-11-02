package controllers;

import models.JsonHelper;
import models.Redis;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.add;

public class API extends Controller {

    public static Result add() {
    	String [] data = request().body().asFormUrlEncoded().get("data");
    	if(data != null && data.length > 0) {
    		Redis redis = new Redis();
    		redis.publish(JsonHelper.toJsonString(data[0]));
    	}
    	return ok(add.render("OK"));
    }
    
    public static Result getAllMessages() {
    	Redis redis = new Redis();
    	response().setContentType("application/json");
    	return ok(JsonHelper.construct(redis.getAll()));
    }

}

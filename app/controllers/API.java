package controllers;

import models.Redis;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.add;

public class API extends Controller {

    public static Result add() {
    	String [] data = request().body().asFormUrlEncoded().get("data");
    	if(data != null && data.length > 0) {
    		Redis redis = new Redis();
    		redis.add(data[0]);
    	}
    	return ok(add.render("OK"));
    }

}

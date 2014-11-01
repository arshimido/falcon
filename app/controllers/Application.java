package controllers;

import models.Redis;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
    	Redis redis = new Redis();
    	
        return ok(index.render("OK"));
    }

}

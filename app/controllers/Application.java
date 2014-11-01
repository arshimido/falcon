package controllers;

import play.*;
import play.mvc.*;
import redis.clients.jedis.Jedis;

import views.html.*;

public class Application extends Controller {

    public static Result index() {

        return ok(index.render("Your new application is ready."));
    }

}

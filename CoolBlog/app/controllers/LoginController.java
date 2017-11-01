package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.login.login;
import views.html.login.register;

/**
 * Created by Enver on 11/1/2017.
 */
public class LoginController extends Controller {


    public Result login(){
        return ok(login.render());
    }

    public Result register() {
        return ok(register.render());
    }
}

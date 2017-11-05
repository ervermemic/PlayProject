package controllers;

import com.google.inject.Inject;
import helpers.MD5Hash;
import models.User;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.login.login;

/**
 * Created by Enver on 11/1/2017.
 */
public class LoginController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result login(){
        return ok(login.render(formFactory.form(User.class)));
    }

    public Result loginUser(){
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();
        dynamicForm.bindFromRequest(request());

        String email = dynamicForm.get("email");
        String pass = dynamicForm.get("password");
        User user = User.findByEmail(email);
        if(user != null){
            if(user.getPassword().equals(MD5Hash.getEncriptedPasswordMD5(pass))){
                createSession(user);
                return ok(index.render(("Welcome " + user.getUsername())));
            }
        }
        return redirect("singout");
    }

    public static void createSession(User user) {
        session().clear();
        session().put("email", user.getEmail());
    }

    public static void clear(){
        session().clear();
        session().remove("email");
        response().discardCookie("email");
        response().cookies().clear();
    }

    public Result singUp() {
        session().clear();
        session().remove("email");
        response().discardCookie("email");
        response().cookies().clear();
        return redirect("/");
    }


}

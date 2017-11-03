package controllers;

import com.google.inject.Inject;
import models.User;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.login.register;



/**
 * Created by Enver on 11/2/2017.
 */
public class RegisterController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result register() {
        return ok(register.render(formFactory.form(User.class)));
    }

    public Result registerUser(){
       // Form<User> submitted = formFactory.form(User.class).bindFromRequest();

        DynamicForm dynamicForm = formFactory.form().bindFromRequest();
        dynamicForm.bindFromRequest(request());

        if(User.createUser(dynamicForm)){
            flash("success", "message");
            return ok(index.render("Welcome"));
        }else{
            flash("warning", "message");
        }
        return ok(index.render("Welcome"));
    }

}

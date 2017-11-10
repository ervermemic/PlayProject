package controllers;

import com.google.inject.Inject;
import helpers.SessionHelper;
import models.Category;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.adm_panel;
import views.html.admin.categories;

/**
 * Created by Enver on 11/5/2017.
 */
public class AdminController extends Controller {

    @Inject
    FormFactory formFactory;

    public Result admin(){
        return ok(adm_panel.render(SessionHelper.getCurrentUser(ctx())));
    }

    public Result categories(){
        return ok(categories.render(Category.getAllCategories(), formFactory.form(Category.class)));
    }

    public Result addCategory(){
        DynamicForm dynamicForm = formFactory.form().bindFromRequest();
        if(Category.saveCategory(dynamicForm)){
            flash("success", "Category is saved");
        }else {
            flash("error", "Category is not saved");
        }
        return ok(categories.render(Category.getAllCategories(), formFactory.form(Category.class)));
    }
}

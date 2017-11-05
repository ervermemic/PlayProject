package controllers;

import helpers.SessionHelper;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.admin.adm_panel;

/**
 * Created by Enver on 11/5/2017.
 */
public class AdminController extends Controller {

    public Result admin(){
        return ok(adm_panel.render(SessionHelper.getCurrentUser(ctx())));
    }
}

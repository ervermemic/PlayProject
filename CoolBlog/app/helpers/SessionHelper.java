package helpers;

import models.User;
import play.mvc.Http;
import play.mvc.Result;

/**
 * Created by Enver on 11/3/2017.
 */
public class SessionHelper {

    public static User getCurrentUser(Http.Context context) {
        String email = context.session().get("email");
        User user = User.findByEmail(email);
        if(user != null){
            return user;
        }
        return null;
    }

}

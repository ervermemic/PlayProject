package helpers;

import models.Role;

/**
 * Created by Enver on 11/5/2017.
 */
public class StartFactors {

    public static void addRole(){
        Role role = new Role();
        role.setName("USER");
        role.save();

        Role role1 = new Role();
        role1.setName("Admin");
        role1.save();
    }
}

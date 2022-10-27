package com.ouieeat.ouieat.responses;

import com.ouieeat.ouieat.OuiLogger;
import com.ouieeat.ouieat.implementation.UserImplementation;
import com.ouieeat.ouieat.models.User;
import com.ouieeat.ouieat.repository.UserRepository;
import org.apache.logging.log4j.Level;

public class UserResponses {

    public static Response RegistrationResponse(String status, String username){
        String origin;
        try {
            origin = UserImplementation
                    .class
                    .getDeclaredMethod("saveNewUser", UserRepository.class, User.class)
                    .getName();
        } catch (NoSuchMethodException e){
            OuiLogger.log(Level.ERROR, "No such method found thrown for saveNewUser");
            origin = "NoSuchMethodException";
        }
        return new Response(status, username, origin, "client-register");
    }

    public static Response LoginResponse(String id, String status){
        String origin;
        try {
            origin = UserImplementation
                    .class
                    .getDeclaredMethod("loginUser", UserRepository.class, String.class, String.class)
                    .getName();
        } catch (NoSuchMethodException e){
            OuiLogger.log(Level.ERROR, "No such method found thrown for saveNewUser");
            origin = "NoSuchMethodException";
        }

        return new Response(status, id, origin, "client-login");
    }

}

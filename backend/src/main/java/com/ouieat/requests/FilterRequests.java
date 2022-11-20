package com.ouieat.requests;
import com.ouieat.OuiLogger;
import com.ouieat.implementation.FilterImplementation;
import com.ouieat.models.Filter;
import com.ouieat.repository.FilterRepository;
import com.ouieat.responses.ExceptionResponses;
import com.ouieat.responses.Response;
import com.ouieat.responses.FilterResponses;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.Level;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.ouieat.repository.FilterRepository;

import java.util.Optional;
import java.util.logging.Filter;

public class FilterRequests {
    public static class FilterRequests {

        public static String doSearch(
                FilterRepository userRepository,Filter filter ) {
            if(
                    newUser.getUsername().length() == 0 ||
                            newUser.getPassword().length() == 0 ||
                            newUser.getFirstName().length() == 0 ||
                            newUser.getLastName().length() == 0 ||
                            newUser.getEmail().length() == 0
            )
        }

        public static String doLogin(
                UserRepository userRepository,
                UserLogin userLogin
        ) {
            if (userLogin == null) return ExceptionResponses
                    .MissingRequestParametersResponse()
                    .getJsonString();

            if (
                    userLogin.getPassword().length() == 0 ||
                            userLogin.getUsername().length() == 0
            ) return ExceptionResponses
                    .MissingRequestParametersResponse()
                    .getJsonString();

            Response response = UserImplementation.loginUser(
                    userRepository,
                    userLogin.getUsername(),
                    userLogin.getPassword()
            );
            return response.getJsonString();
        }

        public static String getDashboard(
                UserRepository userRepository,
                UserCredentials credentials
        ) {
            return FilterRequests.doUserAction(
                    userRepository,
                    credentials,
                    new UserActionHandler() {
                        @Override
                        public Response onUserValidated(User user) {
                            return UserResponses.DashboardResponse(user);
                        }
                    }
            );
        }
    }

}

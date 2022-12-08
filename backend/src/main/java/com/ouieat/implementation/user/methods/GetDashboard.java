package com.ouieat.implementation.user.methods;

import com.ouieat.OuiLogger;
import com.ouieat.interactor.user.UserInteractor;
import com.ouieat.models.user.User;
import com.ouieat.responses.exception.ExceptionResponses;
import com.ouieat.responses.handler.Response;
import com.ouieat.responses.user.UserResponses;
import org.apache.logging.log4j.Level;

public interface GetDashboard {
    static Response apply(UserInteractor interactor, User user) {
        try {
            return UserResponses.GetDashboardResponse(user);
        } catch (Exception e) {
            OuiLogger.log(Level.ERROR, "Unable to get dashboard: " + e);
            return ExceptionResponses.UnknownExceptionResponse();
        }
    }
}

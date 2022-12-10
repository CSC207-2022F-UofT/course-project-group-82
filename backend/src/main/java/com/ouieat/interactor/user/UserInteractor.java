package com.ouieat.interactor.user;

import com.ouieat.interactor.handler.Interactor;
import com.ouieat.models.user.User;
import com.ouieat.repository.user.UserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserInteractor extends Interactor<User, UserRepository> {

    public static UserInteractor instance;

    @Autowired
    public UserInteractor(UserRepository userRepository) {
        super(userRepository);
        instance = this;
    }

    public ArrayList<User> findUserByUsernameAndPassword(
        String username,
        String password
    ) {
        return repository.findUserByUsernameAndPassword(username, password);
    }

    public ArrayList<User> findUserById(String id) {
        return repository.findUserById(id);
    }

    public ArrayList<User> findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    public ArrayList<User> findUserByEmail(String email) {
        return repository.findUserByEmail(email);
    }
}

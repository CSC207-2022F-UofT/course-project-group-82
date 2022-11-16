package com.ouieat.repository;

import com.ouieat.models.User;
import java.util.ArrayList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{username:  '?0', password:  '?1'}")
    ArrayList<User> findUserByUsernameAndPassword(
        String username,
        String password
    );

    @Query("{username:  '?0'}")
    ArrayList<User> findUserByUsername(String username);

    @Query("{email:  '?0'}")
    ArrayList<User> findUserByEmail(String email);

    @Query("{username:  '?0'}")
    ArrayList<User> findAllPostsByUsername(String username);
}

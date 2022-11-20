package com.ouieat.repository;

import com.ouieat.models.User;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{username:  '?0', password:  '?1'}")
    ArrayList<User> findUserByUsernameAndPassword(
        String username,
        String password
    );
    @Query("{id: '?0'}")
    ArrayList<User> findUserById(String id);

    @Query("{username:  '?0'}")
    ArrayList<User> findUserByUsername(String username);

    @Query("{email:  '?0'}")
    ArrayList<User> findUserByEmail(String email);

    @Query("{username:  '?0'}")
    ArrayList<User> findAllPostsByUsername(String username);
}

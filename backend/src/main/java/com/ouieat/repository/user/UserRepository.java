package com.ouieat.repository.user;

import com.ouieat.models.user.User;
import com.ouieat.repository.handler.Repository;
import java.util.ArrayList;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends Repository<User> {
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

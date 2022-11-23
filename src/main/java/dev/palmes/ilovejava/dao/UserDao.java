package dev.palmes.ilovejava.dao;

import dev.palmes.ilovejava.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserDao extends Dao<User> {

    Optional<User> get(UUID id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> listByRecent();
    List<User> listByPopular();
}

package dev.palmes.ilovejava.dao;

import dev.palmes.ilovejava.model.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    User findByUsername(String username);
    List<User> listByRecent();
    List<User> listByPopular();
}

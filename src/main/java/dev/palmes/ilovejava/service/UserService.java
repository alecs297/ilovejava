package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> get(UUID id);
    void save(User user);
    void delete(User user);
    void update(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> listByRecent();
    List<User> listByPopular();
    List<User> getAll();
}

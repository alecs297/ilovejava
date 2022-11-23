package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.model.Post;
import dev.palmes.ilovejava.model.User;

import java.util.Optional;
import java.util.UUID;

public interface PostService {
    Optional<Post> get(UUID id);
    void save(Post post);
    void delete(Post post, User user);
    void update(Post post, User user);
}

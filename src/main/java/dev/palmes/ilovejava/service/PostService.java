package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.model.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostService {
    Optional<Post> get(UUID id);
    void save(Post post);
    void delete(Post post);
    void update(Post post);
    List<Post> getAll();
}

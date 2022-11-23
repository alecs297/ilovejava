package dev.palmes.ilovejava.dao;

import dev.palmes.ilovejava.model.Post;

import java.util.Optional;
import java.util.UUID;

public interface PostDao extends Dao<Post> {
    Optional<Post> get(UUID id);
}

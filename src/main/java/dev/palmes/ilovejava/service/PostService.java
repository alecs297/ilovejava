package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.model.Post;
import dev.palmes.ilovejava.model.User;

import java.util.Optional;
import java.util.UUID;

public interface PostService {
    /**
     * Get the post using its id
     * @return The post wrapped in a null safe container
     */
    Optional<Post> get(UUID id);

    /**
     * Create a new post in the database
     * @param post The post to create
     */
    void save(Post post);

    /**
     * Delete a post from the database, the user performing the
     * action need to be the author of the post or an admin
     * @param post Post to delete
     * @param user The user requesting the deletion
     */
    void delete(Post post, User user);

    /**
     * Update a post from the database, the user performing the
     * action need to be the author of the post or an admin
     * @param post Post to update
     * @param user The user requesting the update
     */
    void update(Post post, User user);
}

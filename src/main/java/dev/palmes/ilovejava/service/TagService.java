package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.model.Tag;
import dev.palmes.ilovejava.model.User;

import java.util.List;
import java.util.Optional;

public interface TagService {

    /**
     * Get a tag using it's id
     * @return The tag wrapped in a null safe container
     */
    Optional<Tag> get(String id);

    /**
     * Create a new tag in the database, the user performing
     * the action need to be an admin.
     *
     * @param tag The tag tag to create
     * @param user User requesting to create a tag
     */
    void save(Tag tag, User user);

    /**
     * Delete a tag from the database, the user performing
     * the action need to be an admin
     *
     * @param tag The tag to delete
     * @param user User requesting tag deletion
     */
    void delete(Tag tag, User user);

    /**
     * Update a tag from the database, the user performing the
     * action need to be an admin
     * @param tag The tag to update
     * @param user User requesting tag deletion
     */
    void update(Tag tag, User user);

    /**
     * Get all tags that exist, the user performing the action
     * need to be an admin
     * @param user User requesting the tags
     * @return List of tags
     */
    List<Tag> getAll(User user);

    /**
     * Get all public tags
     * @return List of tags
     */
    List<Tag> getPublicTags();
}

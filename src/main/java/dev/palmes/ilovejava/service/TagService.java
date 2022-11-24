package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.Tag;
import dev.palmes.ilovejava.model.User;

import java.util.List;

public interface TagService {

    /**
     * Get a tag using its id
     *
     * @return The tag
     * @throws NotFoundException Tag doesn't exist
     */
    Tag get(String id) throws NotFoundException;

    /**
     * Create a new tag in the database, the user performing
     * the action need to be an admin.
     *
     * @param tag  The tag tag to create
     * @param user User requesting to create a tag
     * @throws PermissionLevelException User is not an admin
     */
    void save(Tag tag, User user) throws PermissionLevelException;

    /**
     * Delete a tag from the database, the user performing
     * the action need to be an admin
     *
     * @param tag  The tag to delete
     * @param user User requesting tag deletion
     * @throws PermissionLevelException User is not an admin
     */
    void delete(Tag tag, User user) throws PermissionLevelException;

    /**
     * Update a tag from the database, the user performing the
     * action need to be an admin
     *
     * @param tag  The tag to update
     * @param user User requesting tag deletion
     */
    void update(Tag tag, User user) throws PermissionLevelException;

    /**
     * Get all tags that exist, the user performing the action
     * need to be an admin
     *
     * @param page Page number
     * @param size Number of tags per page
     * @param user User requesting the tags
     * @return List of tags
     */
    List<Tag> getAll(int page, int size, User user) throws PermissionLevelException;

    /**
     * Get all public tags
     *
     * @param page Page number
     * @param size Number of tags per page
     * @return List of tags
     */
    List<Tag> getPublicTags(int page, int size);
}

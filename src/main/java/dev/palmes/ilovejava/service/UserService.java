package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.exceptions.AlreadyExistException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    /**
     * Get a user using its id
     *
     * @return The user
     * @throws NotFoundException User doesn't exist
     */
    User get(UUID id) throws NotFoundException;

    /**
     * Create a new user in the database
     *
     * @param user The user to create
     */
    void save(User user) throws AlreadyExistException;

    /**
     * Update a user from the database, the user performing the
     * action need to be the user to update or an admin
     *
     * @param user           The user to update
     * @param userRequesting The user requesting the update
     * @throws PermissionLevelException User is not an admin
     */
    void delete(User user, User userRequesting) throws PermissionLevelException;

    /**
     * Update a user from the database, the user performing the
     * action need to be the user to update or an admin
     *
     * @param user           The user to update
     * @param userRequesting The user requesting the update
     * @throws PermissionLevelException User is not an admin
     */
    void update(User user, User userRequesting) throws PermissionLevelException;

    /**
     * Find a user by its username
     *
     * @param username Username of the user to look for
     * @return The user
     * @throws NotFoundException User doesn't exist
     */
    User findByUsername(String username) throws NotFoundException;

    /**
     * Find a user by its email
     *
     * @param email Email of the user to look for
     * @return The user
     * @throws NotFoundException User doesn't exist
     */
    User findByEmail(String email) throws NotFoundException;

    /**
     * Get all users that exist sorted by recent
     *
     * @param page Page number
     * @param size Number of users per page
     * @return List of users
     */
    List<User> listByRecent(int page, int size);

    /**
     * Get all users that exist sorted by popularity
     *
     * @param page Page number
     * @param size Number of users per page
     * @return List of users
     */
    List<User> listByPopular(int page, int size);

    /**
     * Get all users that exist
     *
     * @param page Page number
     * @param size Number of users per page
     * @return List of users
     */
    List<User> getAll(int page, int size);
}

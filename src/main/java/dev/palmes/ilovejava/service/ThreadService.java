package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.exceptions.NotAvailableException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.Thread;
import dev.palmes.ilovejava.model.User;

import java.util.List;
import java.util.UUID;

public interface ThreadService {

    /**
     * Get a thread using its id
     *
     * @return The thread
     * @throws NotAvailableException Parent thread as been deleted
     * @throws NotFoundException     Thread doesn't exist
     */
    Thread get(UUID id) throws NotFoundException, NotAvailableException;

    /**
     * Get a thread using its id
     *
     * @return The thread
     * @throws NotAvailableException Parent thread as been deleted
     * @throws NotFoundException     Thread doesn't exist
     */
    Thread get(UUID id, User user) throws NotFoundException, NotAvailableException;


    /**
     * Create a new thread in the database
     *
     * @param thread The thread to create
     */
    void save(Thread thread);

    /**
     * Mark a thread a deleted, the user performing the
     * action need to be the author of the thread or an admin
     *
     * @param thread The thread to delete
     * @param user   The user requesting the deletion
     * @throws PermissionLevelException User is not an admin
     */
    void delete(Thread thread, User user) throws PermissionLevelException;


    /**
     * Update a thread from the database, the user performing the
     * action need to be the author of the thread or an admin
     *
     * @param thread The thread to update
     * @param user   The user requesting the update
     * @throws PermissionLevelException User is not an admin
     */
    void update(Thread thread, User user) throws PermissionLevelException;

    /**
     * Get all threads that exist
     *
     * @param page Page number
     * @param size Number of threads per page
     * @return List of threads
     */
    List<Thread> getAll(int page, int size);

    /**
     * Get all threads that exist
     *
     * @param page    Page number
     * @param size    Number of threads per page
     * @param removed If true, include removed threads
     * @param user    The user requesting the threads.
     *                If null or not an admin, prevent from getting removed threads
     * @return List of threads
     */
    List<Thread> getAll(int page, int size, boolean removed, User user) throws PermissionLevelException;

    /**
     * Get all threads that exist
     *
     * @param page    Page number
     * @param size    Number of threads per page
     * @param removed If true, include removed threads
     * @param user    The user requesting the threads.
     *                If null or not an admin, prevent from getting removed threads
     * @return List of threads
     */
    List<Thread> getAllByTag(String tag, int page, int size, boolean removed, User user) throws PermissionLevelException;

    /**
     * Get all threads that exist
     *
     * @param page    Page number
     * @param size    Number of threads per page
     * @param removed If true, include removed threads
     * @param user    The user requesting the threads.
     *                If null or not an admin or the owner,
     *                prevent from getting removed threads
     * @return List of threads
     */
    List<Thread> getAllByUser(User user, int page, int size, boolean removed, User currentUser) throws PermissionLevelException;

    /**
     * Get all threads that exist
     *
     * @param page    Page number
     * @param size    Number of threads per page
     * @param removed If true, include removed threads
     * @param user    The user requesting the threads.
     *                If null or not an admin or the owner,
     *                prevent from getting removed threads
     * @return List of threads
     */
    List<Thread> getAllByUser(String username, int page, int size, boolean removed, User user) throws PermissionLevelException;

    /**
     * Get all threads that exist
     *
     * @param page Page number
     * @param size Number of threads per page
     * @return List of threads
     */
    List<Thread> listByRecent(int page, int size);

    /**
     * Get all threads that exist
     *
     * @param page Page number
     * @param size Number of threads per page
     * @return List of threads
     */
    List<Thread> listByPopular(int page, int size);
}

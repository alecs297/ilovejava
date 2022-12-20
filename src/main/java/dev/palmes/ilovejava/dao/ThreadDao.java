package dev.palmes.ilovejava.dao;

import dev.palmes.ilovejava.model.Thread;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ThreadDao extends Dao<Thread> {
    Optional<Thread> get(UUID id);

    /**
     * Get all threads
     *
     * @param page    Page number
     * @param size    Number of threads per page
     * @param removed If true, include removed threads
     * @return List of threads
     */
    List<Thread> getAll(int page, int size, boolean removed);

    /**
     * Get all threads having tag
     *
     * @param tag     Tag name
     * @param page    Page number
     * @param size    Number of threads per page
     * @param removed If true, include removed threads
     * @return List of threads having tag
     */
    List<Thread> getAllByTag(String tag, int page, int size, boolean removed);

    /**
     * Get <em>size</em> threads from user
     *
     * @param userId  UUID of the user
     * @param page    Page number
     * @param size    Number of threads per page
     * @param removed If true, include removed threads
     * @return List of threads from user
     */
    List<Thread> getAllByUser(UUID userId, int page, int size, boolean removed);

    /**
     * Get <em>size</em> threads from user
     *
     * @param username Username
     * @param page     Page number
     * @param size     Number of threads per page
     * @param removed  If true, include removed threads
     * @return List of threads from user
     */
    List<Thread> getAllByUser(String username, int page, int size, boolean removed);


    /**
     * Get <em>size</em> threads sorted by creation date excluding deleted threads
     *
     * @param page Page number
     * @param size Number of threads per page
     * @return List of threads sorted by creation date
     */
    List<Thread> listByRecent(int page, int size);


    /**
     * Get <em>size</em> threads that have the most comments posted since <em>date</em> excluding deleted threads
     *
     * @param page     Page number
     * @param size     Number of threads per page
     * @param fromDate Date to start counting posts
     * @return List of threads sorted by number of posts
     */
    List<Thread> listByPopular(int page, int size, Date fromDate);

}

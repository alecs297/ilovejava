package dev.palmes.ilovejava.dao;

import dev.palmes.ilovejava.model.Thread;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ThreadDao extends Dao<Thread> {
    Optional<Thread> get(UUID id);

    List<Thread> getAll(int page, int size);

    List<Thread> getAllByTag(String tag, int page, int size);

    List<Thread> getAllByUser(UUID userId, int page, int size);

    List<Thread> getAllByUser(String username, int page, int size);

    List<Thread> listByRecent(int page, int size);

    List<Thread> listByPopular(int page, int size, Date fromDate);
}

package dev.palmes.ilovejava.dao;

import dev.palmes.ilovejava.model.Thread;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ThreadDao extends Dao<Thread> {
    Optional<Thread> get(UUID id);
    List<Thread> getAllByTag(String tag);
    List<Thread> getAllByUser(UUID userId);
    List<Thread> getAllByUser(String username);
    List<Thread> listByRecent();
    List<Thread> listByPopular();
}

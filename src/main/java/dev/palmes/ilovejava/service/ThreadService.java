package dev.palmes.ilovejava.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ThreadService {
    Optional<Thread> get(UUID id);
    void save(Thread thread);
    void delete(Thread thread);
    void update(Thread thread);
    List<Thread> getAll();
    List<Thread> getAllByTag(String tag);
    List<Thread> getAllByUser(UUID userId);
    List<Thread> getAllByUser(String username);
    List<Thread> listByRecent();
    List<Thread> listByPopular();
}

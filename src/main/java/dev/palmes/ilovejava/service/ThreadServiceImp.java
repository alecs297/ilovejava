package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.dao.ThreadDao;
import dev.palmes.ilovejava.exceptions.NotAvailableException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.Thread;
import dev.palmes.ilovejava.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ThreadServiceImp implements ThreadService {

    private final ThreadDao threadDao;

    public ThreadServiceImp(ThreadDao threadDao) {
        this.threadDao = threadDao;
    }

    @Override
    public Thread get(UUID id) throws NotFoundException, NotAvailableException {
        Thread thread = threadDao.get(id).orElseThrow(NotFoundException::new);
        if (thread.isRemoved()) {
            throw new NotAvailableException();
        }
        return thread;
    }

    @Override
    public void save(Thread thread) {
        threadDao.save(thread);
    }

    @Override
    public void delete(Thread thread, User user) throws PermissionLevelException {
        if (!user.isAdmin() && !user.equals(thread.getEntry().getAuthor())) {
            throw new PermissionLevelException();
        }
        thread.setRemoved(true);
        threadDao.update(thread);
    }

    @Override
    public void update(Thread thread, User user) throws PermissionLevelException {
        if (!user.isAdmin() && !user.equals(thread.getEntry().getAuthor())) {
            throw new PermissionLevelException();
        }
        threadDao.update(thread);
    }

    @Override
    public List<Thread> getAll(int page, int size) {
        return threadDao.getAll(page, size);
    }

    @Override
    public List<Thread> getAllByTag(String tag, int page, int size) {
        return threadDao.getAllByTag(tag, page, size);
    }

    @Override
    public List<Thread> getAllByUser(UUID userId, int page, int size) {
        return threadDao.getAllByUser(userId, page, size);
    }

    @Override
    public List<Thread> getAllByUser(String username, int page, int size) {
        return threadDao.getAllByUser(username, page, size);
    }

    @Override
    public List<Thread> listByRecent(int page, int size) {
        return threadDao.listByRecent(page, size);
    }

    @Override
    public List<Thread> listByPopular(int page, int size) {
        return threadDao.listByPopular(page, size);
    }
}

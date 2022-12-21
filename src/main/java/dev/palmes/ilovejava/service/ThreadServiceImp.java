package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.dao.ThreadDao;
import dev.palmes.ilovejava.exceptions.NotAvailableException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.Thread;
import dev.palmes.ilovejava.model.User;
import org.springframework.stereotype.Service;

import java.util.Calendar;
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
            throw new PermissionLevelException("You don't have permission to delete this thread");
        }
        thread.setRemoved(true);
        threadDao.update(thread);
    }

    @Override
    public void update(Thread thread, User user) throws PermissionLevelException {
        if (!user.isAdmin() && !user.equals(thread.getEntry().getAuthor())) {
            throw new PermissionLevelException("You don't have the permission to update this thread");
        }
        threadDao.update(thread);
    }

    @Override
    public List<Thread> getAll(int page, int size) {
        return threadDao.getAll(page, size);
    }

    @Override
    public List<Thread> getAll(int page, int size, boolean removed, User user) throws PermissionLevelException {
        if (user != null && user.isAdmin()) {
            return threadDao.getAll(page, size, removed);
        } else if (removed) {
            throw new PermissionLevelException("You don't have permission to see removed threads");
        }
        return threadDao.getAll(page, size);
    }

    @Override
    public List<Thread> getAllByTag(String tag, int page, int size, boolean removed, User user) throws PermissionLevelException {
        if (user != null && user.isAdmin()) {
            return threadDao.getAllByTag(tag, page, size, removed);
        } else if (removed) {
            throw new PermissionLevelException("You don't have permission to see removed threads");
        }

        return threadDao.getAllByTag(tag, page, size, false);
    }

    @Override
    public List<Thread> getAllByUser(User user, int page, int size, boolean removed, User currentUser) throws PermissionLevelException {
        if (currentUser != null && (currentUser.isAdmin() || currentUser.getId().equals(user.getId()))) {
            return threadDao.getAllByUser(user, page, size, removed);
        } else if (removed) {
            throw new PermissionLevelException("You don't have permission to see removed threads");
        }

        return threadDao.getAllByUser(user, page, size, false);
    }

    @Override
    public List<Thread> getAllByUser(String username, int page, int size, boolean removed, User user) throws PermissionLevelException {
        if (user != null && (user.isAdmin() || user.getUsername().equals(username))) {
            return threadDao.getAllByUser(username, page, size, removed);
        } else if (removed) {
            throw new PermissionLevelException("You don't have permission to see removed threads");
        }

        return threadDao.getAllByUser(username, page, size, false);
    }

    @Override
    public List<Thread> listByRecent(int page, int size) {
        return threadDao.listByRecent(page, size);
    }

    @Override
    public List<Thread> listByPopular(int page, int size) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return threadDao.listByPopular(page, size, cal.getTime());
    }
}

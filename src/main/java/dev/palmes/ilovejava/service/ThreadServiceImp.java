package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.dao.ThreadDao;
import dev.palmes.ilovejava.exceptions.InvalidFormatException;
import dev.palmes.ilovejava.exceptions.NotAvailableException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.Post;
import dev.palmes.ilovejava.model.Tag;
import dev.palmes.ilovejava.model.Thread;
import dev.palmes.ilovejava.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

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
    public Thread get(UUID id, User user) throws NotFoundException, NotAvailableException {
        Thread thread = threadDao.get(id).orElseThrow(NotFoundException::new);
        if (thread.isRemoved() && (user == null || !user.isAdmin() || !user.equals(thread.getEntry().getAuthor()))) {
            throw new NotAvailableException();
        }
        return thread;
    }

    @Override
    public void save(Thread thread) throws InvalidFormatException {
        thread.setTitle(HtmlUtils.htmlEscape(thread.getTitle()));

        if (thread.getTitle().length() < 1 || thread.getTitle().length() > 100) {
            throw new InvalidFormatException("The title must be between 1 and 100 characters");
        }

        if (thread.getEntry().getContent().length() < 2 || thread.getEntry().getContent().length() >= Post.MAX_CONTENT_SIZE) {
            throw new InvalidFormatException("The content must be between 1 and 10000 characters");
        }

        if (thread.getTags().size() < 1) {
            throw new InvalidFormatException("The thread must have at least one tag");
        }

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
        thread.setTitle(HtmlUtils.htmlEscape(thread.getTitle()));
        threadDao.update(thread);
    }

    @Override
    public void lock(Thread thread, User user) throws PermissionLevelException {
        if (!user.isAdmin() && !user.equals(thread.getEntry().getAuthor())) {
            throw new PermissionLevelException("You don't have the permission to lock this thread");
        }
        thread.setLocked(true);
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
    public List<Thread> getAllByTag(Tag tag, int page, int size, boolean removed, User user) throws PermissionLevelException {
        if (user != null && user.isAdmin()) {
            return threadDao.getAllByTag(tag.getId(), page, size, removed);
        } else if (removed) {
            throw new PermissionLevelException("You don't have permission to see removed threads");
        }

        return threadDao.getAllByTag(tag.getId(), page, size, false);
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
    public Integer getNumberOfPages(int size, boolean removed, User user) throws PermissionLevelException {
        if (user != null && user.isAdmin()) {
            return threadDao.getNumberOfPages(size, removed);
        } else if (removed) {
            throw new PermissionLevelException("You don't have permission to see removed threads");
        }

        return threadDao.getNumberOfPages(size, false);
    }

    @Override
    public Integer getNumberOfPagesByTag(Tag tag, int size, boolean removed, User user) throws PermissionLevelException {
        if (user != null && user.isAdmin()) {
            return threadDao.getNumberOfPagesByTag(tag.getId(), size, removed);
        } else if (removed) {
            throw new PermissionLevelException("You don't have permission to see removed threads");
        }

        return threadDao.getNumberOfPages(size, false);
    }

    @Override
    public List<Thread> listByRecent(int page, int size) {
        return threadDao.listByRecent(page, size);
    }

    @Override
    public List<Thread> listByTop(int page, int size) {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return threadDao.listByTop(page, size, cal.getTime());
    }
}

package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.dao.UserDao;
import dev.palmes.ilovejava.exceptions.AlreadyExistException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.User;

import java.util.List;
import java.util.UUID;

public class UserServiceImp implements UserService {
    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override

    public User get(UUID id) throws NotFoundException {
        return userDao.get(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void save(User user) throws AlreadyExistException {
        userDao.save(user);
    }

    @Override
    public void delete(User user, User userRequesting) throws PermissionLevelException {
        if (!userRequesting.isAdmin() && !userRequesting.equals(user)) {
            throw new PermissionLevelException();
        }
        userDao.delete(user);
    }

    @Override
    public void update(User user, User userRequesting) throws PermissionLevelException {
        if (!userRequesting.isAdmin() && !userRequesting.equals(user)) {
            throw new PermissionLevelException();
        }
        userDao.update(user);
    }

    @Override
    public User findByUsername(String username) throws NotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    @Override
    public User findByEmail(String email) throws NotFoundException {
        User user = userDao.findByEmail(email);
        if (user == null) {
            throw new NotFoundException();
        }
        return user;
    }

    @Override
    public List<User> listByRecent(int page, int size) {
        return userDao.listByRecent(page, size);
    }

    @Override
    public List<User> listByPopular(int page, int size) {
        return userDao.listByPopular(page, size);
    }

    @Override
    public List<User> getAll(int page, int size) {
        return userDao.getAll(page, size);
    }
}

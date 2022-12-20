package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.dao.UserDao;
import dev.palmes.ilovejava.exceptions.AlreadyExistException;
import dev.palmes.ilovejava.exceptions.InvalidFormatException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {
    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public User get(UUID id) throws NotFoundException {
        return userDao.get(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void save(User user, String originalPassword) throws AlreadyExistException, InvalidFormatException {
        if (userDao.findByUsername(user.getUsername()) != null) {
            throw new AlreadyExistException("Username already exists");
        }
        if (!checkUsername(user.getUsername())) {
            throw new InvalidFormatException("Username is not valid");
        }
        if (userDao.findByEmail(user.getEmail()) != null) {
            throw new AlreadyExistException("Email already exists");
        }
        if (!checkEmail(user.getEmail())) {
            throw new InvalidFormatException("Email is not valid");
        }
        if (!checkPassword(originalPassword)) {
            throw new InvalidFormatException("Password is not valid");
        }
        user.setPassword(new BCryptPasswordEncoder().encode(originalPassword));

        userDao.save(user);
    }

    /**
     * Check if the username is valid
     * Username is valid if it contains only letters and numbers and "_"
     * and it's length is between 4 and 16
     * @param username Username to check
     * @return true if the username is valid
     */
    private boolean checkUsername(String username) {
        return username != null &&  username.matches("^[a-zA-Z0-9_]{4,16}$");
    }

    /**
     * Check if the email is valid
     * @param email Email to check
     * @return true if the email is valid
     */
    private boolean checkEmail(String email) {
        return email != null &&  email.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
    }

    /**
     * Check if the password is valid
     * Password is valid if it has at least 8 characters, one uppercase, one lowercase
     * one number and one special character
     * @param password Password to check
     * @return true if the password is valid
     */
    private boolean checkPassword(String password) {
        return password != null &&  password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    }

    @Transactional
    public void delete(User user, User userRequesting) throws PermissionLevelException {
        if (!userRequesting.isAdmin() && !userRequesting.equals(user)) {
            throw new PermissionLevelException();
        }
        userDao.delete(user);
    }

    @Transactional
    public void update(User user, User userRequesting) throws PermissionLevelException {
        if (!userRequesting.isAdmin() && !userRequesting.equals(user)) {
            throw new PermissionLevelException();
        }
        userDao.update(user);
    }

    @Transactional
    public Optional<User> findByUsername(String username) {
        User user = userDao.findByUsername(username);
        return Optional.ofNullable(user);
    }

    @Transactional
    public Optional<User> findByEmail(String email) {
        User user = userDao.findByEmail(email);
        return Optional.ofNullable(user);
    }

    @Transactional
    public List<User> listByRecent(int page, int size) {
        return userDao.listByRecent(page, size);
    }

    @Transactional
    public List<User> listByPopular(int page, int size) {
        return userDao.listByPopular(page, size);
    }

    @Transactional
    public List<User> getAll(int page, int size) {
        return userDao.getAll(page, size);
    }

    @Transactional
    public User getUserFromCredentials(String login, String password) throws NotFoundException {
        User user;

        Optional<User> result = findByEmail(login);
        if (result.isPresent()) {
            user = result.get();
        } else {
            result = findByUsername(login);
            if (result.isPresent()) {
                user = result.get();
            } else {
                throw new NotFoundException();
            }
        }
        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            throw new NotFoundException();
        }
        return user;
    }
}

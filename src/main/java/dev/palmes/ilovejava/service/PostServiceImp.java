package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.dao.PostDao;
import dev.palmes.ilovejava.exceptions.NotAvailableException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.Post;
import dev.palmes.ilovejava.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostServiceImp implements PostService {
    private final PostDao postDao;

    public PostServiceImp(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public Post get(UUID id) throws NotAvailableException, NotFoundException {
        Post post = postDao.get(id).orElseThrow(NotFoundException::new);

        if (post.getThread().isRemoved()) {
            throw new NotAvailableException();
        }

        if (post.isRemoved()) {
            post.setContent("[removed]");
        }
        return post;
    }

    @Override
    public void save(Post post) {
        postDao.save(post);
    }

    @Override
    public void delete(Post post, User user) throws PermissionLevelException {
        if (post.getAuthor().equals(user) || user.isAdmin()) {
            post.setRemoved(true);
            postDao.update(post);
        } else {
            throw new PermissionLevelException();
        }
    }

    @Override
    public void update(Post post, User user) throws PermissionLevelException {
        if (post.getAuthor().equals(user)) {
            postDao.update(post);
        } else {
            throw new PermissionLevelException();
        }
    }
}

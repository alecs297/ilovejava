package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.dao.PostDao;
import dev.palmes.ilovejava.model.Post;
import dev.palmes.ilovejava.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImp implements  PostService{
    private final PostDao postDao;

    public PostServiceImp(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public Optional<Post> get(UUID id) {
        Optional<Post> post = postDao.get(id);
        if (post.isPresent() && post.get().isRemoved()) {
            post.get().setContent("[removed]");
        }
        return post;
    }

    @Override
    public void save(Post post) {
        postDao.save(post);
    }

    @Override
    public void delete(Post post, User user) {
        // TODO: Verif admin permissions
        if (post.getAuthor().equals(user)) {
            post.setRemoved(true);
            postDao.update(post);
        }
    }

    @Override
    public void update(Post post, User user) {
        if (post.getAuthor().equals(user)) {
            postDao.update(post);
        }
    }
}

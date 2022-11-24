package dev.palmes.ilovejava.dao;

import dev.palmes.ilovejava.model.Post;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class PostDaoImp implements PostDao {

    private final SessionFactory sessionFactory;

    public PostDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Post> get(UUID id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Post.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Post> getAll(int page, int size) {
        // TODO: Implement pagination
        return sessionFactory.getCurrentSession().createQuery("from Post").list();
    }

    @Override
    public void save(Post post) {
        sessionFactory.getCurrentSession().save(post);
    }

    @Override
    public void update(Post post) {
        sessionFactory.getCurrentSession().update(post);
    }

    @Override
    public void delete(Post post) {
        sessionFactory.getCurrentSession().delete(post);
    }
}

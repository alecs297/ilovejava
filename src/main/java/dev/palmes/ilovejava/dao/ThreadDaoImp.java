package dev.palmes.ilovejava.dao;

import dev.palmes.ilovejava.model.Post;
import dev.palmes.ilovejava.model.Thread;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class ThreadDaoImp implements ThreadDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Optional<Thread> get(UUID id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Thread.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Thread> getAll(int page, int size) {
        return sessionFactory.getCurrentSession().createQuery("from Thread").setFirstResult(page * size).setMaxResults(size).list();
    }

    @Override
    public void save(Thread thread) {
        sessionFactory.getCurrentSession().save(thread);
    }

    @Override
    public void update(Thread thread) {
        sessionFactory.getCurrentSession().update(thread);
    }

    @Override
    public void delete(Thread thread) {
        sessionFactory.getCurrentSession().delete(thread);
    }

    @Override
    public List<Thread> getAllByTag(String tag, int page, int size) {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Thread> query = builder.createQuery(Thread.class);
        Root<Thread> result = query.from(Thread.class);
        Join<Thread, dev.palmes.ilovejava.model.Tag> tagJoin = result.join("tags");
        query.select(result).where(builder.equal(tagJoin.get("name"), tag));
        return sessionFactory.getCurrentSession()
                .createQuery(query)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Thread> getAllByUser(UUID userId, int page, int size) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Thread where id = :userId")
                .setParameter("userId", userId)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Thread> getAllByUser(String username, int page, int size) {
        return sessionFactory.getCurrentSession().createQuery("from Thread where Thread.entry.author.username = :username")
                .setParameter("username", username)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .list();
    }

    @Override
    public List<Thread> listByRecent(int page, int size) {
        return null; // TODO
    }

    @Override
    public List<Thread> listByPopular(int page, int size) {
        return null; // TODO
    }
}

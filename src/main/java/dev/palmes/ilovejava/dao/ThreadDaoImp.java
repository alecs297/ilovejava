package dev.palmes.ilovejava.dao;

import dev.palmes.ilovejava.model.Thread;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class ThreadDaoImp implements ThreadDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Optional<Thread> get(int id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Thread.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Thread> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Thread").list();
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
    public List<Thread> getAllByTag(String tag) {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Thread> query = builder.createQuery(Thread.class);
        Root<Thread> result = query.from(Thread.class);
        Join<Thread, dev.palmes.ilovejava.model.Tag> tagJoin = result.join("tags");
        query.select(result).where(builder.equal(tagJoin.get("name"), tag));
        return sessionFactory.getCurrentSession().createQuery(query).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Thread> getAllByUser(UUID userId) {
        return sessionFactory.getCurrentSession().createQuery("from Thread where id = :userId")
                .setParameter("userId", userId)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Thread> getAllByUser(String username) {
        return sessionFactory.getCurrentSession().createQuery("from Thread where Thread.entry.author.username = :username")
                .setParameter("username", username)
                .list();
    }

    @Override
    public List<Thread> listByRecent() {
        return null; // TODO
    }

    @Override
    public List<Thread> listByPopular() {
        return null; // TODO
    }
}

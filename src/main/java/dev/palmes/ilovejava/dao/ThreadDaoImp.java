package dev.palmes.ilovejava.dao;

import dev.palmes.ilovejava.model.Post;
import dev.palmes.ilovejava.model.Thread;
import dev.palmes.ilovejava.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
    public List<Thread> getAll(int page, int size) {
        return this.getAll(page, size, false);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Thread> getAll(int page, int size, boolean removed) {
        return sessionFactory.getCurrentSession().createQuery("from Thread where removed = false or removed = :removed")
                .setParameter("removed", removed)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .list();
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
    public List<Thread> getAllByTag(String tag, int page, int size, boolean removed) {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Thread> query = builder.createQuery(Thread.class);
        Root<Thread> result = query.from(Thread.class);
        Join<Thread, dev.palmes.ilovejava.model.Tag> tagJoin = result.join("tags");
        query.select(result).where(
                builder.and(
                        builder.equal(tagJoin.get("id"), tag),
                        builder.or(
                                builder.equal(result.get("removed"), false),
                                builder.equal(result.get("removed"), removed)

                        )
                )
        );

        return sessionFactory.getCurrentSession()
                .createQuery(query)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<Thread> getAllByUser(User user, int page, int size, boolean removed) {

        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Thread> query = builder.createQuery(Thread.class);
        Root<Thread> result = query.from(Thread.class);
        Join<Thread, Post> postJoin = result.join("posts", JoinType.INNER);

        query.select(result).where(
                builder.and(
                        builder.equal(postJoin.get("author"), user),
                        builder.or(
                                builder.equal(result.get("removed"), false),
                                builder.equal(result.get("removed"), removed)

                        )
                )
        );


        return sessionFactory.getCurrentSession()
                .createQuery(query)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public Integer getNumberOfPages(int size, boolean removed) {
        return ((Long) sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Thread where removed = false or removed = :removed")
                .setParameter("removed", removed)
                .uniqueResult()).intValue() / size;
    }

    @Override
    public Integer getNumberOfPagesByTag(String tag, int size, boolean removed) {
        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Thread> query = builder.createQuery(Thread.class);
        Root<Thread> result = query.from(Thread.class);
        Join<Thread, dev.palmes.ilovejava.model.Tag> tagJoin = result.join("tags");
        query.select(result).where(
                builder.and(
                        builder.equal(tagJoin.get("id"), tag),
                        builder.or(
                                builder.equal(result.get("removed"), false),
                                builder.equal(result.get("removed"), removed)

                        )
                )
        );

        // Count the number thread
        CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
        countQuery.select(builder.count(result));


        return sessionFactory.getCurrentSession()
                .createQuery(countQuery)
                .getSingleResult().intValue() / size;
    }

    @Override
    public List<Thread> listByRecent(int page, int size) {

        CriteriaBuilder builder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Thread> query = builder.createQuery(Thread.class);
        Root<Thread> root = query.from(Thread.class);

        Join<Thread, Post> children = root.join("entry", JoinType.INNER);

        query.where(builder.equal(root.get("removed"), false));
        query.orderBy(builder.desc(children.get("creationDate")));

        return sessionFactory.getCurrentSession().createQuery(query)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }

    @Override
    public List<Thread> listByTop(int page, int size, Date fromDate) {
        CriteriaBuilder cb = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Thread> query = cb.createQuery(Thread.class);
        Root<Thread> root = query.from(Thread.class);
        Join<Thread, Post> entryJoin = root.join("entry");
        query.orderBy(cb.desc(cb.size(entryJoin.get("votes"))));
        TypedQuery<Thread> typedQuery = sessionFactory.getCurrentSession().createQuery(query);

        return typedQuery
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }
}

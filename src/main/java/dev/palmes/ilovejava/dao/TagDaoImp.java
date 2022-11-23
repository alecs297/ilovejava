package dev.palmes.ilovejava.dao;

import dev.palmes.ilovejava.model.Tag;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class TagDaoImp implements TagDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<Tag> get(int id) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Tag.class, id));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tag> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Tag").list();
    }

    @Override
    public void save(Tag tag) {
        sessionFactory.getCurrentSession().save(tag);
    }

    @Override
    public void update(Tag tag) {
        sessionFactory.getCurrentSession().update(tag);
    }

    @Override
    public void delete(Tag tag) {
        sessionFactory.getCurrentSession().delete(tag);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tag> getPublicTags() {
        return sessionFactory.getCurrentSession().createQuery("from Tag where locked = false").list();
    }
}

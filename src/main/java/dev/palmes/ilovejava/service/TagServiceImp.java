package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.dao.TagDao;
import dev.palmes.ilovejava.model.Tag;
import dev.palmes.ilovejava.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class TagServiceImp implements TagService {

    private final TagDao tagDao;

    public TagServiceImp(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Optional<Tag> get(String id) {
        return tagDao.get(id);
    }

    @Override
    public void save(Tag tag, User user) {
        // TODO : Check if user is admin
        tagDao.save(tag);
    }

    @Override
    public void delete(Tag tag, User user) {
        // TODO : Check if user is admin
        tagDao.delete(tag);
    }

    @Override
    public void update(Tag tag, User user) {
        // TODO : Check if user is admin
        tagDao.update(tag);
    }

    @Override
    public List<Tag> getAll(User user) {
        // TODO : Check if user is admin
        return tagDao.getAll();
    }

    @Override
    public List<Tag> getPublicTags() {
        return tagDao.getPublicTags();
    }
}

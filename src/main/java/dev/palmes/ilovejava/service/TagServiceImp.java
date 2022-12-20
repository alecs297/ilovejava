package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.dao.TagDao;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.Tag;
import dev.palmes.ilovejava.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImp implements TagService {

    private final TagDao tagDao;

    public TagServiceImp(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Tag get(String id) throws NotFoundException {
        return tagDao.get(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public void save(Tag tag, User user) throws PermissionLevelException {
        if (!user.isAdmin()) {
            throw new PermissionLevelException();
        }
        tagDao.save(tag);
    }

    @Override
    public void delete(Tag tag, User user) throws PermissionLevelException {
        if (!user.isAdmin()) {
            throw new PermissionLevelException();
        }
        tagDao.delete(tag);
    }

    @Override
    public void update(Tag tag, User user) throws PermissionLevelException {
        if (!user.isAdmin()) {
            throw new PermissionLevelException();
        }
        tagDao.update(tag);
    }

    @Override
    public List<Tag> getAll(int page, int size, User user) throws PermissionLevelException {
        if (!user.isAdmin()) {
            throw new PermissionLevelException();
        }
        return tagDao.getAll(page, size);
    }

    @Override
    public List<Tag> getPublicTags(int page, int size) {
        return tagDao.getPublicTags(page, size);
    }
}

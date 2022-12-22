package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.dao.TagDao;
import dev.palmes.ilovejava.exceptions.AlreadyExistException;
import dev.palmes.ilovejava.exceptions.NotFoundException;
import dev.palmes.ilovejava.exceptions.PermissionLevelException;
import dev.palmes.ilovejava.model.Tag;
import dev.palmes.ilovejava.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

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
    public void save(Tag tag, User user) throws AlreadyExistException, PermissionLevelException {
        if (!user.isAdmin()) {
            throw new PermissionLevelException();
        }
        if (tagDao.get(tag.getId()).isPresent()) {
            throw new AlreadyExistException("Tag with same id already exists");
        }
        tag.setDisplayName(HtmlUtils.htmlEscape(tag.getDisplayName()));
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
        tag.setDisplayName(HtmlUtils.htmlEscape(tag.getDisplayName()));
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

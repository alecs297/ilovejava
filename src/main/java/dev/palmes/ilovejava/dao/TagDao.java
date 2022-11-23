package dev.palmes.ilovejava.dao;

import dev.palmes.ilovejava.model.Tag;

import java.util.List;

public interface TagDao extends Dao<Tag> {
    List<Tag> getPublicTags();
}

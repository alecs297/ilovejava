package dev.palmes.ilovejava.dao;

import dev.palmes.ilovejava.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagDao extends Dao<Tag> {
    Optional<Tag> get(String id);

    List<Tag> getPublicTags(int page, int size);
}

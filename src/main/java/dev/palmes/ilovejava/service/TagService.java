package dev.palmes.ilovejava.service;

import dev.palmes.ilovejava.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {
    Optional<Tag> get(String id);
    void save(Tag tag);
    void delete(Tag tag);
    void update(Tag tag);
    List<Tag> getAll();
    List<Tag> getPublicTags();
}

package dev.palmes.ilovejava.dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll(int page, int size);

    void save(T t);

    void update(T t);

    void delete(T t);
}

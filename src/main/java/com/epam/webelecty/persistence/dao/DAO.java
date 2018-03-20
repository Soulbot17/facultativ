package com.epam.webelecty.persistence.dao;

import java.util.List;

public interface DAO<P> {
    List<P> getAllEntries();
    void updateById(int id, P entry);
    void removeById(int id);
    void insert(P entry);
    P getById(int id);
}

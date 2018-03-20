package com.epam.webelecty.persistence.dao;

import java.util.Set;

public interface DAO<P> {
    Set<P> getAllEntries();
    P updateEntry(P entry);
    void removeById(int id);
    P insert(P entry);
    P getById(int id);
}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;


public interface Storage {

    void clear();
    void update(Resume r);
    void save(Resume r);
    int getResumeIndex(String uuid);
    Resume get(String uuid);
    void delete(String uuid);
    Resume[] getAll();
    int size();
}

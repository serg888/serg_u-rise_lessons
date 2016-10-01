package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Сергей on 01.10.2016.
 */
public abstract class AbstractStorage implements Storage {

    protected static final int ARRAY_LENGHT = 1000;
    protected int size = 0;

    @Override
    public void clear() {
        clearAll();
    }

    protected abstract void clearAll();

    @Override
    public void update(Resume r) {
        if (getResume(r.getUuid())!=null) {
            deleteResume(r.getUuid());
            addResume(r);
        } else {
            throw new NotExistStorageException("Error: resume not found", r.getUuid());
        }
    }

    protected abstract void addResume(Resume r);

    protected abstract Resume getResume(String uuid);

    protected abstract void deleteResume(String uuid);

    
    @Override
    public void save(Resume r) {
        if (getResume(r.getUuid())!=null) {
            throw new ExistStorageException("Error: resume already in Array", r.getUuid());
        } else {
            if(size>=ARRAY_LENGHT-1){
                throw new StorageException("Error: overflow",r.getUuid());
            } else{
                addResume(r);
                size++;
            }
        }
    }

    @Override
    public Resume get(String uuid) {
        return getResume(uuid);
    }

    @Override
    public void delete(String uuid) {
        deleteResume(uuid);
    }

    @Override
    public Resume[] getAll() {
        return getAllResume();
    }

    protected abstract Resume[] getAllResume();

    @Override
    public int size() {
        return size;
    }
}

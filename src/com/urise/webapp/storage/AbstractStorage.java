package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

/**
 * Created by Сергей on 01.10.2016.
 */
public abstract class AbstractStorage implements Storage {

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract Resume doGet(Object searchKey);

    @Override
    public void update(Resume r) {
        Object searchKey = null;
        searchKey = getExistedSearchKey(r.getUuid());
        if (searchKey != null) doUpdate(r, searchKey);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = null;
        searchKey = getNotExistedSearchKey(r.getUuid());
        if (searchKey != null) doSave(r, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = null;
        searchKey = getExistedSearchKey(uuid);
        return (searchKey == null) ? null : doGet(searchKey);
    }


    @Override
    public void delete(String uuid) {
        Object searchKey = null;
        searchKey = getExistedSearchKey(uuid);
        if (searchKey != null) doDelete(searchKey);
    }


    private Object getExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistedSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

}

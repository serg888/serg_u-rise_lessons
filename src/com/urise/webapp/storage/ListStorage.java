package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

/**
 * Created by Сергей on 01.10.2016.
 */

public class ListStorage extends AbstractStorage {
    private List<Resume> list;

    public ListStorage() {
        this.list = new ArrayList<>();
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public List<Resume> doCopyAll() {
        return new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        list.set((Integer)searchKey,r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return ((Integer)searchKey==-1)?false:true;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        list.add(r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        int i=(Integer)searchKey;
        list.remove(i);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return list.get((Integer)searchKey);
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Сергей on 01.10.2016.
 */
public class ListStorage extends AbstractStorage {
    private List<Resume> list;

    public ListStorage() {
        this.list = new ArrayList<>();
    }

    @Override
    protected void clearAll() {
        list.clear();
    }

    @Override
    protected void addResume(Resume r) {
        list.add(r);
    }

    @Override
    protected Resume getResume(String uuid) {
        for(Resume r:list){
            if(r.getUuid().equals(uuid)) return r;
        }
        return null;
    }

    @Override
    protected void deleteResume(String uuid) {
        list.remove(getResume(uuid));
    }

    @Override
    protected Resume[] getAllResume() {
        return (Resume[])list.toArray();
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.*;

/**
 * Created by Сергей on 01.10.2016.
 */
public class MapUUIDStorage extends AbstractStorage {
    private Map<String, Resume> map;

    public MapUUIDStorage() {
        this.map = new HashMap<>();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public List<Resume> doCopyAll() {
        List<Resume>list=new ArrayList<Resume>(map.values());
        return list;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        map.put((String)searchKey,r);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        String s=(String)searchKey;
        char c=s.charAt(0);
        return (c=='-')?false:true;
    }

    @Override
    protected void doSave(Resume r, Object searchKey) {
        String s=(String)searchKey;
        String key=s.substring(1);
        map.put(key,r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove((String)searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return (map.get(uuid)==null)?("-"+uuid):uuid;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get((String)searchKey);
    }
}

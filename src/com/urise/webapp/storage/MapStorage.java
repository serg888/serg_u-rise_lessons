package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Сергей on 01.10.2016.
 */
public class MapStorage extends AbstractStorage {
    private Map<String,Resume>map;

    public MapStorage() {
        this.map = new HashMap<>();
    }

    @Override
    protected void clearAll() {
       map.clear();
    }

    @Override
    protected void addResume(Resume r) {
        map.put(r.getUuid(),r);
    }

    @Override
    protected Resume getResume(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void deleteResume(String uuid) {
        map.remove(uuid);
    }

    @Override
    protected Resume[] getAllResume() {
        return (Resume[])map.values().toArray();
    }
}

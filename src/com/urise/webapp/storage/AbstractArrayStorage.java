package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 * Test Branches
 */
public abstract class AbstractArrayStorage implements Storage{
    protected static final int ARRAY_LENGHT =1000;
    protected Resume[] storage = new Resume[ARRAY_LENGHT];
    protected static final String RES_NOT_FOUND="Error: resume not found";
    protected int size=0;

    public void save(Resume r){
        int i=getResumeIndex(r.getUuid());
        if(i<0)
        {
            //сравнение с переменной (на непревышение максимальной длины массива)
            if(size< ARRAY_LENGHT) insert(r,i); else
                throw new StorageException("Error: Not enough space",r.getUuid());
        } else
            throw new ExistStorageException("Error: this resume is already in array",r.getUuid());
    }

    public void update(Resume r){
        delete(r.getUuid());
        save(r);
    }

    public void delete(String uuid) {
        //если резюме есть, то удалить
        int i=getResumeIndex(uuid);
        if(i>=0){
            storage[i]=storage[size-1];
            fillDeletedElement(i);
            storage[size-1]=null;
            size--;
        } else
            throw new NotExistStorageException("Error: resume not found",uuid);
    }

    public void clear() {
        Arrays.fill(storage,0,size,null);
        size=0;
    }

    public Resume get(String uuid) {
        int i=getResumeIndex(uuid);
        return (i<0)?null:storage[i];
    }

    public Resume[] getAll() {return Arrays.copyOfRange(storage,0,size);    }

    public int size() {
        return size;
    }

    protected abstract int getResumeIndex(String uuid);

    protected abstract void insert(Resume r, int i);

    protected abstract void fillDeletedElement(int i);


}

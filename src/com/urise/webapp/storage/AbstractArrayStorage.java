package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 * Test Branches
 */
public abstract class AbstractArrayStorage implements Storage{
    protected static final int ARRAY_LENGHT =100000;
    protected Resume[] storage = new Resume[ARRAY_LENGHT];
    protected static final String RES_NOT_FOUND="Error: resume not found";
    protected int size=0;


    protected boolean isPossiblyToSave(Resume r){
        int i=getResumeIndex(r.getUuid());
        if(i<0)
        {
            //сравнение с переменной (на непревышение максимальной длины массива)
            if(size< ARRAY_LENGHT) return true; else
                System.out.println("Error: Not enough space");
        } else
            System.out.println("Error: this resume is already in array " +i);
        return false;

    }
    protected boolean isPossyblyToUpdate(Resume r){
        if(getResumeIndex(r.getUuid())<0){
            System.out.println(RES_NOT_FOUND);
            return false;
        }
        else return true;

    }
    public void clear() {
        Arrays.fill(storage,0,size,null);
        size=0;
    }

    public abstract int getResumeIndex(String uuid);

    public Resume get(String uuid) {
        int i=getResumeIndex(uuid);
        return (i<0)?null:storage[i];
    }

    public Resume[] getAll() {return Arrays.copyOfRange(storage,0,size);    }


    public int size() {
        return size;
    }
}

package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 * Test Branches
 */
public class ArrayStorage extends AbstractArrayStorage{

    @Override
    public void update(Resume r) {if(isPossyblyToUpdate(r)) storage[getResumeIndex(r.getUuid())]=r;}

    public void save(Resume r) { if (isPossiblyToSave(r)) storage[size++]=r;}

    public int getResumeIndex(String uuid){
        //если резюме не найдено, возвращает -1, иначе индекс в массиве
        for(int i=0;i<size;i++){
            if(storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }

    public void delete(String uuid) {
        //если резюме есть, то удалить
        int i=getResumeIndex(uuid);
        if(i!=-1){
            storage[i]=storage[size-1];
            storage[size-1]=null;
            size--;
        } else
            System.out.println(RES_NOT_FOUND);
    }

}

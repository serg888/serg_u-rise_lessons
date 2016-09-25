package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Сергей on 25.09.2016.
 */
public class SortedArrayStorage extends AbstractArrayStorage {


    @Override
    public void update(Resume r) {
        if(isPossyblyToUpdate(r)) storage[getResumeIndex(r.getUuid())]=r;
        Arrays.sort(storage,0,size);
    }

    @Override
    public void save(Resume r) {
        if(isPossiblyToSave(r)) storage[size++]=r;
        Arrays.sort(storage,0,size);
    }

    @Override
    public void delete(String uuid) {
        //если резюме есть, то удалить
        int i=getResumeIndex(uuid);
        if(i>=0){
            for(int i1=i;i1<size-1;i1++)
                storage[i1]=storage[i1+1];

            storage[size-1]=null;
            size--;
        } else
            System.out.println(RES_NOT_FOUND);

    }

    @Override
    public int getResumeIndex(String uuid) {
        Resume searchKey=new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage,0,size,searchKey);
    }
}

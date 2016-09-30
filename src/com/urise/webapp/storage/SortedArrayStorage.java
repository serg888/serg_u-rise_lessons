package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Сергей on 25.09.2016.
 */
public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public int getResumeIndex(String uuid) {
        Resume searchKey=new Resume(uuid);
        return Arrays.binarySearch(storage,0,size,searchKey);
    }

    @Override
    protected void insert(Resume r, int i) {
        for(int i1=size;i1>-(i+1);i1--)
            storage[i1]=storage[i1-1];
        storage[-(i+1)]=r;
        size++;
    }

    @Override
    protected void fillDeletedElement(int i) {
        for(int i1=i;i1<size-1;i1++)
            storage[i1]=storage[i1+1];
    }
}

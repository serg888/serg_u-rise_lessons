package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * Array based storage for Resumes
 * Test Branches
 */
public class ArrayStorage {
    public final int arrayLenght=10000;
    private Resume[] storage = new Resume[arrayLenght];
    private final String RES_NOT_FOUND="Error: resume not found";
    private int size=0;

    public void clear() {
        for(int i=0;i<size;i++)
            storage[i]=null;
        size=0;
    }

    public void update(Resume r){
        //проверка если такое резюме присутствует, то апдейт
        int i=getResumeIndex(r.getUuid());
        if(i!=-1)
            storage[i]=r;
        else System.out.println(RES_NOT_FOUND);

    }
    public void save(Resume r) {
        //проверка есть ли уже такой элемент в массиве
        if(getResumeIndex(r.getUuid())==-1)
        {
            //сравнение с переменной (на непревышение максимальной длины массива)
            if(size<arrayLenght) storage[size++]=r; else
            System.out.println("Error: Not enough space");
        } else
            System.out.println("Error: this resume is already in array" );
    }

    public int getResumeIndex(String uuid){
        //если резюме не найдено, возвращает -1, иначе индекс в массиве
        for(int i=0;i<size;i++){
            if(storage[i].getUuid().equals(uuid)) return i;
        }
        return -1;
    }
    public Resume get(String uuid) {

        int i=getResumeIndex(uuid);
        return (i==-1)?null:storage[i];
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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] r1=new Resume[size];
        for(int i=0;i<size;i++)
            r1[i]=storage[i];
        return r1;    }

    public int size() {
        return size;
    }
}

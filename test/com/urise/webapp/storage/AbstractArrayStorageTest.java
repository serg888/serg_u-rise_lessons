package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Сергей on 28.09.2016.
 */
public  abstract class AbstractArrayStorageTest {
    protected Storage storage;

    private static final String UUID_1="uuid1";
    private static final String UUID_2="uuid2";
    private static final String UUID_3="uuid3";


    protected AbstractArrayStorageTest(Storage storage){
        this.storage=storage;
    }
    @Before
    public void setUp() throws Exception{
        storage.clear();
        storage.save(new Resume(UUID_3));
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
    }



    @Test
    public void save() throws Exception {
        //must be overrided
        }

    @Test
    public void update() throws Exception {
        //must be overrided
    }

    @Test
    public void delete() throws Exception {
        //must be overrided
    }

    @Test
    public void clear() throws Exception {
        Class superClass=storage.getClass().getSuperclass();
        Field field=superClass.getDeclaredField("storage");
        Resume[] resumes=(Resume[])field.get(storage);
        storage.clear();
        for(int i=0;i<resumes.length;i++)
            assertTrue(resumes[i]==null);
    }

    @Test
    public void get() throws Exception {
        assertTrue((new Resume("uuid1").equals(storage.get("uuid1"))));
    }

    @Test
    public void getAll() throws Exception {
        //must be overrided
    }

    @Test
    public void size() throws Exception {
        assertEquals(3,storage.size());

    }

    @Test(expected = ExistStorageException.class)
    public void saveExistRezume()throws Exception{
        storage.save(new Resume(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void overflow() throws Exception{
        Class superClass=storage.getClass().getSuperclass();
        Field field=superClass.getDeclaredField("storage");
        Resume[] resumes=(Resume[])field.get(storage);
        for(int i=3;i<=resumes.length;i++)
            storage.save(new Resume("uuid_new"+i));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResume(){
        storage.delete("dummy");
    }



}
package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Сергей on 28.09.2016.
 */
public abstract class AbstracStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    protected static final Resume RESUME_1 = new Resume(UUID_1, "Name1");

    private static final String UUID_2 = "uuid2";
    protected static final Resume RESUME_2 = new Resume(UUID_2, "Name2");

    private static final String UUID_3 = "uuid3";
    protected static final Resume RESUME_3 = new Resume(UUID_3, "Name3");

    private static final String UUID_4 = "uuid4";
    protected static final Resume RESUME_4 = new Resume(UUID_4, "Name4");


    protected AbstracStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_3);
        storage.save(RESUME_1);
        storage.save(RESUME_2);
    }


    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void update() throws Exception {
        Resume newResume=new Resume(UUID_1, "Zhenya");
        Resume oldResume=RESUME_1;
        storage.update(newResume);
        assertSize(3);
        assertEquals(newResume,storage.get(UUID_1));
        assertTrue(storage.get(UUID_1)!=oldResume);
        storage.update(RESUME_4);

    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        assertEquals(null,storage.get(UUID_1));
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void get() throws Exception {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected =NotExistStorageException.class )
    public void getNotExist() throws Exception{
        assertEquals(null,storage.get("dummy"));
    }

    @Test
    public void getAllSorted() throws Exception {
        //must be overrided for sorted
        List<Resume> resumes = storage.getAllSorted();
        assertEquals(3, resumes.size());
        assertEquals(resumes, Arrays.asList(RESUME_1,RESUME_2,RESUME_3));
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistRezume() throws Exception {
        Resume r=new Resume(UUID_1, "Vasya");
        storage.save(new Resume(UUID_1,"Vasya" ));
        assertTrue(r!=storage.get(UUID_1));
    }


    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResume() {
        storage.delete("dummy");
    }

    protected void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    protected void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }


}
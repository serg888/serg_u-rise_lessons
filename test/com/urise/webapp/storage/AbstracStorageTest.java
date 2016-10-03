package com.urise.webapp.storage;


import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

/**
 * Created by Сергей on 28.09.2016.
 */
public abstract class AbstracStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    protected static final Resume RESUME_1 = new Resume(UUID_1);

    private static final String UUID_2 = "uuid2";
    protected static final Resume RESUME_2 = new Resume(UUID_2);

    private static final String UUID_3 = "uuid3";
    protected static final Resume RESUME_3 = new Resume(UUID_3);

    private static final String UUID_4 = "uuid4";
    protected static final Resume RESUME_4 = new Resume(UUID_4);

    static {
        RESUME_1.setFullName("Vasya");
        RESUME_2.setFullName("Petya");
        RESUME_3.setFullName("Avrik");
        RESUME_4.setFullName("Yasha");
    }


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
        Resume newResume=new Resume(UUID_1);
        Resume oldResume=RESUME_1;
        storage.update(newResume);
        assertSize(3);
        assertGet(RESUME_1);
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
        assertEquals(RESUME_3, resumes.get(0));
        assertEquals(RESUME_2, resumes.get(1));
        assertEquals(RESUME_1, resumes.get(2));
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExistRezume() throws Exception {
        Resume r=new Resume(UUID_1);
        storage.save(new Resume(UUID_1));
        assertTrue(r!=storage.get(UUID_1));
    }

    @Test(expected = StorageException.class)
    public void overflow() throws Exception {
        Class superClass = storage.getClass().getSuperclass();
        Field field = superClass.getDeclaredField("storage");
        Resume[] resumes = (Resume[]) field.get(storage);
        try {
            for (int i = 3; i < resumes.length; i++) {
                storage.save(new Resume());
            }
        } catch(StorageException e){
            fail();
        }
        assertSize(resumes.length);
        storage.save(new Resume());

    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistResume() {
        storage.delete("dummy");
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }


}
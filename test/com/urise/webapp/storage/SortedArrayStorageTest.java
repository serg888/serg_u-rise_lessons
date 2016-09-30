package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Created by Сергей on 30.09.2016.
 */
public class SortedArrayStorageTest extends AbstractArrayStorageTest{
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
    @Override
    @Test
    public void save() throws Exception {
        Class superClass=storage.getClass().getSuperclass();
        Field field=superClass.getDeclaredField("storage");
        Resume[] resumes=(Resume[])field.get(storage);
        assertTrue(resumes[0].getUuid()=="uuid1");
        assertTrue(resumes[1].getUuid()=="uuid2");
        assertTrue(resumes[2].getUuid()=="uuid3");

    }

    @Override
    @Test
    public void update() throws Exception {
        Class superClass=storage.getClass().getSuperclass();
        Field field=superClass.getDeclaredField("storage");
        Resume[] resumes=(Resume[])field.get(storage);
        Object rOld=resumes[0];
        storage.update(new Resume("uuid1"));
        Resume rNew=resumes[0];
        assertTrue(rOld.equals(rNew));
        assertTrue(rOld!=rNew);

    }

    @Override
    @Test
    public void delete() throws Exception {
        Class superClass=storage.getClass().getSuperclass();
        Field field=superClass.getDeclaredField("storage");
        Resume[] resumes=(Resume[])field.get(storage);
        storage.delete("uuid3");
        assertTrue(resumes[0].getUuid().equals("uuid1"));
        assertTrue(resumes[1].getUuid().equals("uuid2"));
        assertTrue(resumes[2]==null);
    }

    @Override
    @Test
    public void getAll() throws Exception {
        Resume[]testResumes=storage.getAll();
        assertTrue(testResumes[0].getUuid().equals("uuid1"));
        assertTrue(testResumes[1].getUuid().equals("uuid2"));
        assertTrue(testResumes[2].getUuid().equals("uuid3"));
        assertEquals(3,testResumes.length);


    }

}
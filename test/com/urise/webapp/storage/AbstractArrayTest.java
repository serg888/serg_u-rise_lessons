package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.fail;

/**
 * Created by Сергей on 05.10.2016.
 */
public abstract class AbstractArrayTest extends AbstracStorageTest {
    protected AbstractArrayTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void overflow() throws Exception {
        Class superClass = storage.getClass().getSuperclass();
        Field field = superClass.getDeclaredField("storage");
        Resume[] resumes = (Resume[]) field.get(storage);
        try {
            for (int i = 3; i < resumes.length; i++) {
                storage.save(new Resume("new"+i));
            }
        } catch(StorageException e){
            fail();
        }
        assertSize(resumes.length);
        storage.save(new Resume("last"));

    }

}

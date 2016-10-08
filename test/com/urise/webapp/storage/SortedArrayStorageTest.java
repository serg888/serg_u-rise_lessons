package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Сергей on 30.09.2016.
 */
public class SortedArrayStorageTest extends AbstractArrayTest {
    public SortedArrayStorageTest() {
        super(new SortedArrayStorage());
    }
}
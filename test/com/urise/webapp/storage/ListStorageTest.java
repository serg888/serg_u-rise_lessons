package com.urise.webapp.storage;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Сергей on 02.10.2016.
 */
public class ListStorageTest extends AbstracStorageTest{

    public ListStorageTest() {
        super(new ListStorage());
    }

    @Override
    @Ignore
    @Test
    public void overflow() throws Exception {

    }
}
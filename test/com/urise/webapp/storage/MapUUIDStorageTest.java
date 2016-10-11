package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Сергей on 03.10.2016.
 */
public class MapUUIDStorageTest extends AbstracStorageTest{
    public MapUUIDStorageTest(){
        super(new MapUUIDStorage());
    }

}
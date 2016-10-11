package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Сергей on 05.10.2016.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
                ArrayStorageTest.class,
                ListStorageTest.class,
                MapUUIDStorageTest.class,
                SortedArrayStorageTest.class
        }
)
public class AllStorageTest {
}

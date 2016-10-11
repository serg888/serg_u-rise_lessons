package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.*;

/**
 * Test for com.urise.webapp.storage.com.urise.webapp.storage.ArrayStorage
 */
public class MainArray {
   // private static final Storage ARRAY_STORAGE = new SortedArrayStorage();
    private static final Storage ARRAY_STORAGE = new MapUUIDStorage();

    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1", "uuid1");
        final Resume r2 = new Resume("uuid2", "uuid2");
        final Resume r3 = new Resume("uuid3", "uuid3");

        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r1);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Updating new R2...");
        ARRAY_STORAGE.update(new Resume("uuid2", "uuid2"));
        System.out.println("r2 after updating:"+ARRAY_STORAGE.get("uuid2"));

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));


        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAllSorted()) {
            System.out.println(r);
        }
    }
}

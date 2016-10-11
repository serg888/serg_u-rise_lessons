package com.urise.webapp.exception;

/**
 * Created by Сергей on 28.09.2016.
 */
public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super("Error: resume already in Array", uuid);
    }
}

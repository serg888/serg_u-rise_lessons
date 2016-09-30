package com.urise.webapp.exception;

/**
 * Created by Сергей on 28.09.2016.
 */
public class ExistStorageException extends StorageException {

    public ExistStorageException(String message, String uuid) {
        super(message, uuid);
    }
}

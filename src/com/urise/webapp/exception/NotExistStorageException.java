package com.urise.webapp.exception;

/**
 * Created by Сергей on 28.09.2016.
 */
public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String message, String uuid) {
        super(message, uuid);
    }
}

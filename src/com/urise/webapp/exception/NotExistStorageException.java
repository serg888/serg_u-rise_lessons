package com.urise.webapp.exception;

/**
 * Created by Сергей on 28.09.2016.
 */
public class NotExistStorageException extends StorageException {

    public NotExistStorageException(String uuid) {
        super("Error: resume not found", uuid);
    }
}

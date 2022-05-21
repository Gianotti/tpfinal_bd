package com.bd.tpfinal.exceptions.persistence;

import org.apache.commons.lang3.StringUtils;

public class PersistenceEntityException extends RuntimeException {
    public PersistenceEntityException(String message) {
        super(StringUtils.substringBefore(message, "; nested exception is"));
    }
}

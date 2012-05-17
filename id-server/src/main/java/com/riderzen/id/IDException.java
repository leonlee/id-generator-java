package com.riderzen.id;

import java.io.Serializable;

/**
 * Author: guoqiang.li@riderzen.com
 * Date: 12-5-16
 */
public class IDException extends Exception implements Serializable {

    private static final long serialVersionUID = 2206850652247763000L;

    public IDException(String message) {
        super(message);
    }

    public IDException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public IDException(Throwable throwable) {
        super(throwable);
    }

    public IDException() {
    }
}

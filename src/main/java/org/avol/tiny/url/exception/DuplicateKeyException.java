package org.avol.tiny.url.exception;

import org.avol.tiny.common.error.ErrorCode;

/**
 *
 * Created by lovababu on 10/06/18.
 */
public class DuplicateKeyException extends Exception{

    private final String message;

    public DuplicateKeyException(){
        super();
        this.message = "";
    }

    public DuplicateKeyException(ErrorCode message) {
        super(message.getMessage());
        this.message = message.getMessage();
    }
}

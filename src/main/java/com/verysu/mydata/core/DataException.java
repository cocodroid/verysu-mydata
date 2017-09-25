package com.verysu.mydata.core;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-07-19 9:12
 */
public class DataException extends Exception{

    public DataException() {
        super();
    }

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataException(Throwable cause) {
        super(cause);
    }
}

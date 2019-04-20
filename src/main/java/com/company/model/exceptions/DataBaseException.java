package com.company.model.exceptions;



public class DataBaseException extends Exception {
    public DataBaseException(String wrongData) {
        super(wrongData);
        super.printStackTrace();
    }

}
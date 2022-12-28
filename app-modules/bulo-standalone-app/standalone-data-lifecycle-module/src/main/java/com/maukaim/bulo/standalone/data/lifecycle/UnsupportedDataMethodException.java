package com.maukaim.bulo.standalone.data.lifecycle;

public class UnsupportedDataMethodException extends RuntimeException{
    public static final UnsupportedDataMethodException isSubStore(){
        return new UnsupportedDataMethodException("This method should be used from main store directly.");
    }
    public UnsupportedDataMethodException(String msg){ super(msg);}
}

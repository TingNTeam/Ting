package com.ting.ting.exception.errors;

import com.ting.ting.exception.ErrorCode;

public class RegisterFailedException extends RuntimeException {

    public RegisterFailedException(){
        super(ErrorCode.REGISTER_FAILED.getMessage());
    }

    public RegisterFailedException(Exception ex){
        super(ex);
    }
}
package com.ting.ting.core.security;

public interface AuthToken<T> {
    boolean validate();
    T getData();
}

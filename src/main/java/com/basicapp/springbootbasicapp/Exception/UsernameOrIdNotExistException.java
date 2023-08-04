package com.basicapp.springbootbasicapp.Exception;

public class UsernameOrIdNotExistException extends Exception {
    public UsernameOrIdNotExistException(){
        super("Usuario o id no encontrado");
    }

    public UsernameOrIdNotExistException(String message) {
        super(message);
    }
}

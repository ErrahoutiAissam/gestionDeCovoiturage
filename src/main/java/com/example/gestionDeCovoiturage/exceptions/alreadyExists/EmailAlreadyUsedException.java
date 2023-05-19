package com.example.gestionDeCovoiturage.exceptions.alreadyExists;

public class EmailAlreadyUsedException extends Exception{

    public EmailAlreadyUsedException() {
    }

    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}

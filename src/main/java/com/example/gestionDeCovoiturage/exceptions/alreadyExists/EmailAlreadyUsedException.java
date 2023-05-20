package com.example.gestionDeCovoiturage.exceptions.alreadyExists;

public class EmailAlreadyUsedException extends AlreadyExistException{

    public EmailAlreadyUsedException(String email) {
        super("Email " + email + " déjà utilisée !");
    }

    public EmailAlreadyUsedException() {
        super("Email déjà utilisée !");
    }
}

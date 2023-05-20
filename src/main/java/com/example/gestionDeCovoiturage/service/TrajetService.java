package com.example.gestionDeCovoiturage.service;


import com.example.gestionDeCovoiturage.repositories.TrajetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrajetService {


    private final TrajetRepository trajetRepo;

    @Autowired
    public TrajetService(TrajetRepository trajetRepo) {
        this.trajetRepo = trajetRepo;
    }
}

package com.example.server.repository;

import java.util.List;

import com.example.server.model.Participant;

public interface ParticipantRepository {

    //save and update -> is it book or participant
    int save (Participant book);
    int update (Participant book);

    Participant findById(Long id);
    int deleteById(Long id);
    List<Participant> findAll();
    List<Participant> findByAttending(boolean attending);
    List<Participant> findByNameContaining(String name);
    int deleteAll();
}

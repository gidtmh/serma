package com.example.server.controller;


import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.server.model.Participant;
import com.example.server.repository.ParticipantRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ParticipantController {

  @Autowired
  ParticipantRepository participantRepository;
  @GetMapping("/participants")
  public ResponseEntity<List<Participant>> getAllParticipants(@RequestParam(required = false) String name) {
    try {
      List<Participant> participants = new ArrayList<Participant>();
      if (name == null)
        participantRepository.findAll().forEach(participants::add);
      else
        participantRepository.findByNameContaining(name).forEach(participants::add);
      if (participants.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(participants, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/participants/{id}")
  public ResponseEntity<Participant> getParticipantById(@PathVariable("id") long id) {
    Participant Participant = participantRepository.findById(id);
    if (Participant != null) {
      return new ResponseEntity<>(Participant, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  @PostMapping("/participants")
  public ResponseEntity<String> createParticipant(@RequestBody Participant participant) {
    try {
      participantRepository.save(new Participant(participant.getName(), participant.getContact(),true));
      return new ResponseEntity<>("Participant was created successfully.", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @PutMapping("/participants/{id}")
  public ResponseEntity<String> updateParticipant(@PathVariable("id") long id, @RequestBody Participant participant) {
    Participant _participant = participantRepository.findById(id);
    if (_participant != null) {
      _participant.setId(id);
      _participant.setName(participant.getName());
      _participant.setContact(participant.getContact());
      //_participant.setAttendance(participant.isAttending());
      participantRepository.update(_participant);
      return new ResponseEntity<>("Participant was updated successfully.", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Cannot find Participant with id=" + id, HttpStatus.NOT_FOUND);
    }
  }
  @DeleteMapping("/participants/{id}")
  public ResponseEntity<String> deleteParticipant(@PathVariable("id") long id) {
    try {
      int result = participantRepository.deleteById(id);
      if (result == 0) {
        return new ResponseEntity<>("Cannot find Participant with id=" + id, HttpStatus.OK);
      }
      return new ResponseEntity<>("Participant was deleted successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete Participant.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @DeleteMapping("/participants")
  public ResponseEntity<String> deleteAllParticipants() {
    try {
      int numRows = participantRepository.deleteAll();
      return new ResponseEntity<>("Deleted " + numRows + " Participant(s) successfully.", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Cannot delete Participants.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
  @GetMapping("/participants/attending")
  public ResponseEntity<List<Participant>> findByPublished() {
    try {
      List<Participant> participants = participantRepository.findByAttending(true);
      if (participants.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(participants, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}

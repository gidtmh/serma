package com.example.server.controller;

import com.example.server.auth.JwtUser;
import com.example.server.dto.response.EventResponse;
import com.example.server.service.EventService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping(value = "/api/events")
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents() {

        List<EventResponse> eventResponses = eventService.getAll();
        return new ResponseEntity<>(eventResponses, HttpStatus.OK);
    }

    @GetMapping("/upcoming")
    public ResponseEntity<List<EventResponse>> getUpcomingEvents(
        @AuthenticationPrincipal JwtUser user) {
        List<EventResponse> eventResponses = eventService.getAllUpcoming(user);
        return new ResponseEntity<>(eventResponses, HttpStatus.OK);
    }
}

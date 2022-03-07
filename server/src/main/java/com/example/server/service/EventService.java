package com.example.server.service;

import com.example.server.auth.JwtUser;
import com.example.server.dto.response.EventResponse;
import com.example.server.mapper.EventMapper;
import com.example.server.repository.EventRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public List<EventResponse> getAll() {
        return eventRepository.findAll().stream()
            .map(eventMapper::toResponse)
            .collect(Collectors.toList());
    }

    public List<EventResponse> getAllUpcoming(JwtUser user) {
        return eventRepository.findAll().stream()
            .filter(event -> event.getEmployees().stream()
                .noneMatch(employee -> employee.getId().equals(user.getId())))
            .map(eventMapper::toResponse)
            .collect(Collectors.toList());
    }
}

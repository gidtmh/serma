package com.example.server.mapper;

import com.example.server.dto.response.EventResponse;
import com.example.server.model.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    imports = {String.class})
public interface EventMapper {

    @Mapping(target = "registeredManpower", expression = "java(event.getEmployees().size())")
    EventResponse toResponse(Event event);
}

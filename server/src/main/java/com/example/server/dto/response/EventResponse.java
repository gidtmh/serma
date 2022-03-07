package com.example.server.dto.response;

import lombok.Data;

@Data
public class EventResponse {

    private long id;
    private String name;
    private int requiredManpower;
    private int registeredManpower;
}

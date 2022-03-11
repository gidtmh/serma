package com.event.EventApp.controller;

import com.event.EventApp.model.*;
import com.event.EventApp.repository.EventRepository;
import com.event.EventApp.repository.UserRepository;
import com.event.EventApp.service.MyUserDetailsService;
import com.event.EventApp.util.JwtUtil;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@RequestMapping(value = "api")
@RestController
public class EventController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping(value = "signUp")
    @ResponseBody
    public Object signUp(@RequestBody User user) {
        userRepository.save(user);
        return  new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(value = "authenticate")
    @ResponseBody
    public Object login(@RequestBody AuthenticationRequest authenticationRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    @PostMapping(value = "event")
    @ResponseBody
    public Object createEvent(@RequestBody Event event) {
        eventRepository.save(event);
        sendEmail(event.getName() +" event is create by "+event.getOrganiser()+" on "+event.getDate()+" at "+event.getTime());
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @RequestMapping(value = "event")
    @ResponseBody
    public Object getEvents(@RequestParam int id) {
        if(id!=-1){
           Event event= eventRepository.getById(String.valueOf(id));
            return new ResponseEntity<>(event, HttpStatus.OK);
        }
        List<Event> eventList=eventRepository.findAll();
        return new ResponseEntity<>(eventList, HttpStatus.OK);
    }

    @RequestMapping(value = "random")
    @ResponseBody
    public Object random() {
        String externalApi="https://www.boredapi.com/api/activity";
        RestTemplate template=new RestTemplate();
        Random result= template.getForObject(externalApi,Random.class);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    void sendEmail(String message) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("gideontmh@gmail.com");

        msg.setSubject("Event Created");
        msg.setText(message);

        javaMailSender.send(msg);

    }


}

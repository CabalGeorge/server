package com.licenta.server.controller;


import com.licenta.server.model.Event;
import com.licenta.server.model.User;
import com.licenta.server.repository.EventRepository;
import com.licenta.server.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@RestController
@RequestMapping(path = "/")
public class EventController {

    @Autowired
    EventRepository eventRepo;

    @Autowired
    EventService eventService;

    @GetMapping(path = "getAllEvents")
    public @ResponseBody
    Iterable<Event> getAllEvents() {
        return eventService.getAllEvents();
    }


    @PostMapping(path = "addEvent")
    public String addEvent(@RequestParam("name") String name,
                           @RequestParam("time") String dateTime,
                           @RequestParam("location") String location,
                           @RequestParam("username") String createdBy) {

        Event event = new Event(name, dateTime, location, createdBy);

        if (eventRepo.existsEventByName(name) == true) {
            return "EXISTING EVENT";
        } else if (name == "") {
            return "NEED NAME";
        } else if (dateTime == "") {
            return "NEED DATETIME";
        } else if (location == "") {
            return "NEED LOCATION";
        } else {
            eventRepo.save(event);
            return "SUCCESSFUL";
        }
    }

    @Transactional
    @PostMapping(path = "deleteEvent")
    public String deleteEvent(@RequestParam("name") String name) {
        if (eventService.deleteByName(name) != 0) {
            return "SUCCESSFUL";
        } else if (name == null) {
            return "NO NAME";
        } else {
            return "FAILED";
        }

    }

    @PostMapping(path = "getEvent")
    public @ResponseBody
    Event getEvent(@RequestParam("name") String name) {
        if (this.eventRepo.findEventByName(name) != null) {
            Event event = this.eventRepo.findEventByName(name);
            return event;
        }
        return null;
    }

    @PostMapping(path = "updateEvent")
    public String updateEvent(@RequestParam("name") String name,
                              @RequestParam("dateTime") String dateTime,
                              @RequestParam("location") String location) {

        if (name == null) {
            return "NEED NAME";
        } else if (dateTime == null) {
            return "NEED DATE";
        } else if (location == null) {
            return "NEED LOCATION";
        }

        Event event = this.eventRepo.findEventByName(name);
        event.setDateTime(dateTime);
        event.setLocation(location);
        eventRepo.save(event);

        return "SUCCESSFUL";
    }
}

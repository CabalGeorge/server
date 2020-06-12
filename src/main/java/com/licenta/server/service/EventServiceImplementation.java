package com.licenta.server.service;


import com.licenta.server.model.Event;
import com.licenta.server.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EventServiceImplementation implements EventService {

    @Autowired
    EventRepository eventRepo;

    @Override
    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    @Transactional
    public int deleteByName(String name) {
        return eventRepo.deleteByName(name);
    }
}

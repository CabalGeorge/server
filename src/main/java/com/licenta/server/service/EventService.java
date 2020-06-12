package com.licenta.server.service;


import com.licenta.server.model.Event;

import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;


@Service
public interface EventService {

    List<Event> getAllEvents();

    @Transactional
    int deleteByName(String name);


}

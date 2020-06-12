package com.licenta.server.repository;


import com.licenta.server.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event,Integer> {

    boolean existsEventByName(String name);
    int deleteByName(String name);
    Event findEventByName(String name);

}

package com.licenta.server.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Table
@Entity(name="event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="event_id")
    private Integer event_id;

    @Column(name="name")
    private String name;


    @Column(name="datetime")
    private String datetime;

    @Column(name="location")
    private String location;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name="favorites")
    private User userList;

    private String createdBy;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDateTime() {
        return datetime;
    }

    public void setDateTime(String time) {
        this.datetime = datetime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Event(String name, String time, String location, String createdBy){
        this.name=name;
        this.datetime=time;
        this.location=location;
        this.createdBy=createdBy;
    }

    public Event(){}

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", time=" + datetime +
                ", location='" + location + '\'' +
                '}';
    }
}

package com.licenta.server.model;

import javax.persistence.*;
import java.util.Set;

@Table
@Entity(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Integer user_id;

    @Column(name="username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name = "favorites")
    private String favorites;


    @OneToMany(mappedBy = "user")
    private Set<Event> events;

    @OneToMany(mappedBy = "user")
    private Set<Event> favoriteEvents;

    public Set<Event> getFavoriteEvents() {
        return favoriteEvents;
    }

    public void setFavoriteEvents(Set<Event> favoriteEvents) {
        this.favoriteEvents = favoriteEvents;
    }


    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getEmail() { 
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User() {}

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}

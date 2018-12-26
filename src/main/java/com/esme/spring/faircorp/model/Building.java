package com.esme.spring.faircorp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Building {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "building")
    private List<Room> lOfRooms = new ArrayList<Room>();

    public Building() {
    }

    public Building(String name,List<Room> lOfRooms) {
        this.name = name;
        this.lOfRooms = lOfRooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getlOfRooms() {
        return lOfRooms;
    }

    public void setlOfRooms(List<Room> lOfRooms) {
        this.lOfRooms = lOfRooms;
    }
}
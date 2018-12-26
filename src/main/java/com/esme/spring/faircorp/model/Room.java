package com.esme.spring.faircorp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer floor;

    @OneToMany(mappedBy = "room")
    private List<Light> lOfLights = new ArrayList<Light>();

    @ManyToOne
    private Building building;

    public Room() {
    }

    public Room(String name,Integer floor,List<Light> lOfLights,Building building) {
        this.name = name;
        this.floor = floor;
        this.lOfLights = lOfLights;
        this.building = building;
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

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public List<Light> getlOfLights() {
        return lOfLights;
    }

    public void setlOfLights(List<Light> lOfLights) {
        this.lOfLights = lOfLights;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
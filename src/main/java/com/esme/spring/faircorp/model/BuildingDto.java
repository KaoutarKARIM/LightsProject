package com.esme.spring.faircorp.model;

import java.util.ArrayList;
import java.util.List;

public class BuildingDto {

    private Long id;
    private String name;
    private List<Long> roomsIds = new ArrayList<Long>();

    public BuildingDto() {
    }

    public BuildingDto(Long id, String name, List<Long> roomsIds) {
        this.id = id;
        this.name = name;
        this.roomsIds = roomsIds;
    }

    public BuildingDto(Building building) {
        this.id = building.getId();
        this.name = building.getName();
        for (Room r : building.getlOfRooms()){
            this.roomsIds.add(r.getId());
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Long> getroomsIds() {
        return roomsIds;
    }
}

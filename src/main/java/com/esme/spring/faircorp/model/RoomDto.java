package com.esme.spring.faircorp.model;

import java.util.ArrayList;
import java.util.List;

public class RoomDto {

    private Long id;
    private String name;
    private Integer floor;
    private List<Long> lightsIds = new ArrayList<Long>();
    private Long buildingId;

    public RoomDto() {
    }

    public RoomDto(Long id, String name, Integer floor, List<Long> lightsIds, Long buildingId) {
        this.id = id;
        this.name = name;
        this.floor = floor;
        this.lightsIds = lightsIds;
        this.buildingId = buildingId;
    }

    public RoomDto(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.floor = room.getFloor();
        this.buildingId = room.getBuilding().getId();
        for (Light l : room.getlOfLights()){
            this.lightsIds.add(l.getId());
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getFloor() {
        return floor;
    }

    public List<Long> getlightsIds() {
        return lightsIds;
    }

    public Long getBuildingId() {
        return buildingId;
    }
}

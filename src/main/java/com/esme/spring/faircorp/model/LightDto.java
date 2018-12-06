package com.esme.spring.faircorp.model;

public class LightDto {

    private  Long id;
    private Integer level;
    private  Status status;
    private  Long roomId;

    public LightDto(){}

    public LightDto(Long id, Integer level, Status status, Long roomId) {
        this.id = id;
        this.level = level;
        this.status = status;
        this.roomId = roomId;
    }

    public LightDto(Light light) {
        this.id = light.getId();
        this.level = light.getLevel();
        this.status = light.getStatus();
        this.roomId=light.getRoom().getId();
    }

    public Long getId() {
        return id;
    }

    public Integer getLevel() {
        return level;
    }

    public Status getStatus() {
        return status;
    }

    public Long getRoomId() {return roomId;}

}
package com.esme.spring.faircorp.model;

import java.util.List;

public interface RoomDaoCustom {
    List<Room> findByName(String name);
    List findRoomLightById(Long id);
}

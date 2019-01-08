package com.esme.spring.faircorp.model;

import com.esme.spring.faircorp.MqttClient.MqttPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/rooms")
@Transactional
public class RoomController {

    @Autowired
    private MqttPublisher mqttpub;

    @Autowired
    private final RoomDao roomDao;

    @Autowired
    private LightDao lightDao;

    @Autowired
    private BuildingDao buildingDao;

    public RoomController(RoomDao roomDao) {
        this.roomDao = roomDao;
    }


    @GetMapping
    public List<RoomDto> findAll() {
        return roomDao.findAll()
                .stream()
                .map(RoomDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{room_id}")
    public RoomDto findById(@PathVariable Long room_id) {
        return roomDao.findById(room_id).map(RoomDto::new).orElse(null);
    }

    @PostMapping
    public RoomDto create(@RequestBody RoomDto rDto) {
        Room room = null;
        if (rDto.getId() != null) {
            room = roomDao.findById(rDto.getId()).orElse(null);
        }

        if (room == null) {
            List<Light> lOfLights =new ArrayList<Light>();
            for(Long lightId : rDto.getlightsIds()){
                lOfLights.add(lightDao.findById(lightId).orElse(null));
            }
            room = roomDao.save(new Room(rDto.getName(), rDto.getFloor(), lOfLights,buildingDao.getOne(rDto.getBuildingId())));
        } else {
            room.setName(rDto.getName());
            room.setFloor(rDto.getFloor());
            roomDao.save(room);
        }

        return new RoomDto(room);
    }

    @DeleteMapping(path = "/{room_id}")
    public void delete(@PathVariable Long room_id) {
        Room room = null;
        room = roomDao.findById(room_id).orElse(null);
        for (Light l : room.getlOfLights()) lightDao.deleteById(l.getId());
        roomDao.deleteById(room_id);
    }

    @PutMapping(path = "/{room_id}/switchLight")
    public RoomDto switchLight(@PathVariable Long room_id) {
        Room room = roomDao.findById(room_id).orElseThrow(IllegalArgumentException::new);
        for (Light l : room.getlOfLights()){
            l.setStatus(l.getStatus() == Status.ON ? Status.OFF: Status.ON);
            String topic = "ONOFF" ;
            String mssg = l.getStatus() == Status.ON ? "ON" : "OFF";
            String mssgsend = mssg + "/" + l.getId(); 
            mqttpub.publish(topic,mssgsend);
        }
        return new RoomDto(room);
    }
}

package com.esme.spring.faircorp.model;

import com.esme.spring.faircorp.MqttClient.MqttPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/api/lights")
@Transactional
public class LightController {

    @Autowired
    private MqttPublisher mqttpub;

    @Autowired
    private final LightDao lightDao;

    public LightController(LightDao lightDao) {
        this.lightDao = lightDao;
    }

    @Autowired
    private RoomDao roomDao;


    @GetMapping
    public List<LightDto> findAll() {
        return lightDao.findAll()
                .stream()
                .map(LightDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public LightDto findById(@PathVariable Long id) {
        return lightDao.findById(id).map(LightDto::new).orElse(null);
    }

    @PutMapping(path = "/{id}/switch")
    public LightDto switchStatus(@PathVariable Long id) {
        Light light = lightDao.findById(id).orElseThrow(IllegalArgumentException::new);
        light.setStatus(light.getStatus() == Status.ON ? Status.OFF: Status.ON);
        //String topic = "ONOFF" ;
        //String mssg = light.getStatus() == Status.ON ? "ON" : "OFF" + "/" + light.getId();
        //String mssg = String.format("%s/%s",light.getStatus(),light.getId());
        //mqttpub.publish(topic,mssg);
        return new LightDto(light);
    }

    @PostMapping
    public LightDto create(@RequestBody LightDto dto) {
        Light light = null;
        if (dto.getId() != null) {
            light = lightDao.findById(dto.getId()).orElse(null);
        }

        if (light == null) {
            light = lightDao.save(new Light(dto.getLevel(), dto.getStatus(),roomDao.getOne(dto.getRoomId())));
        } else {
            light.setLevel(dto.getLevel());
            light.setStatus(dto.getStatus());
            lightDao.save(light);
        }

        return new LightDto(light);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        lightDao.deleteById(id);
    }
}
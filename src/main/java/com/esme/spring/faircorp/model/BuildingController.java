package com.esme.spring.faircorp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/buildings")
@Transactional
public class BuildingController {

    @Autowired
    private final BuildingDao buildingDao;

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private LightDao lightDao;

    public BuildingController(BuildingDao buildingDao) {
        this.buildingDao = buildingDao;
    }

    @GetMapping
    public List<BuildingDto> findAll() {
        return buildingDao.findAll()
                .stream()
                .map(BuildingDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{building_id}")
    public BuildingDto findById(@PathVariable Long building_id) {
        return buildingDao.findById(building_id).map(BuildingDto::new).orElse(null);
    }

    @PostMapping
    public BuildingDto create(@RequestBody BuildingDto bDto) {
        Building building = null;
        if (bDto.getId() != null) {
            building = buildingDao.findById(bDto.getId()).orElse(null);
        }

        if (building == null) {
            List<Room> lOfRooms =new ArrayList<Room>();
            for(Long roomId : bDto.getroomsIds()){
                lOfRooms.add(roomDao.findById(roomId).orElse(null));
            }
            building = buildingDao.save(new Building(bDto.getName(),lOfRooms));
        } else {
            building.setName(bDto.getName());
            buildingDao.save(building);
        }

        return new BuildingDto(building);
    }

    @DeleteMapping(path = "/{building_id}")
    public void delete(@PathVariable Long building_id) {
        Building building = null;
        building = buildingDao.findById(building_id).orElse(null);
        assert building != null;
        for (Room r : building.getlOfRooms()) {
            for (Light l : r.getlOfLights()) lightDao.deleteById(l.getId());
            roomDao.deleteById(r.getId());
        }
        buildingDao.deleteById(building_id);
    }
}

package com.esme.spring.faircorp.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class BuildingDaoImplTest {

    @Autowired
    BuildingDao buildingDao;

    @Test
    public void shouldFindLightsByBuildingId() {
        Long idTest = -5L;
        assertThat(buildingDao.findLightsByBuildingId(idTest))
                .hasSize(2);
    }

}

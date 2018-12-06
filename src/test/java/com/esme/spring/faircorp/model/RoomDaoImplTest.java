package com.esme.spring.faircorp.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan
public class RoomDaoImplTest {

    @Autowired
    RoomDao roomDao;

    @Test
    public void shouldFindRoomByName() {
        String nameTest = new String("Room1");
        assertThat(roomDao.findByName(nameTest))
                .hasSize(1)
                .extracting("id", "name","floor")
                .containsExactly(Assertions.tuple(-10L, "Room1", 1));
    }

}

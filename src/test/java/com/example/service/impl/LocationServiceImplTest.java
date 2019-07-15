package com.example.service.impl;

import com.example.domain.dataobject.Location;
import com.example.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/4/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LocationServiceImplTest {
    @Autowired
    private LocationService service;
    @Test
    public void getLocationSelectesByUserId(){
        List<Location> list = service.getLocationSelectesByUserId("1");

        for(Location l:list){
            log.info("{}",l);
        }
    }

    @Test
    public void deleteLocationById(){
        Location location = service.deleteLocationById("1555850938717582883");
        log.info("{}",location);
    }

    @Test
    public void getAllByUserIdAndLocationExist(){
        List<Location> list = service.getAllByUserIdAndLocationExist("1");

        for (Location l:list) {
            log.info("{}",l);
        }
    }

    @Test
    public void updateLocationSelectedByUserId() throws Exception {
        service.updateLocationSelectedByUserId("1");
    }

}
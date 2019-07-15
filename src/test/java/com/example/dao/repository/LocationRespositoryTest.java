package com.example.dao.repository;

import com.example.domain.dataobject.Location;
import com.example.enums.LocationExistStatusEnum;
import com.example.enums.LocationSelectedStatusEnum;
import com.example.utils.KeyUtil;
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
public class LocationRespositoryTest {
    @Autowired
    private LocationRespository respository;

    @Test
    public void findByLocationId(){
        Location location = respository.findByLocationId("1555828048398554390");
        log.info("{}",location);
    }

    @Test
    public void findAllByUserIdAndAndLocationExist(){
        List<Location> list = respository.findAllByUserIdAndAndLocationExist("1", LocationExistStatusEnum.EXIST.getCode());
        for (Location l: list) {
            System.out.println(l);
        }
    }

    @Test
    public void findByUserIdAndAndLocationSelected(){
        List<Location> list = respository.findByUserIdAndAndLocationSelected("1", LocationSelectedStatusEnum.SELECTED.getCode());
        System.out.print(list.size());
    }


    @Test
    public void save() throws Exception {
        Location location = new Location();
        location.setLocationId("1555850938717582883");
        location.setLocationExist(LocationExistStatusEnum.DELETED.getCode());

        Location demo = respository.save(location);
        System.out.print(demo);
    }

}
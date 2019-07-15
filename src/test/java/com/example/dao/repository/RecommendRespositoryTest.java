package com.example.dao.repository;

import com.example.domain.dataobject.Recommend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by wangjie_fourth on 2019/5/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecommendRespositoryTest {

    @Autowired
    private RecommendRespository recommendRespository;

    @Test
    public void save() throws Exception {
        Recommend recommend = new Recommend();
        recommend.setProductId1("1");
        recommend.setProductId2("1");
        recommend.setProductId3("1");
        recommend.setRecommendId("1");

        recommendRespository.save(recommend);
    }

}
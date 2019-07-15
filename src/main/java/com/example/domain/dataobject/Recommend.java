package com.example.domain.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by wangjie_fourth on 2019/5/16.
 */
@Data
@Entity
public class Recommend {

    @Id
    private String recommendId;

    private String productId1;

    private String productId2;

    private String productId3;
}

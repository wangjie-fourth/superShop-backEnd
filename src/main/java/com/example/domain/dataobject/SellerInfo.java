package com.example.domain.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by wangjie_fourth on 2019/5/10.
 */
@Data
@Entity
public class SellerInfo {
    @Id
    private String sellerId;

    private String sellerUsername;

    private String sellerPassword;

}

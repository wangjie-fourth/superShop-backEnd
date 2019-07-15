package com.example.web.Form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

/**
 *
 * Created by wangjie_fourth on 2019/4/27.
 */
@Data
public class OrderDetailForm {

    private String productId;

    private String productName;


    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

}

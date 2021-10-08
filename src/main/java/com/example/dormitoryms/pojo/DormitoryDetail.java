package com.example.dormitoryms.pojo;

import lombok.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther Shelter
 * @Date 8/5/2021
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DormitoryDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private BigDecimal waterbalance;
    private BigDecimal powerbalance;
}

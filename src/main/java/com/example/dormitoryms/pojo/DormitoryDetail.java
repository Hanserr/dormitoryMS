package com.example.dormitoryms.pojo;

import lombok.*;
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
public class DormitoryDetail {
    private Integer id;
    private BigDecimal waterbalance;
    private BigDecimal powerbalance;
}

package com.zwj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zwj
 * @date 2021/10/25 - 19:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    private int proId;
    private String proName;
    private String proImg;
    private String proKind;
    private String proDetail;
    private int proTotal;
}

package com.zwj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author zwj
 * @date 2021/10/28 - 11:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Image {
    private int id;
    private String imgName;
    private int imgKind;
}

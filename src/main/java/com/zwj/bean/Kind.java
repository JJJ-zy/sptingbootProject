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
public class Kind {
    private int id;
    private String proKind;
    private String proNum;
}

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
public class Company {
    private int id;
    private String cyName;
    private String cyPhone;
    private String cyManager;
    private String cyVx;
    private String cyOicq;
    private String cyAddress;
    private String cyWeb;
    private String cyIntro;
}

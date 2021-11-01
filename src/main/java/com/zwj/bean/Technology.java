package com.zwj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

/**
 * @author zwj
 * @date 2021/10/28 - 10:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Technology {
    private int id;
    private String tecTitle;
    private String tecContext;
    private String tecDate;
    private String tecAuthor;
    private int tecShow;
}

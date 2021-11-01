package com.zwj.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

/**
 * @author zwj
 * @date 2021/10/28 - 9:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class News {
    private int id;
    private String newsTitle;
    private String newsContext;
    private String newsDate;
    private String newsAuthor;
    private int newsShow;
}

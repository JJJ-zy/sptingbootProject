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
public class Editor {
    private int id;
    private String editorName;
    private int editorKind;
}

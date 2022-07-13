package com.example.event;

import lombok.Data;

import java.util.Objects;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2022/7/11
 */
@Data
public class FieldValue {

    private String fieldName;
    private Object beforeValue;
    private Object afterValue;
    private String fieldType;
}

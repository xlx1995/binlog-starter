package com.example.event;

import lombok.Data;

import java.util.Objects;

/**
 * @Author : xlx
 * @Description :
 * @Date: 2022/7/12
 */
@Data
public class UpdateData {

    private Object[] before;
    private Object[] after;

}
